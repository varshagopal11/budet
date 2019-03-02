/**
 * 
 */
package edu.ncsu.csc216.wolf_results.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the RaceTime class and the creation of a race time 
 * @author Varsha Gopal
 * @author Abigail Ormond
 *
 */
public class RaceTimeTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.RaceTime#RaceTime(int, int, int)}.
	 */
	@Test
	public void testRaceTimeIntIntInt() {
		RaceTime rt = new RaceTime(3, 49, 12);
		try {
			rt = new RaceTime(-1, 12, 13);
		} catch (IllegalArgumentException e) {
			assertEquals("Illegal time", e.getMessage());
		}
		assertEquals(3, rt.getHours());
		assertEquals(49, rt.getMinutes());
		assertEquals(12, rt.getSeconds());

		RaceTime rt2 = new RaceTime(333, 35, 12);
		assertEquals(333, rt2.getHours());
		assertEquals(35, rt2.getMinutes());
		assertEquals(12, rt2.getSeconds());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.RaceTime#RaceTime(java.lang.String)}.
	 */
	@Test
	public void testRaceTimeString() {
		RaceTime rt = new RaceTime("02:35:12");
		assertEquals(2, rt.getHours());
		assertEquals(35, rt.getMinutes());
		assertEquals(12, rt.getSeconds());

		RaceTime rt2 = new RaceTime("222:35:12");
		assertEquals(222, rt2.getHours());
		assertEquals(35, rt2.getMinutes());
		assertEquals(12, rt2.getSeconds());

		try {
			rt = new RaceTime("hello");
		} catch (IllegalArgumentException e) {
			assertEquals("Time formatted incorrectly", e.getMessage());
		}

		try {
			rt = new RaceTime("hi:imjoe");
		} catch (IllegalArgumentException e) {
			assertEquals("Time formatted incorrectly", e.getMessage());
		}

		try {
			rt = new RaceTime("01:00");
		} catch (IllegalArgumentException e) {
			assertEquals("Time formatted incorrectly", e.getMessage());
		}

		try {
			rt = new RaceTime("-1:13:21");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid hours", e.getMessage());
		}

		try {
			rt = new RaceTime("02:-1:21");
			rt = new RaceTime("02:99:21");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid minutes", e.getMessage());
		}

		try {
			rt = new RaceTime("02:32:-1");
			rt = new RaceTime("02:21:99");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid seconds", e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.RaceTime#getTimeInSeconds()}.
	 */
	@Test
	public void testGetTimeInSeconds() {
		RaceTime rt = new RaceTime("02:35:12");
		assertEquals(9312, rt.getTimeInSeconds());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.RaceTime#toString()}.
	 */
	@Test
	public void testToString() {
		RaceTime rt = new RaceTime("02:35:12");
		assertEquals("2:35:12", rt.toString());

		RaceTime rt2 = new RaceTime(3, 12, 45);
		assertEquals("3:12:45", rt2.toString());

		RaceTime rt3 = new RaceTime(11, 12, 45);
		assertEquals("11:12:45", rt3.toString());

		RaceTime rt4 = new RaceTime(3, 2, 5);
		assertEquals("3:02:05", rt4.toString());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.RaceTime#compareTo(edu.ncsu.csc216.wolf_results.util.RaceTime)}.
	 */
	@Test
	public void testCompareTo() {
		RaceTime r1 = new RaceTime("02:13:14");
		RaceTime r2 = new RaceTime("02:13:14");
		RaceTime r3 = new RaceTime("03:14:15");
		RaceTime r4 = new RaceTime("01:14:15");

		assertEquals(0, r1.compareTo(r2));
		assertEquals(0, r2.compareTo(r1));
		assertEquals(-1, r1.compareTo(r3));
		assertEquals(1, r3.compareTo(r1));
		assertEquals(-1, r4.compareTo(r1));
		assertEquals(1, r1.compareTo(r4));

	}

}
