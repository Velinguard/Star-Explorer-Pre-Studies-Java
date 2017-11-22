package Prototype1;

public class Pixel {
    private Integer red;
    private Integer green;
    private Integer blue;
    private Boolean black;
    private Float percent;
    private Integer count;
    //Initialises the object Pixel
    public Pixel(){
        red = 0;
        green = 0;
        blue = 0;
        black = false;
        percent = (float)0;
        count = 0;           
    }
    //Allows for the RGB and 'isBlack' variables to be set from a generated integer created when analysing the image. 
    public Pixel(int pix, int accuracy){
        red = (pix >> 16) & 0xFF;
        green = (pix >> 0) & 0xFF;
        blue = (pix >> 8) & 0xFF;
        black = isBlack(accuracy);
    }
    //Allows for the Red, Green and Blue values of the image to be set manually.
    public void setRGB(int r, int g, int b){
        red = r;
        green = g;
        blue = b;
    }
    //Determines if the pixel is black, 1 is black, 0 is not black.
    private boolean isBlack(int accuracy){
       if (red <= accuracy && green <= accuracy && blue <= accuracy){
           return true;
        } else {return false;} 
    }
    //Allows for the percent to be set manually.
    public void setPercent(float per){
        percent = per;
    }
    //Allows for the width to be set manually.
    public void setCount(int c){
        count = c;
    }
    //Allows for the Red to be set individually.
    public int getRed(){
        return red;
    }
    //Allows for the Green to be set individually.
    public int getGreen(){
        return green;
    }
    //Allows for the Blue to be set individually.
    public int getBlue(){
        return blue;
    }
    //Returns 1 if the Pixel is black, and 0 if it is not.
    public boolean getBlack(){
        return black;
    }
    //Returns the location of the Pixel.
    public float getPercent(){
        return percent;
    }
    //Returns the width of the Absorption Line.
    public int getCount(){
        return count;
    }
}
