/*
* Run Draw // Hector Dondiego portion, except where it says Nadia
* 1. Takes in info about the starting time and ending time,
*    the starting location and ending location
* 2. Calculates the duration of the run, the pace, and the overall distance
* 3. Saves the accepted and calculated info on to a file
* 4. The pace, the duration, and the distance of each run is drawn on to a panel
* 5. The distance graph features not only the distance in kilometers, but it also
*    has the number of miles on the graph
* 6. To optimize the experience with using SoupRun, it would be useful to use the
*    distance conversion as shown on the graph.
* 7. The duration supports up to approximately 80 minutes
* 8. The distance supports up to 14km or about 9 miles
* 9. The pace supports up to about 16min/km
* 10. Once the Soup button is clicked, it will open the SoupRun program, which
*     asks the user to enter the miles ran manually and performs calculations
*     to determine whether the user has burned enough calories to consume soup.
*     Otherwise, the user is discouraged from consuming soup.
*/
import java.lang.Math;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedOutputStream;
import javax.swing.filechooser.FileFilter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;


class Time{
    // private data variables for hours, minutes, and seconds
    private int hours;
    private int minutes;
    private int seconds;

    // constructors for hours
    // range checker for hours
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours >= 24 || hours < 0){
            this.hours = 0;
        } else {
            this.hours = hours;
        }
    }

    // constructors for minutes
    // range checker for minutes
    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        if (minutes >= 60 || minutes < 0) {
            this.minutes = 0;
        } else {
            this.minutes = minutes;
        }
    }

    // constructors for seconds
    // range checker for seconds
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        if (seconds >= 60 || seconds < 0) {
            this.seconds = 0;
        } else {
            this.seconds = seconds;
        }
    }

    // non-default constructor for Time
    public Time(int hours, int minutes, int seconds) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    // calculateSecondsSinceMidnight converts the time to seconds
    public double calculateSecondsSinceMidnight() {
        double newSeconds = (hours * 60 * 60) + (minutes * 60) + seconds;
        return newSeconds;
    }

    // appends to the Run toString function
    // includes a zero if the hours, minutes, or seconds is a single digit
    @Override
    public String toString() {
        return String.format("%02d %02d %02d",hours,minutes,seconds);
    }
}

class Date{
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date(String date) {
        setDate(date);
    }

    @Override
    public String toString() {
        return String.format("%s", date);
    }
}

class Location{
    // private data variables for latitude and longitude
    private double lat;
    private double lon;

    // constructor for latitude
    // range checker for latitude
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        if (lat >= -90 && lat <= 90){
            this.lat = lat;
        } else{
            this.lat = 0;
        }
        
    }

    // constructor for longitude
    // range checker for longitude
    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        if (lon >= -180 && lon <= 180){
            this.lon = lon;
        } else {
            this.lon = 0;
        }
    }

    // non-default constructor for Location
    public Location(double lat, double lon) {
        setLat(lat);
        setLon(lon);
    }

    // appends to the Run toString function
    @Override
    public String toString() {
        return String.format("%.4f %.4f",
            lat,lon);
    }
}

    // possibly add a color data type
    // color data type can be used to distinguish between different runs





class RunInfoPanel extends JPanel{
    private ArrayList<Run> runs;
    private String date;
    private double distanceKM;
    private int distanceMiles;
    private double duration;
    private double pace;

    public void setLabels() {
        JLabel lblDate = new JLabel("Date: ");
        JLabel lblDistanceKm = new JLabel("Distance (km.): ");
        JLabel lblDistanceMiles = new JLabel("Distance (mi.): ");
        JLabel lblDuration = new JLabel("Duration: ");
        JLabel lblPace = new JLabel("Pace: ");
            
            if (runs.size() > 0) {
                int placement = runs.size() - 1;
                Run runny = runs.get(0);
                date = runny.getDate();
                distanceKM = runny.calculateDistance();
                distanceMiles = (int)(runny.calculateDistance() / 1.609);
                duration = runny.getElapsedMinutes();
                pace = runny.calculatePace();
    
                //String.format()
    
                
                lblDate.setText("Date: " + String.format("%s",date));
                lblDistanceKm.setText("Distance (km.)" + String.format("%s",distanceKM));
                lblDistanceMiles.setText("Distance (mi.)" + String.format("%s",distanceMiles));
                lblDuration.setText("Duration: " + String.format("%s",duration));
                lblPace.setText("Pace: " + String.format("%s",pace));
                
        }
        
            else {
                lblDate.setText("Date: ");
                lblDistanceKm.setText("Distance (km.): ");
                lblDistanceMiles.setText("Distance (mi.): ");
                lblDuration.setText("Duration: ");
                lblPace.setText("Pace: ");
            }
    }

    public RunInfoPanel(ArrayList<Run> runs) {
        this.runs = runs;
        setLabels();
        //setPreferredSize(new Dimension(300,20));
    }


}


class RunDurationPanel extends JPanel{
    private ArrayList<Run> runs;

    final int bottomY = 625;
    final int leftX = 50;
    final double pixelSpacingY = 6.9;
    final int pixelSpacingX = 120;


    private int duration;
    private int datePlot;
    private double decimalPart;
    private Font f;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        f = new Font("Arial", Font.PLAIN, 12);
        g.setFont(f);

        // lines for the x and y-axis
        g.drawLine(50,25,50,625);
        g.drawLine(50,625,700,625);

        // dash marks min
        g.drawString("min.",15,25);

        g.drawLine(45,73,55,73);
        g.drawString("80",25,78);

        g.drawLine(45,142,55,142);
        g.drawString("70",25,147);

        g.drawLine(45,211,55,211);
        g.drawString("60",25,216);

        g.drawLine(45,280,55,280);
        g.drawString("50",25,285);

        g.drawLine(45,349,55,349);
        g.drawString("40",25,354);

        g.drawLine(45,418,55,418);
        g.drawString("30",25,423);

        g.drawLine(45,487,55,487);
        g.drawString("20",25,492);

        g.drawLine(45,556,55,556);
        g.drawString("10",25,561);

        // dash marks per day
        g.drawLine(170,620,170,630);
        g.drawLine(290,620,290,630);
        g.drawLine(410,620,410,630);
        g.drawLine(530,620,530,630);
        g.drawLine(650,620,650,630);

        // Draws each run
        for(Run runny : runs) {
            // Retrieves information about where on the x-axis should the run be plotted
            // and the calculation to find the duration
            datePlot = runs.indexOf(runny) + 1;
            duration = ((int)(runny.getElapsedMinutes()));
            decimalPart = runny.getElapsedMinutes() - duration;

            g.fillOval(leftX + (pixelSpacingX * datePlot), (int)((bottomY - (pixelSpacingY * duration) - (pixelSpacingY * decimalPart))), 5,5);
        }
    }
    public RunDurationPanel(ArrayList<Run> runs) { 
        this.runs = runs;
    }

}
class RunDistancePanel extends JPanel{
    private ArrayList<Run> runs;

    final int bottomY = 625;
    final int leftX = 50;
    final int pixelSpacingY = 40;
    final int pixelSpacingX = 120;

    private int distance;
    private int datePlot;
    private double decimalPart;
    private Font f;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        f = new Font("Arial",Font.PLAIN,12);
        g.setFont(f);

        g.drawLine(50,25,50,625);
        g.drawLine(50,625,700,625);

        // dash marks per kilometer
        g.drawString("(km.)",10,35);

        g.drawLine(45,65,50,65);
        g.drawString("14",25,70);

        g.drawLine(45,105,50,105);
        g.drawString("13",25,110);

        g.drawLine(45,145,50,145);
        g.drawString("12",25,150);

        g.drawLine(45,185,50,185);
        g.drawString("11",25,190);

        g.drawLine(45,225,50,225);
        g.drawString("10",25,230);

        g.drawLine(45,265,50,265);
        g.drawString("9",25,270);

        g.drawLine(45,305,50,305);
        g.drawString("8",25,310);

        g.drawLine(45,345,50,345);
        g.drawString("7",25,350);

        g.drawLine(45,385,50,385);
        g.drawString("6",25,390);

        g.drawLine(45,425,50,425);
        g.drawString("5",25,430);

        g.drawLine(45,465,50,465);
        g.drawString("4",25,470);

        g.drawLine(45,505,50,505);
        g.drawString("3",25,510);

        g.drawLine(45,545,50,545);
        g.drawString("2",25,550);

        g.drawLine(45,585,50,585);
        g.drawString("1",25,590);


        // dash marks per mile
        g.drawString("(mi.)",65,25);

        g.drawLine(50,49,55,49);
        g.drawString("9",65,54);

        g.drawLine(50,113,55,113);
        g.drawString("8",65,118);

        g.drawLine(50,177,55,177);
        g.drawString("7",65,182);

        g.drawLine(50,241,55,241);
        g.drawString("6",65,246);

        g.drawLine(50,305,55,305);
        g.drawString("5",65,310);

        g.drawLine(50,369,55,369);
        g.drawString("4",65,374);

        g.drawLine(50,433,55,433);
        g.drawString("3",65,438);

        g.drawLine(50,497,55,497);
        g.drawString("2",65,502);

        g.drawLine(50,564,55,564);
        g.drawString("1",65,569);


        // dash marks per day
        g.drawLine(170,620,170,630);
        g.drawLine(290,620,290,630);
        g.drawLine(410,620,410,630);
        g.drawLine(530,620,530,630);
        g.drawLine(650,620,650,630);

        // plots the point for the distance of the run
        for(Run runny : runs) {
            // Retrieves information regarding where on the x-axis to plot
            // and the distance of the run
            datePlot = runs.indexOf(runny) + 1;
            distance = (int)(runny.calculateDistance());
            decimalPart = runny.calculateDistance() - distance;
            g.fillOval(leftX + (pixelSpacingX * datePlot), (int)((bottomY - (pixelSpacingY * distance)) - (pixelSpacingY * decimalPart)), 5,5);
        }
    }

    public RunDistancePanel(ArrayList<Run> runs) {
        this.runs = runs;
    }

}

class RunPacePanel extends JPanel{
    private ArrayList<Run> runs;

    final int bottomY = 625;
    final int leftX = 50;
    final double pixelSpacingY = 34.5;
    final int pixelSpacingX = 120;

    private int pace;
    private int datePlot;
    private double decimalPart;
    private Font f;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        f = new Font("Arial", Font.PLAIN, 12);
        g.setFont(f);
        g.drawLine(50,25,50,625);
        g.drawLine(50,625,700,625);

        // dash marks min/km
        g.drawString("min/km",5,25);

        g.drawLine(45,73,55,73);
        g.drawString("16",25,78);

        g.drawLine(45,142,55,142);
        g.drawString("14",25,147);

        g.drawLine(45,211,55,211);
        g.drawString("12",25,216);

        g.drawLine(45,280,55,280);
        g.drawString("10",25,285);

        g.drawLine(45,349,55,349);
        g.drawString("8",25,354);

        g.drawLine(45,418,55,418);
        g.drawString("6",25,423);

        g.drawLine(45,487,55,487);
        g.drawString("4",25,492);

        g.drawLine(45,556,55,556);
        g.drawString("2",25,561);

        // dash marks per day
        g.drawLine(170,620,170,630);
        g.drawLine(290,620,290,630);
        g.drawLine(410,620,410,630);
        g.drawLine(530,620,530,630);
        g.drawLine(650,620,650,630);

        int runSize = runs.size();
        for(Run runny : runs) {
            // Retrieves information about where along the x-axis to plot
            // and the pace of the run
            datePlot = runs.indexOf(runny) + 1;
            pace = (int)(runny.calculatePace());
            decimalPart = runny.calculatePace() - pace;

            // in case the user enters a negative time
            if (pace > 0){
                g.fillOval(leftX + (pixelSpacingX * datePlot), (int)((bottomY - (pixelSpacingY * pace) - (pixelSpacingY * decimalPart))), 5,5);
            }
        }
    }

    public RunPacePanel(ArrayList<Run> runs) {
        this.runs = runs;
    }
}




class RunFrame extends JFrame{
    private ArrayList<Run> runs;
    private Run newRun;

    private JTabbedPane funRun;
    private RunInfoPanel runInfo;
    private RunDurationPanel durationRun;
    private RunDistancePanel distanceRun;
    private RunPacePanel paceRun;

    private JPanel statement;
    private JPanel textFieldPan;
    private JPanel buttonPan;


    private JTextField txtDateRun;
    private JTextField txtStartLoc;
    private JTextField txtEndLoc;
    private JTextField txtStartTime;
    private JTextField txtEndTime;


    private JLabel enterFields;
    private GroupLayout textFieldLay;

    private String date;
    private String startLocation;
    private String endLocation;
    private String startTime;
    private String endTime;

    private double distanceKM;
    private int distanceMiles;
    private double duration;
    private double pace;
    
    @SuppressWarnings("unchecked")
    public void RunMenu() { // Nadia Creating the menu 
        JMenuBar rbar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenuItem miOpen = new JMenuItem("Open");
        mnuFile.add(miOpen);
        rbar.add(miOpen);
        JMenuItem miSave = new JMenuItem("Save");
        mnuFile.add(miSave);
        rbar.add(miSave);
        JMenuItem miExit = new JMenuItem("Exit");
        mnuFile.add(miExit);
        rbar.add(miExit);

        JFileChooser jfc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("XML","xml");
        jfc.addChoosableFileFilter(filter);
        
        
        
        miSave.addActionListener(new ActionListener() { // Save
            public void actionPerformed(ActionEvent e) {
                /*
                    File f;
                    try {
                        if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            f = jfc.getSelectedFile();
                            if (f.getName().endsWith("xml")){
                                XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
                                System.out.println(runs.size());
                                enc.writeObject(runs); //Adds array list of runs to a file
                                enc.close();
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    */
            }
        });
    
        mnuFile.add(miExit); 
        miOpen.addActionListener(new ActionListener() { //Open
            public void actionPerformed(ActionEvent e) {
                File f;
                runs.clear();
                    /*
                    try {   
                        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            f = jfc.getSelectedFile();
                            if (f.getName().endsWith("xml")) {
                                XMLDecoder xmlDec = new XMLDecoder(new FileInputStream(f));
                                ArrayList<Run> runnies = (ArrayList<Run>)(xmlDec.readObject());
                                xmlDec.close();
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,"Could not open the file.");
                    }
                    */
            }   
        });
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuFile.add(miOpen);
        mnuFile.add(miSave);
        mnuFile.add(miExit);
        rbar.add(mnuFile);
        setJMenuBar(rbar);
        
    }
        
    public void configureText() {
        txtDateRun = new JTextField(10);
        txtStartLoc = new JTextField(10);
        txtEndLoc = new JTextField(10);
        txtStartTime = new JTextField(10);
        txtEndTime = new JTextField(10);

        enterFields = new JLabel();
        
        runInfo.add(txtDateRun, BorderLayout.EAST);
        runInfo.add(txtStartLoc, BorderLayout.EAST);
        runInfo.add(txtEndLoc, BorderLayout.EAST);
        runInfo.add(txtStartTime, BorderLayout.EAST);
        runInfo.add(txtEndTime, BorderLayout.EAST);
    }

    public void configureUI() {
        // Creates the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100,100,800,750);
        setTitle("Run For Your Life!");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // creates the tabs in the frame
        funRun = new JTabbedPane();
        JPanel house = new JPanel();
        runInfo = new RunInfoPanel(runs);
        durationRun = new RunDurationPanel(runs);
        distanceRun = new RunDistancePanel(runs);
        paceRun = new RunPacePanel(runs);

        funRun.addTab("Info", house);
        funRun.addTab("Duration", durationRun);
        funRun.addTab("Distance (km./mi.)", distanceRun);
        funRun.addTab("Pace", paceRun);



        /*
        This section is utilized for entering information about the run.

        The top section would feature a message for the user to enter
        all fields.

        The middle section would contain labels to guide the user with what
        to enter inside the textfields. It will also contain three buttons
        for the user to use. There will be a Calculate button to graph the run.
        The Clear button will be used to only clear the textfields. The Soup button
        will be used to open the SoupRun program.
        */
        statement = new JPanel();
        statement.setLayout(new FlowLayout());
        statement.setPreferredSize(new Dimension(800,75));
        enterFields = new JLabel("Please enter the following:");
        statement.add(enterFields);
        house.add(statement, BorderLayout.NORTH);

        // Holds everything that the center of the Info tabbed pane will need
        JPanel panCenter = new JPanel();
        panCenter.setLayout(new BorderLayout());
        panCenter.setPreferredSize(new Dimension(300,200));

        // This will hold all the labels and the textfields for the user to enter
        textFieldPan = new JPanel();
        textFieldPan.setLayout(new GridLayout(5,2));
        textFieldPan.setPreferredSize(new Dimension(100,200));

        JLabel lblDate = new JLabel("Date (M/D/YY): ");
        textFieldPan.add(lblDate);
        txtDateRun = new JTextField();
        textFieldPan.add(txtDateRun);

        JLabel lblStartLocation = new JLabel("Start Location: ");
        textFieldPan.add(lblStartLocation);
        txtStartLoc = new JTextField();
        textFieldPan.add(txtStartLoc);

        JLabel lblEndLocation = new JLabel("End Location: ");
        textFieldPan.add(lblEndLocation);
        txtEndLoc = new JTextField();
        textFieldPan.add(txtEndLoc);

        JLabel lblStartTime = new JLabel("Start Time: ");
        textFieldPan.add(lblStartTime);
        txtStartTime = new JTextField();
        textFieldPan.add(txtStartTime);

        JLabel lblEndTime = new JLabel("End Time: ");
        textFieldPan.add(lblEndTime);
        txtEndTime = new JTextField();
        textFieldPan.add(txtEndTime);


        panCenter.add(textFieldPan, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(200,100));

        JButton btnCalculate = new JButton("Calculate"); //Nadia
        JButton btnClear = new JButton("Clear"); //Nadia
        JButton btnSoup = new JButton("Soup"); //Nadia
        buttonPanel.add(btnCalculate,BorderLayout.SOUTH); //Nadia
        buttonPanel.add(btnClear,BorderLayout.SOUTH); //Nadia
        buttonPanel.add(btnSoup,BorderLayout.SOUTH); //Nadia

        panCenter.add(buttonPanel, BorderLayout.SOUTH);

        house.add(panCenter, BorderLayout.CENTER);

        RunFrame me = this; 
        RunMenu(); //Nadia
        
        btnClear.addActionListener(new ActionListener(){ //Nadia
            public void actionPerformed(ActionEvent e) {
                txtDateRun.setText("");
                txtStartLoc.setText("");
                txtEndLoc.setText("");
                txtStartTime.setText("");
                txtEndTime.setText("");
                repaint();
            
                }
            });
        
        btnCalculate.addActionListener(new ActionListener(){ //Nadia
            public void actionPerformed(ActionEvent e) {
                date = txtDateRun.getText();
                startLocation = txtStartLoc.getText();
                endLocation = txtEndLoc.getText();
                startTime = txtStartTime.getText(); //Difference in Time
                endTime = txtEndTime.getText();

                //double location1[] = new double[2];
                String location1[] = startLocation.split(" ");
                double startLat = Double.parseDouble(location1[0]);
                double startLong = Double.parseDouble(location1[1]);

                //double location2[] = new double[2];
                String location2[] = endLocation.split(" ");
                double endLat = Double.parseDouble(location2[0]);
                double endLong = Double.parseDouble(location2[1]);

                //int time1[] = new int[3];
                String time1[] = startTime.split(" ");
                int startHours = Integer.parseInt(time1[0]);
                int startMinutes = Integer.parseInt(time1[1]);
                int startSeconds = Integer.parseInt(time1[2]);
                System.out.printf("%d\n",startHours);
                System.out.printf("%d\n",startMinutes);
                System.out.printf("%d\n",startSeconds);

                //int time2[] = new int[3];
                String time2[] = endTime.split(" ");
                int endHours = Integer.parseInt(time2[0]);
                int endMinutes = Integer.parseInt(time2[1]);
                int endSeconds = Integer.parseInt(time2[2]);
                System.out.printf("%d\n",endHours);
                System.out.printf("%d\n",endMinutes);
                System.out.printf("%d\n",endSeconds);

                if (runs.size() != 5) {
                    newRun = new Run(startHours, startMinutes, startSeconds, endHours, endMinutes,
        endSeconds, startLat, startLong, endLat, endLong, date);
                    runs.add(newRun);
                    System.out.println("JUst added " + newRun.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "You have reached the maximum amount of runs allowable.");
                }
                

                // send the run list to the distance panel
            }
        });
        
        btnSoup.addActionListener(new ActionListener() { //Nadia
            public void actionPerformed(ActionEvent e) {
                SoupFrame srfm = new SoupFrame();
                srfm.setVisible(true);
                //SoupRun sr = new SoupRun(me,true);
                //sr.setVisible(true);
                //System.out.println("Welcome. Have you earned soup?");
                //sr.dispose();
            }
        });
        //runInfo.add(, BorderLayout.CENTER);
        
        c.add(funRun);
    }

    public RunFrame(ArrayList<Run> runs){
        this.runs = runs;
        configureUI();
    }
} 

class Run implements Serializable{
    // Time and location is a data type -> classes for them
    // private data types for the start and end time
    private Time startTime;
    private Time endTime;
    
    // private data types for start and end location
    private Location startLocation;
    private Location endLocation;

    private Date dateVal;

    // non-default constructor for Run
    public Run(int startHour,int startMinutes, int startSeconds, int endHour, int endMinutes,
        int endSeconds, double startLat, double startLong, double endLat, double endLong, String date){
        startTime = new Time(startHour, startMinutes, startSeconds);    
        endTime = new Time(endHour,endMinutes,endSeconds);
        startLocation = new Location(startLat,startLong);
        endLocation = new Location(endLat,endLong);
        dateVal = new Date(date);
    }

    public String getDate() {
        String date = dateVal.getDate();
        return date;
    }

    // uses the Haversine formula to calculate the distance
    // between two coordinates
    public double calculateDistance() {
        double startLat, startLong;
        double endLat, endLong;
        startLat = startLocation.getLat();
        startLong = startLocation.getLon();
        endLat = endLocation.getLat();
        endLong = endLocation.getLon();
        double R = 6371; // Radius of earth in km
        double distanceLat = deg2rad(endLat - startLat);
        double distanceLon = deg2rad(endLong - startLong);
        double a =
            Math.sin(distanceLat/2) * Math.sin(distanceLat/2) +
            Math.cos(deg2rad(startLat)) * Math.cos(deg2rad(endLat)) *
            Math.sin(distanceLon/2) * Math.sin(distanceLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // distance in kilometers
        return d;
    }

    // this function is required for the calculateDistance function
    public double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

    public double getElapsedMinutes() {
        double elapsedMinutes = (endTime.calculateSecondsSinceMidnight() - 
            startTime.calculateSecondsSinceMidnight()) / 60;
        return elapsedMinutes;
    }

    public double calculatePace() {
        // dividing by 60 converts the seconds into minutes
        double elapsedMinutes = (endTime.calculateSecondsSinceMidnight() - 
            startTime.calculateSecondsSinceMidnight()) / 60;
        double pace = elapsedMinutes / calculateDistance();
        return pace;
    }

    // used to print all the information needed from all the calculations and the info entered
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %.2f %.2f",dateVal.toString(),startLocation.toString(),
            startTime.toString(),endLocation.toString(),endTime.toString(),calculateDistance(),calculatePace());
    }
}

public class RunDraw {
    public static void main (String[] args) {
        // creates the frame object and allows it to be visible
        ArrayList<Run> runs = new ArrayList<Run>();
        RunFrame rf = new RunFrame(runs);
        rf.setVisible(true);

        // creates the frame object for the SoupRun program
        SoupFrame sfrm = new SoupFrame();
        WelcomeSoupPanel btp = new WelcomeSoupPanel();
        RunDraw rd = new RunDraw();
        sfrm.setVisible(false);
    }
}