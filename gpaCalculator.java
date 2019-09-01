import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;



public class gpaCalculator extends JFrame {
	private JTextArea credits;
	private JTextArea grade;
	private JTextArea name;
	private JTextArea targetGPA;
	private JButton enter;
	private JButton fifteen;
	private JButton clear;
	private JComboBox delete;
	private JButton summary;
	private JLabel instructions;
	private JLabel courses;
	private JLabel currentGPA; 
	private JLabel requiredGPA;
	private FlowLayout layout;
	
	JPanel panel;
	
	
	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {     
	               createAndShowGUI();
	            }
	        });
	}
	
	public double gpaAvg(ArrayList<Course> classes) {
		double cumGPA = 0.0;
		for(Course c: classes) {
			cumGPA += c.letterToGpa();
		}
		return cumGPA;
	}
	
	public int totalCredits(ArrayList<Course> classes) {
		int total = 0;
		for(Course c: classes) {
			total += c.getCredits();
		}
		return total;
	}
	
	public int currentCredits(ArrayList<Course> classes) {
		int current = 0;
		for(Course c: classes) {
			if(c.getGrade().equals("Unknown")) {
				current += c.getCredits();
			}
		}
		return current;
	}
	
	public int untakenCredits(ArrayList<Course> classes) {
		int untaken = 0;
		for(Course c: classes) {
			if(c.getGrade().equals("Unknown")) {
				untaken += c.getCredits();
			}
		}
		return untaken;
	}
	
	public void addCreditHours(ArrayList<Course> classes) {
		Course a = new Course(3,"Unknown", "Unknown");
		Course b = new Course(3,"Unknown", "Unknown");
		Course c = new Course(3,"Unknown", "Unknown");
		Course d = new Course(3,"Unknown", "Unknown");
		Course e = new Course(3,"Unknown", "Unknown");
		classes.add(a);
		classes.add(b);
		classes.add(c);
		classes.add(d);
		classes.add(e);
	}
	
	public String addedCourses(ArrayList<Course> classes) {
		String cs = "";
		for(Course c: classes) {
			cs += c.toString();
		}
		return cs;
	}
	
	public ArrayList<Course> removeAllCourses(ArrayList<Course> classes) {
		classes.clear();
		return classes;
	}
	
	private static void createAndShowGUI() {
	        //Create and set up the window. 
	        gpaCalculator frame = new gpaCalculator();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //Set up the content pane.
	        frame.addComponentsToPane(frame.getContentPane());
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	}
	 
	public void addComponentsToPane(Container pane) {
		ArrayList<Course> courseList = new ArrayList<Course>();//list of courses
		class enterListener implements ActionListener /**add course button**/{
			public void actionPerformed(ActionEvent e) {
				String cs = "";
				if(e.getActionCommand().equals("click")) {
					if(name.getText().trim().equals("")) /**name not provided**/{
						name.setText("Unknown");
					} 
					if(grade.getText().trim().equals("")) /**grade not provided**/ {
						grade.setText("Unknown");
					} 
					/**course object equals inputs**/
					int hours = Integer.parseInt(credits.getText());
					Course c = new Course(hours, grade.getText(), name.getText()); 
					courseList.add(c);
					/**changes course label text to course name of course objects in course list**/
					/*for(Course c1: courseList) {
						cs += c1.toString();
					}*/
					cs = addedCourses(courseList);
					courses.setText(cs);
				}
			}
		}
		
		
		class fifteenListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("click")) {
					addCreditHours(courseList);
					courses.setText(addedCourses(courseList));
				}
			}
		}
		
		/*class deleteListener implements ActionListener() {
			
		}*/
		
		class clearListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("click")) {
					removeAllCourses(courseList);
					courses.setText(addedCourses(courseList));
				}
			}
		}
		
				//panel1
				layout = new FlowLayout();
				panel = new JPanel();
			    panel.setLayout(layout);
			    layout.setAlignment(FlowLayout.LEFT);
			    
				//button - add course
			    enter = new JButton("Add course");
			    enter.setActionCommand("click");
			    enter.addActionListener(new enterListener());
			    panel.add(enter);
			    
			    //button - add fifteen credit hours
			    fifteen = new JButton("Add 15 Credit Hours");
			    fifteen.setActionCommand("click");
			    fifteen.addActionListener(new fifteenListener());
			    panel.add(fifteen);
			    
			    //button - clear all courses
			    clear = new JButton("Delete all courses");
			    clear.setActionCommand("click");
			    clear.addActionListener(new clearListener());
			    panel.add(clear);
			    
			    
			    //label - courses
			    courses = new JLabel("Added courses: ");
			    panel.add(courses);
			    
			    //textfield - credits
			    credits = new JTextArea("Enter credit hours");
			    credits.setEditable(true);
			    panel.add(credits);
			    
			    //textfield - grade
			    grade = new JTextArea("Enter course grade");
			    grade.setEditable(true);
			    panel.add(grade);
			    
			    //textfield - name
			    name = new JTextArea("Enter course name");
			    name.setEditable(true);
			    panel.add(name);
			    
	
		
	    //add panel to content pane
	    //pane.add(panel);
	    
	    //next screen
	    JPanel panel2 = new JPanel();
	    panel2.setLayout(layout);
	    layout.setAlignment(FlowLayout.CENTER);
	    panel2.setVisible(false);
	    
	    //button
	    /*summary = new JButton("View summary");
	    summary.setActionCommand("click");
	    summary.addActionListener(new summaryListener());
	    panel.add(summary);*/
	    //textfield 
	    targetGPA = new JTextArea("Enter target GPA");
	    //panel2.add(targetGPA);
	    //label - current gpa
	    currentGPA = new JLabel("Current GPA");
	    //panel2.add(currentGPA);
	    //label - required gpa
	    requiredGPA = new JLabel("Required GPA is: ");
	    //panel2.add(requiredGPA);
	    
	    //add panel to content pane
	    pane.add(panel);
	    //pane.add(panel2);
	    
		/*class summaryListener implements ActionListener  {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("click")) {
					panel2.setVisible(true);
				}
			}
		}
	    
	}*/ /**view summary button**/
		 
	
	}
	public double reqGpa(ArrayList<Course> classes) {
		double target = Double.valueOf(targetGPA.getText());
		double required = (gpaAvg(classes)*currentCredits(classes)) - (target/totalCredits(classes)); 
		if(required > 4.0) {
			System.out.println("You should add more credit hours");
		}
		if(required < 2.0) {
			System.out.println("You can take fewer credit hours");
		}
		return required;
	}
}

		
		

	


