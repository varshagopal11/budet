package edu.ncsu.csc216.wolf_results.model.io;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.wolf_results.race_results.IndividualResult;
import edu.ncsu.csc216.wolf_results.race_results.Race;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;
import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Tests the WolfResultsReader class by reading race lists from a file and
 * comparing to expected race list results
 * 
 * @author Abigail Ormond
 * @author Varsha Gopal
 *
 */
public class WolfResultsReaderTest {

	/**
	 * Test to ensure the provided sample file is read without throwing an exception
	 */
	@Test
	public void testSampleFile() {

		RaceList rl = null;
		rl = WolfResultsReader.readRaceListFile("test-files/sample.md");
		assertEquals(2, rl.size());

	}

	/**
	 * Tests a file that has multiple races and asserts that each field is correct
	 */
	@Test
	public void testMultipleRaces() {
		String name = "Krispy Kreme Challenge";
		Double dist = 5.0;
		LocalDate ld = LocalDate.parse("2018-02-18");
		String location = "Raleigh, NC";

		Race r = new Race(name, dist, ld, location);
		String pName = "BILLY FETZNER";
		int age = 24;
		RaceTime time = new RaceTime(0, 11, 51);
		IndividualResult ir = new IndividualResult(r, pName, age, time);

		String pName2 = "STEPHEN HENKEL";
		int age2 = 21;
		RaceTime time2 = new RaceTime(0, 12, 02);
		IndividualResult ir2 = new IndividualResult(r, pName2, age2, time2);

		r.addIndividualResult(ir);
		r.addIndividualResult(ir2);

		String name2 = "2 Krispy 2 Kreme Challenge";
		Double dist2 = 5.0;
		LocalDate ld2 = LocalDate.parse("2018-02-18");
		String location2 = "Raleigh, NC";

		Race r2 = new Race(name2, dist2, ld2, location2);
		String pName22 = "SHNEEPSHNOP FETZNER";
		int age22 = 24;
		RaceTime time22 = new RaceTime(0, 11, 51);
		IndividualResult ir22 = new IndividualResult(r2, pName22, age22, time22);

		String pName222 = "STEPH HENKEL";
		int age222 = 21;
		RaceTime time222 = new RaceTime(0, 12, 02);
		IndividualResult ir222 = new IndividualResult(r2, pName222, age222, time222);

		r2.addIndividualResult(ir22);
		r2.addIndividualResult(ir222);

		RaceList rl = new RaceList();
		rl.addRace(r);
		rl.addRace(r2);

		RaceList rl2 = WolfResultsReader.readRaceListFile("test-files/ts_test.md");

		Race race1 = rl.getRace(0);
		Race race2 = rl2.getRace(0);
		assertTrue(race1.equals(race2));
		assertEquals(race1.getResults().getResult(0).getAge(), race2.getResults().getResult(0).getAge());
		assertEquals(race1.getResults().getResult(0).getName(), race2.getResults().getResult(0).getName());
		assertEquals(0, race1.getResults().getResult(0).getTime().compareTo(race2.getResults().getResult(0).getTime()));
		assertEquals(0, race1.getResults().getResult(0).getPace().compareTo(race2.getResults().getResult(0).getPace()));

		assertEquals(race1.getResults().getResult(1).getAge(), race2.getResults().getResult(1).getAge());
		assertEquals(race1.getResults().getResult(1).getName(), race2.getResults().getResult(1).getName());
		assertEquals(0, race1.getResults().getResult(1).getTime().compareTo(race2.getResults().getResult(1).getTime()));
		assertEquals(0, race1.getResults().getResult(1).getPace().compareTo(race2.getResults().getResult(1).getPace()));

		race1 = rl.getRace(1);
		race2 = rl2.getRace(1);
		assertTrue(race1.equals(race2));
		assertEquals(race1.getResults().getResult(0).getAge(), race2.getResults().getResult(0).getAge());
		assertEquals(race1.getResults().getResult(0).getName(), race2.getResults().getResult(0).getName());
		assertEquals(0, race1.getResults().getResult(0).getTime().compareTo(race2.getResults().getResult(0).getTime()));
		assertEquals(0, race1.getResults().getResult(0).getPace().compareTo(race2.getResults().getResult(0).getPace()));

		assertEquals(race1.getResults().getResult(1).getAge(), race2.getResults().getResult(1).getAge());
		assertEquals(race1.getResults().getResult(1).getName(), race2.getResults().getResult(1).getName());
		assertEquals(0, race1.getResults().getResult(1).getTime().compareTo(race2.getResults().getResult(1).getTime()));
		assertEquals(0, race1.getResults().getResult(1).getPace().compareTo(race2.getResults().getResult(1).getPace()));
	}

	/**
	 * Tests WolfResultReader by creating Race and IndividualResult objects,
	 * assigning them to a RaceList and using them to assert each field in a
	 * RaceList that is read in from the test file is correct.
	 */
	@Test
	public void testWolfResultsReader() {
		String name = "Krispy Kreme Challenge";
		Double dist = 5.0;
		LocalDate ld = LocalDate.parse("2018-02-18");
		String location = "Raleigh, NC";

		Race r = new Race(name, dist, ld, location);
		String pName = "BILLY FETZNER";
		int age = 24;
		RaceTime time = new RaceTime(0, 11, 51);
		IndividualResult ir = new IndividualResult(r, pName, age, time);

		String pName2 = "STEPHEN HENKEL";
		int age2 = 21;
		RaceTime time2 = new RaceTime(0, 12, 02);
		IndividualResult ir2 = new IndividualResult(r, pName2, age2, time2);

		r.addIndividualResult(ir);
		r.addIndividualResult(ir2);

		RaceList rl = new RaceList();
		rl.addRace(r);

		RaceList rl2 = WolfResultsReader.readRaceListFile("test-files/wolf_results_actual.md");

		for (int i = 0; i < rl.size(); i++) {
			Race race1 = rl.getRace(i);
			Race race2 = rl2.getRace(i);
			assertTrue(race1.equals(race2));
			assertEquals(race1.getResults().getResult(0).getAge(), race2.getResults().getResult(0).getAge());
			assertEquals(race1.getResults().getResult(0).getName(), race2.getResults().getResult(0).getName());
			assertEquals(0,
					race1.getResults().getResult(0).getTime().compareTo(race2.getResults().getResult(0).getTime()));
			assertEquals(0,
					race1.getResults().getResult(0).getPace().compareTo(race2.getResults().getResult(0).getPace()));

			assertEquals(race1.getResults().getResult(1).getAge(), race2.getResults().getResult(1).getAge());
			assertEquals(race1.getResults().getResult(1).getName(), race2.getResults().getResult(1).getName());
			assertEquals(0,
					race1.getResults().getResult(1).getTime().compareTo(race2.getResults().getResult(1).getTime()));
			assertEquals(0,
					race1.getResults().getResult(1).getPace().compareTo(race2.getResults().getResult(1).getPace()));

		}

		// test a bunch of invalid files
		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv1.md");
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid file");
		}
		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv2.md");
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid file");
		}
		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv3.md");
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid file");
		}
		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv4.md");
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid file 4");

		}
		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv5.md");
		} catch (IllegalArgumentException e) {
			//
		}
		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv6.md");
		} catch (IllegalArgumentException e) {
			//
		}
		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv7.md");
		} catch (IllegalArgumentException e) {
			//
		}

		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv8.md");
		} catch (IllegalArgumentException e) {
			//
		}
//		try {
//			RaceList rl3 = WolfResultsReader.readRaceListFile("test-files/iv10.md");
//			fail();
//		} catch (IllegalArgumentException e) {
//			//
//		}
		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv11.md");
			fail();
		} catch (IllegalArgumentException e) {
			//
		}

		try {
			rl2 = WolfResultsReader.readRaceListFile("test-files/iv12.md");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

}
