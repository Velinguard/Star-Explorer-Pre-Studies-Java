package Prototype1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyFile extends Element{
    public String location;
    Element element;
    java.io.File f;
    
    //Constructor
    public MyFile(String local){
        try{
            location = local;
            f = new java.io.File(getClass().getResource(location).toURI());
        } catch (Exception e){
            System.err.println("File can not be located.");
        }
        element = new Element();
    }
    //Writes the array of elements to the file.
    public void writeToFile(ArrayList<String> array){
        try {
            f.setWritable(true);
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            //Wipes the file so it can be written to from scratch.
            wipeFile();
            //For each element in the array of elements.
            for(int i = 0; i < array.size(); i++){
                writer.newLine();
                writer.write(array.get(i));
            }
            writer.close();
            //Ensures that only valid data can be entered within the file.
            f.setReadOnly();
        } catch(Exception e){System.err.println("Error writing to file");}
    }
    //Procedure that wipes the contents of the file.
    private void wipeFile() {
        try {
            PrintWriter wiper = new PrintWriter(f.getAbsolutePath());
            wiper.println("");
        } catch (Exception ex) {
            System.err.println("error wiping file");
        }
    }  
}
