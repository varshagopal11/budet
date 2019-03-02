package edu.ncsu.csc216.wolf_results.race_results;

import java.time.LocalDate;
import java.util.Observable;


import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * A Race object that describes the properties and behaviors of a Race. Has
 * name, distance, data and location fields that make up the details of a race.
 * Has the ability to add a runner's results for a race and change the distance
 * of a race if necessary.
 * 
 * @author Varsha Gopal
 * @author Abigail Ormond
 *
 */
public class Race extends Observable {
	/** Represents the name of a race */
	private String name;
	/** Represents the length of the race or distance the runners run */
	private double distance;
	/** The date the race took place */
	private LocalDate date;
	/** The location the race took place */
	private String location;
	/** List of results for the race */
	private RaceResultList results;

	/**
	 * Constructor for the race that sets the name, distance date and location
	 * 
	 * @param name     name of race
	 * @param distance double variable for the distance of race in miles
	 * @param date     LocalDate object of the race's date
	 * @param location String for holding the address of the race
	 * @throws IllegalArgumentException if (a) name is null, empty string, or all
	 *                                  whitespace; (b) distance is non-positive;
	 *                                  (c) date is null; (d) location is null; or
	 *                                  (e) results is null. name and location
	 *                                  should be trimmed of leading and/or trailing
	 *                                  whitespace.
	 */
	public Race(String name, double distance, LocalDate date, String location) {
		
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Invalid name");
		}
		this.name = name.trim();
		if (distance <= 0) {
			throw new IllegalArgumentException("Invalid distance");
		}
		this.distance = distance;
		if (date == null) {
			throw new IllegalArgumentException("Invalid date");
		}
		this.date = date;

		if (location == null) {
			throw new IllegalArgumentException("Invalid location");
		}
		this.location = location.trim();
		results = new RaceResultList();
		setChanged();
		notifyObservers(this);
		
	}

	/**
	 * Constructor for the race that sets the name, distance date and location and
	 * takes in the results for the race if given
	 * 
	 * @param name     name of race
	 * @param distance double variable for the distance of race in miles
	 * @param date     LocalDate object of the race's date
	 * @param location String for holding the address of the race
	 * @param results  RaceResultList of results for the race
	 */
	public Race(String name, double distance, LocalDate date, String location, RaceResultList results) {

		this(name, distance, date, location);
		if (results == null) {
			throw new IllegalArgumentException("Results are null");
		}
		this.results = results;
	}

	/**
	 * Gets the name of the race
	 * 
	 * @return String variable holding the race name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the distance of the race
	 * 
	 * @return double variable holding the distance of the race
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Gets the date of the race
	 * 
	 * @return LocalDate date of the race
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Gets the location of the race
	 * 
	 * @return String variable holding the race location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Gets the results of the race
	 * 
	 * @return RaceResultList variable holding the race results
	 */
	public RaceResultList getResults() {
		return results;
	}

	/**
	 * Adds an individual result from the race if a runner was added to run the race
	 * 
	 * @param ir Individual result from a runner of the race
	 */
	public void addIndividualResult(IndividualResult ir) {
		results.addResult(ir);
		// NOTIFY OBSERVERS
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Sets the distance of the race to a new distance if it needs to be changed
	 * 
	 * @param distance double variable for the distance in miles of the race
	 * @throws IllegalArgumentException if distance is not positive
	 */
	public void setDistance(double distance) {
		if (distance <= 0) {
			throw new IllegalArgumentException("Invalid distance");
		}
		this.distance = distance;

		setChanged(); // Marks the Observable as changed
		notifyObservers(this); // Sends a message to any Observer classes that the object has changed.
								// The current instance is passed to notifyObservers()
	}

	/**
	 * Method that takes in a minimum, maximum and two paces to filter the race
	 * results to a specific group of runners. Returns list of results such that
	 * runner’s age is between minAge and maxAge (inclusive) and runner’s pace is
	 * between minPace and maxPace (inclusive).
	 * 
	 * @param min   value for the age of the result
	 * @param max   value for the age of the result
	 * @param pace1 minimum pace for the filter
	 * @param pace2 maximum pace for the filter
	 * @return RaceResultList a list of results that are filtered to match parameter
	 *         specifications
	 * @throws IllegalArgumentException if minPace or maxPace is not a valid
	 *                                  RaceTime
	 */
	public RaceResultList filter(int min, int max, String pace1, String pace2) {
		RaceTime minPace = null;
		RaceTime maxPace = null; 
		try {
			minPace = new RaceTime(pace1);
			maxPace = new RaceTime(pace2);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Illegal pace");
		}
		minPace.toString();
		maxPace.toString();
		RaceResultList filtered = results.filter(min, max, pace1, pace2);
		return filtered;

	}

	/**
	 * Creates a string with the data from the race object
	 * 
	 * @return String representation of the Race object
	 */
	public String toString() {
		String s = "";
		s += name + " (" + distance + " miles) on " + date + " at " + location;
		return s;
	}

	/**
	 * Returns the hashCode of the Race object
	 * 
	 * @return integer value for the hashCode
	 */
	public int hashCode() {
		return 0;
	}

	/**
	 * Checks the equality of the Race object to an instance of the same object
	 * 
	 * @param o Object to compare against
	 * @return true or false if it is equal or not
	 */
	public boolean equals(Object o) {
		if (o instanceof Race) {
			String name2 = ((Race) o).getName();
			Double distance2 = ((Race) o).getDistance();
			LocalDate date2 = ((Race) o).getDate();
			String location2 = ((Race) o).getLocation();
			if (name.equals(name2) && distance == distance2 && date.equals(date2) && location.equals(location2)) {
				return true;
			}
		}
		return false;
	}

}
