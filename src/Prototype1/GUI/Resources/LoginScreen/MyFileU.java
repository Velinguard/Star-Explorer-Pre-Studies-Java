package Prototype1.GUI.Resources.LoginScreen;
import Prototype1.MyFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
public class MyFileU extends MyFile {
    User[] user;
    int lines;
    boolean isAdd;
    
    //Constructor
    public MyFileU(String local) {
        super(local);
        lines = numberOfLines(local);
        isAdd = false;
        fileToArray(local);
    }
    //Writes the new User's details to the file.
    public void write(User newUser){
        isAdd = true;
        this.fileToArray(location);
        user[user.length - 1] = newUser;
        quickSort(0, user.length - 1);
        this.writeToFile(toStringForm(user.length - 1));
    }
    //Converts the array of users into the standard to be used by the super class.
    private ArrayList<String> toStringForm(int len){
        ArrayList<String> array = new ArrayList<String>();
        //For each user, converts there details into the array of strings.
        for (int i = 0; i <= len; i++){
            array.add(user[i].getUsername());
            array.add(user[i].getPassword());
            array.add(Integer.toString(user[i].getState()));
            array.add("-");
        }
        return array;
    }
    //Returns the data from within the file into an array of users.
    private void fileToArray(String file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
            int line = lines / 4;
            String temp[] = new String[3];
            //If we are adding a new user, we need to leave room for that user so the array needs to be one bigger.
            if (isAdd){
                user = new User[line + 1];
            } else {
                user = new User[line];
            }
            reader.readLine();
            //For each four lines in the file.
            for (int i = 0; i < line; i++){
                temp[0] = reader.readLine();
                temp[1] = reader.readLine();
                temp[2] = reader.readLine();
                reader.readLine();
                //Create a new user with details from the last 4 lines.
                user[i] = new User(temp[0], temp[1], Integer.parseInt(temp[2]));
            }
        } catch(Exception e){
            System.err.println("File not found");
        }
    }
    //Returns the number of lines within the file, if the file is not found then 0 is returned.
    private int numberOfLines(String file) {
        try{
            LineNumberReader  lnr = new LineNumberReader(new InputStreamReader(getClass().getResourceAsStream(file)));
            lnr.skip(Long.MAX_VALUE);
            int length = (lnr.getLineNumber() + 1); //Add 1 because line index starts at 0
            lnr.close();
            return length;
        }catch(Exception e){
            System.err.println("File not Found");
            return 0;
        }
    }
    //Implements binary search to search for the username, see documentation for explanation.
    public int searchForUsername(String name){
        name = name.toUpperCase();
        int low = 0;
        int high = lines / 4 - 1;
        int mid;
        while (low <= high){ //By using a while loop it means if it is an empty file than there will be no issue
            mid = low + high;
            mid = mid / 2;
            if ((int) name.charAt(0) > (int) user[mid].getUsername().charAt(0)){
                low = mid + 1;
            } else if ((int) name.charAt(0) < (int) user[mid].getUsername().charAt(0)) {
                high = mid - 1;
            } else {
                if (user[mid].getUsername().equals(name)){
                    return mid;
                }
                else {
                    int pass = 0;
                    do{
                        try {
                            if (user[mid - pass].getUsername().equals(name)){
                                return mid - pass;
                            } else if (user[mid + pass].getUsername().equals(name)) {
                                return mid + pass;
                            }
                            pass++;
                            if (pass >= lines - mid || pass > lines - (lines - mid)){
                                return -1;
                            }
                        } catch (Exception e){
                            return -1;
                        }
                    } while (true);
                }
            }
        }
        return -1;
    }
    //Sorts the array into alphabetical order using quick sort, see documentation for explanation.
    private void quickSort(int low, int high) {
		if (low >= high){
                    return;
                }
		// pick the pivot as being the middle value.
		int middle = low + (high - low) / 2;
		String pivot = user[middle].getUsername();
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
                    while (user[i].getUsername().compareTo(pivot) < 0) {
			i++;
                    }
                    while (user[j].getUsername().compareTo(pivot) > 0) {
                    	j--;
                    }
                    if (i <= j) {
                        User temp = user[i];
			user[i] = user[j];
			user[j] = temp;
			i++;
			j--;
                    }
		}
		// recursively sort two sub parts, this carries on untill each is sorted.
		if (low < j)
			quickSort(low, j);
 
		if (high > i)
			quickSort(i, high);
	}
}
