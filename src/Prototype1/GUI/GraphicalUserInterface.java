package Prototype1.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import Prototype1.GUI.Resources.LoginScreen.LoginDetails;
import Prototype1.GUI.Resources.LoginScreen.User;
import Prototype1.MainCode;
import java.io.File;
import java.util.ArrayList;
import Prototype1.KnownElements;
 
public class GraphicalUserInterface implements ActionListener {
    final static String SOFTWARENAME = "Star Explorer";
    final static String LOGINPANEL = "Login Screen";
    final static String ADDUSERPANEL = "Add User";
    final static String HOMESCREENPANEL = "Home";
    final static String ADDELEMENTPANEL = "Add Element";
    final static String HOWITWORKSPANEL = "How It Works";
    final static String CREDITSPANEL = "Credits";
    JLabel label, label2;
    int index, state;
    String username;
    JButton loginButton, homeButton, starButton, elementButton, userButton, creditsButton, addUserButton, searchForEmission, matchStar, addElementSearch, nextScreen, previousScreen;
    JTextField usernameField, addUsernameField, locationInput, addElementField;
    JPasswordField passwordField, addPasswordField;
    JComboBox addStateField;
    JTabbedPane tabbedPane, howItWorksPane;
    JFileChooser fc;
    JSpinner numberOfLines;
    File image;
    JTextArea elementsOutput;
    
    public void addComponentToPane(Container pane) /*pane defaults to border layout*/ {
        //Creates top and bottom components
        pane.add(topBar(), BorderLayout.NORTH);
        pane.add(bottomBar(), BorderLayout.SOUTH);
        
        //Initalise tabbed pane.
        tabbedPane = new JTabbedPane();
        
        //Create the "cards".
        JPanel card1 = new JPanel();
        card1 = loginCentre();
 
        JPanel card2 = new JPanel();
        card2 = addUserCenter(); 
        
        JPanel card3 = new JPanel();
        card3 = homeScreenCenter();
        
        JPanel card4 = new JPanel();
        card4 = addElementCenter();
        
        JPanel card5 = new JPanel();
        card5 = creditsCenter();
                
        JPanel card6 = new JPanel();
        card6 = howItWorksPanel();
        
        //Place the cards into the tabbed pane.
        tabbedPane.addTab(LOGINPANEL, card1);
        tabbedPane.addTab(HOMESCREENPANEL, card3);
        tabbedPane.addTab(HOWITWORKSPANEL, card6);
        tabbedPane.addTab(ADDELEMENTPANEL, card4);
        tabbedPane.addTab(ADDUSERPANEL, card2);
        tabbedPane.addTab(CREDITSPANEL, card5);
        tabbedPane.setEnabled(false);
        pane.add(tabbedPane, BorderLayout.CENTER);
    }
    public JPanel bottomBar(){   
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Copyright held by: Space Angels Network");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        return panel;
    }
    public JPanel topBar(){
        JPanel panel = new JPanel();
        
        JLabel title = new JLabel(SOFTWARENAME);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Castellar",Font.PLAIN, 18));
        panel.add(title);
        
        panel.add(Box.createRigidArea(new Dimension(500,0)));
        
        JLabel label = new JLabel("");
        ImageIcon icon = new ImageIcon(getClass().getResource("Resources\\logo.jpg"));
        label.setIcon(icon); 
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(label);
        
        return panel;
    }
    public JPanel howItWorksPanel(){
        howItWorksPane = new JTabbedPane();
        JPanel[] screen = new JPanel[5];
        JLabel[] title = new JLabel[5];
        JLabel[] icons = new JLabel[5];
        ImageIcon[] icon = new ImageIcon[5];
        nextScreen = new JButton("Next");
        previousScreen = new JButton("Previous");
        
        //constructor
        for (int i = 0; i < title.length; i++){
            title[i] = new JLabel("");
            icons[i] = new JLabel("");
            screen[i] = new JPanel();
            screen[i].setSize(600,400);
            title[i].setFont(new Font("Segoe Print", Font.PLAIN,12));
        }
        
        //Screen 0
        screen[0].setLayout(new GridBagLayout());
        title[0].setText("Press next to see how spectral analysis works!");
        title[0].setFont(new Font("Imprint MT Shadow",Font.BOLD,20));
        screen[0].add(title[0]);
        
        //Screen 1
        title[1].setText("<html>White light is made up of many different colours. <BR>When this light "
                + "enters a glass prism, the various <BR>wavelengths of the different colours refract<BR>independently this "
                + "causes the colour to break down <BR>into a spectra of colours:</HTML>");
        icon[1] = new ImageIcon(getClass().getResource("Resources\\prism_1.gif"));
        icons[1].setIcon(icon[1]);
        screen[1].add(title[1]);
        screen[1].add(Box.createRigidArea(new Dimension(100,0)));
        screen[1].add(icons[1]);
        
        //Screen 2
        title[2].setText("<HTML>This 'wave like' nature of light can also be seen<BR>within stars. When light"
                + " from a star enters a glass prism<BR>the same affect can be seen. However, there are<BR>black lines on the"
                + " spectra, these lines correspond<BR>to the different elements found within the star:</HTML>");
        icon[2] = new ImageIcon(getClass().getResource("Resources\\star-emission-spec.gif"));
        icons[2].setIcon(icon[2]);
        screen[2].add(title[2]);
        screen[2].add(icons[2]);
        
        //Screen 3
        title[3].setText("<HTML>We can compare these 'Emission Spectras' with <BR>elements absorption spectras"
                + " to determine the <BR>composition of a star. Different elements are<BR>able to absorb photons, ('packets'"
                + " of light) of different<BR>wavelengths. Once a photon has been absorbed, it then<BR>needs to be re-emmitted, "
                + "the odds of it being<BR>re-emmitted in the exact same direction<BR>is none existant, this causes the black lines.</HTML>");
        icon[3] = new ImageIcon(getClass().getResource("Resources\\star-emission.gif"));
        icon[3].getImage().flush();
        icons[3].setIcon(icon[3]);
        screen[3].add(title[3]);
        screen[3].add(icons[3]);
        
        //Screen 4
        title[4].setText("<HTML>As you can see from this picture, we can detect<BR>that Hydrogen and Mercury"
                + " are within the star.<BR>This software automates this process, <BR>providing a more accurate comparison."
                + " It is also able<BR>to quickly examine a very large array of elements, much faster<BR>and more accuratly"
                + " than humans.<BR>For example, the output for this spectra is:<BR>Hydrogen, with a percentage uncertainty of 1%, and<BR>"
                + "Mercury with a percentage uncertainty of 3%.</HTML>");
        icon[4] = new ImageIcon(getClass().getResource("Resources\\Hydrogen Mercury.jpg"));
        icons[4].setIcon(icon[4]);
        screen[4].add(title[4]);
        screen[4].add(icons[4]);
        
        nextScreen.addActionListener(this);
        previousScreen.addActionListener(this);
        
        howItWorksPane.add("Home", screen[0]);
        howItWorksPane.add("The Wave-Like Nature of Light", screen[1]);
        howItWorksPane.add("Stars and Light", screen[2]);
        howItWorksPane.add("The Particle Nature of Light", screen[3]);
        howItWorksPane.add("Spectral Analysis", screen[4]);
        
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
        center.add(howItWorksPane);
        JPanel centerMinor = new JPanel();
        centerMinor.add(previousScreen);
        centerMinor.add(Box.createRigidArea(new Dimension(700,0)));
        centerMinor.add(nextScreen);
        center.add(centerMinor);
        return center;
    }
    public JPanel addUserCenter(){
        //Initialise
        JLabel name = new JLabel("Username:");
        addUsernameField = new JTextField("username");
        JLabel pass = new JLabel("Password:");
        addPasswordField = new JPasswordField(10);
        JLabel st = new JLabel("State:");
        String[] states = {"General User", "Scientist", "Admin"};
        addStateField = new JComboBox(states);
        addUserButton = new JButton("Add User");
        JPanel screen = new JPanel();
        
        //Layout the objects
        screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));
        screen.setBorder(new TitledBorder("Add User"));
        name.setAlignmentX(Component.RIGHT_ALIGNMENT);
        addUsernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        pass.setAlignmentX(Component.RIGHT_ALIGNMENT);
        addPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        addUserButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        st.setAlignmentX(Component.RIGHT_ALIGNMENT);
        addUserButton.addActionListener(this);
        
        //Create the panel
        screen.add(name);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(addUsernameField);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(pass);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(addPasswordField);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(st);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(addStateField);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(addUserButton);
        
        //Put the panel onto the screen
        JPanel fin = new JPanel();
        fin.setSize(600, 400);
        fin.add(Box.createRigidArea(new Dimension(0,100)));
        fin.add(screen);
        return fin;
    }   
    public JPanel loginCentre(){
        //init
        JLabel name = new JLabel("Username:");
        usernameField = new JTextField("username");
        JLabel pass = new JLabel("Password:");
        passwordField = new JPasswordField("password");
        loginButton = new JButton("login");
        JPanel screen = new JPanel();

        //Maneuver objects
        screen.setLayout(new BoxLayout(screen, BoxLayout.PAGE_AXIS));
        screen.setBorder(new TitledBorder("login"));
        name.setAlignmentX(Component.RIGHT_ALIGNMENT);
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        pass.setAlignmentX(Component.RIGHT_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        loginButton.addActionListener((ActionListener) this);
        
        //Create panel
        screen.add(name);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(usernameField);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(pass);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(passwordField);
        screen.add(Box.createRigidArea(new Dimension(0,10)));
        screen.add(loginButton);
        
        //Place panel onto screen
        JPanel fin = new JPanel();
        fin.setSize(600, 400);
        fin.add(Box.createRigidArea(new Dimension(0,100)));
        fin.add(screen);
        
        return fin;
    }  
    public JPanel homeScreenCenter(){
        locationInput = new JTextField("E:\\Computing Project\\ImageID\\src\\prototype1\\images\\perfectHydrogenHelium.png");
        searchForEmission = new JButton("Find File");
        starButton = new JButton("Inspect Star");
        elementsOutput = new JTextArea("Select an emission spectrum\nClick the Inspect Star button\n\n\n\n");
        elementsOutput.setSize(100, 400);
        label = new JLabel("");
        
        searchForEmission.addActionListener(this);
        starButton.addActionListener(this);
        
        JPanel getFile = new JPanel();
        getFile.add(label);
        getFile.add(locationInput);
        getFile.add(searchForEmission);
        getFile.setBorder(new TitledBorder("Select File"));
        
        JPanel output = new JPanel();
        output.add(starButton);
        output.add(Box.createRigidArea(new Dimension(100,0)));
        output.add(elementsOutput);
        
        JPanel fin = new JPanel();
        fin.setLayout(new BoxLayout(fin, BoxLayout.PAGE_AXIS));
        fin.setSize(600, 400);
        fin.add(getFile);
        fin.add(output);
        return fin;
    }
    public JPanel addElementCenter(){
        //Initialise
        JLabel label1 = new JLabel("Enter element name:");
        label1.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JLabel label3 = new JLabel("Enter the number of spectral lines:");
        addElementField = new JTextField("Element");
        addElementField.setColumns(15);
        elementButton = new JButton("Add Element");
        label2 = new JLabel("");
        numberOfLines = new JSpinner(new SpinnerNumberModel(0,0,100,1));
        
        //Set up buttons and text fields.
        elementButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        elementButton.addActionListener(this);
        locationInput = new JTextField("E:\\\\Computing Project\\\\ImageID\\\\src\\\\prototype1\\\\images\\\\Helium.png");
        locationInput.setColumns(40);
        addElementSearch = new JButton("Find File");
        addElementSearch.addActionListener(this);
        
        //Create a panel for the get File section
        JPanel getFile = new JPanel();
        getFile.add(locationInput);
        getFile.add(Box.createRigidArea(new Dimension(100,0)));
        getFile.add(addElementSearch);
        getFile.add(label2);
        getFile.setBorder(new TitledBorder("Select File"));
        
        //Create a sub panel to get the name information
        JPanel getName = new JPanel();
        getName.add(label1);
        getName.add(addElementField);
        
        //Create a sub panel to get the number of spectral lines.
        JPanel getLines = new JPanel();
        getLines.add(label3);
        getLines.add(numberOfLines);
        
        //Combines the two sub panels into one panel.
        JPanel getValues = new JPanel();
        getValues.setLayout(new BoxLayout(getValues, BoxLayout.PAGE_AXIS));
        getValues.add(getName);
        getValues.add(getLines);
        
        //Puts the button into a panel, and made to be to the right of the panel.
        JPanel button = new JPanel();
        button.add(Box.createRigidArea(new Dimension(700,0)));
        button.add(elementButton);
        
        //Creates a master panel which combines all the different features.
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.add(getValues, BorderLayout.NORTH);
        center.add(getFile, BorderLayout.CENTER);
        center.add(button, BorderLayout.SOUTH);
        return center;
    }
    private JPanel creditsCenter(){
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Space Angels Network"));
        
        JTextArea text = new JTextArea("Made and Designed by:\n\tSamuel Brotherton. \nWith Valued Insight From: \n       "
                + "\tMr J.Levermore,\n\tMr I.Clause,\n\tMr S.Parker.");
        text.setColumns(20);
        text.setBackground(new Color(0, 0, 0, 0));
        text.setEditable(false);
        JLabel label = new JLabel("");
        ImageIcon icon = new ImageIcon(getClass().getResource("Resources\\logo.jpg"));
        label.setIcon(icon);
        
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(text);
        
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new GridBagLayout());
        finalPanel.add(panel);
        return finalPanel;
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame(SOFTWARENAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        GraphicalUserInterface gui = new GraphicalUserInterface();
        gui.addComponentToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        LoginDetails login = new LoginDetails();
        MainCode star = new MainCode();
        if(e.getSource() == loginButton){
            username = usernameField.getText();
            username = username.toUpperCase();
            //If the username and password match
            if (login.login(username, passwordField.getText())){
                tabbedPane.setEnabled(true);
                state = login.getState(username);
                switch(state){
                    case 0: //General user 
                        tabbedPane.setEnabledAt(4, false);
                        tabbedPane.setEnabledAt(3, false);
                        break;
                    case 1: //Scientist
                        tabbedPane.setEnabledAt(3, true);
                        tabbedPane.setEnabledAt(4, false);
                        break;
                    case 2:
                        tabbedPane.setEnabledAt(3, true);
                        tabbedPane.setEnabledAt(4, true);
                        break;
                }
                tabbedPane.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(null, "INCORRECT USER-PASSWORD COMBINATION!", "ACCESS DENIED", JOptionPane.ERROR_MESSAGE);
                username = "";
            }
        } else if (e.getSource() == addUserButton){
            User user = new User(addUsernameField.getText(), addPasswordField.getText(), (int) addStateField.getSelectedIndex());
            login.addToFile(user);
        } else if (e.getSource() == searchForEmission ||e.getSource() == addElementSearch){
            JButton open = new JButton("Open");
            fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
            fc.addChoosableFileFilter(filter);
            fc.setAcceptAllFileFilterUsed(false);
            fc.setCurrentDirectory(new File(".\\build\\classes\\Prototype1\\Resources"));
            //The title needs to change depending on if it is the file chooser for emission or absorption.
            if (e.getSource() == searchForEmission){
                fc.setDialogTitle("Select Emmission Spectrum");
            } else {
                fc.setDialogTitle("Select Absorption Spectrum");
            }
            fc.setFileView(new ImageFileView());
            fc.setAccessory(new ImagePreview(fc));
            //If an appropriate image is selected, we need to input the file path to the label, and put the image in the file.
            if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
                File selectedFile = fc.getSelectedFile();
                locationInput.setText(selectedFile.getAbsolutePath());
                ImageIcon img = new ImageIcon(selectedFile.getAbsolutePath());
                img = new ImageIcon(img.getImage().getScaledInstance(700, 100, Image.SCALE_DEFAULT));
                if(tabbedPane.getSelectedIndex() == 1){
                    label.setIcon(img);
                } else {
                    label2.setIcon(img);
                }
                image = selectedFile;
            }
        } else if (e.getSource() == starButton){
            String path = "";
            //If there is no image, then run the code of the text field.
            if (image == null && locationInput.getText() != null){
                path = locationInput.getText();
            } else if (image != null){
                path = image.getAbsolutePath();
            }
            ArrayList<KnownElements> elements = star.checkStarFunction(path, 0);
                String text = "Elements within this star:\n";
                //Output data about each element.
                for (int i = 0; i < elements.size(); i++){
                    NumberFormat pc = NumberFormat.getPercentInstance();
                    if (state != 0){
                        text += elements.get(i).getName() + "\n" + "With a percentage uncertanty of " + pc.format(elements.get(i).getGreatestPercent()) + "\n";
                    } else {
                        text += elements.get(i).getName() + "\n";
                    }
                }
                elementsOutput.setText(text);
        } else if (e.getSource() == elementButton){
            String path = "";
            //If the image is not entered than run code of text field, otherwise use image.
            if (image == null && locationInput.getText() != null && addElementField.getText() != null){
                path = locationInput.getText();
                //This just confirms that the user wants to add the element as there is no function to remove an element.
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add the element: " + addElementField.getText()) == JOptionPane.YES_OPTION){
                    star.addElementFunction(addElementField.getText(), path, (int) numberOfLines.getValue());
                    JOptionPane.showMessageDialog(null, "Element successfully added!");
                }
            } else if (image != null){
                path = image.getAbsolutePath();
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add the element: " + addElementField.getText()) == JOptionPane.YES_OPTION){
                    star.addElementFunction(addElementField.getText(), path, (int) numberOfLines.getValue());
                    JOptionPane.showMessageDialog(null, "Element successfully added!");
                }
            }
        } else if (e.getSource() == nextScreen){
            index = howItWorksPane.getSelectedIndex() + 1;
            index %= howItWorksPane.getTabCount();
            howItWorksPane.setSelectedIndex(index);
        } else if (e.getSource() == previousScreen){
            index = howItWorksPane.getSelectedIndex() - 1;
            index %= howItWorksPane.getTabCount();
            //Jave mod function if between 0 and -2, will return a negative integer.
            if (index < 0) {
                index += howItWorksPane.getTabCount();
            }
            howItWorksPane.setSelectedIndex(index);
        }
    }
}
