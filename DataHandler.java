import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {
	
	public static ArrayList<Database> loadDataFromFile(String csvFileName) { //We will have to return something along the lines of an ArrayList
		ArrayList<Database> parkList = new ArrayList<>();
		
		try (FileInputStream fis = new FileInputStream(csvFileName); 
			Scanner scnr = new Scanner(fis)) {
			
			scnr.nextLine(); //This reads the first line because the header row is something we do not need
			
			while (scnr.hasNextLine()) {
			    String line = scnr.nextLine();
//			    Used AI to help me separate the commas. It kept losing data specific to the tables, so it recommended
//			    using a TSV (tab separated values) instead of a CSV (comma separated values).
				String[] parts = line.split("\t", -1); // \t looks for any tabs (approx 4 spaces or 1 tab) and -1 keeps empty fields
				
				if (parts.length < 8){ continue; } //Skips the incomplete lines
				
				String parkName = parts[0].trim();
				String stateName = parts[1].trim();
				String coordinates = parts[2].trim();
				String dateEstablished = parts[3].trim();
				String area = parts[4].trim();
				
				int visitors = 0;
				try {
                    visitors = Integer.parseInt(parts[5].trim().replaceAll("[^\\d]", "")); 
//                    Used AI to help me understand how to separate the parts. It was difficult to figure out how to 
//                    trimp them.
				} catch (NumberFormatException e) {}// If parsing fails, visitors stays as 0

				String climate = parts[6].trim();
				String description = parts[7].trim();
				
				Database park = new Database(parkName, stateName, coordinates, dateEstablished, area, visitors, climate, description);
				parkList.add(park);
			}
			scnr.close();
			fis.close();
		}
		catch (IOException e) {
			System.out.println("Error with loading file: " + e.getMessage());
		}
		    
		return parkList;//End try
	}
}