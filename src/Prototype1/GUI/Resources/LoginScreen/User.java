package Prototype1.GUI.Resources.LoginScreen;

public class User{
    private String username;
    private String password;
    private int state;
    
    //Constructors
    public User(){
        username = "";
        password = "";
        state = 0;
    }
    public User(String user, String pass, int st){
        username = user.toUpperCase();
        password = pass;
        state = st;
    }
    //Sets the username.
    public void setUsername(String name){
        username = name;
    }
    //Sets the password.
    public void setPassword(String pass){
        password = pass;
    }
    /*Sets the state of the user, e.g.: 0 for general user,
    1 for scientist, 2 for admin etc...*/ 
    public void setState(int st){
        state = st;
    }
    //Returns the username.
    public String getUsername(){
        return username;
    }
    //Returns the password.
    public String getPassword(){
        return password;
    }
    //Returns the state.
    public int getState(){
        return state;
    }
}
