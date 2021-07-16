package com.sms.shiro.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sms.shiro.entity.Account;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Administrator
 * @since 2021-06-08
 */
public interface IAccountService{

    boolean save(Account account);

    Collection<Account> list(QueryWrapper<Account> queryWrapper);

    int count();

    Account getById(Long id);

    boolean removeById(Long id);

    Account getByUserName(String username);
}
