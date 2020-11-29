import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
public class Flight {


    public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public FileReader fr;

    Seat seating;

    File flightFile;




    public Flight(String fileName) {
        seating = new Seat();
        flightFile = new File(fileName);

        for(int row = 0; row < seating.getFIRST_ROW(); row++){
            for(int col = 0; col < seating.getFIRST_COL(); col++){
                seating.getFirstClass()[row][col] = new Passenger();
                seating.getFirstClass()[row][col].setName(null);
                seating.getFirstClass()[row][col].setGroupName(null);
                seating.getFirstClass()[row][col].setGroupType(null);
                seating.getFirstClass()[row][col].setSeat(null);
                seating.getFirstClass()[row][col].setFClass(null);
            }
        }

        for(int row = 0; row < seating.getECON_ROW(); row++){
            for(int col = 0; col < seating.getECON_COL(); col++){
                seating.getEconClass()[row][col] = new Passenger();
                seating.getEconClass()[row][col].setName(null);
                seating.getEconClass()[row][col].setGroupName(null);
                seating.getEconClass()[row][col].setSeat(null);
                seating.getEconClass()[row][col].setGroupType(null);
                seating.getEconClass()[row][col].setFClass(null);
            }
        }

        //Read data from existing file
        if(flightFile.exists())
            getData(fileName);


    }
    public void menu(){


        char userChoice;
        try {
            System.out.println("\nAdd [P]assenger, Add [G]roup, "
                    + "[C]ancel Reservations, Print Seating [A]vailability Chart, "
                    + "Print [M]anifest, [Q]uit");
            System.out.printf("Choose an option: ");
            userChoice = br.readLine().charAt(0);

            boolean flag = true;
            while(flag){


                switch (userChoice) {
                    case 'P' :
                        addPassenger();
                        break;
                    case 'G':
                        addGroup();
                        break;
                    case 'C':
                        cancelReservation();
                        break;
                    case 'A':
                        printAvailability();
                        break;
                    case 'M':
                        printManifest();
                        break;
                    case 'Q':
                        saveFlight();
                        flag = false;
                        break;

                    default:
                        System.out.println("Invalid Option");
                        break;
                }
                System.out.println("\nAdd [P]assenger, Add [G]roup, "
                        + "[C]ancel Reservations, Print Seating [A]vailability Chart, "
                        + "Print [M]anifest, [Q]uit");
                System.out.printf("Choose an option: ");
                userChoice = br.readLine().charAt(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    /**
     * get data from an exist file
     * @param fileName gets the file
     */
    public void getData(String fileName){

        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        br = new BufferedReader(fr);

        //end of file check
        boolean eof = false;
        while(!eof){
            String dataLine = null;
            try {
                dataLine = br.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(dataLine == null)  eof = true;
            else{
                String[] data = dataLine.split(", ");

                if(data[1].charAt(0) == 'I'){
                    seating.fileToArrayI(data[0], data[1].charAt(0), data[2]);
                }else{
                    seating.fileToArrayG(data[0], data[1].charAt(0), data[2], data[3]);
                }
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * prompt instruction to console to interact with user's input
     * get proper information to add passenger
     */
    public void addPassenger(){

        String name=null;
        String serviceClass=null;
        String seatPreference=null;
        String seatNum= null;

        try {

            System.out.printf("Name: ");
            name = br.readLine();


            System.out.printf("Service Class: ");
            serviceClass = br.readLine();

            System.out.print("Seat Preference ([W]indow, [C]enter, [A]isle): ");
            seatPreference = br.readLine();

        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }


        // First CLass
        if(serviceClass.equalsIgnoreCase("first")){
            seatNum = seating.searchSeatFirstI(seatPreference, name,serviceClass);
            System.out.println(seatNum);
            //Economy class
        }else if (serviceClass.equalsIgnoreCase("economy")){
            seatNum = seating.searchSeatEconI(seatPreference, name, serviceClass);
            System.out.println(seatNum);
        }

        System.out.printf("You seat is %s\n",seatNum);

    }

    /**
     * prompt instruction to console to interact with user's input
     * get proper information to add passengers as a group
     */
    public void addGroup(){

        String groupName;
        String[] membNames;
        String serviceClass;

        try {
            System.out.printf("Group Name: ");
            groupName = br.readLine();

            System.out.printf("Names: ");
            membNames = br.readLine().split(",");

            System.out.printf("Service Class: ");
            serviceClass = br.readLine();

            String[] seats = new String[membNames.length];
            //First Class
            if(serviceClass.equalsIgnoreCase("first")){
                if(seating.isFullFirst()){
                    System.out.println("No more seat available.");
                    return;
                }else if(seating.isFullFirst(membNames.length)){
                    System.out.println("Not enough seats for all passengers.");
                    return;
                }else{
                    seats = seating.searchSeatFirstG(groupName, membNames.length, membNames, seats, serviceClass);
                    if(seats != null){
                        System.out.println("Your reserved seats are:");
                        for(int i = 0; i < membNames.length; i++){
                            System.out.println(membNames[i].trim() + ": " + seats[i]);
                        }
                    }
                }
                //Economy Class
            }else if(serviceClass.equalsIgnoreCase("Economy")){
                if(seating.isFullEcon()){
                    System.out.println("No more seat available.");
                    return;
                }else if(seating.isFullFirst(membNames.length)){
                    System.out.println("Not enough seats to accomodate all passengers.");
                    return;
                }else{
                    seats = seating.searchSeatEconG(groupName, membNames.length,membNames, seats,serviceClass);
                    if(seats != null){
                        System.out.println("Your reserved seats are:");
                        for(int count = 0; count < membNames.length; count++){
                            System.out.println(membNames[count].trim() + ": " + seats[count]);
                        }
                    }
                }
            }

        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    /**
     * prompt instruction to console to interact with user's input
     * get proper information to cancel reservation
     */
    public void cancelReservation(){

        String inOrGr;
        String name;
        String groupName;
        boolean success = false;

        try {
            System.out.printf("Cancel [I]ndividual or [G]roup? ");
            inOrGr = br.readLine();

            if(inOrGr.equalsIgnoreCase("I")){
                System.out.printf("Name: ");
                name = br.readLine();
                success = seating.cancelInd(name);
            }else {
                System.out.println("Group Name: ");
                groupName = br.readLine();
                success = seating.cancelGr(groupName);
            }


        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }

        if(success){
            System.out.println("Cancelation Successfully");
        }else{
            System.out.println("Could not find the name");
        }

    }


    /**
     * prompt instruction to console to interact with user's input
     * get proper information to show available seats
     */
    public void printAvailability(){


        try {
            System.out.printf("Service Class (First or Economy): ");
            String serviceClass = br.readLine();
            seating.printAvai(serviceClass);

        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }



    }

    /**
     * prompt instruction to console to interact with user's input
     * get proper information to show taken show passengers' names
     * with their seats
     */
    public void printManifest(){

        try {
            System.out.printf("Service Class (First or Economy): ");
            String serviceClass = br.readLine();
            seating.printManifes(serviceClass);

        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }



    }

    /**
     * save all the data to file
     */
    public void saveFlight(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(flightFile));
            writer.write("First 1-2, Left: A-B, Right: C-D; Economy 10-29, Left: A-C, Right: D-F");
            writer.newLine();

          
            //pArrayFirst Print
            for(int rowF = 0; rowF < seating.getFIRST_ROW(); rowF++){
                for(int colF = 0; colF < seating.getFIRST_COL(); colF++){
                    if(seating.getFirstClass()[rowF][colF].getName() != null){
                        char column = (char)(colF + 1 + 64);
                        String s1 = Integer.toString(rowF + 1);
                        String s2 = Character.toString(column);
                        String s3 = s1.concat(s2);
                        writer.write(s3);
                        writer.write(", ");
                        if(seating.getFirstClass()[rowF][colF].getGroupName() != null){
                            writer.write("G, ");
                            writer.write(seating.getFirstClass()[rowF][colF].getGroupName());
                            writer.write(", ");
                            writer.write(seating.getFirstClass()[rowF][colF].getName());
                        }else{
                            writer.write("I, ");
                            writer.write(seating.getFirstClass()[rowF][colF].getName());
                        }
                        writer.newLine();
                    }
                }
            }
            //pArrayFirst Print
            for(int rowE = 0; rowE < seating.getECON_ROW(); rowE++){
                for(int colE = 0; colE < seating.getECON_COL(); colE++){
                    if(seating.getEconClass()[rowE][colE].getName() != null){
                        char column = (char)(colE + 1 + 64);
                        String s1 = Integer.toString(rowE + 10);
                        String s2 = Character.toString(column);
                        String s3 = s1.concat(s2);
                        writer.write(s3);
                        writer.write(", ");
                        if(seating.getEconClass()[rowE][colE].getGroupName() != null){
                            writer.write("G, ");
                            writer.write(seating.getEconClass()[rowE][colE].getGroupName());
                            writer.write(", ");
                            writer.write(seating.getEconClass()[rowE][colE].getName());
                        }else{
                            writer.write("I, ");
                            writer.write(seating.getEconClass()[rowE][colE].getName());
                        }
                        writer.newLine();
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error reading the output file to write");
            e.printStackTrace();
        }
    }


}