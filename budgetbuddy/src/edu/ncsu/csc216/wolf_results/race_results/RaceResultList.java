
package edu.ncsu.csc216.wolf_results.race_results;

import edu.ncsu.csc216.wolf_results.util.RaceTime;
import edu.ncsu.csc216.wolf_results.util.SortedLinkedList;

/**
 * RaceResultList class holds the list of results for one race - it can add a
 * result and filter the results for the race
 * 
 * @author Varsha Gopal
 * @author Abigail Ormond
 */
public class RaceResultList {
	/** SortedLinkedList that sorts the race results */
	private SortedLinkedList<IndividualResult> results;

	/**
	 * Constructor for the RaceResultList that sets the fields and creates a
	 * RaceResultList object
	 */
	public RaceResultList() {
		results = new SortedLinkedList<IndividualResult>();
	}

	/**
	 * Adds a race result given the individual result object
	 * 
	 * @param ir Individual Result object to be added to the RaceResultList
	 * @throws IllegalArgumentException if IndividualResult is null
	 */
	public void addResult(IndividualResult ir) {
		if (ir == null) {
			throw new IllegalArgumentException("Invalid result");
		}
		results.add(ir);
	}

	/**
	 * Adds a result given the specific information of a result
	 * 
	 * @param r  Race object that result associates with
	 * @param s  String name of the runner
	 * @param i  int age of the runner
	 * @param rt RaceTime how long it took the runner to complete the race
	 * @throws IllegalArgumentException if unable to construct IndividualResult
	 *                                  object
	 */
	public void addResult(Race r, String s, int i, RaceTime rt) {
		try {
			IndividualResult ir = new IndividualResult(r, s, i, rt);
			results.add(ir);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Returns the result at a given index
	 * 
	 * @param index Integer index of given result
	 * @return IndividualResult object
	 * @throws IndexOutOfBoundsException if index below zero or greater than size
	 */
	public IndividualResult getResult(int index) {
		if (index < 0 || index >= results.size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		return results.get(index);
	}

	/**
	 * Returns the size of the race result list
	 * 
	 * @return integer size
	 */
	public int size() {
		return results.size();
	}

	/**
	 * Returns the results as a 2D string array
	 * 
	 * @return String[][] array of result lists
	 */
	public String[][] getResultsAsArray() {
		String[][] arr = new String[results.size()][4];
		if (results.size() == 0) {
			return arr;
		}
		for (int i = 0; i < results.size(); i++) {
			arr[i][0] = results.get(i).getName();
			arr[i][1] = "" + results.get(i).getAge();
			arr[i][2] = "" + results.get(i).getTime();
			arr[i][3] = "" + results.get(i).getPace();
		}
		return arr;
	}

	/**
	 * Filters the race results by min and max age boundaries and min and max pace
	 * boundaries
	 * 
	 * @param min   integer minimum age for runner
	 * @param max   integer maximum age for runner
	 * @param pace1 String min pace for runner
	 * @param pace2 String max pace for runner
	 * @return RaceResultList returns the filtered list of Race Results
	 * @throws IllegalArgumentException if minPace or maxPace is not a valid
	 *                                  RaceTime.
	 */
	public RaceResultList filter(int min, int max, String pace1, String pace2) {
		/*
		 * Returns list of results such that runner’s age is between minAge and maxAge
		 * (inclusive) and runner’s pace is between minPace and maxPace (inclusive).
		 */
		RaceTime timeMin = new RaceTime(pace1);
		RaceTime timeMax = new RaceTime(pace2);

		RaceResultList r1 = new RaceResultList();
		int age;
		RaceTime time;

		for (int i = 0; i < results.size(); i++) {
			age = results.get(i).getAge();
			time = results.get(i).getPace();
			if (age >= min && age <= max && time.compareTo(timeMin) >= 0 && time.compareTo(timeMax) <= 0) {
				r1.addResult(results.get(i));
			}
		}

		return r1;
	}
}
