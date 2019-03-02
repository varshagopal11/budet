package edu.ncsu.csc216.wolf_results.manager;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.wolf_results.model.io.WolfResultsReader;
import edu.ncsu.csc216.wolf_results.model.io.WolfResultsWriter;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;

/**
 * WolfResultsManager class oversees the RaceList and Race functions and works
 * with the GUI. It loads a file, saves a file, changes filename, and updates
 * using the Observable Pattern
 * 
 * @author Varsha Gopal
 * @author Abigail Ormond
 * 
 */
public class WolfResultsManager extends Observable implements Observer {
	/** String filename for the name of the file that data is being read from */
	private String filename;
	/**
	 * Boolean object that determines if a file has been changed/needs to be saved
	 */
	private boolean changed;
	/** List of races to be assigned from file */
	private RaceList list;
	/** Instance of WolfResultsManager */
	private static WolfResultsManager instance;

	/**
	 * Constructor that sets the private fields of filename and changed and
	 * initializes a WolfResultsManager object
	 */
	private WolfResultsManager() {

		list = new RaceList();
		list.addObserver(this);

	}

	/**
	 * Gets an instance of the WolfResultsManager class
	 * 
	 * @return WolfResultsManager instance
	 */
	public static WolfResultsManager getInstance() {
		if (instance == null) {
			instance = new WolfResultsManager();
		}
		return instance;
	}

	/**
	 * Creates a new list for the races
	 */
	public void newList() {
		list = new RaceList();
		list.addObserver(this);
		setChanged(false);
		setChanged();
		notifyObservers(this);

	}

	/**
	 * Checks if the file is changed and returns true or false
	 * 
	 * @return true or false if file isChanged or not
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Changes the isChanged variable and sets a change
	 * 
	 * @param isChanged boolean variable that determines change or not
	 */
	private void setChanged(boolean isChanged) {
		this.changed = isChanged;
	}

	/**
	 * Returns the filename of the file being used by the WolfResultsManager
	 * 
	 * @return String name of file
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename if a user is saving a file as a specific name
	 * 
	 * @param file String file name to be changed
	 */
	public void setFilename(String file) {

		if (file == null || file.trim().length() == 0) {
			throw new IllegalArgumentException("Filename incorrect");
		}

		this.filename = file.trim();
	}

	/**
	 * Loads a file using the filename string passed in
	 * 
	 * @param file String filename to be loaded
	 */
	public void loadFile(String file) {
		setFilename(file);
		// WolfResultsReader r = new WolfResultsReader();
		list = WolfResultsReader.readRaceListFile(this.filename);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Saves a file using the filename string passed in
	 * 
	 * @param file String filename to be saved
	 */
	public void saveFile(String file) {
		// WolfResultsWriter w = new WolfResultsWriter();
		WolfResultsWriter.writeRaceFile(file, this.list);

	}

	/**
	 * Gets the list of races from the file information
	 * 
	 * @return RaceList list of races
	 */
	public RaceList getRaceList() {
		return list;
	}

	/**
	 * Updates the RaceList
	 * 
	 * @param ob Observable object
	 * @param o  Object to be updated
	 */
	public void update(Observable ob, Object o) {
		// If a RaceList observed by WolfResultsManager changes, the update() method is
		// automatically called. WolfResultsManager should propagate the notification of
		// the change to its Observers and changed should be updated to true.
		this.list = (RaceList) o;
		setChanged(true);
		setChanged();
		notifyObservers(this);

	}

}