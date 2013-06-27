package org.forex.loadhtm;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.mail.SimpleEmail;
import org.forex.domain.ContactUs;
import org.forex.domain.EmailAddress;
import org.forex.domain.EmailAddressRowMapper;
import org.forex.mapper.ContactUsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;

/**
 * @author zoujianwei 2012-11-26 保存联系人信息
 * @modify ilulu 2013.04.16 发送邮件
 */
@Service
public class ContactUsMan {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @author zoujianwei
	 * @param params
	 * @throws Exception
	 *             保存联系人信息
	 */
	@Expose
	public void saveContactInfo(Map params) throws Exception {

		String name = (String) params.get("name");
		String email = (String) params.get("email");
		String comments = (String) params.get("comments");
		String insertSql = "insert into forex_contactus values(?,?,?,?,?)";
		String uuid = UUID.randomUUID().toString();
		int result = 0;
		try {
			result = this.jdbcTemplate.update(insertSql, new Object[] { uuid,
					name, "", email, comments });
			sendMail(name, email, comments);
		} catch (DataAccessException e) {
			e.printStackTrace();
			// throw new Exception("Sorry,Saving data failed!");
		}
	}

	/**
	 * @author zoujianwei
	 * @param map
	 * @param page
	 *            查询联系人信息
	 */
	@DataProvider
	public Collection<ContactUs> getContactInfo(Map<String, Object> map) {

		String baseSql = " from forex_contactus where 1 = 1 ";
		
		if (map != null && map.size() > 0) {
			ContactUs us = (ContactUs) map.get("param");
			String name = us.getName();
			String phone = us.getPhone();
			String email = us.getEmail();
			if (StringUtils.hasText(name)) {
				baseSql += " and name like '%" + name + "%'";
			}
			if (StringUtils.hasText(phone)) {
				baseSql += " and phone like '%" + phone + "%'";
			}
			if (StringUtils.hasText(email)) {
				baseSql += " and email like '%" + email + "%'";
			}
		}
		StringBuffer querySql = new StringBuffer("select * ").append(baseSql);
		StringBuffer countSql = new StringBuffer("select count(*) ")
				.append(baseSql);

		Collection<ContactUs> col = this.jdbcTemplate.query(
				querySql.toString(), new ContactUsRowMapper());
		return col;
	}

	/**
	 * 
	 * @param uid
	 * @return 维护页面登陆验证
	 */
	@Expose
	public boolean loginCheck(String pwd) {

		boolean flag = false;
		if (pwd.equals("admin") && StringUtils.hasText(pwd)) {
			flag = true;
		}
		return flag;
	}

	@DataProvider
	public EmailAddress getCurrentEmail() {
		StringBuffer sql = new StringBuffer(
				"select * from admin_email where id='1'");
		List<EmailAddress> emails = jdbcTemplate.query(sql.toString(),
				new EmailAddressRowMapper());
		if (emails.size() > 0) {
			return emails.get(0);
		} else {
			return new EmailAddress();
		}
	}

	@DataResolver
	public Object saveEmail(EmailAddress email, Object parameter)
			throws Exception {

		StringBuffer sql = new StringBuffer();
		List result = jdbcTemplate.queryForList("select * from admin_email");
		if (result.size() > 0) {
			sql.append("update admin_email set ADDRESS='");
			sql.append(email.getAddress());
			sql.append("',PASSWORD='");
			sql.append(email.getPassword());
			sql.append("',SMTPSERVER='");
			sql.append(email.getSmtpServer());
			sql.append("',SMTPPORT='");
			sql.append(email.getSmtpPort());
			sql.append("',ISSSL=");
			sql.append(email.getIsSSL());
			sql.append(",ISTLS=");
			sql.append(email.getIsTLS());
			sql.append(" where ID='1'");
		} else {
			sql.append("insert into admin_email(ID,ADDRESS,PASSWORD,SMTPSERVER,SMTPPORT,ISSSL,ISTLS) values('1','"
					+ email.getAddress() + "','" + email.getPassword() + "','"
					+ email.getSmtpServer() + "','" + email.getSmtpPort()
					+ "',"+email.getIsSSL()+","+email.getIsTLS()+")");
		}
		jdbcTemplate.execute(sql.toString());
		return null;
	}

	@Expose
	public Object testMail() throws Exception {
		List<EmailAddress> emailAddressList = jdbcTemplate.query(
				"select * from admin_email where ID=1",
				new EmailAddressRowMapper());
		String flag="0";
		if (emailAddressList.size() > 0) {
			EmailAddress emailAddress = emailAddressList.get(0);
			SimpleEmail semail = new SimpleEmail();
			semail.setHostName(emailAddress.getSmtpServer());
			semail.setSmtpPort(Integer.parseInt(emailAddress.getSmtpPort()));
			semail.setAuthentication(emailAddress.getAddress(),
					emailAddress.getPassword()); // 用户名和密码
			semail.setStartTLSEnabled(emailAddress.getIsTLS());
			semail.setSSLOnConnect(emailAddress.getIsSSL());
			semail.addTo(emailAddress.getAddress());
			semail.setFrom(emailAddress.getAddress());
			semail.setSubject("Just Test Mail");
			semail.setMsg("The mail is work!\n邮件可用");
			semail.setCharset("utf-8");
			semail.setDebug(true);
			semail.send();
			flag="1";//发送成功状态为1
		} else {
			System.out.println("There is no email Setting");
			flag="2";//没有邮箱设置状态为2
		}
		return flag;
	}

	public Object sendMail(String name, String email, String comments)
			throws Exception {
		List<EmailAddress> emailAddressList = jdbcTemplate.query(
				"select * from admin_email where ID=1",
				new EmailAddressRowMapper());
		if (emailAddressList.size() > 0) {
			EmailAddress emailAddress = emailAddressList.get(0);
			SimpleEmail semail = new SimpleEmail();
			semail.setHostName(emailAddress.getSmtpServer());
			semail.setSmtpPort(Integer.parseInt(emailAddress.getSmtpPort()));
			semail.setAuthentication(emailAddress.getAddress(),
					emailAddress.getPassword()); // 用户名和密码
			semail.setStartTLSEnabled(emailAddress.getIsTLS());
			semail.setSSLOnConnect(emailAddress.getIsSSL());
			semail.addTo(emailAddress.getAddress());
			semail.setFrom(emailAddress.getAddress());
			semail.setSubject("邮件提醒");
			semail.setMsg("\t"+name + "(邮箱" + email + ")说:\n" + comments);
			semail.setCharset("utf-8");
			semail.setDebug(true);
			semail.send();
		} else {
			System.out.println("There is no email Setting");
		}
		return null;
	}

}
