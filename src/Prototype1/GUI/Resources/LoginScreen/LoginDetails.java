package Prototype1.GUI.Resources.LoginScreen;

public class LoginDetails {
    final String location = "textFiles\\PasswordScreen.txt";
    MyFileU f;
    //The different states the user can be.
    final int GENERAL_USER = 0;
    final int SCIENTIST = 1;
    final int ADMIN = 2;
    
    //Constructor
    public LoginDetails(){
        f = new MyFileU(location);
    }
    //Returns true if the user is logged in, and false if not.
    public boolean login (String username, String password){
        /*If we encrypt the entered password then it means that the password never needs to 
        be decrypted therefore making it more secure.*/
        password = encrypt(password);
        //Returns where the user is in the array, if the user is found, if not then index = -1.
        int index = f.searchForUsername(username);
        //If the user is not found then -1 is returned, and therefore the details are incorrect.
        if (index != -1){
            //Returns true if the passwords match.
            if (password.equals(f.user[index].getPassword())) {
                return true;
            } else {
                System.err.println("Username and Passwords do not match.");
                return false;
            }
        } else {
            System.err.println("User not found.");
            return false;
        }
    }
    //Returns true if the user is able to add a new user.
    public int getState(String username){
        int index = f.searchForUsername(username);
        //If the users 'state' is 1 then they are able to add new users, so return true.
        return f.user[index].getState();
    }
    //Adds the new user to the file.
    public void addToFile(User newUser){
        //If the user name is not already in the file then we do not want to re-add the user.
        if (f.searchForUsername(newUser.getUsername()) == -1) {
            newUser.setPassword(encrypt(newUser.getPassword()));
            f.write(newUser);
        } else {
            System.err.println("User already exists");
        }
    }
    //Adds the new user to the file, when the data is not in the User format.
    public void addToFile(String username, String password, int state){
        User newUser = new User(username, password, state);
        addToFile(newUser);
    }
    //Encrypts the password using a simple Caesar Cipher.
    private String encrypt (String password){
        String encrypted = "";
        char temp;
        int ASCII;
        //For each letter in password.
        for (int i = 0; i < password.length(); i++){
            temp = password.charAt(i);
            ASCII = (int) temp;
            //If the letter is a character.
            if (ASCII >= 32 && ASCII <= 127){
                int x = ASCII - 32;
                x = (x + 6) % 96; /*Mod the characters so that it cannot go over the amount.
                The letters are all shifted plus 6 characters along. */
                encrypted += (char) (x + 32);
            }
        }
        return encrypted;
    }
}
