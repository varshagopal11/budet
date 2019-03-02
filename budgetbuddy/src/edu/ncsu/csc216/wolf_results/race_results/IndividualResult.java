
// more for update() ???????? (also is pace math right?? who knows)

package edu.ncsu.csc216.wolf_results.race_results;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Individual Result class holds the information for one result (or one runner)
 * of a race. Has fields of name and age to define a runner and gets a runners
 * time.
 * 
 * @author Varsha Gopal
 * @author Abigail Ormond
 *
 */
public class IndividualResult extends Observable implements Comparable<IndividualResult>, Observer {
	/** String name of the runner */
	private String name;
	/** Integer value of the runner's age */
	private int age;
	/** Race object associated with the individual result */
	private Race race;
	/** RaceTime object to hold data for pace of runner */
	private RaceTime pace;
	/** RaceTime object to hold data for time of runner */
	private RaceTime time;

	/**
	 * Constructor for the IndividualResult object that creates the object and sets
	 * the fields
	 * 
	 * @param race Race object associated with the result
	 * @param name String for name of the runner
	 * @param age  Integer for age of runner
	 * @param time RaceTime for runner's time
	 * @throws IllegalArgumentException if (a) race is null; (b) name is null, empty
	 *                                  string, or all whitespace; (c) age is
	 *                                  negative; or (d) time is null.
	 */
	public IndividualResult(Race race, String name, int age, RaceTime time) {
		if (race == null) {
			throw new IllegalArgumentException("Invalid race object");
		}
		this.race = race;
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Invalid name");
		}
		if (age < 0) {
			throw new IllegalArgumentException("Invalid age");
		}
		this.age = age;
		if (time == null) {
			throw new IllegalArgumentException("Invalid race time");
		}
		this.time = time;

//		
//		// need to check if name is all whitespace
//		for (int i = 0; i < name.length(); i++) {
//			if (Character.isWhitespace(name.charAt(i))) {
//				name = name.substring(i + 1);
//			}
//			break;
//		}
//		if (name.length() == 0) {
//			throw new IllegalArgumentException("Invalid name");
//		}
		this.name = name.trim();
		// set pace
		double racePace = time.getTimeInSeconds() / race.getDistance();
		int raceHrs;

		raceHrs = (int) racePace / 3600;
		int raceMin = (int) ((racePace / 60) % 60);
		int raceSec = (int) racePace - raceHrs * 3600 - raceMin * 60;

		this.pace = new RaceTime(raceHrs, raceMin, raceSec);

		// An Observer should be added for race.
		race.addObserver(this);

	}

	/**
	 * Get's the Race object for the result
	 * 
	 * @return Race object for the race that the runner is running
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * Gets the name of the individual result
	 * 
	 * @return String name of runner
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the age of the individual result
	 * 
	 * @return int age of runner
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Gets the time of the individual result
	 * 
	 * @return RaceTime time of runner
	 */
	public RaceTime getTime() {
		return time;
	}

	/**
	 * Gets the pace of the individual result
	 * 
	 * @return RaceTime pace of runner
	 */
	public RaceTime getPace() {
		return pace;
	}

	/**
	 * Compares the individual result to an object passed in for equality based on
	 * time by delegating to RaceTime.compareTo()
	 * 
	 * @param ir IndividualResult object being compared to the current one
	 * @return integer value if it does or doesn't match
	 */
	public int compareTo(IndividualResult ir) {
		RaceTime rt1 = this.getTime();
		RaceTime rt2 = ir.getTime();
		return rt1.compareTo(rt2);
	}

	/**
	 * Returns a string representation of the IndividualResult fields
	 * 
	 * @return String holding information about the Individual Result
	 */
	public String toString() {
		String s = "";
		s += "IndividualResult [name=" + name + ",";
		s += " age=" + age + ",";
		s += " time=" + time + ",";
		s += " pace=" + pace + "]";
		return s;
	}

	/**
	 * Updates the Individual result based on if the Race's distance changes and the
	 * pace needs to be updated.
	 * 
	 * @param ob Observable object that observes the client
	 * @param o  Object being updated
	 */
	public void update(Observable ob, Object o) {
		Race newRace = (Race) o;
		this.race = newRace;
		// pace
		double racePace = time.getTimeInSeconds() / race.getDistance();
		int raceHrs;
		raceHrs = (int) racePace / 3600;
		int raceMin = (int) ((racePace / 60) % 60);
		int raceSec = (int) racePace - raceHrs * 3600 - raceMin * 60;
		this.pace = new RaceTime(raceHrs, raceMin, raceSec);
		// sets observable has changed to false;
		race.notifyObservers(this);
	}

}
