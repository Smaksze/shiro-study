package com.sms.shiro.web.vo;

import com.sms.shiro.entity.Account;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能：用于大于等于小于需要传入两个入参值的查询，比如价格大于小于
 *
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AccountVo extends Account {

}
