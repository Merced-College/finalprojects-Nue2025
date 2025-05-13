/**
 * Center.java
 * @author Nue Lopez
 * @version 2.0
 * @since April 21, 2025
 * Class: CPSC-39
 * Final Project: National Park Search Engine and Trivia Game!
 * Short Description: A Java program with a terminal-based search engine and trivia game.
 */
import java.io.IOException;
import java.util.ArrayList; //The Scanner library class is imported here and is used to read user input.
import java.util.Scanner;

public class Center {
	/**
	 * The main method begins the program by loading park data, displaying the main menu,
	 * and coordinating user interaction through different methods (e.g searching and trivia).
	 * @param args Command-line arguments (not used in this program).
	 * @throws IOException If there is an error reading the parks data file.
	 */
	public static void main(String[] args) throws IOException {		
		Scanner scnr = new Scanner(System.in); //Reads in user input.
        /**********************************************************************************************************************
		* Title: National Park List
		* Author: Jacob Stopak
		* Date: March 26, 2025
		* Code version: 1.0
		* Availability: https://www.downloadexcelfiles.com/us_en/download-excel-file-list-national-parks-us
		* 
		* Title: National Park List of Names and Descriptions (Edited from Jacob Stopak)
		* Author: Nue Lopez
		* Date: May 1, 2025
		* Code version: 3.0
		* Availability: https://docs.google.com/spreadsheets/d/1ziaFXhW1AH9-fRpfj2ejbEoWEulhMTGtHpQ8wXPhLpQ/edit?usp=sharing
		*********************************************************************************************************************/
		ArrayList<Database> parkList1 = DataHandler.loadDataFromFile("nationalParkList04232025.tsv"); //Loads the file from the folder.
		ParkSearch park1 = new ParkSearch(); //This creates a new ParkSearch class, which will be used later on. 
		TimeDelay time1 = new TimeDelay(); //Time Delay is created to slow down how much information is output to the console.
		int SECONDS = 1;

		//User is entering the National Park Center and will be prompted with a question.
		System.out.println("\t\tWelcome to the National Park Search and Trivia Game!");
		time1.pauseTime(SECONDS);
		
		boolean inCenter = true;
		String nextAction;
		while(inCenter) {
			boolean validActionSelected = false; //validActionSelected Works on if the user has entered a correct response 
			//to the question. Valid responses include a select variety of responses. If they do not enter any of the following, 
			//they will need to reenter a valid message. When this happens, the program will continue on.
			
			while(!validActionSelected) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.print("Do you want to search for a Park name, State name, or Play the Trivia Game? ");
				nextAction = scnr.nextLine();
				nextAction = nextAction.toLowerCase();
				if (nextAction.contains("park")) {	//If the user types a valid question, the validActionSelected will be true, 
					validActionSelected = true;		//meaning, they didn't type an invalid response.
					parkSearch(scnr, parkList1, park1, time1); //Calls parkSearch(Scanner scnr, ArrayList<Database> parkList1, ParkSearch park1, TimeDelay time1)
				}//End first if statement for park search
				
				else if (nextAction.contains("state")) {
					validActionSelected = true;
	                searchState(scnr, parkList1, time1);
				}//End else-if statement for state search.
				
				else if (nextAction.contains("play") || nextAction.contains("trivia") || nextAction.contains("game")){
					validActionSelected = true;
					time1.pauseTime(SECONDS * 2);
					int numberOfPoints = triviaGame(scnr, parkList1, park1, time1); //and stores the return value as an int.
					
					if (numberOfPoints == 0) { //Once numberOfPoints is retrieved from the return of triviaGame(), we go to this:
						System.out.println("You did awful. You got " + numberOfPoints + " points.");
						System.out.println("Try again next time...");
						time1.pauseTime(SECONDS);
					}else if(numberOfPoints == 1) {
						System.out.print("You won " + numberOfPoints + " point. ");
						time1.pauseTime(SECONDS);
						System.out.println("You could have done better.");
						time1.pauseTime(SECONDS);
					}else if (numberOfPoints == 2) {
						System.out.println("Great job! You won " + numberOfPoints + " points!");
						time1.pauseTime(SECONDS);
					}else if(numberOfPoints == 3) {
						System.out.println("FANTASTIC WORK! You got all 3 questions right! " + numberOfPoints + " points!");
						time1.pauseTime(SECONDS);
						System.out.println("Are you a genius?");
						time1.pauseTime(SECONDS);
					}//End else-if for the calculation of points and print statements.
				}//End else-if statement for the Trivia Search
				
				else if (nextAction.contains("exit") || nextAction.contains("nothing")) { //If the user doesn't know what to enter, it will accept these Strings.
					validActionSelected=true; //This remains true because they entered a correct string.
					System.out.println("Exit Selected...");
					time1.pauseTime(SECONDS);
				}
				/*else if(nextAction.contains("tourist")) {
					System.out.println("There are 63 National Parks in the United States. Here's the following: ");
				}*/
				else { //Assuming they didn't enter a validAction, it will remain false. They will stay in the center.
					inCenter=true;
					System.out.println("Unavailable option...");
				}
			}//Leave while !validActionSelected
			
			boolean questionToLeave = true;
			String leaveCenter;
			while(questionToLeave){
				System.out.print("Do you want to leave the center? Yes or no? ");
				leaveCenter = (scnr.nextLine()).toLowerCase();
				if (leaveCenter.equals("yes")) { 
					inCenter = false; 
					questionToLeave = false;
				}else if (leaveCenter.equals("no")) {
					System.out.println("Staying in center");
					questionToLeave = false;
				}else {
					System.out.println("Invalid option... Try again");
				}//End if/else statements
			}//End questionToLeave while loop

		}//You left the leaveCenter while-loop.
		System.out.print("\nGoodbye! ");
		time1.pauseTime(SECONDS);
		System.out.println("You left the center.");
		scnr.close(); //Closes the scnr object.
	}//End of main.
	
	/**
	 * This method handles park searching by name and allows user to request specific park details. It looks into the Database 
	 * class and captures the data the user wants. ParkSearch is passed into this function, allowing the ParkSearch result 
	 * to be stored in the ArrayList.
	 * @param scnr Scanner object for user input.
	 * @param parkList1 List of all parks loaded from the file.
	 * @param park1 ParkSearch object used for searching parks.
	 * @param time1 TimeDelay object to manage pause timing between messages.
	 */
	public static void parkSearch(Scanner scnr, ArrayList<Database> parkList1, ParkSearch park1, TimeDelay time1) {	
		int SECONDS = 1;
		System.out.println("Welcome to the park search! ");
		time1.pauseTime(SECONDS);
		
		System.out.print("What park do you want to search for?" + " Do NOT include \"National Park\" in your search. ");
		String stateResponse1 = scnr.nextLine();
		System.out.println("\nLooking for " + stateResponse1 + "...");
		time1.pauseTime(SECONDS * (2));
		
		if (park1.searchByParkName(parkList1, stateResponse1) == null) { System.out.println("Park not found"); }
		else { 
			System.out.println("Park found!");
			System.out.println("-----------");
			boolean action1 = false;
			String respondForInfo;
			while (!action1) {
				Database database1 = park1.searchByParkName(parkList1, stateResponse1);
				
				System.out.print("Do you want any information for " + database1.getParkName() +" National Park? Type \"No\" to exit ");
				respondForInfo = scnr.nextLine();
				if ((respondForInfo.toLowerCase()).contains("yes") || (respondForInfo.toLowerCase()).contains("yeah") || (respondForInfo.toLowerCase()).contains("ye")){
					System.out.println("Do you want to know what state it's in, coordinates, the date established"
							+ ", the area in miles/kh, visitors per year, the climate, or description?");
					String respondForData = scnr.nextLine();
					
					if ((respondForData.toLowerCase()).contains("state") || (respondForData.toLowerCase()).contains("location")) {
						System.out.println("State: " + database1.getStateName());
					}
					else if ((respondForData.toLowerCase()).contains("coordinate") || (respondForData.toLowerCase()).contains("direction")) {
						System.out.println("Coordinate (in Latitude and Longitude): " + database1.getCoordinateLocation());
						System.out.println("\tDo you know what Latitude and Longitude mean? Yes/No");
						String respondForDirectionInfo = scnr.nextLine();
						if (respondForDirectionInfo.toLowerCase().contains("yes") || respondForDirectionInfo.toLowerCase().contains("ya") || 
								respondForDirectionInfo.toLowerCase().contains("ye") || respondForDirectionInfo.toLowerCase().contains("yeah")) {
							//Nothing happens.
						}
						else { //Used AI to ensure the meaning is here:
							System.out.println("\t1. Degrees and minutes with cardinal directions (e.g., N, S, E, W).");
							System.out.println("\t2. Decimal degrees with cardinal directions.");
							System.out.println("\t3. Pure decimal degrees to precisely locate the park.");
						}
					}//End else-if statement.
					else if ((respondForData.toLowerCase()).contains("date") || (respondForData.toLowerCase()).contains("creat") || (respondForData.toLowerCase()).contains("establish")) { System.out.println("Date Established: " + database1.getDateEstablished()); }
					else if ((respondForData.toLowerCase()).contains("area") || (respondForData.toLowerCase()).contains("size")) { System.out.println("Area: " + database1.getArea2023()); }
					else if ((respondForData.toLowerCase()).contains("visitor") || (respondForData.toLowerCase()).contains("count")) { System.out.println("Visitor count is " + database1.getRecreationVisitors()); }
					else if ((respondForData.toLowerCase()).contains("climate") || (respondForData.toLowerCase()).contains("weather") || (respondForData.toLowerCase()).contains("temp")) { System.out.println("Climate: " + database1.getClimate()); }
					else if ((respondForData.toLowerCase()).contains("description")) { System.out.println("Description: " + database1.getDescription()); }
					else { 
						System.out.println("Invalid response for " + database1.getParkName() + "'s search. Try again."); 
					}
				}else if ((respondForInfo.toLowerCase()).contains("no") || (respondForInfo.toLowerCase()).contains("nah")){ 
					action1=true; 
					System.out.println("--------------------------------------------------------------------------");

					} //Leaves if the user doesn't want info.
				else { System.out.println("Invalid Response. Try again..."); }//End if/else statements with the else{.
			}//Ends the while-loop for Boolean: action1
		}//End else statement when the park is found.
	}//End parkSearch() class. Nothing is returned.
	
	/**
	 * The method, searchState(), searches for national parks by state based on user input. It then prompts the user to enter a 
	 * state name and then searches through the list of parks to find and display all parks located in that state. The search is 
	 * case-insensitive and displays relevant park information if any matches are found.
	 * @param scnr Scanner object for user input.
	 * @param parkList1 List of all parks loaded from the file.
	 * @param park1 A Park object used for temporary data access or comparison during the search.
	 * @param time1 TimeDelay object to manage pause timing between messages.
	 */
	public static void searchState(Scanner scnr, ArrayList<Database> parkList1, TimeDelay time1) {
		System.out.println("--------------------------------------------------------------------------");

        System.out.print("Welcome to the State search! ");	//Similar to parkSearch(). 
		System.out.print("What state are you looking for? ");
        String state1 = scnr.nextLine();
        
        // Search parks in the selected state
        ParkSearch park2 = new ParkSearch();
        park2.searchByStateName(parkList1, state1);
        
        boolean stateSearchDone = false;
        while (!stateSearchDone) {
            System.out.print("Do you want to do a park search within this state? ");
            String userInput1 = scnr.nextLine().toLowerCase();
            if (userInput1.contains("yes")) {
                System.out.println("\nYou will now search for a park within " + state1);
                parkSearch(scnr, parkList1, park2, time1); 
                stateSearchDone = true;
            } else if (userInput1.contains("no")) {
                System.out.println("\nReturning to the main menu.");
                stateSearchDone = true;
            } else {
                System.out.println("\nInvalid option. Try again.");
            }//End if/else statements
        }//Leave while loop.
	}//Leave function searchState(). Nothing is returned.
	
	/**
	 * This method runs an interactive trivia game where the user is asked three trivia questions about national parks. The game 
	 * selects parks and facts from the TSV file and prompts the user to answer trivia questions. It uses the ParkSearch 
	 * object to retrieve relevant data and the TimeDelay object to add pauses for a better user experience.
	 * @param scnr Scanner object for user input.
	 * @param parkList1 List of all parks loaded from the file.
	 * @param park1 ParkSearch object used for searching parks and for the trivia game.
	 * @param time1 TimeDelay object to manage pause timing between messages.
	 * @return numPoints returns an integer; how many trivia questions the user answered correctly.
	 */
	public static int triviaGame(Scanner scnr, ArrayList<Database> parkList1, ParkSearch park1, TimeDelay time1) {
		int SECONDS = 1;
		String triviaResponse;
		Database database2 = new Database();
		int numPoints = 0;
		
		//Gameplay!
		System.out.println("===========================");
		System.out.println("Welcome to the Trivia Game!"); 				//Starts the trivia game, 
		System.out.println("Game 1!");
		time1.pauseTime(SECONDS);
		//Loading the question.
		System.out.println("\tWhat *STATE* does the following climate belong to? ");
		time1.pauseTime(SECONDS * 3);
		database2 = park1.searchByParkName(parkList1, "Yosemite");
		System.out.println(database2.getClimate());
		triviaResponse = scnr.nextLine();
		if (database2.getStateName().contains(triviaResponse) || triviaResponse.contains(database2.getStateName())
				|| database2.getStateName().toLowerCase().contains(triviaResponse)
				/*database2.getStateName().equals(triviaResponse) || triviaResponse.equals(database2.getStateName())
				|| database2.getStateName().toLowerCase().equals(triviaResponse)*/ //Only if the user types in "/".
				){ 
			time1.pauseTime(SECONDS);
			System.out.println("Yes! You got it right! The state belongs to " + database2.getStateName());
			numPoints++;
		} else {
			System.out.print("You got it wrong.");
			time1.pauseTime(SECONDS * 2);
			System.out.println(" The correct state is " + database2.getStateName());
			System.out.println();
		}

		//Adding a break before second question
		time1.pauseTime(SECONDS * 3);

		System.out.println("Game 2!");
		database2 = park1.searchByParkName(parkList1, "Olympic");
		System.out.println("\tWhat state does this description belong to? ");
		time1.pauseTime(SECONDS * 3);
		System.out.println(database2.getDescription());
		triviaResponse = scnr.nextLine();
		if (database2.getStateName().contains(triviaResponse) || triviaResponse.contains(database2.getStateName())
				|| database2.getStateName().toLowerCase().contains(triviaResponse)){ 
			System.out.println("Yes! You got it right! The state belongs to " + database2.getStateName() + "!");
			numPoints++;
		} else {
			System.out.print("You got it wrong.");
			time1.pauseTime(SECONDS * 2);
			System.out.println(" The correct state is " + database2.getStateName());
		}
		
		//Adding a break before final question
		time1.pauseTime(SECONDS * 3);

		System.out.println("Game 3!");
		database2 = park1.searchByParkName(parkList1, "Voyageurs");
		System.out.println("The name of the park is " + database2.getParkName() + " National Park. It is located in the state of " + database2.getStateName() + ".");
		time1.pauseTime(SECONDS * 3);
		System.out.println("Description: " + database2.getDescription());
		time1.pauseTime(SECONDS * 3);
		System.out.println("\tWhen was " + database2.getParkName() + " National Park established?\nEnter response as Month ##, Year (January 01, 2000) ");
		triviaResponse = scnr.nextLine();
		if (database2.getDateEstablished().contains(triviaResponse) || triviaResponse.contains(database2.getDateEstablished())
				|| database2.getDateEstablished().toLowerCase().contains(triviaResponse)){ 
			System.out.println("Yes! You got it right! It was established " + database2.getDateEstablished());
			numPoints++;
		} else {
			System.out.print("You got it wrong.");
			time1.pauseTime(SECONDS);
			System.out.println(" The correct date is " + database2.getDateEstablished());
		}
		return numPoints;
	} //End triviaGame() with numPoints being returned. 

}//End of public class center.