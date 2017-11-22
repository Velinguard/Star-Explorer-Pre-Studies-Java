package Prototype1;

import Prototype1.GUI.Resources.LoginScreen.LoginDetails;
import java.util.ArrayList;

public class MainCode {     
    
    private final ImageToUse img = new ImageToUse();
    
    /*This is the function that will be the master runner of the process, this will be called upon by the GUI and return to the 
    GUI an array list of elements. It calls for the image to be created, and then to be analysed.*/
    
    public ArrayList<KnownElements> checkStarFunction(String file, int expectedLines){
        img.setImage(file, expectedLines);
                
        Element elementDetails = getInfo(false, "");
        MyFileE f = new MyFileE("TextFiles\\stars.txt");
        ArrayList<KnownElements> elements = checkElements(f, elementDetails);
        
        return elements;
    }
    
    // This function is used to get the information about an elements emission spectra.
    
    public Element addElementFunction(String name, String fileName, int expectedLines){
        MyFileE f = new MyFileE("TextFiles\\stars.txt");
        img.setImage(fileName, expectedLines);
        //get input for location of element
        Element el = getInfo(true, name);
        //write to file function
        f.write(el);
        return el;
    }
    //This is used to get all the information about the element, determining where the absorption lines are, and all the details necessary.
    
    private Element getInfo(boolean black, String name){ //black is represented if we want to know if it is black or is not black
        name = name.toUpperCase(); //standardises the element names.
        Element element = new Element();
        element.setName(name);
        int y = 10; 
        int count = 0;
        float percent;
        Pixel tempStore = new Pixel();
        //This will go through one horizontal line of the image, it is inefficient to analyse the entirety of the image as it should be the 
        //same throughout.
        for (int z = 1; z < img.getWidth(); z++){
            //If there is a black absorption line then the data about the location of this needs to be stored as a pixel.
            if (isBlack(z, y, black) == 0){
                count = 1;
                percent = (float) ((double) z / (double) img.getWidth()) * 100;
                tempStore = img.getPixel(z, y);
                tempStore.setPercent(percent);
                tempStore.setCount(count);
                element.addPixel(tempStore);
            //If there is a black line, but there was a black line before it, I do not want to store the details as a new pixel, what 
            //I shall do instead is update the length of the black line.
            } else if (isBlack(z, y, black) == 1){
                count++;
                //This just ensures that a pixel has already been entered, this should never be false, but this is just a precaution.
                if (element.getLength() != 0) {
                    tempStore = element.getPixel(element.getLength() - 1);
                    tempStore.setCount(count);
                    element.removePixel();
                    element.addPixel(tempStore);
                }
            }
        }
        return element;
        
    }
    
    /*This is used to determine if an absorption line has been detected and if so whether the line before it was part of the same line.
    0 is returned if that is the first pixel to be black in the spectrum, 1 is returned if it is not the first, and 2 is returned if it 
    is not part of an absorption line.*/
    
    private int isBlack(int z, int y, boolean isIsnt){
        //If we want to find where it is black, or where it is not black.
        if (isIsnt == false){
        //If the pixel is black.
            if (img.getPixel(z, y).getBlack()){
            //If the pixel before it is black.
                if (img.getPixel(z - 1, y).getBlack()){
                    return 1;
                } else {
                    return 0;
                }
            } else {return 2;}
        } else if (isIsnt == true){
            //If the pixel is not black.
            if (!img.getPixel(z, y).getBlack()){
                //If the pixel before it is not black.
                if (!img.getPixel(z - 1, y).getBlack()){
                    return 1;
                } else {
                    return 0;
                }
            } else {return 2;}
        }
        System.err.println("This should not be reached.");
        return 2;
    } 
    //Function that returns the elements that are within the star as well as the percentage uncertanty.
    private ArrayList<KnownElements> checkElements(MyFileE f, Element star){
        double greatestPercent = 0;
        double perc;
        double percent;
        double starPercent;
        int max = 0;
        double match = 0;
        ArrayList<KnownElements> correspondingElements = new ArrayList<KnownElements>();
        //The outter most for loop which goes through each element.
        for (int i = 0; i < f.getData().size(); i++){
            Element element = f.getData().get(i);
            //The middle for loop which goes through each absorption line in each element.
            for (int k = 1; k < element.getLength(); k++){
                perc = 0;
                percent = element.getPixel(k).getPercent();
                //The inner most loop, which goes through each emission line in the star.
                for (int l = 0; l < star.getLength(); l++){
                    starPercent = star.getPixel(l).getPercent();
                    perc = Math.abs(starPercent - percent); //returns positive difference.
                    //Divides perc by the smallest number to get the greatest uncertanty.
                    if (starPercent > percent) {
                        perc /= percent;
                    } else {
                        perc /= starPercent;
                    }
                    perc *= 100;
                    if (perc <= 10){
                        match++;
                        //causes the inner most for loop to terminate, as the absorption line
                        //has now been mapped.
                        l = star.getLength();
                        //Calculates the greatest uncertanty within the percentage that is still viable.
                        if (perc > greatestPercent * 100){
                            perc /= 100;
                            greatestPercent = perc;
                        } 
                    }
                }
                max++;
            }
            //If the number of matched absorption lines is 0.85 of the number of absorption lines,
            //We can say that the element is found within the star.
            if (match >= max * 0.85){ 
                //Adds a new element to the list.
                correspondingElements.add(new KnownElements(element.getName(), greatestPercent));
            };
            max = 0;
            match = 0;
            greatestPercent = 0;
        }
        return correspondingElements;
    }
}
