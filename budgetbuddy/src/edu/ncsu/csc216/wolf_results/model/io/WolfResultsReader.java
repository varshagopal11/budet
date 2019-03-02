
package edu.ncsu.csc216.wolf_results.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_results.race_results.IndividualResult;
import edu.ncsu.csc216.wolf_results.race_results.Race;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;
import edu.ncsu.csc216.wolf_results.util.RaceTime;

/**
 * Reads Results from a file to put them in the wolf results reader
 * 
 * @author Varsha Gopal
 * @author Abigail Ormond
 */
public class WolfResultsReader {

	/**
	 * Takes in a fileName and reads that file to get the information. Takes that
	 * information and creates a RaceList object with the list of races from that
	 * file
	 * 
	 * @param fileName String file holding data
	 * @return RaceList object to be used by the program
	 * @throws IllegalArgumentException on any error or incorrect formatting.
	 */
	public static RaceList readRaceListFile(String fileName) {
		RaceList raceList = new RaceList();
		String raceName;
		Double raceDist;
		String raceDate;
		String raceLocation;
		String runnerName;
		int runnerAge;
		String runnerTime;
		Race r;
		IndividualResult ir;

//		# CITY OF OAKS MARATHON 2017
//		26.2
//		2017-11-06
//		Raleigh, NC
//
//		* MICHAEL DIXON,34,2:37:02
//		* IVAYLO BENOV,42,2:41:00
//		* MAX HOFMANN,20,2:52:13
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Can't find file");
		}
		while (fileScanner.hasNextLine()) {
//			String raceChunk = fileScanner.next();
//			Scanner raceScanner = new Scanner(raceChunk);
			raceName = fileScanner.nextLine().replace("#", "").trim(); // when a new race is found go ahead and
//			if (!fileScanner.hasNextDouble()) { // assign the race name dist date & location
//				throw new IllegalArgumentException("Invalid distance in file");
//			}
			try {
				raceDist = Double.parseDouble(fileScanner.nextLine());
			} catch (Exception e) {
				fileScanner.close();
				throw new IllegalArgumentException("Bad distance");
			}
			// raceScanner.nextLine();
			LocalDate ld;
			raceDate = fileScanner.nextLine();
			try {
				ld = LocalDate.parse(raceDate);
			} catch (Exception e) {
				fileScanner.close();
				throw new IllegalArgumentException("Not a date");
			}
			if (!fileScanner.hasNextLine()) {
				fileScanner.close();
				throw new IllegalArgumentException("No location");
			}
			raceLocation = fileScanner.nextLine();
			r = new Race(raceName, raceDist, ld, raceLocation);
			if (fileScanner.hasNextLine()) {
				String temp = fileScanner.nextLine();
				if (temp.length() > 0) {
					fileScanner.close();
					throw new IllegalArgumentException("Incorrect formatting");
				}
			}

			while (fileScanner.hasNextLine()) { // each iteration of loop represents ONE runner

				String runnerChunk = fileScanner.nextLine();
				if (runnerChunk.trim().length() == 0) {
					break;
				}
				Scanner runnerScanner = new Scanner(runnerChunk);
				String star = runnerScanner.next();
				if (!star.equals("*")) {
					fileScanner.close();
					runnerScanner.close();
					throw new IllegalArgumentException("Incorrect formatting (*)");
				}
				runnerScanner.useDelimiter(",");

				try {
					runnerName = runnerScanner.next().replace("*", "").trim();
					runnerAge = 0;
					try {
						runnerAge = runnerScanner.nextInt();
					} catch (Exception e) {
						runnerScanner.close();
						fileScanner.close();
						throw new IllegalArgumentException("Invalid age");
					}
					runnerTime = runnerScanner.next();

				} catch (NoSuchElementException e) {
					fileScanner.close();
					runnerScanner.close();
					throw new IllegalArgumentException("Invalid file");
				}
				RaceTime rt = new RaceTime(runnerTime);

				ir = new IndividualResult(r, runnerName, runnerAge, rt);
				r.addIndividualResult(ir);
				runnerScanner.close();
			}
			raceList.addRace(r);
		}
		fileScanner.close();
		return raceList;
	}

}
