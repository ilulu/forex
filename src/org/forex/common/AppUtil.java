package org.forex.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.mail.SimpleEmail;

public class AppUtil {
	/**
	 * 读取htm文件位置
	 * 
	 * @return
	 */
	public static String getHtmName() {
		ResourceBundle rb = ResourceBundle.getBundle("project");
		return rb.getString("fileName");
	}

	/**
	 * 获得本月最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayMonth() {
		Calendar cal = Calendar.getInstance();
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return cal.getTime();
	}

	/**
	 * 获得本月第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 获得本年第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		cal.set(Calendar.MONTH, cal.getActualMinimum(Calendar.MONTH));
		cal.set(Calendar.HOUR_OF_DAY,
				cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		return cal.getTime();
	}

	/**
	 * 获得从年初到现在经过的天书
	 * 
	 * @return
	 */
	public static long getLostDayFromNow() {
		Calendar cal = Calendar.getInstance();
		long diff = cal.getTime().getTime() - getFirstDayOfYear().getTime();
		return diff / (1000 * 60 * 60 * 24);
	}

	/**
	 * 根据某时间计算到今天位置经过了多少天
	 * @param inputDate
	 * @return
	 */
	public static long getLostDayFromIn(String inputDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		long days=0l;
		Date currentDate;
		try {
			Calendar cal=Calendar.getInstance();
			currentDate = sdf.parse(inputDate);
			long nowMil=cal.getTime().getTime();
			cal.setTime(currentDate);
			long inputMil=cal.getTime().getTime();
			days=(nowMil-inputMil) / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days;
	}
	public static int getLostMonthFrom(Date firstOpenTime) {
		Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get(Calendar.MONTH);
		int currentYear = cal.get(Calendar.YEAR);
		cal.setTime(firstOpenTime);
		int firstMonth = cal.get(Calendar.MONTH);
		int firstYear = cal.get(Calendar.YEAR);
		int yearDD = currentYear - firstYear;

		return yearDD * 12 + currentMonth - firstMonth + 1;
	}

	/**
	 * 四舍五入
	 * 
	 * @param source
	 *            double类型的源数据
	 * @param scale
	 *            int类型,保留几位小数
	 * @return
	 */
	public static double getRoundUp(double source, int scale) {
		BigDecimal bd = new BigDecimal(source);
		return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static String getBalanceSize() {
		ResourceBundle rb = ResourceBundle.getBundle("project");
		return rb.getString("balance.size");
	}

	public static boolean createDir(String path) {
		File file = new File(path);
		return file.mkdir();
	}

	/**
	 * 删除文件，可以是单个文件或文件夹
	 * 
	 * @param fileName
	 *            待删除的文件名
	 * @return 文件删除成功返回true,否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println(" delete failt  删除文件失败：" + fileName + "文件不存在");
			return false;
		} else {
			if (file.isFile()) {

				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			System.out.println("delete sucess  删除单个文件" + fileName + "成功！");
			return true;
		} else {
			System.out.println("delete failt  删除单个文件" + fileName + "失败！");
			return false;
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param dir
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			System.out.println("删除目录失败" + dir + "目录不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			System.out.println("delete failt 删除目录失败");
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			System.out.println("删除目录" + dir + "失败！");
			return false;
		}
	}
	
	public static void copyFile(String oldFile,String newFile){
		 try  {  
	           int  bytesum  =  0;  
	           int  byteread  =  0;  
	           File  oldfile  =  new  File(oldFile);  
	           if  (oldfile.exists())  {  //文件存在时  
	               InputStream  inStream  =  new  FileInputStream(oldFile);  //读入原文件  
	               FileOutputStream  fs  =  new  FileOutputStream(newFile);  
	               byte[]  buffer  =  new  byte[2097152];  
	               int  length;  
	               while  (  (byteread  =  inStream.read(buffer))  !=  -1)  {  
	                   bytesum  +=  byteread;  //字节数  文件大小  
	                   fs.write(buffer,  0,  byteread);  
	               }  
	               inStream.close();
	               fs.close();
	           }  
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("复制单个文件操作出错");  
	           e.printStackTrace();  
	 
	       } 
	}
	
	public static void renameFileInWindows(String oldFileName,String newFileName){
		oldFileName=oldFileName.replace("/", "\\");
		newFileName=oldFileName+"l";
		String command="cmd /c copy "+oldFileName+" "+newFileName;
		System.out.println(command);
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] arg){
		SimpleEmail email=new SimpleEmail();
		email.setHostName("smtp.qq.com");       
//		email.setSmtpPort(465);
        email.setAuthentication("531225379@qq.com", "lhy1,6s5q.");   //用户名和密码 
//        email.setStartTLSEnabled(true);
//        email.setSSLOnConnect(true);
        try{
       	email.setFrom("531225379@qq.com");
        email.addTo("lucifer.li@outlook.com");
        email.setSubject("Java Mail Test");
        email.setMsg("邮件测试");
        email.setCharset("utf-8");
        email.setDebug(true);
        email.send();
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	public static boolean isNum(String nums){
		return nums.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
}