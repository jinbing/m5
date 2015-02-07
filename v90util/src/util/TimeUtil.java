package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
	private final static String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	private final static String TIMESTAMP_ID_FORMAT = "yyyyMMdd_HHmmss";

	public static String getDateTimeString() {
		Date date = (Date) GregorianCalendar.getInstance().getTime();
		return new SimpleDateFormat(TIMESTAMP_FORMAT).format(date);
	}

	public static String getDateTimeStringAsID() {
		Date date = (Date) GregorianCalendar.getInstance().getTime();
		return new SimpleDateFormat(TIMESTAMP_ID_FORMAT).format(date);
	}
}
