package com.gizwits.energy.android.lib.utils;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
	private TimeUtil() {
	}

	public static Date parse(String time, String pattern) {
		try {
			SimpleDateFormat e = new SimpleDateFormat(pattern, Locale.getDefault());
			return e.parse(time);
		} catch (Exception var3) {
			return null;
		}
	}

	public static String format(long time, String pattern) {
		return format(new Date(time), pattern);
	}

	public static String format(Date date, String pattern) {
		try {
			SimpleDateFormat e = new SimpleDateFormat(pattern, Locale.getDefault());
			return e.format(date);
		} catch (Exception var3) {
			return "";
		}
	}
}
