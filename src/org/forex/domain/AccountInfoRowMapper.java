package org.forex.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccountInfoRowMapper implements RowMapper<AccountInfo> {

	@Override
	public AccountInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		AccountInfo info=new AccountInfo();
		info.setId(rs.getString("ID_"));
		info.setProductName(rs.getString("PRODUCT_NAME_"));
		info.setFilePath(rs.getString("FILE_PATH_"));
		info.setOrder(rs.getInt("ORDER_"));
		info.setAccount(rs.getString("ACCOUNT_"));
		info.setIsable(rs.getInt("ISABLE_"));
		info.setBalanceSize(rs.getString("BALANCE_SIZE_"));
		info.setOutLinkUrl(rs.getString("OUT_LINK_URL_"));
		info.setMinimumInvestment(rs.getString("MINIMUM_INVESTMENT"));
		return info;
	}

}
