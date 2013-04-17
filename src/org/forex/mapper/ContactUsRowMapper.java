package org.forex.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.forex.domain.ContactUs;
import org.springframework.jdbc.core.RowMapper;


public class ContactUsRowMapper implements RowMapper<ContactUs> {
	
	@Override
	public ContactUs mapRow(ResultSet rs, int arg1)throws SQLException{
		ContactUs cu = new ContactUs();
		cu.setId(rs.getString("id"));
		cu.setName(rs.getString("name"));
		cu.setPhone(rs.getString("phone"));
		cu.setEmail(rs.getString("email"));
		cu.setComments(rs.getString("comments"));
		return cu;
	}
}
