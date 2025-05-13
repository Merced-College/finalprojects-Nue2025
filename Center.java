/**
 * Center.java
 * @author Nue Lopez <email>
 * @version 1.3
 * @since April 22, 2025
 * Class: CPSC-39
 * Final Project: National Park Search Engine and Trivia Game!
 * Short Description: A Java program with a terminal-based search engine
 * and trivia game.
 * As of 5/10/2025, ___________________
 */
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Center {
	public static void parkSearch() {
	//Not in use. Use later for calling parkSearch in other if/else statements.	
	}
	
	public static void main(String[] args) throws IOException {		
		//Loads the file from the eclipse folder
		Scanner scnr = new Scanner(System.in); //The Scanner library class is imported here and is used to read user input.
		ArrayList<Database> parkList1 = DataHandler.loadDataFromFile("nationalParkList04232025.tsv"); 
		ParkSearch park1 = new ParkSearch(); //This calls a new park search class. 
		
		//Loading time delay so it doesn't overwhelm the user. It will be used in other classes later on so the screen
		//isn't overwhelming.
		TimeDelay time1 = new TimeDelay(); 
		int SECONDS = 1;

//		For testing purposes, there are variables:
		String spaceTab = "    "; //Uses 4 [space] characters. Can be used for recursion cases, however it is not needed.
		String spaceDash = "--------------------------------------------------------------------";
		String notFinished = "Not Finished"; //notFinished tells the user that a section of the code has not been completed YET.
		//Once it is completed, it will be removed from print statements.

		//Welcome to the game screen
		System.out.println("Welcome to the National Park Search and Trivia Game*!");
		System.out.println(spaceTab + "*trivia game coming soon.");
		time1.pauseTime(SECONDS);
		
		/**
		 * Logic and programming starts here. 
		 * This section is where the user will enter the National Park Center and will be prompted with a question.
		 */
		boolean inCenter = true;
		String nextAction;
		while(inCenter) {
			boolean validActionSelected = false;
			/**
			 * validActionSelected Works on if the user has entered a correct response to the prompt/question. 
			 * Valid responses include their statement containing "park", "state", "play" or "game" or "trivia", or 
			 * if they want to "exit" or "leave". If they do not enter any of the following, they will be requested to
			 * reenter a valid message. In doing so, the program will continue on.
			 */
			while(!validActionSelected) {
					System.out.printf("%s%n", spaceDash);
				System.out.print("Do you want to look for a park name, state name, or something else? ");
				nextAction = scnr.nextLine();
				nextAction = nextAction.toLowerCase();
				if (nextAction.contains("park")) {
					validActionSelected=true;
					System.out.println("Entering park!");
					System.out.println();
					
					time1.pauseTime(SECONDS);

					System.out.print("Welcome to the park search! ");
					System.out.print("What park do you want to search for? Note, it must be the name of the park"
							+ ". Do NOT include \"National Park\" in your search.");
					System.out.println();
					String stateResponse1 = scnr.nextLine();
					System.out.println("Looking for " + stateResponse1 + "...");
					time1.pauseTime(SECONDS * (2));
					if (park1.searchByParkName(parkList1, stateResponse1) == null) {
						System.out.println("Park not found");
					}else {
						System.out.println("Park found!");
						
						boolean action1 = false;
						String response1;
						while (!action1) {
							Database database1 = park1.searchByParkName(parkList1, stateResponse1);
							
							System.out.println("Do you want any information for " + database1.getParkName() +" National Park? Type \"No\" to exit");
							response1 = scnr.nextLine();
							if ((response1.toLowerCase()).contains("yes") || (response1.toLowerCase()).contains("yeah") || (response1.toLowerCase()).contains("ye")) {
								System.out.println("Do you want to know what state it's in, coordinates, the date established"
										+ ", the area in miles/kh, visitors per year, the climate, or description?");
								String response2 = scnr.nextLine();
								
								if ((response2.toLowerCase()).contains("state") || (response2.toLowerCase()).contains("location")) {
									System.out.println("State: " + database1.getStateName());
								}
								else if ((response2.toLowerCase()).contains("coordinate")) {
									System.out.println("Coordinate (in Latitude and Longitude): " + database1.getCoordinateLocation());
									System.out.println(spaceTab + "Do you know what Latitude and Longitude mean? Yes/No");
									String response3 = scnr.nextLine();
									if (response3.toLowerCase().contains("yes") || response3.toLowerCase().contains("ya") || 
											response3.toLowerCase().contains("ye") || response3.toLowerCase().contains("yeah")) {
									}
									else {
										System.out.print("The first part of those coordinates mean the degrees and minutes with cardinal direction. ");
										System.out.print("The second section are the decimal degrees with cardinal direction. ");
										System.out.print("The last bit are the decimal degrees, which help you determine the ");
										System.out.println("latitude and longitude.");
									}
								}
								else if ((response2.toLowerCase()).contains("date")) {
									System.out.println("Date Established: " + database1.getDateEstablished());
								}
								else if ((response2.toLowerCase()).contains("area")) {
									System.out.println("Area: " + database1.getArea2023());
								}
								else if ((response2.toLowerCase()).contains("visitor") || (response2.toLowerCase()).contains("count")) {
									System.out.println("Visitor count for the year " + database1.getRecreationVisitors());
								}
								else if ((response2.toLowerCase()).contains("climate") || (response2.toLowerCase()).contains("weather")
										|| (response2.toLowerCase()).contains("temperature")) {
									System.out.println("Climate: " + database1.getClimate());

								}
								else if ((response2.toLowerCase()).contains("description")) {
									System.out.println("Description: " + database1.getDescription());

								}
								else {
									System.out.println("Invalid response for " + database1.getParkName() + "'s search. Try again.");
								}
								
							}else if ((response1.toLowerCase()).contains("no") || (response1.toLowerCase()).contains("nah")){
								action1=true;
							}else {
								System.out.println("Invalid Response. Try again...");
							}//End if/else statements with the else{.
							
						}//Ends the while-loop for Boolean: action1
					}//End else statement when the park is found.
				}//End first if statement
				
				else if (nextAction.contains("state")) {
					validActionSelected = true;
					System.out.println("State search:");
					System.out.println("What state are you looking for? ");
					String state1 = scnr.nextLine();
					
					ParkSearch park2 = new ParkSearch();
					
					park2.searchByStateName(parkList1, state1);
					
					/* Not completed yet, however, the plan is to				COMPLETED
					 * allow the user to look for a park by the state name. 	COMPLETED
					 * It will iterate through a while loop with a condition.	COMPLETED
					 * WHILE usersStateInput is equal to the current state name	COMPLETED
					 * after doing the search, it will continue to look at the 	COMPLETED
					 * next line until it's false. Then, it will print it out	COMPLETED
					 * to the user!												COMPLETED
					 * We DO NOT need a return since it doesn't need to be held				CONSIDER
					 * any where.															CONSIDER
					 */
				}//End else-if statement for state search.
				
				else if (nextAction.contains("play") || nextAction.contains("game") || nextAction.contains("trivia")
						||nextAction.contains("something else")){
					validActionSelected = true;
					System.out.println("Trivia game coming soon!");
					String triviaResponse;
						
					System.out.print("Welcome to the Trivia Game**!");
					System.out.println(" **in development.");

					Database database2 = new Database();
					database2 = park1.searchByParkName(parkList1, "Yosemite");
					
					System.out.println(database2.getClimate());
					System.out.println("What *STATE* does this description belong to? ");

					triviaResponse = scnr.nextLine();
					
					if (database2.getStateName().contains(triviaResponse) || triviaResponse.contains(database2.getStateName())
							|| database2.getStateName().toLowerCase().contains(triviaResponse)
							/*database2.getStateName().equals(triviaResponse) || triviaResponse.equals(database2.getStateName())
							|| database2.getStateName().toLowerCase().equals(triviaResponse)*/
							){ 
						System.out.println("Yes! You got it right! The state belongs to " + database2.getStateName());
					}
					else {
						System.out.println("You got it wrong! The correct state is " + database2.getStateName());
					}
					
					//Not sure how to make the trivia game, but it will hopefully
					//be a really cool game that gives the user a description 
					//of the park and they have to figure out what it is.
					//Probably have to add another file with removed information
					//and a whole other system to figure out how many
					//points the users gets.
				}//End else-if statement for the trivia search

				else if (nextAction.contains("exit") || nextAction.contains("nothing")) {
					validActionSelected=true;
					System.out.println("Exit Selected...");
					time1.pauseTime(SECONDS);
				}
				else {
					inCenter=true;
					System.out.println("Unavailable option...");
				}
			}
			
			boolean qToLeave = true;
			String leaveCenter;
			while(qToLeave) {
				System.out.print("Do you want to leave the center? Yes or no? ");
				leaveCenter = (scnr.nextLine()).toLowerCase();
				if (leaveCenter.equals("yes")) { 
					inCenter = false; 
					qToLeave = false;
				}else if (leaveCenter.equals("no")) {
					System.out.println("Staying in center");
					qToLeave = false;
				}else {
					System.out.println("Invalid option... Try again");
				}
			}//End qToLeave while loop
			
		}//Left leaveCenter while loop
		System.out.print("\nGoodbye! ");
		time1.pauseTime(SECONDS);
		System.out.println("You left the center.");

		scnr.close(); //Closes the Scanner scnr object.
		
	}//End of main.
}//End of public class center.
