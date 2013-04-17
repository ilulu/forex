package org.forex.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccountBaseInfoRowMapper implements RowMapper<AccountBaseInfo> {

	@Override
	public AccountBaseInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		AccountBaseInfo base=new AccountBaseInfo();
		base.setId(rs.getString("ID_"));
		base.setAccount(rs.getString("ACCOUNT_"));
		base.setName(rs.getString("NAME_"));
		base.setCurrency(rs.getString("CURRENCY_"));
		base.setDeposit(rs.getDouble("DEPOSIT_"));
		base.setCloseTrade(rs.getDouble("CLOSETRADE_"));
		base.setProductName(rs.getString("PRODUCT_NAME_"));
		base.setOutLinkUrl(rs.getString("OUT_LINK_URL_"));
		base.setMinimumInvestment(rs.getString("MINIMUM_INVESTMENT"));
		return base;
	}

}
