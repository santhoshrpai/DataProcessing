package Stackoverflow.operation;

import java.util.Date;

public class CommonUtil {

	public static Date getDateFromUnixTime(String time) {
		int timeStamp = Integer.parseInt(time);
		return new java.util.Date((long) timeStamp * 1000);
	}

}
