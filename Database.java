/**
 * Database.java
 */
public class Database {
	//Private variables
	private String parkName, stateName, coordinateLocation, dateEstablished, area2023;
	private int recreationVisitors;
	private String climate, description;
	
	//Constructor | The constructor sets them to unknown values because we haven't read them yet.
	public Database() {
		parkName = "Unknown";
		stateName = "Unknown";
		coordinateLocation = "Unknown";
		dateEstablished = "Unknown";
		area2023 = "Unknown";
		recreationVisitors = -1;
		climate = "Unknown";
		description = "Unknown";
	}//End Database() constructor

	/**
	 * Constructs a Database object containing all information about a national park.
	 * @param parkNameInput Name of the national park as a String parameter.
	 * @param stateNameInput U.S. state(s) where the park is located as a String parameter.
	 * @param coordinateLocationInput GPS coordinates of the park in decimal degrees as a String parameter.
	 * @param dateEstablishedInput Date the park was officially established as a String parameter.
	 * @param area2023Input Area of the park in 2023 (e.g., in acres or square miles) as a String parameter.
	 * @param recreationVisitorsInput Number of recreational visitors the park had as an int parameter.
	 * @param climateInput Climate type of the park (e.g., desert, alpine, coastal) as a String parameter.
	 * @param descriptionInput Brief description of the park's features or significance as a String parameter.
	 */
	public Database(String parkNameInput, String stateNameInput, String coordinateLocationInput, String dateEstablishedInput,
			String area2023Input, int recreationVisitorsInput, String climateInput, String descriptionInput) {
		parkName = parkNameInput;
		stateName = stateNameInput;
		coordinateLocation = coordinateLocationInput;
		dateEstablished = dateEstablishedInput;
		area2023 = area2023Input;
		recreationVisitors = recreationVisitorsInput;
		climate = climateInput;
		description = descriptionInput;
	}

	/**
	 * This is the setter (mutator) section for the data being read, respectively to the variables names.
	 * If the argument/parameter's variable name includes the word "Input", it means that this is the variable being passed 
	 * in, all with respect to the park. To make this class light, it is reduced to one line of code. It becomes tedious 
	 * and there are ways to read the code with ease. 
	 * @param parkNameInput a String parameter is setting the park name.
	 * @param stateNameInput a String parameter is setting the state name.
	 * @param coordinateLocationInput a String parameter is setting the coordinate location (latitude and longitude) and other data with respect.
	 * @param dateEstablishedInput a String parameter is setting the date established of the park.
	 * @param area2023Input a String parameter is setting the park's area in both miles and kilometers.
	 * @param recreationVisitorsInput an int (integer) parameter is setting the visitor count as of 2022.
	 * @param climateInput a String parameter is setting the climate of the park.
	 * @param descriptionInput a String parameter is setting a description of the park.
	 */
	public void setParkName(String parkNameInput) { parkName = parkNameInput; }
	public void setStateName(String stateNameInput) { stateName = stateNameInput; }
	public void setCoordinateLocation(String coordinateLocationInput) { coordinateLocation = coordinateLocationInput; }
	public void setDateEstablished(String dateEstablishedInput) { dateEstablished = dateEstablishedInput; }
	public void setArea2023(String area2023Input) { area2023 = area2023Input; }
	public void setRecreationVisitors(int recreationVisitorsInput) { recreationVisitors = recreationVisitorsInput; }
	public void setClimate(String climateInput) { climate = climateInput; }
	public void setDescription(String descriptionInput) { description = descriptionInput; }	
	
	/**
	 * Getter (accessor) section for the data. 
	 * All return statements here are Strings, except for recreationVisitors.
	 * @return parkName returns the park name.
	 * @return stateName returns the state name.
	 * @return coordinateLocation returns the coordinate location (latitude and longitude) and other data with respect.
	 * @return dateEstablished returns the date established of the park.
	 * @return area2023 returns the area in miles and kilometers of the park.
	 * @return recreationVisitors returns the visitor count of the park as of 2022.
	 * @return climate returns the climate of the park.
	 * @return description returns the description of the park
	 */
	public String getParkName() { return parkName; }
	public String getStateName() { return stateName; }
	public String getCoordinateLocation() { return coordinateLocation; }
	public String getDateEstablished() { return dateEstablished; }
	public String getArea2023() { return area2023; }
	public int getRecreationVisitors() { return recreationVisitors; }
	public String getClimate() { return climate; }
	public String getDescription() { return description; }	
	
	@Override 
	/**
	 * Needs override because Java doesn't know how to make this into a string. Java is considering/making it as 
	 * generic object info. In doing this, we are showing we are doing it our own way. Otherwise, Java uses a 
	 * default which incorporates an '@' symbol. 
	 * USED AI in this section to explain how @Override works and why my information came out as addresses (GIBBERISH@NUMBERS).
	 */
	public String toString() {
		return "Park name: " + parkName + ", State: " + stateName + ", Location: " + coordinateLocation
				+ ", Established: " + dateEstablished + ", Area: " + area2023 + ", Visitors: " + 
				recreationVisitors + ", Climate: " + climate + "\nDescription of " + parkName + ": "+  description + "\n";
	}
}//End public class Database{
