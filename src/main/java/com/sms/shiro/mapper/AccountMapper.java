package com.sms.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sms.shiro.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Administrator
 * @since 2021-06-08
 */
@Repository
public interface AccountMapper extends BaseMapper<Account> {

    Account getOneAccount(Integer id);

}
