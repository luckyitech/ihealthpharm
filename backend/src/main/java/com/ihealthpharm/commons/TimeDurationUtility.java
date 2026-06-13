package com.ihealthpharm.commons;

import java.util.Date;

public class TimeDurationUtility {

	static long secondsInMilli = 1000;
	static long minutesInMilli = secondsInMilli * 60;
	static long hoursInMilli = minutesInMilli * 60;
	static long daysInMilli = hoursInMilli * 24;

	public static String duration(Date startDate) {

		long different = new Date().getTime() - startDate.getTime();

		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		String duration = String.format("%d days, %d hours, %d minutes, %d seconds", elapsedDays, elapsedHours,
				elapsedMinutes, elapsedSeconds);

		return duration;
	}
}
