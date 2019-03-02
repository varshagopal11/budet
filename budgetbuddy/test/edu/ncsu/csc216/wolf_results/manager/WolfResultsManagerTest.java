package edu.ncsu.csc216.wolf_results.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.wolf_results.race_results.IndividualResult;
import edu.ncsu.csc216.wolf_results.race_results.Race;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;
import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Tests the WolfResultsManager class and its methods
 * 
 * @author Varsha Gopal
 * @author Abigail Ormond
 */
public class WolfResultsManagerTest {
	/** Delta for testing doubles */
	public static final double DELTA = 0.0001;

	/** Race that is observed by the test class. */
	/** Boolean flag to let us know if update() has been called */
	// private boolean updated;
	/**
	 * Reference to Observable passed to update(). That way we can check that the
	 * right object was passed to update().
	 */
	// private Object updatedObject;
	// creating race r
	String name = "Krispy Kreme Challenge";
	Double dist = 5.0;
	LocalDate ld = LocalDate.parse("2018-02-18");
	String location = "Raleigh, NC";
	Race r = new Race(name, dist, ld, location);
	// creating individualresult ir
	String pName = "BILLY FETZNER";
	int age = 24;
	RaceTime time = new RaceTime(0, 11, 51);
	IndividualResult ir = new IndividualResult(r, pName, age, time);
	// creating individualresult ir2
	String pName2 = "STEPHEN HENKEL";
	int age2 = 21;
	RaceTime time2 = new RaceTime(0, 12, 02);
	IndividualResult ir2 = new IndividualResult(r, pName2, age2, time2);
	// creating racelist
	RaceList rl;


	/**
	 * Tests the saveFile method by saving the WolfResultsManager's Instance of
	 * raceList to a file and checking it against an expected file. File checker
	 * taken from PackScheduler's CourseRecordIOTest checkFiles method (provided in
	 * implementation instructions on GitHub)
	 */
	@Test
	public void testSaveFile() {
		WolfResultsManager.getInstance().newList();
		WolfResultsManager.getInstance();
		String expFile = "test-files/wolf_results_expected.md";
		String actFile = "test-files/wolf_results_actual2.md";
		WolfResultsManager.getInstance().getRaceList().addRace(r);
		WolfResultsManager.getInstance().saveFile(actFile);
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading files.");
		}

	}

	/**
	 * Tests the LoadFile method
	 */
	@Test
	public void testLoadFile() {
		WolfResultsManager.getInstance().newList();
		try {
			WolfResultsManager.getInstance().loadFile("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Filename incorrect", e.getMessage());
		}

		try {
			WolfResultsManager.getInstance().loadFile(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Filename incorrect", e.getMessage());
		}

		try {
			WolfResultsManager.getInstance().loadFile("macarena.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Can't find file", e.getMessage());
		}

		WolfResultsManager.getInstance().loadFile("  test-files/wolf_results_expected.md");
		assertEquals("test-files/wolf_results_expected.md", WolfResultsManager.getInstance().getFilename());
		assertTrue(WolfResultsManager.getInstance().getRaceList().getRace(0).equals(r));

	}

	/**
	 * Tests the update method using the observable pattern
	 */
	@Test
	public void testUpdate() {
		assertTrue(WolfResultsManager.getInstance().isChanged());
		// WolfResultsManager.getInstance().update(ob, o);
	}

	/**
	 * Set up method. Adds an observer to the instance under test.
	 */
	@Before
	public void setUp() throws Exception {
		// racelist has 1 race with 2 individualresults
		r.addIndividualResult(ir);
		r.addIndividualResult(ir2);
		
		RaceList rlll = new RaceList();
		rlll.addRace(r);

	}
}
