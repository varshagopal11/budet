/**
 * 
 */
package edu.ncsu.csc216.wolf_results.race_results;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the RaceList class 
 * @author Varsha Gopal
 * @author Abigail Ormond
 *
 */
public class RaceListTest {

	/** Delta for testing doubles */
	public static final double DELTA = 0.0001;

	/** Race that is observed by the test class. */
	//private Race r;
	/** Boolean flag to let us know if update() has been called */
	public boolean updated;
	/**
	 * Reference to Observable passed to update(). That way we can check that the
	 * right object was passed to update().
	 */
	public Object updatedObject;

	/**
	 * Set up method. Adds an observer to the instance under test.
	 */
	@Before
	public void setUp() throws Exception {
		// Create a new RaceList
		RaceList rl = new RaceList();
		String name = "Krispy Kreme Run";
		String name2 = "Turkey Trot";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
		Race rrrrr = new Race(name, dist, ld, location);
		Race r2 = new Race(name2, dist, ld, location);
		rl.addRace(rrrrr);
		rl.addRace(r2);

//		// Adds a new Observer to the RaceList
//		rl.addObserver(new Observer() {
//		
//			@Override
//			public void update(Observable o, Object obj) {
//				updated = true;
//				updatedObject = obj;
//			}
//
//		});
	}

//   /**
//    * Tests update distance.
//    */
//   @Test
//   public void testSetDistanceObserver() {
//       // Always set updated/updatedObject to false/null BEFORE calling a
//       // method that should notifyObservers(). This way you can make sure that
//       // the checks are testing the right thing!
//       updated = false;
//       updatedObject = null;
//
//       assertEquals("Checks initial distance", 3.1, r.getDistance(), DELTA);
//
//       // Race.setDistance() should call setChanged; notifiyObservers(); if the
//       // count is incremented.
//       r.setDistance(5);
//       // Check that the turnCount has been updated
//       assertEquals("Updating distance from 3.1 to 5.0", 5.0, r.getDistance(),
//               DELTA);
//       // Check that the Observer.update() method defined in the test has been
//       // called. If so, updated is true!
//       assertTrue("Observers should be notified after updating distance,"
//               + " but they were not.", updated);
//       // Check that the correct object was passed to Observer.update()
//       assertEquals(
//               "Observers should be notified after updating distance,"
//                       + " but they were not.",
//               "One 5k", ((Race) updatedObject).getName());
//
//       // Reset our flags
//       updated = false;
//       updatedObject = null;
//
//       // Race.setDistance() should call setChanged; notifiyObservers(); if the
//       // count is incremented.
//       r.setDistance(15.5);
//       // Check that the turnCount has been updated
//       assertEquals("Updating distance from 5.0 to 15.5", 15.5,
//               r.getDistance(), DELTA);
//       // Check that the Observer.update() method defined in the test has been
//       // called. If so, updated is true!
//       assertTrue("Observers should be notified after updating distance,"
//               + " but they were not.", updated);
//       // Check that the correct object was passed to Observer.update()
//       assertEquals(
//               "Observers should be notified after updating distance,"
//                       + " but they were not.",
//               "One 5k", ((Race) updatedObject).getName());
//   }

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.race_results.RaceList#RaceList()}.
	 */
	@Test
	public void testRaceList() {
		RaceList rl = new RaceList();
		assertEquals(0, rl.size());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.race_results.RaceList#addRace(edu.ncsu.csc216.wolf_results.race_results.Race)}.
	 */
	@Test
	public void testAddRaceRace() {
		RaceList rl = new RaceList();
		String name = "Krispy Kreme Run";
	//	String nameWithSpace = "    Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
	//	String locationWithSpace = "   Raleigh, NC";

		try {
			rl.addRace(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Race is null", e.getMessage());
		}
		Race r = new Race(name, dist, ld, location);
		rl.addRace(r);
		assertEquals(1, rl.size());

		String name2 = "Turkey Trot";
		rl.addRace(name2, dist, ld, location);

		try {
			rl.addRace(null, dist, ld, location);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to construct Race", e.getMessage());
		}

		assertEquals(2, rl.size());
		rl.removeRace(1);
		assertEquals(1, rl.size());
		assertEquals(rl.getRace(0).getName(), "Krispy Kreme Run");

		try {
			rl.getRace(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of bounds", e.getMessage());
		}

		try {
			rl.getRace(4);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of bounds", e.getMessage());
		}

	}

//	/**
//	 * Test method for {@link edu.ncsu.csc216.wolf_results.race_results.RaceList#update(java.util.Observable, java.lang.Object)}.
//	 */
//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}

}
