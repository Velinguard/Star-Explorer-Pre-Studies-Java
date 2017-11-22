package Prototype1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ImageToUse {
    private Integer height;
    private Integer width;
    private BufferedImage img;
    private Pixel[][] pixel;
    private int shouldBe;
    private int step;
    private boolean stop, goneTo;
    private ArrayList<Pixel[][]> array = new ArrayList<Pixel[][]>();
    //Constructor
    public ImageToUse(){
        height = 0;
        width = 0;
        img = null;
        pixel = null;
        stop = false;
        goneTo = false;
        step = 0;
    }
    //Allows for an image to be set, and for the details of the image to be generated.
    public void setImage (String imageNameAndType, int should){
        img = null;
        step = 0;
        shouldBe = should;
        try {
            img = ImageIO.read(new File(imageNameAndType));
        //If the image cannot be read do this.
        } catch (IOException e) {
            System.err.println("Error reading image");
        }
        setHeight();
        setWidth();
        pixel = setPixels(70);
        //We want the last value as the recursion implements a stack.  
        pixel = array.get(array.size() - 1);
    }
    //Sets the variable for the height of the image.
    private void setHeight (){
        height = img.getHeight(null);
    }
    //Sets the variable for the width of the image.
    private void setWidth (){
        width = img.getWidth(null);
    }
    //Generates a 2-Dimensional array of the image with each (x,y) Co-ordinate representing information about the pixel.
    private Pixel[][] setPixels (int accuracy) {
        boolean last = false;
        step = 0;
        Pixel pixels[][] = new Pixel[width][height];
        int pix;       
        //Goes along the height of the image, for example when i = 0, we are working on the top row.
        for (int i = 0; i <= height - 1; i++){
            //Goes across the image, for example when x = 0, we are working on the left most pixel.
            for (int x = 0; x <= width - 1; x++) {
                pixels[x][i] = new Pixel(img.getRGB(x, i), accuracy);
                //If it is black then add to a count to determine how many absorption lines there are, the last means that
                //the same absorption line is not counted twice.
                if(pixels[x][i].getBlack() && !last){
                    step++;
                    last = true;
                } else {
                    last = false;
                }
            }
            //If we have already found the best degree of accuracy or if this is the right degree.
            if (step == shouldBe || stop){
                stop = true;
            } else if (shouldBe == 0){
                stop = true;
                array.add(setPixels(70));
            //This allows for me to make a simplified version for the general user, with an average accuracy.
            } else {
                //If the number it should be is less than the number it is, it should go back up.
                if (step < shouldBe || goneTo){
                    goneTo = true;
                    //If the number it should be is more than the number it is, we go back one and use that as the closest
                    //that we will get.
                    if (step > shouldBe){
                        stop = true;
                        array.add(setPixels(accuracy - 1)); //worst come situation.
                    }
                    array.add(setPixels(accuracy + 2));
                } else {
                    array.add(setPixels(accuracy - 10));
                }
            }
        }
        return pixels;
    }
    //Returns the height of the image.
    public int getHeight(){
        return height;
    }
    //Returns the width of the image.
    public int getWidth(){
        return width;
    }
    //Returns the image.
    public BufferedImage getImage(){
        return img;
    }
    //Returns the Pixel at (x,y) Co-Ordinates.
    public Pixel getPixel(int x, int y){
        return pixel[x][y];
    }
}
