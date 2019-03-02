package edu.ncsu.csc216.wolf_results.race_results;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Test for individual result class
 * @author Varsha gopal
 *
 */
public class IndividualResultTest {
	/**
	 * Test the constructor
	 */
	@Test
	public void testIndividualResult() {

		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";

		Race r = new Race(name, dist, ld, location);
		String pName = "Abigail";
		String pName2 = "  Space";
		String blankName = "    ";
		int age = 19;
		RaceTime time = new RaceTime(2, 59, 52);
		RaceTime pace = new RaceTime(0, 6, 51);
		IndividualResult i = new IndividualResult(r, pName, age, time);
		IndividualResult i2 = new IndividualResult(r, pName, age, time);
		IndividualResult i3 = new IndividualResult(r, pName2, age, time);
		try {
			i3 = new IndividualResult(r, blankName, age, time);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}
		assertNotNull(i3);
		assertEquals(r, i.getRace());
		assertEquals(pName, i.getName());
		assertEquals(age, i.getAge());
		assertEquals(time, i.getTime());
		assertEquals(0, pace.compareTo(i.getPace()));
		assertEquals(0, i.compareTo(i2));
		assertEquals("IndividualResult [name=Abigail, age=19, time=2:59:52, pace=0:06:51]", i.toString());

		try {
			i = new IndividualResult(null, pName, age, time);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid race object", e.getMessage());
		}

		try {
			i = new IndividualResult(r, null, age, time);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}

		try {
			i = new IndividualResult(r, "", age, time);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}

		try {
			i = new IndividualResult(r, pName, -3, time);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid age", e.getMessage());
		}

		try {
			i = new IndividualResult(r, pName, age, null);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid race time", e.getMessage());
		}

		r.setDistance(52.4);
		RaceTime newPace = new RaceTime(0, 3, 25);
		assertEquals(0, newPace.compareTo(i.getPace()));
	}

}
