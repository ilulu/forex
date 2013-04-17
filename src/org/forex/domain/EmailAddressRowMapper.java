package org.forex.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmailAddressRowMapper implements RowMapper<EmailAddress> {

	@Override
	public EmailAddress mapRow(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		EmailAddress email=new EmailAddress();
		email.setId(arg0.getString("ID"));
		email.setAddress(arg0.getString("ADDRESS"));
		email.setPassword(arg0.getString("PASSWORD"));
		email.setSmtpServer(arg0.getString("SMTPSERVER"));
		email.setSmtpPort(arg0.getString("SMTPPORT"));
		email.setIsSSL(arg0.getBoolean("ISSSL"));
		email.setIsTLS(arg0.getBoolean("ISTLS"));
		return email;
	}
}
