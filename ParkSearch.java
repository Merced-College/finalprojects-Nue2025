/**
 * ParkSearch.java
 */

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class ParkSearch {
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
	
	public LinkedList<Database> searchByStateName(ArrayList<Database> inputParkData, String keyState) {
	    LinkedList<Database> result = new LinkedList<>();
	    
	    for (Database park:inputParkData) { //Creates a new Database called Park, which calls getParkName in the if-statement.
	    	//We're putting the following if statement's comparisons to lower-case so we don't have any accidental mistakes.
	        if (park.getStateName().toLowerCase().contains(keyState.toLowerCase())) { 
	            result.add(park); //Stores each park name in its state to a Linked List.
		        System.out.println("- " + park.getParkName());
	        }//End if-statement
	    }//End for-loop
	    
	    if (result.isEmpty()) {
	    	System.out.println("No park is found in " + keyState);
	    }
	    
	    return result; //Will return a Linked List called result.
	}//End searchByStateName().
	
	// RECURSIVE merge sort on park data by state name
	public static List<Database> mergeSortByState(List<Database> parkArrayList){
		if (parkArrayList.size() <= 1) {
			return parkArrayList;
		}
	    int mid = parkArrayList.size() / 2;
	    List<Database> left = mergeSortByState(parkArrayList.subList(0, mid));
	    List<Database> right = mergeSortByState(parkArrayList.subList(mid, parkArrayList.size()));

	    return merge(left, right);
	}//end mergeSortByState()
		
	// Merges two sorted lists of parks by state name
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
}