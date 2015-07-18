package common;

import java.util.Calendar;
import java.util.Date;

public class Constant {
	public final static String url="http://localhost:8888/joomla/administrator/index.php";
	public final static String adminUsername="admin";
	public final static String adminPassword="123456";
	public final static String FIRE_FOX = "firefox";
	public final static String CHROME = "chrome";
	public final static String IE = "ie";

	
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
