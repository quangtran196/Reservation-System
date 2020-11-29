import java.util.Arrays;

/**
 * Seat represents all seats in the plane 
 * the class shows how many seats are there in the class and 
 * how many seats have been reserved
 * @author Duc Tran
 *
 */
public class Seat {
	
	public static char[] alphabet = {'A','B','C','D','E','F'};
	public static String alpToCol = "ABCDEF";
	
	//First class has 8 seats: 2 rows, 4 columns each
	//Economy class has 20 rows, 6 columns each
	private  int FIRST_ROW = 2;
	private  int FIRST_COL = 4;
	private  int FIRST_TOTAL = 8;
	private  int ECON_ROW = 20;
	private  int ECON_COL = 6;
	private  int ECON_TOTAL = 120;
	
	private Passenger[][] firstClass = new Passenger[FIRST_ROW][FIRST_COL];
	private Passenger[][] econClass = new Passenger[ECON_ROW][ECON_COL];
	
	
	private int firstCurNum = 0;
	private int econCurNum = 0;
	
	/**
	 * to get values from first class array
	 * @return first class's values
	 */
	public Passenger[][] getFirstClass(){
		return firstClass;
	}
	
	/**
	 * to get values from economy class array
	 * @return economy class's values
	 */
	public Passenger[][] getEconClass(){
		return econClass;
	}
	
	/**
	 * get number of seat row in the first class
	 * @return seat row number in the first class
	 */
	public  int getFIRST_ROW() {
		return FIRST_ROW;
	}
	
	/**
	 * to get seat columns (A,B,C,D) in the first class
	 * @return seat columns (A,B,C,D) in the first class
	 */
	public  int getFIRST_COL() {
		return FIRST_COL;
	}
	
	/**
	 * to get number of row in economy class
	 * @return seat row number in the first class
	 */
	public  int getECON_ROW() {
		return ECON_ROW;
	}
	
	/**
	 * to get seat columns (A,B,C,D,E,F) in the economy class
	 * @return seat columns (A,B,C,D,E,F) in the economy class
	 */
	public  int getECON_COL() {
		return ECON_COL;
	}
	
	/**
	 * to add more passenger to reserved list of first class
	 * @param f number of passenger added to the list of first class
	 */
	public void setFirstNumPass (int f) {
		this.firstCurNum += f;
		
	}
	
	/**
	 * to keep track how many passenger already reserved in first class
	 * @return number of passengers in the list of first class
	 */
	public int getFirstNumPass() {
		return this.firstCurNum;
	}
	
	/**
	 * to add more passenger to reserved list of economy class
	 * @param e number of passenger added to the list of economy class
	 */
	public void setEconNumPass (int e) {
		this.econCurNum += e;
		
	}
	
	/**
	 * to keep track how many passenger already reserved in economy class
	 * @return number of passengers in the list of economy class
	 */
	public int getEconNumPass() {
		return this.econCurNum;
	}
	
	/**
	 * to check if the list is full or not in first class
	 * @return Yes if full, no if not full
	 */
	public boolean isFullFirst() {
		return firstCurNum >= FIRST_TOTAL;
	}
	
	/**
	 * to determine if there's enough available seat to add passengers in first class
	 * @param addSeat number of passengers need to add
	 * @return Yes if can add more, no if cannot add more
	 */
	public boolean isFullFirst( int addSeat) {
		return firstCurNum + addSeat > FIRST_TOTAL;
	}
	
	/**
	 * to check if the list is full or not in Economy class
	 * @return Yes if full, no if not full
	 */
	public boolean isFullEcon() {
		return econCurNum >= ECON_TOTAL;
	}
	
	/**
	 * to determine if there's enough available seat to add passengers in economy class
	 * @param addSeat number of passengers need to add
	 * @return Yes if can add more, no if cannot add more
	 */
	public boolean isFullEcon( int addSeat) {
		return econCurNum + addSeat > ECON_TOTAL;
	}
	
	/**
	 * Get information from an exist file for individuals
	 * @param seatNum gets the seat number of the passenger
	 * @param groupType gets whether the passenger going in group or going alone
	 * @param passName gets passenger name
	 */
	public void fileToArrayI(String seatNum, char groupType, String passName){
		
		int row = Integer.parseInt(seatNum.substring(0,seatNum.length() - 1));
        char col = seatNum.charAt(seatNum.length() - 1);
		//First Class
		if(row <= 2){
			
			firstClass[row-1][alpToCol.indexOf(col)].setName(passName);
			firstClass[row-1][alpToCol.indexOf(col)].setSeat(seatNum);
			firstClass[row-1][alpToCol.indexOf(col)].setFClass("First");
			firstClass[row-1][alpToCol.indexOf(col)].setGroupType("I");
			firstClass[row-1][alpToCol.indexOf(col)].setGroupName(null);
			setFirstNumPass(1);

		//Economy Class
		}else if (row >= 10){
			econClass[row-10][alpToCol.indexOf(col)].setName(passName);
			econClass[row-10][alpToCol.indexOf(col)].setSeat(seatNum);
			econClass[row-10][alpToCol.indexOf(col)].setFClass("Economy");
			econClass[row-10][alpToCol.indexOf(col)].setGroupType("G");
			econClass[row-10][alpToCol.indexOf(col)].setGroupName(null);
			setEconNumPass(1);
		}			
	}
	
	
	/**
	 * Get information from an exist file for groups
	 * @param seatNum gets the seat passenger's seat number
	 * @param groupType gets passenger's whether going with a group or not
	 * @param groupName gets passenger's group name
	 * @param passName gets passenger's name
	 */
	public void fileToArrayG(String seatNum, char groupType, String groupName, String passName){
			
		int row = Integer.parseInt(seatNum.substring(0,seatNum.length() - 1));
	    char col = seatNum.charAt(seatNum.length() - 1);
		//First Class
		if(row <= 2){
			
			firstClass[row-1][alpToCol.indexOf(col)].setName(passName);
			firstClass[row-1][alpToCol.indexOf(col)].setSeat(seatNum);
			firstClass[row-1][alpToCol.indexOf(col)].setFClass("First");
			firstClass[row-1][alpToCol.indexOf(col)].setGroupType("I");
			firstClass[row-1][alpToCol.indexOf(col)].setGroupName(groupName);
			setFirstNumPass(1);
	
		//Economy Class
		}else if(row >= 10){
			econClass[row-10][alpToCol.indexOf(col)].setName(passName);
			econClass[row-10][alpToCol.indexOf(col)].setSeat(seatNum);
			econClass[row-10][alpToCol.indexOf(col)].setFClass("Economy");
			econClass[row-10][alpToCol.indexOf(col)].setGroupType("G");
			econClass[row-10][alpToCol.indexOf(col)].setGroupName(groupName);
			setEconNumPass(1);
		}			
	}
	
	/**
	* Looking for available seats.
	* @param seatPreference gets passenger's desired seat position (Window, center or aisle)
	* @param name gets passenger's name
	* @param serviceClass gets class service
	* @return gives passenger's seat
	*/
	
	public String searchSeatFirstI(String seatPreference, String name, String serviceClass){
			
			if(seatPreference.equalsIgnoreCase("w")){
				for(int row = 0; row < FIRST_ROW; row++){
					//Window A is at column 0
					if(firstClass[row][0].getName() == null){	
						return setSeatFirstI(row,0,name,serviceClass);
						
					//Window D is at column 3
					}else if(firstClass[row][3].getName() == null){	
						return setSeatFirstI(row,3,name,serviceClass);
					}	
				}
			}else{
				for(int row = 0; row < FIRST_ROW; row++){
					//Aisle B is at column 1
					if(firstClass[row][1].getName() == null){	
						return setSeatFirstI(row,1,name,serviceClass);
										
					//Aisle C is at column 2
					}else if(firstClass[row][2].getName() == null){	
						return setSeatFirstI(row,2,name,serviceClass);
					}	
				}
			}	
			return "None";	
		}
	
	/**
	 * Reserve seat for passenger in first class
	 * @param row gets row number of the seat (1 to 2)
	 * @param col gets column of the seat (A to D)
	 * @param name gets passenger's name
	 * @param serviceClass gets class service
	 * @return gives passenger's seat
	 */
	public String setSeatFirstI(int row, int col, String name, String serviceClass){
		
		firstClass[row][col].setName(name);
		firstClass[row][col].setFClass(serviceClass);
		String seatNum = Integer.toString(row+1).concat(Character.toString(alphabet[col]));
		firstClass[row][col].setSeat(seatNum);
		firstClass[row][col].setGroupType("I");
		setFirstNumPass(1);
		return seatNum;
	}
	
	
	
	/**
	 * Looking for available seat for passenger in economy class
	 * @param seatPreference gets passenger's desired seat position (Window, center or aisle)
	 * @param name gets passenger's name
	 * @param serviceClass gets class service
	 * @return gives passenger's seat
	 */
	public String searchSeatEconI(String seatPreference, String name, String serviceClass){
		if(seatPreference.equalsIgnoreCase("w")){
			for(int row = 0; row < FIRST_ROW; row++){
				//Window A is at column 0
				if(econClass[row][0].getName() == null){	
					return setSeatEconI(row,0,name,serviceClass);
				//Window D is at column 3
				}else if(econClass[row][5].getName() == null){						
					return setSeatEconI(row,5,name,serviceClass);
				}	
			}
		}else if(seatPreference.toUpperCase().equals("C")){
			for(int row = 0; row < FIRST_ROW; row++){
				//Center B is at column 1
				if(econClass[row][1].getName() == null){	
					return setSeatEconI(row,1,name,serviceClass);
				//Center E is at column 4
				}else if(econClass[row][4].getName() == null){		
					return setSeatEconI(row,4,name,serviceClass);
				}	
			}
		}else if(seatPreference.toUpperCase().equals("A")){
			for(int row = 0; row < FIRST_ROW; row++){
				//Aisle C is at column 2
				if(econClass[row][2].getName() == null){	
					return setSeatEconI(row,2,name,serviceClass);
				//Aisle D is at column 3
				}else if(econClass[row][3].getName() == null){		
					return setSeatEconI(row,3,name,serviceClass);
				}	
			}
		}		
		return "None";	
	}
	
	
	/**
	 * Reserve seat for passenger in economy class
	 * @param row gets row number of the seat (10 to 29)
	 * @param col gets column of the seat (A to F)
	 * @param name gets passenger's name
	 * @param serviceClass gets class service
	 * @return gives passenger's seat
	 */
	public String setSeatEconI(int row, int col, String name, String serviceClass){
		
		econClass[row][0].setName(name);
		econClass[row][0].setFClass(serviceClass);
		String seatNum = Integer.toString(row+10).concat(Character.toString(alphabet[col]));
		econClass[row][0].setSeat(seatNum);
		econClass[row][0].setGroupType("I");
		setEconNumPass(1);
		return seatNum;
	}

	
	/**
	 * Looking for seat for a group in first class
	 * @param groupName gets group name
	 * @param membNum get number of members in the group
	 * @param names gets members' names in an array
	 * @param seats empty array to store seats of the group
	 * @param serviceClass gets service class
	 * @return gives a list of seats for group
	 */
	public String[] searchSeatFirstG(String groupName,int membNum, String[] names, String[] seats, String serviceClass){
		/*	find the largest seat which can fit enough people in a row
		 */
		int numSeats = membNum; //number of seat needed for all member in the group
		int total;
		int largestAdjacent;//seat can find in the row
		int rowLargest; //row has the most seat
		int countName = 0;	//Counter for members of the group	
		
		while (numSeats > 0) {
			largestAdjacent = 0;
			rowLargest = 0;
			for(int row = 0; row < FIRST_ROW; row++){
				total = 0;
				for(int col = 0; col < FIRST_COL; col++){
					if(firstClass[row][col].getName() == null){
						total++;
					}	
				}
				if(total > largestAdjacent){
					largestAdjacent = total;
					rowLargest = row;
				}
			}
			
			for(int col = 0; col < FIRST_COL; col++){
				if(firstClass[rowLargest][col].getName() == null && countName < names.length){
					seats[countName] = setSeatFirstG(rowLargest, col, names[countName].trim(), groupName, serviceClass);
					countName++;
				}
			}
			//Find the remain members who hasn't been seated
			numSeats -= largestAdjacent;
		}

	return seats;	
	}
	
	
	/**
	 * Looking for seat for a group in economy class
	 * @param groupName gets group name
	 * @param membNum get number of members in the group
	 * @param names gets members' names in an array
	 * @param seats empty array to store seats of the group
	 * @param serviceClass gets service class
	 * @return gives a list of seats for group
	 */
	public String[] searchSeatEconG(String groupName,int membNum, String[] names, String[] seats, String serviceClass){
		/*	find the largest seat which can fit enough people in a row
		 */
		int numSeats = membNum; //number of seat needed for all member in the group
		int total;
		int largestAdjacent;//seat can find in the row
		int rowLargest; //row has the most seat
		int countName = 0;	//Counter for members of the group	
		
		while (numSeats > 0) {
			largestAdjacent = 0;
			rowLargest = 0;
			for(int row = 0; row < ECON_ROW; row++){
				total = 0;
				for(int col = 0; col < ECON_COL; col++){
					if(econClass[row][col].getName() == null){
						total++;
					}	
				}
				if(total > largestAdjacent){
					largestAdjacent = total;
					rowLargest = row;
				}
			}
			
			for(int col = 0; col < ECON_COL; col++){
				if(econClass[rowLargest][col].getName() == null && countName < names.length){
					seats[countName] = setSeatEconG(rowLargest, col, names[countName].trim(), groupName, serviceClass);
					countName++;
				}
			}
			//Find the remain members who hasn't been seated
			numSeats -= largestAdjacent;
		}
	return seats;	
	}
	
	/**
	 * Reserve seat for all passengers in first class
	 * @param row gets row number of the seat (10 to 29)
	 * @param col gets column of the seat (A to F)
	 * @param passName gets passenger's name
	 * @param groupName gets group's name
	 * @param serviceClass gets class service
	 * @return gives passenger's seat
	 */
	public String setSeatFirstG(int row, int col, String passName, String groupName, String serviceClass){
		
		
		firstClass[row][col].setName(passName);
		firstClass[row][col].setGroupName(groupName);
		firstClass[row][col].setFClass(serviceClass);
		String seatNum = Integer.toString(row+1).concat(Character.toString(alphabet[col]));
		firstClass[row][col].setSeat(seatNum);
		firstClass[row][col].setGroupType("G");
		setFirstNumPass(1);
		
		return seatNum;
	}
		
	/**
	 * Reserve seat for all passengers in economy class
	 * @param row gets row number of the seat (10 to 29)
	 * @param col gets column of the seat (A to F)
	 * @param passName gets passenger's name
	 * @param groupName gets group's name
	 * @param serviceClass gets class service
	 * @return gives passenger's seat
	 */
	public String setSeatEconG(int row, int col, String passName, String groupName, String serviceClass){
	
		econClass[row][col].setName(passName);
		econClass[row][col].setGroupName(groupName);
		econClass[row][col].setFClass(serviceClass);
		String seatNum = Integer.toString(row+10).concat(Character.toString(alphabet[col]));
		econClass[row][col].setSeat(seatNum);
		econClass[row][col].setGroupType("G");
		setEconNumPass(1);
		
		return seatNum;
	}
	

	/**
	 * Cancel reservation for passenger
	 * @param name gets passenger's name
	 * @return to inform whether the reservation cancel successfully or not
	 * 
	 */
	public boolean cancelInd(String name){
		boolean flag = false;
		//First Class
		for(int row = 0; row < FIRST_ROW; row++){
			for(int col = 0; col < FIRST_COL; col++){
				if(firstClass[row][col].getName().equalsIgnoreCase(name)){
					firstClass[row][col].setName(null);
					firstClass[row][col].setGroupName(null);
					firstClass[row][col].setSeat(null);
					firstClass[row][col].setFClass(null);
					firstClass[row][col].setGroupType(null);
					setFirstNumPass(-1);
					flag = true;
				}	
			}
		}
		
		//Economy class
		for(int row = 0; row < ECON_ROW; row++){
			for(int col = 0; col < ECON_COL; col++){	
				if(econClass[row][col].getName().equalsIgnoreCase(name)){
					econClass[row][col].setName(null);
					econClass[row][col].setGroupName(null);
					econClass[row][col].setSeat(null);
					econClass[row][col].setFClass(null);
					econClass[row][col].setGroupType(null);
					setEconNumPass(-1);
					flag = true;
				}	
			}
		}
		return flag;
	}
	
	
	/**
	 * Cancel reservation for a group of passenger
	 * @param groupName gets group name
	 * @return to inform whether the reservation cancel successfully or not
	 */
	public boolean cancelGr(String groupName){
		boolean flag = false;
		//Search in First Class
		for(int row = 0; row < FIRST_ROW; row++){
			for(int col = 0; col < FIRST_COL; col++){	
				if(firstClass[row][col].getGroupName().equalsIgnoreCase(groupName)){
					firstClass[row][col].setName(null);
					firstClass[row][col].setGroupName(null);
					firstClass[row][col].setSeat(null);
					firstClass[row][col].setGroupType(null);
					flag = true;
					setFirstNumPass(-1);			
				}	
			}
		}
		//Search in Economy Class
		for(int row = 0; row < ECON_ROW; row++){
			for(int col = 0; col < ECON_COL; col++){			
				if(econClass[row][col].getGroupName().equalsIgnoreCase(groupName)){
					econClass[row][col].setName(null);
					econClass[row][col].setGroupName(null);
					econClass[row][col].setSeat(null);
					econClass[row][col].setGroupType(null);
					flag = true;
					setEconNumPass(-1);
				}
			}
		}	
		return flag;
	}
	
	
	/**
	 * To determine which class user want to get information for available seats
	 * @param serviceClass gets chosen class service
	 */
	public void printAvai(String serviceClass) {
		if (serviceClass.equalsIgnoreCase("first"))
			printFirst();
		else
			printEcon();
	}
	
	
	/**
	 * print all available seats in first class
	 */
	public void printFirst(){
		char[] seatCol = new char[4];

		System.out.println("First");
		for(int row = 0; row < FIRST_ROW; row++){
			for(int col = 0; col < FIRST_COL; col++){				
				if(firstClass[row][col].getName() == null){
					seatCol[col] = alphabet[col];
				}
			}
			System.out.printf("%d: %s\n", row + 1, 
					Arrays.toString(seatCol).replace("[","").replace("]",""));
		}		
	}
	
	
	/**
	 * Print all available seats in economy class
	 */
	public void printEcon(){
		char[] seatCol = new char[6];

		System.out.println("First");
		for(int row = 0; row < ECON_ROW; row++){
			for(int col = 0; col < ECON_COL; col++){				
				if(econClass[row][col].getName() == null){
					seatCol[col] = alphabet[col];
				}
			}
			System.out.printf("%d: %s\n", row + 10, 
					Arrays.toString(seatCol).replace("[","").replace("]",""));
		}			
	}
	
	/**
	 * To determine which class user want to get information of taken seats
	 * @param serviceClass
	 */
	public void printManifes(String serviceClass) {
		if (serviceClass.equalsIgnoreCase("first")) {
			printManFirst();
		}
		else {
			printManEcon();
		}
	}
	
	
	/**
	 * print all the seats which were taken and the passengers' names who took those seats
	 * in the first class
	 */
	public void printManFirst(){
		System.out.println("First");

		for(int row = 0; row < FIRST_ROW; row++){
			for(int col = 0; col < FIRST_ROW; col++){		
				if(firstClass[row][col].getName() != null){			
					System.out.printf("%s: %s\n", firstClass[row][col].getSeat(),
							firstClass[row][col].getName());
				}
			}
		}		
	}

	/**
	 * print all the seats which were taken and the passengers' names who took those seats
	 * in the economy class
	 */
	public void printManEcon(){
		System.out.println("Economy");

		for(int row = 0; row < ECON_ROW; row++){
			for(int col = 0; col < ECON_COL; col++){		
				if(econClass[row][col].getName() != null){			
					System.out.printf("%s: %s\n", econClass[row][col].getSeat(),
							econClass[row][col].getName());
				}
			}
		}		
	}
	

}
