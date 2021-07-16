package com.sms.shiro.web.controller;

import com.sms.shiro.entity.Account;
import com.sms.shiro.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2021-06-08
 */
@SuppressWarnings("rawtypes")
@Controller
@Slf4j
public class AccountController {
    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{url}")
    public String url(@PathVariable("url") String url) {
        return url;
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        Account account = accountService.getByUserName(username);

        if (account != null){
            // token存放用户的输入身份信息、凭证信息
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // subject存放用户真实的身份信息
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(token);//去realm中认证，没抛异常则认证通过
                Session session = subject.getSession();
                session.setAttribute("account", subject.getPrincipal());
                return "index";
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                log.info("没有此账号");
                return "login";
            } catch (IncorrectCredentialsException e) {
                log.info("{}", e);
                log.info("密码错了");
                return "login";
            }
        }
        log.info("没有此账号");
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @GetMapping(value = "/get/{id}")
    @ResponseBody
    public Object get(@PathVariable Long id) throws InterruptedException {
        Account account = accountService.getById(id);
        return account;
    }

//    @GetMapping(value = "/total")
//    public Object total()
//            throws InterruptedException {
//
//        int total = accountService.count();
//        return total;
//    }

}
