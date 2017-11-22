package Prototype1.GUI;

import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

public class Extensions {
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String png = "png";
    
    public static String getExtension(File f) {
        String ext = null;
        String fileName = f.getName();
        int i = fileName.lastIndexOf('.');

        if (i > 0 &&  i < fileName.length() - 1) {
            ext = fileName.substring(i+1).toLowerCase();
        }
        return ext;
    }
    public static ImageIcon createImageIcon(String path) {
        URL imgURL = Extensions.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.out.println("Couldn't find file");
            return null;
        }
    }
}
