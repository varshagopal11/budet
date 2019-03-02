// idk what to put in catch

package edu.ncsu.csc216.wolf_results.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
 * WolfResultsWriter class creates a file to hold the results from the
 * WolfResults GUI
 * 
 * @author Varsha Gopal
 * @author Abigail Ormond
 */
public class WolfResultsWriter {

	/**
	 * Method that writes a race file for the results
	 * 
	 * @param fileName String filename
	 * @param rl       RaceList to be turned into a file
	 */
	public static void writeRaceFile(String fileName, RaceList rl) {
		PrintStream fileWriter = null;
		try {
			fileWriter = new PrintStream(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Can't create file"); // e.printStackTrace();
		}
		Race r;
		for (int i = 0; i < rl.size(); i++) {
			r = rl.getRace(i);
			fileWriter.println("# " + r.getName());
			fileWriter.println(r.getDistance());
			fileWriter.println(r.getDate());
			fileWriter.println(r.getLocation());
			for (int j = 0; j < r.getResults().size(); j++) {
				fileWriter.print("\n* ");
				fileWriter.print(r.getResults().getResult(j).getName() + ",");
				fileWriter.print(r.getResults().getResult(j).getAge() + ",");
				fileWriter.print(r.getResults().getResult(j).getTime());
			}
		}
		fileWriter.close();

	}
}
