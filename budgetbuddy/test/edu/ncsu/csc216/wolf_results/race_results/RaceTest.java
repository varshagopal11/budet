package edu.ncsu.csc216.wolf_results.race_results;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.wolf_results.util.RaceTime;
/**
 * Tests the Race class and the creation of a race and the fields of a race object
 * @author Varsha Gopal
 */
public class RaceTest {

	/** Delta for testing doubles */
	public static final double DELTA = 0.0001;

	/** Race that is observed by the test class. */
	private Race r;
	/** Boolean flag to let us know if update() has been called */
	private boolean updated;
	/**
	 * Reference to Observable passed to update(). That way we can check that the
	 * right object was passed to update().
	 */
	private Object updatedObject;

	/**
	 * Set up method. Adds an observer to the instance under test.
	 */
	@Before
	public void setUp() throws Exception {
		// Create a new Race
		r = new Race("One 5k", 3.1, LocalDate.of(2018, 11, 30), "NCSU");

		// Adds a new Observer to the Race
		r.addObserver(new Observer() {

			/**
			 * If the Observable object calls notifyObservers(), this update() method will
			 * be called. It will set the updated flag to true and assign the second
			 * argument, obj, to the updatedObject. This is the Object that was passed into
			 * the notifyObservers() call by the Observable object.
			 * 
			 * For this project, every call to notifyObservers() should pass in the current
			 * instance. Then we can check that the current instance is correct!
			 */
			@Override
			public void update(Observable o, Object obj) {
				updated = true;
				updatedObject = obj;
			}

		});
	}

	/**
	 * Tests update distance.
	 */
	@Test
	public void testSetDistanceObserver() {
		// Always set updated/updatedObject to false/null BEFORE calling a
		// method that should notifyObservers(). This way you can make sure that
		// the checks are testing the right thing!
		updated = false;
		updatedObject = null;

		assertEquals("Checks initial distance", 3.1, r.getDistance(), DELTA);

		// Race.setDistance() should call setChanged; notifiyObservers(); if the
		// count is incremented.
		r.setDistance(5);
		// Check that the turnCount has been updated
		assertEquals("Updating distance from 3.1 to 5.0", 5.0, r.getDistance(), DELTA);
		// Check that the Observer.update() method defined in the test has been
		// called. If so, updated is true!
		assertTrue("Observers should be notified after updating distance," + " but they were not.", updated);
		// Check that the correct object was passed to Observer.update()
		assertEquals("Observers should be notified after updating distance," + " but they were not.", "One 5k",
				((Race) updatedObject).getName());

		// Reset our flags
		updated = false;
		updatedObject = null;

		// Race.setDistance() should call setChanged; notifiyObservers(); if the
		// count is incremented.
		r.setDistance(15.5);
		// Check that the turnCount has been updated
		assertEquals("Updating distance from 5.0 to 15.5", 15.5, r.getDistance(), DELTA);
		// Check that the Observer.update() method defined in the test has been
		// called. If so, updated is true!
		assertTrue("Observers should be notified after updating distance," + " but they were not.", updated);
		// Check that the correct object was passed to Observer.update()
		assertEquals("Observers should be notified after updating distance," + " but they were not.", "One 5k",
				((Race) updatedObject).getName());
	}
	/**
	 * Tests the Race creation if given a string
	 */
	@Test
	public void testRaceStringDoubleLocalDateString() {
		String name = "Krispy Kreme Run";
		String nameWithSpace = "    Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
		String locationWithSpace = "   Raleigh, NC";

		r = new Race(name, dist, ld, location);
		Double d = r.getDistance();
		assertEquals(name, r.getName());
		assertEquals(dist, d);
		assertEquals(ld, r.getDate());
		assertEquals(location, r.getLocation());

		Race r2 = new Race(nameWithSpace, dist, ld, locationWithSpace);
		Double d2 = r.getDistance();
		assertEquals(name, r2.getName());
		assertEquals(dist, d2);
		assertEquals(ld, r2.getDate());
		assertEquals(location, r2.getLocation());

	}
	/**
	 * Tests creation with local date
	 */
	@Test
	public void testRaceStringDoubleLocalDateStringRaceResultList() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
		r = new Race(name, dist, ld, location);

		String pName = "Abigail";
		int age = 19;
		RaceTime time = new RaceTime(2, 59, 52);
	//	IndividualResult ir = new IndividualResult(r, pName, age, time);

		String pName2 = "Varsha";
		int age2 = 18;
		RaceTime time2 = new RaceTime(2, 58, 16);
		IndividualResult ir2 = new IndividualResult(r, pName2, age2, time2);

		RaceResultList rr = new RaceResultList();
		rr.addResult(r, pName, age, time);
		// rr.addResult(r, pName2, age2, time2);

		r = new Race(name, dist, ld, location, rr);

		r.addIndividualResult(ir2);

		assertEquals(ir2, r.getResults().getResult(0));
	}
	/**
	 * Tests changing the distance or setting the distance 
	 */
	@Test
	public void testSetDistance() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		Double dist2 = 13.1;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";

		r = new Race(name, dist, ld, location);
		r.setDistance(13.1);
		try {
			r.setDistance(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid distance", e.getMessage());
		}

		Double d = r.getDistance();
		assertEquals(name, r.getName());
		assertEquals(dist2, d);
		assertEquals(ld, r.getDate());
		assertEquals(location, r.getLocation());
	}
	/**
	 * Tests filtering the races
	 */
	@Test
	public void testFilter() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
		r = new Race(name, dist, ld, location);

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

		r = new Race(name, dist, ld, location, rr);

		RaceResultList expectedFilter = new RaceResultList();
		expectedFilter.addResult(ir);

//		System.out.println(ir.getPace());  	//		00:05:04
//		System.out.println(ir2.getPace());	//		00:06:48
//		System.out.println(ir3.getPace());	//		00:06:52

		RaceResultList actualFilter = r.filter(18, 20, "00:00:00", "00:06:00");

		assertEquals(expectedFilter.size(), actualFilter.size());
		assertEquals(expectedFilter.getResult(0).getName(), actualFilter.getResult(0).getName());
	}
	/**
	 * Tests the to string method
	 */
	@Test
	public void testToString() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
		r = new Race(name, dist, ld, location);

		assertEquals("Krispy Kreme Run (26.2 miles) on 2017-02-06 at Raleigh, NC", r.toString());

	}
	/**
	 * Tests the equals method
	 */
	@Test
	public void testEqualsObject() {
		String name = "Krispy Kreme Run";
		Double dist = 26.2;
		LocalDate ld = LocalDate.parse("2017-02-06");
		String location = "Raleigh, NC";
		r = new Race(name, dist, ld, location);

	//	String name2 = "Krispy Kreme Run";
	//	Double dist2 = 26.2;
	//	LocalDate ld2 = LocalDate.parse("2017-02-06");
	//	String location2 = "Raleigh, NC";
		//Race r2 = new Race(name2, dist2, ld2, location2);

		String name3 = "Krispy Kreme Run";
		Double dist3 = 26.2;
		LocalDate ld3 = LocalDate.parse("2017-02-06");
		String location3 = "Cary, NC";
		Race r3 = new Race(name3, dist3, ld3, location3);

		assertFalse(r.equals(r3));
	}

//	@Test
//	public void testHashCode() {
//		fail("Not yet implemented");
//	}

}
