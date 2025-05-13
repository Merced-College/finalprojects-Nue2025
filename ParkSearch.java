/**
 * ParkSearch.java
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParkSearch {
	/**
	 * Searches for a national park by its name using the binary search algorithm.
	 * @param inputParkData a List of Database objects containing all the park data.
	 * @param keyParkName a String representing the name of the park to search for.
	 * @return a Database object corresponding to the park with the given name, or null if not found.
	 */
	public Database searchByParkName(List<Database> inputParkData, String keyParkName) { // Assuming the list is already in order.																				// sorted by park.
		// Binary search ALGORITHM
		int left = 0;
		int right = inputParkData.size() - 1;

		while (left <= right) {
			int middle = (right + left) / 2;
			String middleName = inputParkData.get(middle).getParkName(); // Looking for the item in the middle.
			int comparison = middleName.compareToIgnoreCase(keyParkName);

			if (comparison < 0) { // Comparison is LESS THAN (<) zero, then
				left = middle + 1;
			} else if (comparison > 0) {
				right = middle - 1;
			} else {
				return inputParkData.get(middle);
			}
		} // end while loop
		return null;
	}// End searchByParkName class.

	/**
	 * Searches for national parks by state name and returns a LINKED LIST of parks that match the given state.
	 * It will print the names of the parks and introduce a 1-second delay between each output.
	 * @param inputParkData an ArrayList of Database objects containing all the park data.
	 * @param keyState a String representing the state to search for.
	 * @return a LinkedList of Database objects representing the parks located in the specified state.
	 */
	public LinkedList<Database> searchByStateName(ArrayList<Database> inputParkData, String keyState) {
		TimeDelay time1 = new TimeDelay(); 
	    LinkedList<Database> result = new LinkedList<>();
		int SECONDS = 1;
	    
	    for (Database park:inputParkData) { //Creates a new Database called Park, which calls getParkName in the if-statement.
	    	//We're putting the following if statement's comparisons to lower-case so we don't have any accidental mistakes.
	        if (park.getStateName().toLowerCase().contains(keyState.toLowerCase())) { 
	            result.add(park); //Stores each park name in its state to a *Linked List*.
		        System.out.println("- " + park.getParkName());
		        time1.pauseTime(SECONDS); 	//Will delay the response by 1 second for every park in the state. 
		        							//Tries to replicate an actual load.
	        }//End if-statement
	    }//End for-loop
	    
	    if (result.isEmpty()) {
	    	System.out.println("No park is found in " + keyState);
	    }
	    
	    return result; //Will return a Linked List called result.
	}//End searchByStateName().
	
	/**
	 * Sorts a list of national parks by state name using the merge sort algorithm.
	 * This method is RECURSIVE and divides the input list into smaller sublists for sorting.
	 * @param parkArrayList a List of Database objects containing the parks to be sorted.
	 * @return a List of Database objects sorted by state name.
	 */
	public static List<Database> mergeSortByState(List<Database> parkArrayList){
		if (parkArrayList.size() <= 1) {
			return parkArrayList;
		}
	    int mid = parkArrayList.size() / 2;
	    List<Database> left = mergeSortByState(parkArrayList.subList(0, mid));
	    List<Database> right = mergeSortByState(parkArrayList.subList(mid, parkArrayList.size()));

	    return merge(left, right);
	}//end mergeSortByState()
		
	/**
	 * Merges two sorted lists of parks by state name into a single sorted list.
	 * This method is used as part of the merge sort algorithm to combine the two halves of the list.
	 * @param left a List of Database objects representing the left sorted sublist.
	 * @param right a List of Database objects representing the right sorted sublist.
	 * @return a List of Database objects containing all elements from both sublists, sorted by state name.
	 */
	private static List<Database> merge(List<Database> left, List<Database> right) {
	    List<Database> merged = new ArrayList<>();
	    int i = 0, j = 0;
	    while (i < left.size() && j < right.size()) {
	        String leftState = left.get(i).getStateName();
	        String rightState = right.get(j).getStateName();

	        if (leftState.compareToIgnoreCase(rightState) <= 0) {
	            merged.add(left.get(i));
	            i++;
	        } else {
	            merged.add(right.get(j));
	            j++;
	        }
	    }
	    while (i < left.size()) {
	        merged.add(left.get(i));
	        i++;
	    }
	    while (j < right.size()) {
	        merged.add(right.get(j));
	        j++;
	    }
	    return merged;
	}//End merge
}//End ParkSearch class.