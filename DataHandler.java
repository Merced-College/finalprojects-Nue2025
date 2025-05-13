/**
 * DataHandler.java
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {

	/**
	 * Loads national park data from a CSV file and stores each row as a Database object in an ArrayList.
	 * @param csvFileName a String representing the name or path of the CSV file to load park data from.
	 * @return parkList an ArrayList of Database objects representing all the parks loaded from the file.
	 */
	public static ArrayList<Database> loadDataFromFile(String tsvFileName) { //We will have to return something 
		//																		along the lines of an ArrayList
		ArrayList<Database> parkList = new ArrayList<>();
		try {
			//Note: GitHub acts weird; they want the FileInputStream on the same line as "try{". 
			//Note: Check the GitHub link for more questions.
			FileInputStream fis = new FileInputStream(tsvFileName);
			Scanner fileScnr = new Scanner(fis);
			
			fileScnr.nextLine(); //This reads the first line because the header row is something we do not need.
			
			while (fileScnr.hasNextLine()) {
			    String line = fileScnr.nextLine();
//			    Used AI to help me separate the commas. It kept losing data specific to the tables, so it recommended
//			    using a TSV (tab separated values) instead of a CSV (comma separated values).
				String[] parts = line.split("\t", -1); // \t looks for tabs. -1 keeps empty fields.
				
				if (parts.length < 8) continue; //Skips the incomplete lines
				
				String parkName = parts[0].trim();
				String stateName = parts[1].trim();
				String coordinates = parts[2].trim();
				String dateEstablished = parts[3].trim();
				String area = parts[4].trim();
				
				int visitors = 0;
				try {
                    visitors = Integer.parseInt(parts[5].trim().replaceAll("[^\\d]", "")); 
//                    Used AI to help me understand how to separate the parts. It was difficult to figure out how to 
//                    trim the data.
				} catch (NumberFormatException e) {}//If parsing fails, visitors stays as 0

				String climate = parts[6].trim();
				String description = parts[7].trim();
				
				//Sets a park Database class to the line read.
				Database park = new Database(parkName, stateName, coordinates, dateEstablished, area, visitors, climate, description);
				parkList.add(park);
			}
			fileScnr.close();
			fis.close();
		}
		catch (IOException e) { //If the file does not exist, it will print out an error message.
			System.out.println("Error with loading file: " + e.getMessage());
			return null;
		}
		return parkList;
	}//End loadDataFromFile().
}//End DataHandler class.