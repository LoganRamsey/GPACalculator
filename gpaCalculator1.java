import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;



public class gpaCalculator1 extends JFrame {
	private JFrame mainFrame;
	private JPanel controlPanel;
	private JTextArea credits, grade, name, targetGPA;
	private JButton enter, fifteen, clear, computeGPA, compRequired;
	private JComboBox delete;
	private JList list;
	private JLabel instructions, courses, currentGPA, requiredGPA;
		
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
		if(classes.size() < 45) {
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
	
	public double gpaAvg(ArrayList<Course> classes) {
		double cumGPA = 0.0;
		if(classes.size() > 0) {
			for(Course c: classes) {
				if(!c.getGrade().equals("Unknown")) {
					cumGPA += (c.letterToGpa()*c.getCredits());
				}
			}
			cumGPA /= totalCredits(classes);
		}
		return cumGPA;
	}
	
	public gpaCalculator1() {
		createAndShowGui();
	}
	
	public static void main(String[]args) {
		gpaCalculator1 calc = new gpaCalculator1();
		calc.showBorderLayout();
	}
	
	private void createAndShowGui() {
		mainFrame = new JFrame("Calculator");
		mainFrame.setSize(600, 600);
		mainFrame.setLayout(new BorderLayout());
		instructions = new JLabel("", JLabel.LEFT);
		
		
		mainFrame.add(instructions);
		
		
		
		mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
		
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new GridBagLayout());
	      
	      mainFrame.add(instructions);
	      mainFrame.add(controlPanel);
	      mainFrame.setVisible(true);  
	}
	
	private void showBorderLayout() {
		  instructions.setText("Add courses by entering credit hours, grade, and name");
		  

	      ArrayList<Course> courseList = new ArrayList<Course>();//list of courses

	      JPanel panel = new JPanel();
	      
	      panel.setOpaque(true);
	      BorderLayout layout = new BorderLayout();
	      layout.setHgap(15);
	      layout.setVgap(15);	      
	      panel.setLayout(layout); 

	      credits = new JTextArea("Enter credit hours");
	      grade = new JTextArea("Enter course grade");
	      name = new JTextArea("Enter course name");
	       
	      panel.add(credits, BorderLayout.NORTH);
	      panel.add(grade, BorderLayout.CENTER);
	      panel.add(name, BorderLayout.SOUTH);
	      controlPanel.add(panel);
	      
	      /*enter = new JButton("Add course");
	      enter.setActionCommand("click");
	      fifteen = new JButton("Add 15 credit hours");
	      fifteen.setActionCommand("click");
	      clear = new JButton("Clear course list");
	      clear.setActionCommand("click");
	     
	      JPanel panel2 = new JPanel();
	      c.gridx = 1; c.gridy = 0;
	      BorderLayout layout2 = new BorderLayout();
	      panel2.setLayout(layout2);
	      panel2.add(enter,BorderLayout.SOUTH);
	      panel2.add(fifteen, BorderLayout.CENTER);
	      panel2.add(clear, BorderLayout.NORTH);
	      controlPanel.add(panel2, c);
	      
	      list = new JList(courseList.toArray());
	      list.setVisibleRowCount(courseList.size());
	      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);*/
	      
	      /*JPanel panel3 = new JPanel();
	      c.gridx = 2; c.gridy = 0;
	      c.gridwidth = 2;
	      BorderLayout layout3 = new BorderLayout();
	      panel3.setLayout(layout3);
	      panel.setOpaque(true);
	      layout3.setHgap(15);
	      layout3.setVgap(15);
	      panel3.add(new JScrollPane(list));
	      controlPanel.add(panel3, c);*/
	      
	      /*currentGPA = new JLabel("Current GPA: ");
	      targetGPA = new JTextArea("Enter target GPA");
	      requiredGPA = new JLabel("Required GPA: ");
	      computeGPA = new JButton("Compute current GPA");
	      computeGPA.setActionCommand("click");
	      compRequired = new JButton("Compute required GPA");
	      compRequired.setActionCommand("click");
	      
	      JPanel panel4 = new JPanel();
	      c.gridx = 1; c.gridy = 1;
	      BorderLayout layout4 = new BorderLayout();
	      panel4.setLayout(layout4);
	      panel.setOpaque(true);
	      layout4.setHgap(15);
	      layout4.setVgap(15);
	      panel4.add(currentGPA, BorderLayout.NORTH);
	      panel4.add(targetGPA, BorderLayout.SOUTH);
	      panel4.add(requiredGPA);
	      controlPanel.add(panel4,c); */
	      
	     /* JPanel panel5 = new JPanel();
	      c.gridx = 0; c.gridy = 2;
	      BorderLayout layout5 = new BorderLayout();
	      panel5.setLayout(layout5);
	      layout5.setHgap(15);
	      layout5.setVgap(15);
	      panel5.add(computeGPA, BorderLayout.NORTH);
	      panel5.add(compRequired, BorderLayout.SOUTH);
	      controlPanel.add(panel5,c);
	      
	      
	      
	      
	      controlPanel.add(panel4);
	      controlPanel.add(panel5);*/
	      
	      mainFrame.setVisible(true); 
	      
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
						cs = "Courses: " + addedCourses(courseList);
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
			
			class clearListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().equals("click")) {
						removeAllCourses(courseList);
						courses.setText(addedCourses(courseList));
					}
				}
			}
			
			class currentListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().equals("click")) {
						currentGPA.setText(Double.toString(gpaAvg(courseList)));
					}
				}
			}
			
			class requiredListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().equals("click")) {
						requiredGPA.setText(Double.toString(reqGpa(courseList)));
					}
				}
			}
			
			/*enter.addActionListener(new enterListener());
			fifteen.addActionListener(new fifteenListener());
			clear.addActionListener(new clearListener());
			computeGPA.addActionListener(new currentListener());
			compRequired.addActionListener(new requiredListener());*/
	}
		 
	
	
	public double reqGpa(ArrayList<Course> classes) {
		double target = Double.valueOf(targetGPA.getText());
		double required = ((target/totalCredits(classes) - gpaAvg(classes)*currentCredits(classes)))/untakenCredits(classes); 
		if(required > 4.0) {
			System.out.println("You should add more credit hours");
		}
		if(required < 2.0) {
			System.out.println("You can take fewer credit hours");
		}
		return required;
	}
}

		
//https://www.tutorialspoint.com/swing/swing_borderlayout.htm

	


