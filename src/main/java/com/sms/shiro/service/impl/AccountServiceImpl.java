package com.sms.shiro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sms.shiro.entity.Account;
import com.sms.shiro.mapper.AccountMapper;
import com.sms.shiro.service.IAccountService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2021-06-08
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public boolean save(Account account) {
        int insert = accountMapper.insert(account);
        return insert>0;
    }

    @Override
    public Collection<Account> list(QueryWrapper<Account> queryWrapper) {
        return null;
    }

    @Override
    public int count() {
        return accountMapper.selectCount(null);
    }

    @Override
    public Account getById(Long id) {
        return accountMapper.getOneAccount(id.intValue());
    }

    @Override
    public boolean removeById(Long id) {
        return accountMapper.deleteById(id) >0;
    }

    @Override
    public Account getByUserName(String username) {
        return accountMapper.selectOne(new LambdaQueryWrapper<Account>().eq(Account::getUsername,username));
    }

    private String md5Encryption(String password,String passwordSalt){

        Md5Hash md5Hash = new Md5Hash(password, passwordSalt);
        return md5Hash.toHex();
    }

    public static void main(String[] args) {
        String passwordSalt = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(passwordSalt);
        String md5Encryption = new AccountServiceImpl().md5Encryption("ww", passwordSalt);
        System.out.println(md5Encryption);
    }
}
