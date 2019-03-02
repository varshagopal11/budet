package edu.ncsu.csc216.wolf_results.race_results;

// NEED TO DO OBSERVABLE STUFF 
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.wolf_results.util.ArrayList;

/**
 * RaceList class holds the list of races in the WolfResults program. It has the
 * ability to add a race, remove a race, edit a race's properties, and update it
 * as well.
 * 
 * @author Varsha Gopal
 * 
 * @author Abigail Ormond
 */
public class RaceList extends Observable implements Observer {

	private ArrayList races;

	/**
	 * Constructor for the RaceList class that calls the other methods and creates a
	 * list of races within it
	 */
	public RaceList() {
		races = new ArrayList();
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Adds a race given the Race object
	 * 
	 * @param r Race object to be added to the RaceList
	 * @throws IllegalArgumentException if race is null
	 */
	public void addRace(Race r) {
		if (r == null) {
			throw new IllegalArgumentException("Race is null");
		}
//		Race r2 = new Race(r.getName(), r.getDistance(), r.getDate(), r.getLocation());
//		races.add(r2);
		
		/*
		 * adding the Observer to an Observable. Since the Observer(racelist) constructs
		 * the instance of the Observable(race), after construction the
		 * Observer(racelist) adds itself to the Observable
		 */

		// ADD OBSERVER(RACELIST) TO OBSERVABLE(RACE)
		r.addObserver(this);
		// NOTIFY OBSERVERS
		setChanged();
		notifyObservers(this);
		races.add(r);

	}

	/**
	 * Adds a race with the properties of a Race, the String name of the race, the
	 * distance, the date and the location
	 * 
	 * @param name     name of the race to be added
	 * @param distance distance of race to be added
	 * @param date     date of race to be added
	 * @param location location of race to be added
	 */
	public void addRace(String name, double distance, LocalDate date, String location) {

		try {
			Race r = new Race(name, distance, date, location);
			
			// ADD OBSERVER(RACELIST) TO OBSERVABLE(RACE)
			r.addObserver(this);
			// NOTIFY OBSERVERS
			setChanged();
			notifyObservers(this);
			races.add(r);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to construct Race");
		}

	}

	/**
	 * Removes a race object from the list at a given index
	 * 
	 * @param index location for removing from the list
	 */
	public void removeRace(int index) {
		races.remove(index);
		// NOTIFY OBSERVERS
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the specific race object at an index in the list
	 * 
	 * @param index integer value for the index to get from the list
	 * @return Race object from the race list
	 * @throws IndexOutOfBoundsException if index is out of bounds
	 */
	public Race getRace(int index) {
		if (index < 0 || index >= races.size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		Race race = (Race) races.get(index);
		return race;
	}

	/**
	 * Returns the size of the race list
	 * 
	 * @return integer value of race list size
	 */
	public int size() {
		return races.size();
	}

	/**
	 * Updates the race information if necessary
	 * 
	 * @param ob observable object that observes the Race object
	 * @param o  object to be observed and updated
	 */
	public void update(Observable ob, Object o) {
	
		/*
		 * method is called if the Race that the RaceList is observing notified its
		 * observers of a change. If the Observable is an instance of Race, then the
		 * observers of the RaceList should be updated. The current instance is passed
		 * to notifyObservers().
		 */
		if(ob instanceof Race) {
			notifyObservers(this);
		}
		

		
	}
}
