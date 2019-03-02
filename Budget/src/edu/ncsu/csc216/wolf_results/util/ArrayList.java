package edu.ncsu.csc216.wolf_results.util;
// DOUBLE CHECK: arraylist constructor

// FINISHED 100%
// TESTED 100%

/**
 * Creates an ArrayList of Elements. Has methods that can add, remove and check
 * the size of an array list. Implements the interface List.
 * 
 * @author Abigail Ormond
 * @author Varsha Gopal
 */
public class ArrayList implements List {
	/** Static field for serialVersionUID */
//	private static final long serialVersionUID = 0;
	/** Static field for resize */
	private static final int RESIZE = 10;
	/** Field for array of type Object */
	private Object[] list;
	/** Field for size of ArrayList */
	private int size;
	/** Field for capacity of ArrayList */
	private int capacity;

	/**
	 * Constructs an ArrayList object with no given parameters
	 */
	public ArrayList() {
		this(RESIZE);
	}

	/**
	 * Constructs an ArrayList object with a given size
	 * 
	 * @param cap of integer type
	 * 
	 */
	public ArrayList(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = cap;
		list = new Object[cap];

		this.size = 0;
	}

	/**
	 * Appends the specified element to the end of this list (optional operation).
	 *
	 * Lists that support this operation may place limitations on what elements may
	 * be added to this list. In particular, some lists will refuse to add null
	 * elements, and others will impose restrictions on the type of elements that
	 * may be added. List classes should clearly specify in their documentation any
	 * restrictions on what elements may be added.
	 *
	 * @param o element to be appended to this list
	 * @return true (as specified by {Collection#add})
	 * @throws NullPointerException for adding null object
	 */
	public boolean add(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		if (contains(o)) {
			throw new IllegalArgumentException();
		}
		if (size == capacity) {
			Object[] temp = list;
			capacity = 2 * size;
			list = new Object[capacity];
			for (int i = 0; i < size; i++) {
				list[i] = temp[i];
			}
		}
		size++;
		list[size - 1] = o;
		return true;
	}

	/**
	 * Inserts the specified element at the specified position in this list
	 * (optional operation). Shifts the element currently at that position (if any)
	 * and any subsequent elements to the right (adds one to their indices).
	 *
	 * @param index index at which the specified element is to be inserted
	 * @param o     element to be inserted
	 * @throws NullPointerException      if the specified element is null and this
	 *                                   list does not permit null elements
	 * @throws IllegalArgumentException  if some property of the specified element
	 *                                   prevents it from being added to this list
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 */
	public void add(int index, Object o) {
		if (o == null) {
			throw new NullPointerException("Object to be added is null");
		}
		if (this.contains(o)) {
			throw new IllegalArgumentException("Object exists in list");
		}
		if (index > size() || index < 0) {
			throw new IndexOutOfBoundsException("Index out of range exception.");
		}
		if (size == capacity) {
			Object[] temp = list;
			capacity = 2 * size;
			list = new Object[capacity];
			for (int i = 0; i < size; i++) {
				list[i] = temp[i];
			}
		}
		if (index == size) {
			this.add(o);
		} else if (index < size) {
			for (int i = size; i > index; i--) {
				list[i] = list[i - 1];
			}
			list[index] = o;
			size++;
		}
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element e such
	 * that (o==null ? e==null : o.equals(e)).
	 *
	 * @param o element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	public boolean contains(Object o) {
		if (o == null) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public Object get(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Index out of range exception.");
		}
		return list[index];
		
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
	 * -1 if there is no such index.
	 *
	 * @param o element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	public int indexOf(Object o) {
		int indexOf = -1;
		for (int i = 0; i < size; i++) {
			if (list[i] == o) {
				indexOf = i;
				return indexOf;
			}
		}
		return indexOf;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	public boolean isEmpty() {
		if (size > 0) {
			return false;
		}
		return true;

	}

	/**
	 * Removes the element at the specified position in this list (optional
	 * operation). Shifts any subsequent elements to the left (subtracts one from
	 * their indices). Returns the element that was removed from the list.
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 */
	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		Object removed = list[index];
		if (index < size) {
			for (int i = index; i < size(); i++) {
				list[i] = list[i + 1];
			}
		}
		list[size - 1] = null;
		size--;
		return removed;
	}

	/**
	 * Returns the number of elements in this list. If this list contains more than
	 * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 *
	 * @return the number of elements in this list
	 */
	public int size() {
		if (size >= Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return size;
	}

}
