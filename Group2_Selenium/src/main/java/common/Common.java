package common;

import java.util.Calendar;
import org.openqa.selenium.support.Color;
import java.util.GregorianCalendar;


public class Common {

	
	/**
	 * 
	 * @param splittedString
	 * @author Anh Nguyen
	 * @description: split a string using delimiter "/"
	 */
	public static String[] splitString(String splittedString) {
		String splittedArray[];
		String delimiter = "\\/";
		splittedArray = splittedString.split(delimiter);
		return splittedArray;
	}

	/**
	 * 
	 * @param baseString
	 * @author Ha Nguyen
	 * @description: get uniqueString by day, month, year, second, minute, hour "/"
	 */
	 public static String getUniqueString(String baseString) {
		  
		  	   int day, month, year;
		       int second, minute, hour;
		       GregorianCalendar date = new GregorianCalendar();

		       day = date.get(Calendar.DAY_OF_MONTH);
		       month = date.get(Calendar.MONTH);
		       year = date.get(Calendar.YEAR);
		       
		       second = date.get(Calendar.SECOND);
		       minute = date.get(Calendar.MINUTE);
		       hour = date.get(Calendar.HOUR);
		  
		  return baseString + day + month + year + second + minute + hour;
		 }
	
	 /**
	  * 
	  * @param rgba
	  * @author Ha Nguyen
	  * @description: Convert rgba to hex
	  */
	 public static String convertRgbaToHex(String rgba)
	 {
	 	String hex = Color.fromString(rgba).asHex();
	 	return hex;
	 }
	
}


