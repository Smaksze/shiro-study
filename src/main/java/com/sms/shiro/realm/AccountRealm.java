package com.sms.shiro.realm;

import com.sms.shiro.entity.Account;
import com.sms.shiro.service.impl.AccountServiceImpl;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * @author v-sunms.gd@chinatelecom.cn
 * @date 2021-06-09 10:10
 */
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private AccountServiceImpl accountService;

    /**
     * 权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //Account primaryPrincipal = (Account) principalCollection.getPrimaryPrincipal();
        //获取当前用户的登录信息
        Subject subject = SecurityUtils.getSubject();
        Account account  = (Account) subject.getPrincipal();

        //设置角色
        HashSet<String> se = new HashSet<>();
        se.add(account.getRole());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(se);

        //设置权限
        simpleAuthorizationInfo.addStringPermission(account.getPerms()) ;
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken  由调用方suject调用login方法时传过来的
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        Account account = accountService.getByUserName(username);
        //验证是否有该用户
        if (account != null){
            //验证密码是否正确
            // 参数一 ：数据库中查出来的principal，参数二 ：数据库查出来的正确密码，参数三：当前realm
//            return new SimpleAuthenticationInfo(account,account.getPassword(), getName());
            // md5加密时：参数一 ：数据库中查出来的principal，参数二 ：数据库查出来的正确密码，参数三：固定盐值，参数四：当前realm
            return new SimpleAuthenticationInfo(account,account.getPassword(), ByteSource.Util.bytes(account.getPasswordSalt()),getName());
        }
        return null;
    }
}
