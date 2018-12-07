/* Nadia Beidas coded this portion
 
* This project is entitled SouperRun. 
* This portion of the program will ask the user for the miles ran.  
* If the user successfully burned as much or more calories than the calories in soup, 
* then the user is allowed to consume the soup.
* Otherwise they will see a red square. 
* In this program, the SoupPanel utilizes the Graphics component 
* to draw the bowls of soup in accordance with the amount of calories burned.
* The WelcomeSoupPanel shows the welcome message.
* The SoupFrame houses the WelcomeSoupPanel, File Menu, soup choices, and the SoupPanel.
* This program supports Text Serialization. 
* Users can print their last selected soup to a file.  
* Future enhancements include using images instead of Graphics to draw the soups,
* and adding options with the soup(bread, chips, and/or apple) 
* with extra calories to deduct from the calories burned. 
*/

import javax.swing.JPanel;
import java.awt.BorderLayout; //Import statements
import java.awt.Button;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;


class Soups { // Soup class
	private String broccoliCheddar; //yellow soup
	private String tomato; //red soup
	private String blackBean; //black or brown soup
	private String vegetable; //green soup
	private String redOnion; //purple soup
	private String blueChicken; //blue soup
	private String soupType; //returns the type of soup
	
	public String getSoupType() { //get function
		return soupType;
	}
	
	public void setSoupType(String soupType) { //set function
		this.soupType = soupType;
	}
	
	public Soups() {
		setSoupType(soupType); //constructor
	}
	
	@Override
	public String toString() { //toString
		return String.format("Soup = %s", soupType);
	}
}

class WelcomeSoupPanel extends JPanel { //This panel houses the welcome message
	private String welcome; //Welcome message
	
	
	public String getWelcome() { //get function
		return welcome;
	}

	public void setWelcome(String welcome) { //set function
		this.welcome = welcome;
	}
	
	public void paintComponent(Graphics g) { //Graphics
	     super.paintComponent(g);
	     g.setFont(new Font("Blackadder ITC", Font.BOLD, 30));
	     g.drawString(welcome, 10, 70);
	    }
	
	public WelcomeSoupPanel() {
		welcome = "Savor the aroma. Please select your soup:"; //Welcome message with font and size
		setPreferredSize(new Dimension(300, 82));
	}
	
	
}

class SoupPanel extends JPanel { //This panel houses the soup and draws the soup
	private int choice; //Choice of Soup //Integer
	private int milesRan; //Miles ran //Integer
	private int cheeseCal = 260; //Calories for broccoli cheddar soup
	private int tomCal = 74; //Calories for tomato soup
	private int beanCal = 140; //Calories for black bean soup
	private int vegCal = 49; //Calories for vegetable soup
	private int onionCal = 55; //Calories for red onion soup
	private int mulCal = 107; //Calories for mulukhiya, a Mediterranean soup
	private int blueCal = 162; //Calories for blue chicken soup
	private int caloriesBurned; //Calories burned during the run
	
	
	
	private final int broccoliCheddar = 1; //yellow soup 1 //final unchanging
	private final int tomato = 2; //red soup 2
	private final int blackBean = 3; //black or brown soup 3
	private final int vegetable = 4; //orange soup 4
	private final int redOnion = 5; //purple soup 5
	private final int mulukhiya = 6; //green soup  
	private final int blueChicken = 7; //blue soup 7	
	
	public void setChoice(Integer choice) { //Set function for choice
		this.choice = choice;
	}

	public SoupPanel (Integer milesRan, Integer choice) { // Constructor for SoupPanel
		this.milesRan = milesRan;
		this.choice = choice;
	}
	public int getMilesRan() {
		return milesRan;
	}

	public void setMilesRan(int milesRan) {
		this.milesRan = milesRan;
	} 

	 
	@Override
	public void paintComponent(Graphics g) { //Drawing abilities for soups
		super.paintComponent(g);
		if (choice == 1 && (this.milesRan * 100 >= cheeseCal )) { //Allowing the user broccoli cheddar soup
			System.out.println("Choice is 1");
			g.setColor(Color.YELLOW);
			g.fillOval(105, 40, 100, 100);
			g.drawOval(95, 30, 120, 120);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Congrats! Your theme song is A Whole New World!",20,20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Have a SOUPer day and may your heart be as warm as your soup!", 2, 180);
			System.out.println("Congratulations! Gold star! This soup says you are a gold medalist!");	
		}
		else if (choice ==2 && (this.milesRan * 100 >= tomCal)) { //Allowing the user tomato soup
			g.setColor(Color.RED);
			g.fillOval(105, 40, 100, 100);
			g.drawOval(95, 30, 120, 120);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Congratulations! Your theme song is Go The Distance!",20,20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Have a SOUPer day and may your heart be as warm as your soup!", 2, 180);
			System.out.println("Congratulations! You are a powerful ruler! All should bow down to your excellency!");
		}
		else if (choice ==3 && (this.milesRan * 100 >= beanCal)) { //Allowing the user black bean soup 
			g.setColor(Color.BLACK);
			g.fillOval(105, 40, 100, 100);
			g.drawOval(95, 30, 120, 120);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Congratulations! Your theme song is Colors of the Wind!",20,20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Have a SOUPer day and may your heart be as warm as your soup!", 2, 180);
			System.out.println("Congratulations! You are in touch with the elements of the earth!");
		}
		else if (choice == 4  && (this.milesRan * 100 >= vegCal)) { //Allowing the user vegetable soup
			g.setColor(Color.ORANGE);
			g.fillOval(105, 40, 100, 100);
			g.drawOval(95, 30, 120, 120);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Congrats! Your themesong is From a Distance!",20,20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Have a SOUPer day and may your heart be as warm as your soup!", 2, 180);
			System.out.println("Congratulations! You are living a healthy life!");
		}
		else if (choice == 5 && (this.milesRan * 100 >= onionCal)) { //Allowing the user onion soup
			g.setColor(Color.MAGENTA);
			g.fillOval(105, 40, 100, 100);
			g.drawOval(95, 30, 120, 120);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Congrats! Your theme song is I See The Light!",20,20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Have a SOUPer day and may your heart be as warm as your soup!", 2, 180);
			System.out.println("Congratulations! You are creative and artistic and will create many award-winning programs!");
		}
		else if (choice == 6 && (this.milesRan * 100 >= mulCal)) { //Allowing the user mulukhiya
			g.setColor(Color.GREEN); //mulkhia
			g.fillOval(105, 40, 100, 100);
			g.drawOval(95, 30, 120, 120);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Congrats! Your theme song is Written in the Stars!",20,20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Have a SOUPer day and may your heart be as warm as your soup!", 2, 180);
			System.out.println("Congratulations! Mediterranean cuisine is divine!"); 
		}
		else if (choice == 7 && (this.milesRan * 100 >= blueCal)) { //Allowing the user blue chicken soup
			g.setColor(Color.CYAN);
			g.fillOval(105, 40, 100, 100);
			g.drawOval(95, 30, 120, 120);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Congrats! Your theme song is Blue Christmas!",20,20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("Have a SOUPer day and may your heart be as warm as your soup!", 2, 180);
			System.out.println("Congratulations! Foreign travel and cuisine are in your future!");
		}
		else if (choice == 0) { // This is the opening message in the soup panel when the program first opens
			g.setColor(Color.BLACK);
			g.setFont(new Font("Footlight MT Light", Font.BOLD, 16));
			g.drawString("It's coming soon..." , 200, 200);
		}
		else {
			g.setColor(Color.RED); //For users who did not earn soup
			g.fillRect(150, 150, 200, 200);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 16));
			g.drawString("DO NOT COLLECT A BOWL OR SPOON! ONLY YOUR DOOM!", 0, 145);
			g.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 55));
			g.drawString("STOP...", 155, 220);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 16));
			g.drawString("IN THE NAME OF SOUP!", 155, 260);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 14));
			g.drawString("YOU DID NOT EARN SOUP!", 155,280);
			g.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 16));
			g.drawString("HOW DARE YOU! YOUR THEME SONG IS HEARTBREAKER!", 0, 365);
			System.out.println("You did not earn soup!");	
		}
	}
	
}

class SoupFrame extends JFrame { //This class houses the program
	//int milesRan; //Miles ran
	private int cheeseCal = 260; //Calories for broccoli cheddar soup
	private int tomCal = 74; //Calories for tomato soup
	private int beanCal = 140; //Calories for black bean soup
	private int vegCal = 49; //Calories for vegetable soup
	private int onionCal = 55; //Calories for red onion soup
	private int mulCal = 107; //Calories for mulukhiya, a Mediterranean soup
	private int blueCal = 162; //Calories for blue chicken soup
	private int caloriesBurned; //Calories burned during the run
	private int choice; //Choice of Soup //Integer
	private int milesRan; //Miles ran //Integer
	String rememberSoup = "";
	
	public void SetMenu() { //Creating the menu 
		JMenuBar sbar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		JMenuItem miOpen = new JMenuItem("Open");
		mnuFile.add(miOpen);
		sbar.add(miOpen);
		JMenuItem miSave = new JMenuItem("Save");
		mnuFile.add(miSave);
		sbar.add(miSave);
		JMenuItem miExit = new JMenuItem("Exit");
		mnuFile.add(miExit);
		sbar.add(miExit);
		
		
		
		miSave.addActionListener(new ActionListener() { //Save
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File f = jfc.getSelectedFile();// Text Serialization
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
						pw.print(rememberSoup);
						//pw.print(choice);
						//if panCenter.broched.isSelected(true) {
							
						//}
						
						//for (Soup s : soups) { //ASK
							//pw.println(s);
						//}
						pw.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Unable to save file.");
					}
				}
			}
		});
	
		mnuFile.add(miExit); 
		miOpen.addActionListener(new ActionListener() { //Open
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				String soup;
				String[] soups;
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {	
					File f = jfc.getSelectedFile();
					Scanner sc = new Scanner(f);
					while (sc.hasNextLine()) {
						soup = sc.nextLine().trim();
						soups = soup.split(" ");
						System.out.println(soup);
						rememberSoup = soup;
						//SoupPanel.setChoice(1);
					}
					//actionPerformed(ActionEvent e) 
					//if rememberSoup = broched
					sc.close();
					repaint();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Unable to open the file.");
					}
				}
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
		sbar.add(mnuFile);
		setJMenuBar(sbar);
		
	}
		
	public SoupFrame() { //SoupFrame constructor
		choice = new Integer(0);
		milesRan = new Integer(0);
		setDefaultCloseOperation(HIDE_ON_CLOSE); //Close
	    setBounds(100,100,600,1000); //Boundaries
	    setTitle("Soups 1.0"); //Title
	    Container c = getContentPane();
	    c.setLayout(new BorderLayout()); //BorderLayout
	    SoupPanel panSouth = new SoupPanel(milesRan,choice); 
	    panSouth.setLayout(new BorderLayout());
	    panSouth.setPreferredSize(new Dimension(400,400));
	    JPanel panCenter = new JPanel();
	    panCenter.setLayout(new GridLayout(10,1));
	    panCenter.setPreferredSize(new Dimension(400,400));
	    JPanel panNorth = new JPanel();
	    panNorth.setLayout(new BorderLayout());
	    panNorth.setPreferredSize(new Dimension(100,100));
	    WelcomeSoupPanel bupan = new WelcomeSoupPanel(); 
	    SetMenu(); //Draws Menu    
	    setMilesRan(milesRan); 
	    panNorth.add(bupan,BorderLayout.NORTH);
	    JTextField milesAccomplished; //User inputs miles ran
	    JLabel enter; //Asks the user to input the miles ran
	    milesAccomplished = new JTextField(5);
	    enter = new JLabel("Enter the miles ran: ");
	    panCenter.add(enter,BorderLayout.CENTER);
	    panCenter.add(milesAccomplished,BorderLayout.CENTER);
	        
	    JRadioButton  broched = new JRadioButton ("Broccoli Cheddar"); //Broccoli cheddar soup radio button
	    broched.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.out.println("Broccoli Cheddar");
	        	//panSouth.setChoice(1); This option is kept in case a future version would paint the soup via radio button.
	        	//panSouth.repaint();
	        }
	    });
	    JRadioButton toma = new JRadioButton("Tomato"); //Tomato soup radio button
	    toma.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.out.println("Tomato");
	        	//panSouth.setChoice(2);
	        	//panSouth.repaint();
	        }
	    });
	    JRadioButton bla = new JRadioButton("Black Bean"); //Black bean soup radio button 
	    bla.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	        	System.out.println("Black Bean");
	        	//panSouth.setChoice(3);
	        	//panSouth.repaint();
	        }
	    });
	    JRadioButton veggie = new JRadioButton("Vegetable"); //Vegetable soup radio button
	    veggie.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.out.println("Vegetable");
	        	//panSouth.setChoice(4);
	        	//panSouth.repaint();
	        }
	    });
	    JRadioButton redon = new JRadioButton("Red Onion"); //Red Onion soup radio button
	    redon.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	System.out.println("Red Onion");
	        	//panSouth.setChoice(5);
	        	//panSouth.repaint();
	        }
	    });
	    JRadioButton mulk = new JRadioButton("Mulukhiya"); //Mulukhiya soup radio button
	    mulk.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.out.println("Mulukhiya");
	        	//panSouth.setChoice(6);
	        	//panSouth.repaint();
	        }
	    });
	    JRadioButton bluc = new JRadioButton("Blue Chicken"); //Blue Chicken soup radio button
	    bluc.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	System.out.println("Blue Chicken");
	        	//panSouth.setChoice(7);
	        	//panSouth.repaint();
	        }
	    });
	    
	    ButtonGroup bg = new ButtonGroup(); //Houses all buttons together
	    bg.add(broched); //Adding all the soups to the button group
	    bg.add(redon);
	    bg.add(toma);
	    bg.add(bla);
	    bg.add(veggie);
	    bg.add(mulk);
	    bg.add(bluc);
	    JButton btnEarn = new JButton("Earning Soup");
	    panCenter.add(broched);
	    panCenter.add(toma);
	    panCenter.add(bla);
	    panCenter.add(veggie);
	    panCenter.add(redon);
	    panCenter.add(mulk);
	    panCenter.add(bluc);
	    panCenter.add(btnEarn);
	    c.add(panNorth,BorderLayout.NORTH);
	    c.add(panCenter,BorderLayout.CENTER);
	    c.add(panSouth,BorderLayout.SOUTH);
	    
	    
	    
	    btnEarn.addActionListener(new ActionListener(){ //Button that combines miles ran with the soup selection
	    	public void actionPerformed(ActionEvent e) {
	    		try { 
		    		milesRan = Integer.valueOf(milesAccomplished.getText());
		    		if (broched.isSelected()) { 
		    		choice = 1;
		    		panSouth.setChoice(1);
		    		rememberSoup = "Broccoli Cheddar";
		    		panSouth.setMilesRan(milesRan);
		    		panSouth.repaint();
		    		}
		    		else if (toma.isSelected()) { 
		    			choice = 2;
		    			panSouth.setChoice(2);
		    			rememberSoup = "Tomato";
		    			panSouth.setMilesRan(milesRan);
		    			panSouth.repaint();		
		    		}
		    		else if (bla.isSelected()) { 
		    			choice = 3;
		    			panSouth.setMilesRan(milesRan);
		    			rememberSoup = "Black Bean";
		    			panSouth.setChoice(3);
		    			panSouth.repaint();
		    		}
		    		else if (veggie.isSelected()) { 
		    			choice = 4;
		    			panSouth.setMilesRan(milesRan);
		    			rememberSoup = "Vegetable";
		    			panSouth.setChoice(4);
		    			panSouth.repaint();
		    		}
		    		else if (redon.isSelected()) { 
		    			choice = 5;
		    			panSouth.setMilesRan(milesRan);
		    			rememberSoup = "Red Onion";
		    			panSouth.setChoice(5);
		    			panSouth.repaint();
		    		}
		    		else if (mulk.isSelected()) { 
		    			choice = 6;
		    			panSouth.setMilesRan(milesRan);
		    			rememberSoup = "Mulukhiya";
		    			panSouth.setChoice(6);
		    			panSouth.repaint();
		    		}
		    		else if (bluc.isSelected()) { 
		    			choice = 7;
		    			panSouth.setMilesRan(milesRan);
		    			rememberSoup = "Blue Chicken";
		    			panSouth.setChoice(7);
		    			panSouth.repaint();
		    		}
		    		else {
		    			choice = -1; //The user has not earned soup
		    			panSouth.setMilesRan(milesRan);
		    			repaint();
		    			panSouth.setChoice(-1);
		    			panSouth.repaint();
		    		}
	    		}
	    		catch (Exception ex) { //Error message if the user has made not made a selection or not entered numbers.
	    			JOptionPane.showMessageDialog(null, "Please enter numbers and choose your soup.");
	    		}
	    	}
	    });	    	
		}

	public int getMilesRan() { //get function for milesRan
		return milesRan;
	}

	public void setMilesRan(int milesRan) { //set function for milesRan
		this.milesRan = milesRan;
	} 
}
/*
public class SoupRun extends JDialog { //Main

	public static void main(String[] args) {
		SoupFrame frm = new SoupFrame();
		WelcomeSoupPanel btp = new WelcomeSoupPanel();
		frm.setVisible(true);
		//RunDraw rd = new RunDraw();
		//frm.setVisible(true);

	}

}
*/
