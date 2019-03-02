package edu.ncsu.csc216.wolf_results.util;
// missing equals, hashcode, to string

/**
 * An implementation of the SortedList interface with a data structure of linked
 * Nodes.
 * 
 * @author Varsha Gopal
 * @author Abigail Ormond
 *
 * @param <E> Generic object E that represents the type of the SortedLinkedList
 */
public class SortedLinkedList<E extends Comparable<E>> implements SortedList<E> {
	// uhmmm
	private int size;
	private Node head;

	/**
	 * Constructs an empty SortedLinkedList object
	 */
	public SortedLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * Returns the number of elements in this list. If this list contains more than
	 * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		if (size > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		if (size > 0) {
			return false;
		}
		return true;

	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element a such
	 * that (o==null ? a==null : o.equals(a)).
	 *
	 * @param e element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */
	@Override
	public boolean contains(Comparable e) {
		Node newNode = head;

		while (newNode != null) {
			if (e.compareTo(newNode.value) == 0) {
				return true;
			}
			newNode = newNode.next;
		}

		return false;
	}

	/**
	 * Adds the specified element to list in sorted order
	 *
	 * @param e element to be appended to this list
	 * @return true if true
	 * @throws NullPointerException     if e is null
	 * @throws IllegalArgumentException if list already contains e
	 */
	@Override
	public boolean add(Comparable e) {
		// list already has e
		Node check = head;
		for (int i = 0; i < size; i++) {
			if (this.contains(e)) {
				throw new IllegalArgumentException("Already in list");
			}
			check = check.next;
		}
		// empty list
		if (size == 0) {
			head = new Node(e, null);
			size++;
			return true;
		}
		// beginning of list
		if (e.compareTo(head.value) < 0) {
			head = new Node(e, head);
			size++;
			return true;
		}
		Node current = head;
		Node previous = current;
		for (int i = 0; i < size; i++) {
			// System.out.println(current.value);

			if (e.compareTo(current.value) > 0) {
				// at the end of the list
				if (current.next == null) {
					current.next = new Node(e, null);
					size++;
					return true;
				}
				previous = current;
				current = current.next;
			} else {
				Node newNode = new Node(e, current);
				previous.next = newNode;
				current = new Node(e, current);
				size++;
				return true;
			}
		}
		return false;

	}

	// unclear on diagram
	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index is out of range");
		}

		if (index == 0) {
			return (E) head.value;
		}

		Node<E> currentNode = head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}

		return (E) currentNode.value;

	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices). Returns
	 * the element that was removed from the list.
	 *
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of Bounds!");
		}
		Node temp = head;
		if (index == 0) { // REMOVE FROM FRONT
			temp = head;
			head = head.next;
		} else {
			Node currentScan = head;
			for (int i = 0; i < index - 1; i++) {
				currentScan = currentScan.next;
			}
			temp = currentScan.next;
			currentScan.next = currentScan.next.next;
		}
		size--;
		return (E) temp.value;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or
	 * -1 if there is no such index.
	 *
	 * @param e element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Comparable e) {
		int indexOf = -1;
		Node currentScan = head;
		for (int i = 0; i < size; i++) {
			if (e.compareTo(currentScan.value) != 0) {
				currentScan = currentScan.next;
			} else {
				indexOf = i;
				break;
			}
		}
		return indexOf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + size;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SortedLinkedList other = (SortedLinkedList) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	/**
	 * Converts SortedLinkedList to string form
	 * 
	 * @return list in String form
	 */
	public String toString() {

		String s = "[";
		if (head == null) {
			return "[]";
		}
		Node current = head;
		while (current.next != null) {
			s += current.value + ", ";
			current = current.next;
		}
		s += current.value;

		s += "]";
		return s;
	}

	/**
	 * Public inner class of SortedLinkedList that represents a single Node.
	 * 
	 * @author Varsha Gopal
	 * @author Abigail Ormond
	 *
	 * @param <E> Generic object E that represents the type of the Node
	 * 
	 */
	public class Node<E> {

		/** Field of generic type that represents value of the Node */
		protected E value;
		private Node<E> next;

		/**
		 * Constructs a Node object given an element and another Node object
		 * 
		 * @param element of generic type
		 * @param next    of generic type
		 */
		public Node(E element, Node<E> next) {
			this.value = element;
			this.next = next;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			// result = prime * result; //+ getOuterType().hashCode();
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (next == null) {
				if (other.next != null)
					return false;
			} else if (!next.equals(other.next))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

	}
}
