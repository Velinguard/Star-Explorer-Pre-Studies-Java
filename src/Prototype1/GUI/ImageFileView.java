package Prototype1.GUI;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageFileView extends FileView {
    
    ImageIcon jpgIcon = Extensions.createImageIcon("Resources/jpgIcon.gif");
    ImageIcon gifIcon = Extensions.createImageIcon("Resources/gifIcon.gif");
    ImageIcon pngIcon = Extensions.createImageIcon("Resources/pngIcon.png");
    
    public String getTypeDescription(File f) {
        String extension = Extensions.getExtension(f);
        String type = null;

        if (extension != null) {
            if (extension.equals(Extensions.jpeg) ||
                extension.equals(Extensions.jpg)) {
                type = "JPEG Image";
            } else if (extension.equals(Extensions.gif)){
                type = "GIF Image";
            } else if (extension.equals(Extensions.png)){
                type = "PNG Image";
            }
        }
        return type;
    }

    public Icon getIcon(File f) {
        String extension = Extensions.getExtension(f);
        Icon icon = null;

        if (extension != null) {
            if (extension.equals(Extensions.jpeg) ||
                extension.equals(Extensions.jpg)) {
                icon = jpgIcon;
            } else if (extension.equals(Extensions.gif)) {
                icon = gifIcon;
            } else if (extension.equals(Extensions.png)) {
                icon = pngIcon;
            }
        }
        return icon;
    }
}
