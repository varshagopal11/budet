// FINISHED 
// TESTED
package edu.ncsu.csc216.wolf_results.util;

import java.util.Scanner;

/**
 * Represents a race time in the format hh:mm:ss
 * 
 * @author Varhsa Gopal
 * @author Abigail Ormond
 *
 */
public class RaceTime {
	/**
	 * Hours field may be listed as one integer digit or many digits with leading
	 * zeros
	 */
	private int hours;
	/** Minutes field is always represented by two digits between 0 and 59 */
	private int minutes;
	/** Seconds field is always represented by two digits between 00 and 59 */
	private int seconds;

	/**
	 * Constructs a RaceTime from three integers representing hours, minutes, and
	 * seconds.
	 * 
	 * @param h Integer representing hours
	 * @param m Integer representing minutes
	 * @param s Integer representing seconds
	 * 
	 * @throws IllegalArgumentException if hours is negative or minutes is not
	 *                                  between 0 and 59
	 */
	public RaceTime(int h, int m, int s) {
		if (h < 0 || m < 0 || m > 59 || s < 0 || m > 59) {
			throw new IllegalArgumentException("Illegal time");
		}
		this.hours = h;
		this.minutes = m;
		this.seconds = s;
	}

	/**
	 * Constructs a RaceTime object from a string in the format hh:mm:ss
	 * 
	 * @param s String representing the time in format hh:mm:ss
	 * 
	 * @throws IllegalArgumentException if time is not in format hh:mm:ss or hours
	 *                                  is negative or minutes or seconds is not
	 *                                  between 0 and 59
	 */
	public RaceTime(String s) {
	
		String m;
		String se;
		int hrs;
		int min;
		int sec;
		// 100:59:59
		// size = 9
		// indices of ":" are 3 and 6
		if (s.indexOf(":") != (s.length() - 6) || s.lastIndexOf(":") != (s.length() - 3)) {
			throw new IllegalArgumentException("Time formatted incorrectly");
		}

		Scanner scan = new Scanner(s);
		scan.useDelimiter(":");
		hrs = Integer.parseInt(scan.next());
		m = scan.next();
		se = scan.next();
		min = Integer.parseInt(m);
		sec = Integer.parseInt(se);
		scan.close();
		if (hrs < 0) {
			throw new IllegalArgumentException("Invalid hours");
		}
		if (m.length() != 2 || min < 0 || min > 59) {
			throw new IllegalArgumentException("Invalid minutes");
		}
		if (se.length() != 2 || sec < 0 || sec > 59) {
			throw new IllegalArgumentException("Invalid seconds");
		}
		this.hours = hrs;
		this.minutes = min;
		this.seconds = sec;

//		if (s.indexOf(":") != 2 || s.lastIndexOf(":") != 5) {
//			throw new IllegalArgumentException("Time formatted incorrectly");
//		}
//		String hours = s.substring(0,2);
//		if (!(Integer.parseInt(hours) > 0)) {
//			throw new IllegalArgumentException("Invalid hours");
//		}
//		this.hours = (int)Integer.parseInt(hours);
//		String minutes = s.substring(3,5);
//		if (!(Integer.parseInt(minutes) > 0 && Integer.parseInt(minutes) < 59)) {
//			throw new IllegalArgumentException("Invalid minutes");
//		}
//		this.minutes = (int)Integer.parseInt(minutes);
//		String seconds = s.substring(6,8);
//		if (!(Integer.parseInt(seconds) > 0 && Integer.parseInt(seconds) < 59)) {
//			throw new IllegalArgumentException("Invalid seconds");
//		}
//		this.seconds = (int)Integer.parseInt(seconds);
	}

	/**
	 * Returns the integer hours field
	 * 
	 * @return hours in Integer form
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Returns the integer minutes field
	 * 
	 * @return minutes in Integer form
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Returns the integer seconds field
	 * 
	 * @return seconds in Integer form
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * Converts the time to total seconds and returns that value
	 * 
	 * @return time in Integer form
	 */
	public int getTimeInSeconds() {
		return hours * 3600 + minutes * 60 + seconds;
	}

	/**
	 * Returns the RaceTime as a string with the format hh:mm:ss, where (a) hours
	 * may be listed as one digit or many digits with leading zeros and (b) minutes
	 * and seconds are always two digits between 0 and 59 (inclusive).
	 * 
	 * @return time in String form
	 */
	@Override
	public String toString() {
		String hrs = "";
		String min = "";
		String sec = "";
		if (hours < 10) {
			hrs = "" + this.hours;
		} else {
			hrs += this.hours;
		}

		if (minutes < 10) {
			min += "0" + this.minutes;
		} else {
			min += this.minutes;
		}
		if (seconds < 10) {
			sec += "0" + this.seconds;
		} else {
			sec += this.seconds;
		}
		String time = hrs + ":" + min + ":" + sec;
		return time;
	}

	/**
	 * Racetimes are compared based on total time
	 * 
	 * @param rt RaceTime object to compare
	 * @return 0 or 1 based on which RaceTime is longer
	 */
	public int compareTo(RaceTime rt) {
		int timeCompare = rt.getTimeInSeconds();
		int thisTime = this.getTimeInSeconds();

		if (thisTime < timeCompare) {
			return -1; // return -1 if this is less than param
		} else if (thisTime > timeCompare) {
			return 1; // return 1 if this is greater than param
		}
		return 0; // return 0 if equal
	}
}
