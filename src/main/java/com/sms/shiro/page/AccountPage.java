package com.sms.shiro.page;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sms.shiro.entity.Account;
import com.sms.shiro.web.vo.AccountVo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 自定义分页，用于分页入参和分页返回结果
 *
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountPage extends Page<Account> implements Serializable {

	private AccountVo accountVo;// 查询条件

	public AccountPage(long current, long size) {
		super(current, size);
	}
}
