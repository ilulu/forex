package org.forex.loadhtm;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

import org.forex.common.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Task extends TimerTask {

	@Autowired
	private LoadHtm loadHtm;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
//			 LoadHtm loadHtm=new LoadHtm();
			System.out.println(new Date()+"begin to Read htm data");
//			loadHtm.readHtm();
			System.out.println(new Date()+"finished!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args){
			AppUtil.createDir("d:/dev/test2");
	}
}
