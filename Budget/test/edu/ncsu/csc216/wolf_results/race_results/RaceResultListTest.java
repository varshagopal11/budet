/**
 * 
 */
package edu.ncsu.csc216.wolf_results.race_results;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Tests the RaceResultList class and the methods associated with it
 * @author Varsha Gopal
 * @author Abigail Ormond
 *
 */
public class RaceResultListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.race_results.RaceResultList#addResult(edu.ncsu.csc216.wolf_results.race_results.IndividualResult)}.
	 */
	@Test
	public void testAddResultIndividualResult() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";

		Race r = new Race(name, dist, ld, location);
		String pName = "Abigail";
		int age = 19;
		RaceTime time = new RaceTime(2, 59, 52);
		IndividualResult ir = new IndividualResult(r, pName, age, time);
		String pName2 = "Varsha";
		int age2 = 18;
		RaceTime time2 = new RaceTime(2, 58, 16);
		IndividualResult ir2 = new IndividualResult(r, pName2, age2, time2);

		RaceResultList rr = new RaceResultList();
		assertEquals(0, rr.size());
		rr.addResult(ir);
		assertEquals(1, rr.size());
		rr.addResult(ir2);
		assertEquals(ir2, rr.getResult(0));
		assertEquals(ir, rr.getResult(1));
		assertEquals(2, rr.size());
		try {
			rr.addResult(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid result", e.getMessage());
		}
		try {
			rr.getResult(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of bounds", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.race_results.RaceResultList#addResult(edu.ncsu.csc216.wolf_results.race_results.Race, java.lang.String, int, edu.ncsu.csc216.wolf_results.util.RaceTime)}.
	 */
	@Test
	public void testAddResultRaceStringIntRaceTime() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";

		Race r = new Race(name, dist, ld, location);
		String pName = "Abigail";
		int age = 19;
		RaceTime time = new RaceTime(2, 59, 52);
		String pName2 = "Varsha";
		int age2 = 18;
		RaceTime time2 = new RaceTime(2, 58, 16);

		RaceResultList rr = new RaceResultList();
		assertEquals(0, rr.size());
		rr.addResult(r, pName, age, time);
		assertEquals(1, rr.size());
		rr.addResult(r, pName2, age2, time2);
		assertEquals(2, rr.size());

		assertEquals(pName2, rr.getResult(0).getName());
		assertEquals(pName, rr.getResult(1).getName());
		assertEquals(age2, rr.getResult(0).getAge());
		assertEquals(age, rr.getResult(1).getAge());
		assertEquals(time2, rr.getResult(0).getTime());
		assertEquals(time, rr.getResult(1).getTime());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.race_results.RaceResultList#getResultsAsArray()}.
	 */
	@Test
	public void testGetResultsAsArray() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
		Race r = new Race(name, dist, ld, location);

		String pName = "Abigail";
		int age = 19;
		RaceTime time = new RaceTime(2, 12, 52);
		IndividualResult ir = new IndividualResult(r, pName, age, time);

		String pName2 = "Varsha";
		int age2 = 18;
		RaceTime time2 = new RaceTime(2, 58, 16);
		IndividualResult ir2 = new IndividualResult(r, pName2, age2, time2);

		RaceResultList rr = new RaceResultList();
		rr.addResult(ir);
		rr.addResult(ir2);
		String[][] arrAct = rr.getResultsAsArray();

		String[][] arrExp = new String[2][4];
		arrExp[0][0] = "Abigail";
		arrExp[0][1] = "19";
		arrExp[0][2] = "" + time;
		arrExp[0][3] = "" + ir.getPace();

		arrExp[1][0] = "Varsha";
		arrExp[1][1] = "18";
		arrExp[1][2] = ""  + time2;
		arrExp[1][3] = "" + ir2.getPace();


		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(arrExp[i][j], arrAct[i][j]);
			}
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.race_results.RaceResultList#filter(int, int, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testFilter() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
		Race r = new Race(name, dist, ld, location);

		String pName = "Abigail";
		int age = 19;
		RaceTime time = new RaceTime(2, 12, 52);
		IndividualResult ir = new IndividualResult(r, pName, age, time);

		String pName2 = "Varsha";
		int age2 = 18;
		RaceTime time2 = new RaceTime(2, 58, 16);
		IndividualResult ir2 = new IndividualResult(r, pName2, age2, time2);

		String pName3 = "Paul";
		int age3 = 20;
		RaceTime time3 = new RaceTime(3, 0, 4);
		IndividualResult ir3 = new IndividualResult(r, pName3, age3, time3);

		RaceResultList rr = new RaceResultList();
		rr.addResult(ir);
		rr.addResult(ir2);
		rr.addResult(ir3);

		RaceResultList expectedFilter = new RaceResultList();
		expectedFilter.addResult(ir);

		RaceResultList actualFilter = rr.filter(18, 20, "00:00:00", "00:06:00");

		assertEquals(expectedFilter.size(), actualFilter.size());
		assertEquals(expectedFilter.getResult(0).getName(), actualFilter.getResult(0).getName());

	}

}
