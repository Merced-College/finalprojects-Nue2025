/**
 * Center.java
 * @author Nue Lopez
 * CPSC-39
 * @since April 22, 2025
 * Final Project: National Park Search Engine and Trivia Game!
 * Short Description: A Java program with a terminal-based search engine
 * and trivia game.
 * As of 4/29/2025, I have built a simple TUI (Text User Interface) to 
 * accomodate the user with searching a particular National Park, 
 * looking for a state, and implementing an interactive game. 
 * I need to create more classes and write code to help the user access
 * more data of the park. Visitor count, climate, location (in Latitude
 * and longitude), and other facts about the national park.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Center {
	public static void main(String[] args) throws IOException {
		//Loads the file from the eclipse folder
		ArrayList<Database> parkList1 = DataHandler.loadDataFromFile("nationalParkList04232025.tsv"); 
		// Database database1 = new Database(); //Not in use so I commented out!
		Scanner scnr = new Scanner(System.in);
		
		boolean inCenter = true;
		String nextAction;
		while(inCenter) {
			System.out.print("Do you want to look for a park name, state name, or something else? ");
			nextAction = scnr.nextLine();
			if (nextAction.contains("park")) {
				System.out.println("Entering park!");
				
				System.out.println();
				System.out.println("Welcome to the park search!");
				System.out.println("What park do you want to search for? ");
				String stateResponse1 = scnr.nextLine();
				System.out.println("Looking for " + stateResponse1);
				
				//Print out the park if found. Else print an error if the response is null.
			}//End first if statement
			else if (nextAction.contains("state")) {
				System.out.println("Entering state search soon...");
				//Not completed yet, however, the plan is to 
				//allow the user to look for a park by the state name. 
				//It will iterate through a while loop with a condition.
				//WHILE usersStateInput is equal to the current state name
				//after doing the search, it will continue to look at the 
				//next line until it's false. Then, it will print it out
				//to the user!
				//We DO NOT need a return since it doesn't need to be held
				//any where.
			}//End else-if statement.
			else if (nextAction.contains("game play")){
				System.out.println("Trivia game coming soon!");
				//Not sure how to make the trivia game, but it will hopefully
				//be a really cool game that gives the user a description 
				//of the park and they have to figure out what it is.
				//Probably have to add another file with removed information
				//and a whole other system to figure out how many
				//points the users gets.
			}//End else-if statement.
			else {
				System.out.println("Unavailable option...");
			}//End else statement.
			

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
		System.out.println("\nYou left the center.");

		scnr.close(); //Closes scnr object.
	}//End of main.

}//End of public class center.
