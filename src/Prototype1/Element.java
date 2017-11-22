package Prototype1;

import java.util.ArrayList;

public class Element extends Pixel{
    
    ArrayList<Pixel> pixels = new ArrayList<Pixel>();
    String name;
    int length;
    
    //Constructors
    public Element(){
        for (int i = 0; i < pixels.size(); i++){
            pixels.remove(pixels.size() - i - 1);
        }
        name = "";
        length = 0;
    }
    public Element(ArrayList<Pixel> pix, String eName){
        name = eName;
        pixels = pix;
    }
    //Allows for the elements name to be set.
    public void setName(String eName){
        name = eName;
    }
    //Allows for a Pixel to be added to the ArrayList.
    public void addPixel(Pixel pix){
        pixels.add(pix);
        length++;
    }
    //Allows for a Pixel to be removed to the ArrayList.
    public void removePixel(){
        //Insures no underflow error occurs.
        if(!pixels.isEmpty()){
           pixels.remove(length - 1);
            length--; 
        } else {
            System.err.println("Array is empty");
        }
    }
    //Returns a Pixel at an index from the ArrayList.
    public Pixel getPixel(int index){
        return pixels.get(index);
    }
    //Returns the name of the element.
    public String getName(){
        return name;
    }
    //Returns the amount of Pixels stored in the array.
    public int getLength(){
        return length;
    }
    //Converts the Pixel into a string, this will be used when writing data to the file during prototype 3.
    public String toStringFormat(int index){
        String temp;
        temp = ( Integer.toString(pixels.get(index).getRed()) + "-" +
                 Integer.toString(pixels.get(index).getGreen()) + "-" +
                 Integer.toString(pixels.get(index).getBlue()) + "-" +
                 Float.toString(pixels.get(index).getPercent()) + "-" +
                 Integer.toString(pixels.get(index).getCount()));
        return temp;
    }
}
