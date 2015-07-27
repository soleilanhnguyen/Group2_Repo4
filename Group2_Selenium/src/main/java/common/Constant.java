package common;

import java.util.Calendar;
import java.util.Date;

import config.XmlHelper;

public class Constant {
	public final static String url=XmlHelper.getVariable("url");
	public final static String adminUsername=XmlHelper.getVariable("adminUsername");
	public final static String adminPassword=XmlHelper.getVariable("adminPassword");
	public final static String FIRE_FOX = "firefox";
	public final static String CHROME = "chrome";
	public final static String IE = "ie";

	
	public final static long longTimeOut = 60; 
	
	// Get year, month, date, hour, minutes, second + another String --> create a unique title
	public final static String url_Google="goole.com.vn";
	static Date date;
	static Calendar calendar = Calendar.getInstance();
	public final static int year = calendar.get(Calendar.YEAR);
	public final static int month = calendar.get(Calendar.MONTH);
	public final static int day = calendar.get(Calendar.DAY_OF_MONTH);
	public final static int hours = calendar.get(Calendar.HOUR_OF_DAY);
	public final static int minutes = calendar.get(Calendar.MINUTE);
}
