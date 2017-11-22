package Prototype1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MyFileE extends MyFile{
    ArrayList<Element> data;
    //Constructor
    public MyFileE(String local) {
        super(local);
        fileToArray();
    }
    //Returns the ArrayList of elements.
    public ArrayList<Element> getData(){
        return data;
    }
    //Adds a new element to the file.
    public void write(Element e){
        element = e;
        this.fileToArray();
        data.add(e);
        this.writeToFile(toStringForm());
    }
    //Returns the Array of elements into the 'super' standardised form for writing to the file.
    private ArrayList<String> toStringForm(){
        ArrayList<String> array = new ArrayList<String>();
        //For each element.
        for (int i = 0; i < data.size(); i++){
            //Each line in the file will be a new data in the ArrayList.
            array.add(data.get(i).getName());
            //For each pixel in the element.
            for (int k = 0; k < data.get(i).getLength(); k++){
                array.add(data.get(i).toStringFormat(k));
            }               
            array.add("-");
        }
        return array;
    }
    //Function that converts the data within the file into an array.
    public void fileToArray(){
        data = new ArrayList<Element>();
        Pixel temp = new Pixel();
        Element tempEl = new Element();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = "";
            //Already is used so that the same name is not added twice. 
            Boolean already = false;
            reader.readLine();
            //Reads every line of the file until the whole file has been read.
            do{
                line = reader.readLine();
                //If the line is - then a new element is starting, so we want to add the finished element to the array.
                if ("-".equals(line)){
                    if (!already){
                        data.add(tempEl);
                        tempEl = new Element();
                    }
                    already = false;
                //If the line is empty than the file is at the end.
                } else if (line == null){
                    break;
                } else {
                    //If the element is already there then we dont want to add it twice.
                    if (element.getName().equals(line)){
                        already = true;
                    }
                    //If the line begins with a number then store the data on that line as a pixel, and adding that data to the array.
                    if (line.substring(0, 1).matches("[0-9]")){
                        String[] tempLine = line.split("-");
                        temp = new Pixel();
                        temp.setRGB(Integer.parseInt(tempLine[0]), Integer.parseInt(tempLine[1]), Integer.parseInt(tempLine[2])); 
                        temp.setPercent(Float.parseFloat(tempLine[3]));
                        temp.setCount(Integer.parseInt(tempLine[4]));
                        tempEl.addPixel(temp);
                    //Otherwise, the line is representative of a name.
                    } else {
                        tempEl.setName(line);
                    }
                }
            } while (true);
        } catch (Exception e) {
            System.err.println("File not able to be converted into an Array");
        }
    }
}
