package org.forex.loadhtm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bstek.dorado.annotation.Expose;


/**
 * 
 * @author zoujianwei
 * 皮肤切换相关
 */
@Service
public class LoadSkin {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @author zoujianwei 
	 * 得到皮肤
	 */
	@Expose
	public String getSkin()throws Exception{
		String sql = "select systemcode from forex_system where 1 = 1 and systemkey='skin'";
		
		Map result = new HashMap();
		try {
			result = this.jdbcTemplate.queryForMap(sql);
		} catch (Exception e) {
			result.put("systemcode", "blue");
		}
		
		String systemskin = result!=null ? result.get("systemcode").toString() : "";
		return systemskin;
	}
	
	/**
	 * @author zoujianwei 
	 * 更改皮肤
	 */
	@Expose
	public void changeSkin(String systemcode)throws Exception{
		System.out.println("systemcode:"+systemcode);
		String sql = "update forex_system set systemcode='"+systemcode+"' where 1 = 1 and systemkey='skin'";
		int result=0;
		try {
			result = this.jdbcTemplate.update(sql);
		} catch (Exception e) {
			throw new Exception("Sorry,It's failed to change skin!Try again or contact with administrator.");
		}
		System.out.println("result:"+result);
	}

	
	
}
