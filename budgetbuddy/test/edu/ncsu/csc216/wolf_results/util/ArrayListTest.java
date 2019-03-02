/**
 * 
 */
package edu.ncsu.csc216.wolf_results.util;

import static org.junit.Assert.*;

import org.junit.Test;
import edu.ncsu.csc216.wolf_results.util.ArrayList;

/**
 * Tests the array list class 
 * @author Varsha Gopal
 *
 */
public class ArrayListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.ArrayList#ArrayList()}.
	 */
	@Test
	public void testArrayList() {
		ArrayList arr = new ArrayList();
		assertEquals(0, arr.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.ArrayList#ArrayList(int)}.
	 */
	@Test
	public void testArrayListInt() {
		ArrayList arr = new ArrayList(10);
		assertEquals(0, arr.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.ArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddObject() {
		ArrayList arr = new ArrayList();
		assertEquals(0, arr.size());
		assertTrue(arr.add("CaryRoadRace"));
		assertEquals(1, arr.size());
		assertEquals("CaryRoadRace", arr.get(0));
		assertTrue(arr.add("TurkeyTrot"));
		assertEquals(2, arr.size());
		assertEquals("TurkeyTrot", arr.get(1));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntObject() {
		ArrayList arr = new ArrayList();
		try {
			arr.add(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("Object to be added is null", e.getMessage());
		}
		assertEquals(0, arr.size());

		arr.add(0, "CaryRoadRace");
		try {
			arr.add(1, "CaryRoadRace");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Object exists in list", e.getMessage());
		}
		assertEquals(1, arr.size());

		try {
			arr.add(2, "TurkeyTrot");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of range exception.", e.getMessage());
		}

		try {
			arr.add(-1, "TurkeyTrot");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of range exception.", e.getMessage());
		}

		arr.add(1, "TurkeyTrot");
		arr.add(2, "Bull City Race");
		arr.add(1, "Reindeer Fun Run");
		assertEquals("Reindeer Fun Run", arr.get(1));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.ArrayList#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		ArrayList arr = new ArrayList();
		assertEquals(0, arr.size());
		arr.add("CaryRoadRace");
		arr.add("TurkeyTrot");

		assertFalse(arr.contains(null));
		assertFalse(arr.contains("RunningSucks"));
		assertTrue(arr.contains("CaryRoadRace"));

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_results.util.ArrayList#get(int)}.
	 */
	@Test
	public void testGet() {
		ArrayList arr = new ArrayList(10);
		try {
			arr.get(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of range exception.", e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.ArrayList#indexOf(java.lang.Object)}.
	 */
	@Test
	public void testIndexOf() {
		ArrayList arr = new ArrayList();
		arr.add("Cary Road Race");
		arr.add("Turkey Trot");
		arr.add("Reindeer Fun Run");
		assertEquals(1, arr.indexOf("Turkey Trot"));
		assertEquals(-1, arr.indexOf("DunderMiflin"));

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.ArrayList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		ArrayList arr = new ArrayList();
		assertTrue(arr.isEmpty());
		arr.add("Dashing Thru The Glow 5K");
		assertFalse(arr.isEmpty());
		arr.remove(0);
		assertTrue(arr.isEmpty());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.ArrayList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		ArrayList arr = new ArrayList();
		arr.add("Night of Light 5K");
		try {
			arr.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of range", e.getMessage());
		}

		try {
			arr.remove(3);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of range", e.getMessage());
		}
	}

}
