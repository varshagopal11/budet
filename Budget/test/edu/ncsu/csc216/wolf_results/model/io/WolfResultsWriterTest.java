// how to check files expected v actual?

package edu.ncsu.csc216.wolf_results.model.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.wolf_results.race_results.IndividualResult;
import edu.ncsu.csc216.wolf_results.race_results.Race;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;
import edu.ncsu.csc216.wolf_results.util.RaceTime;
/**
 * Tests the WolfResultsWriter class that writes a file based on the race lists
 * @author varsha
 *
 */
public class WolfResultsWriterTest {
	/**
	 * Tests the writeRaceFile method by creating a RaceList, writing ti to a file and
	 * Checks to see if two files are the same. File checker taken from PackScheduler's
	 * CourseRecordIOTest checkFiles method (provided in implementation instructions
	 * on GitHub)
	 *
	 */
	@Test
	public void testWriteRaceFile() {
		String name = "Krispy Kreme Challenge";
		Double dist = 5.0;
		LocalDate ld = LocalDate.parse("2018-02-18");
		String location = "Raleigh, NC";

		Race r = new Race(name, dist, ld, location);
		String pName = "BILLY FETZNER";
		int age = 24;
		RaceTime time = new RaceTime(0, 11, 51);
		IndividualResult ir = new IndividualResult(r, pName, age, time);

		String pName2 = "STEPHEN HENKEL";
		int age2 = 21;
		RaceTime time2 = new RaceTime(0, 12, 02);
		IndividualResult ir2 = new IndividualResult(r, pName2, age2, time2);

		r.addIndividualResult(ir);
		r.addIndividualResult(ir2);

		RaceList rl = new RaceList();
		rl.addRace(r);
		
		String expFile = "test-files/wolf_results_expected.md";
		String actFile = "test-files/wolf_results_actual.md";

		WolfResultsWriter.writeRaceFile(actFile, rl);
		
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading files.");
		}
	}


}
