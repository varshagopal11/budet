/**
 * 
 */
package edu.ncsu.csc216.wolf_results.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the sorted linked list class
 * @author varsha
 *
 */
public class SortedLinkedListTest {
//	/**
//	 * Test method for {@link edu.ncsu.csc216.wolf_results.util.SortedLinkedList#hashCode()}.
//	 */
//	@Test
//	public void testHashCode() {
//	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.wolf_results.util.SortedLinkedList#SortedLinkedList()}.
	 */
	@Test
	public void testSortedLinkedList() {
		SortedLinkedList s = new SortedLinkedList();
		assertEquals(0, s.size());
		assertTrue(s.isEmpty());

		assertTrue(s.add(1));
		assertEquals(1, s.size());
		assertEquals(1, s.get(0));

		assertTrue(s.add(3));
		assertEquals(2, s.size());
		assertEquals(3, s.get(1));

		assertTrue(s.add(-1));
		assertEquals(3, s.size());
		assertEquals(-1, s.get(0));

		s.add(2);
		assertEquals(4, s.size());
		assertEquals(-1, s.get(0));
		assertEquals(1, s.get(1));
		assertEquals(2, s.get(2));
		assertEquals(3, s.get(3));

		SortedLinkedList s2 = s;
		assertTrue(s2.equals(s));
		//assertFalse(s2.equals(obj)ull);

		SortedLinkedList s3 = new SortedLinkedList();
		SortedLinkedList s4 = new SortedLinkedList();
		assertTrue(s3.equals(s4));
		s4.add(1);
		assertFalse(s3.equals(s4));
		s3.add(3);
		assertFalse(s3.equals(s4));
		s4.add(3);
		s3.add(1);
		assertEquals(1, s3.get(0));
		assertEquals(3, s3.get(1));
		assertEquals(1, s4.get(0));
		assertEquals(3, s4.get(1));

		SortedLinkedList s5 = new SortedLinkedList();
		SortedLinkedList s6 = new SortedLinkedList();

		s5.add(3);
		s6.add(3);
		assertTrue(s5.equals(s6));
		assertTrue(s5.equals(s5));

		s5.add(4);
		s6.add(5);
		assertFalse(s5.equals(s6));

		s3.add(8);
		assertFalse(s3.equals(s4));

		assertEquals("[1, 3, 8]", s3.toString());

		SortedLinkedList l = new SortedLinkedList();
		l.add("a");
		l.add("b");

		assertEquals(l.hashCode(), 1051460);

		try {
			s.get(5);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index is out of range", e.getMessage());
		}

		try {
			s.add(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Already in list", e.getMessage());
		}
		s.add(19);
		assertEquals(-1, s.get(0));
		assertEquals(19, s.get(4));
		assertFalse(s.isEmpty());
		assertTrue(s.contains(1));
		assertTrue(s.contains(3));
		assertFalse(s.contains(4));

		try {
			s.remove(5);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Index out of Bounds!", e.getMessage());
		}

		s.remove(2);
		assertEquals(4, s.size());
		assertEquals(3, s.get(2));
		s.remove(0);
		assertEquals(3, s.size());
		assertEquals(1, s.get(0));

		assertEquals(-1, s.indexOf(-1));
		assertEquals(1, s.indexOf(3));
	}

//	/**
//	 * Test method for {@link edu.ncsu.csc216.wolf_results.util.SortedLinkedList#equals(java.lang.Object)}.
//	 */
//	@Test
//	public void testEqualsObject() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link edu.ncsu.csc216.wolf_results.util.SortedLinkedList#toString()}.
//	 */
//	@Test
//	public void testToString() {
//		fail("Not yet implemented");
//	}

}
