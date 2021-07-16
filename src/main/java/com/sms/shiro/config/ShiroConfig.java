package com.sms.shiro.config;

import com.sms.shiro.realm.AccountRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author v-sunms.gd@chinatelecom.cn
 * @date 2021-06-09 10:23
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        HashMap<String, String> map = new HashMap<>();

//        map.put("/**","anon"); //anon访问所有页面都不需要认证登录
        map.put("/main","authc"); //authc访问所有页面需要先登录
        map.put("/manage","perms[manage]"); //访问manage页面需要有权限
        map.put("/administrator","roles[administrator]"); //访问administrator页面需要有角色信息
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/login"); //设置自定义登陆页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");//设置自定义权限页面
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm){
        return new DefaultWebSecurityManager(accountRealm);
    }

    @Bean
    public AccountRealm accountRealm(){

        AccountRealm accountRealm = new AccountRealm();
//        时用MD5加密时需要修改密码校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5"); //设置MD5加密
//        credentialsMatcher.setHashIterations(1024);//设置散列次数
        accountRealm.setCredentialsMatcher(credentialsMatcher);

        return accountRealm;
    }

}
