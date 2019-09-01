import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * * Add courses and calculate the student's current gpa. Also, calculate the required GPA to reach a target GPA
 * @author Logan Ramsey (lr8fm)
 *
 */

public class calculator extends JFrame {
    JPanel container; //main
    JPanel p1;/** menu panels**/
    JPanel p2;
    JPanel p3;
    GridBagLayout thelayout; //panel layout
    GridBagLayout layout1;//first tab layout
    GridBagLayout layout2;//second tab layout
    GridBagLayout layout3;//third tab layout
    GridBagConstraints gbc;//constraints for layout

    JScrollPane scroll; //pane for course list
    JTabbedPane menu;//menus
    JComboBox grade, select;//drop down menu for selecting grade, drop down menu for selecting course
    JTextArea credits, name, a, targetGPA; //text area for credit hour input, course name input, course list display, and target GPA
    JButton enter, fifteen, delete, clear, compCurrent, compRequired;/*buttons for adding course, adding 15 credit hours, deleting one course, 
    																clearing a course list, calculating current GPA, calculating required GPA*/
    JLabel hours, letter, course, tar, list, currentGPA, requiredGPA, recommend; //label instructing user to enter credit hours, grade, course name, and target gpa and for displaying current GPA, requiredGPA, and course load suggestion
    
    ArrayList<Course> courseList;//list of courses
    
    /**calculates current gpa**/
	public double gpaAvg(ArrayList<Course> classes) {
		double cumGPA = 0.0;
		if(classes.size() > 0) {
			for(Course c: classes) {
				if(!c.getGrade().equals("Unknown")) {
					cumGPA += (c.letterToGpa()*c.getCredits());
				}
			}
			cumGPA /= currentCredits(classes);
		}
		return cumGPA;
	} 
	
    /**calculates total credits in course list**/
	public int totalCredits(ArrayList<Course> classes) {
		int total = 0;
		for(Course c: classes) {
			total += c.getCredits();
		}
		return total;
	}
	
    /**calculates current credits taken in course list**/
	public int currentCredits(ArrayList<Course> classes) {
		int current = 0;
		for(Course c: classes) { /**taken courses have a grade**/
			if(!c.getGrade().equals("Unknown")) {
				current += c.getCredits();
			}
		}
		return current;
	}
	
    /**calculates untaken credits in course list**/
	public int untakenCredits(ArrayList<Course> classes) {
		int untaken = 0;
		for(Course c: classes) { /**untaken credits do not have a grade **/
			if(c.getGrade().equals("Unknown")) {
				untaken += c.getCredits();
			}
		}
		return untaken;
	}
	
    /**calculates required gpa**/
	public double reqGpa(ArrayList<Course> classes) {
		double target = Double.valueOf(targetGPA.getText());
		double required = 0.0;
		if(untakenCredits(classes) > 0) {
			required = ((target*totalCredits(classes)) - (gpaAvg(classes)*currentCredits(classes)))/untakenCredits(classes); 
		}
		else { 
			required = 0.0; 
		}
		return required;
	}
	
    /**clears course list**/
	public ArrayList<Course> removeAllCourses(ArrayList<Course> classes) {
		classes.clear();
		return classes;
	}
	
    /**creates a string w/ added courses**/
	public String addedCourses(ArrayList<Course> classes) {
		String cs = "";
		for(Course c: classes) {
			cs += c.toString() + "\n";
		}
		return cs;
	}
	
    /**calculator constructor**/
    public calculator() { 
    	
        AddtoPane v = new AddtoPane();//Instance of addtopane 
        container = new JPanel();
        thelayout = new GridBagLayout();
        layout1 = new GridBagLayout();
        layout2 = new GridBagLayout();
        layout3 = new GridBagLayout();
        gbc = new GridBagConstraints();
        container.setLayout(thelayout);
        getContentPane().add(container);
        courseList =  new ArrayList<Course>();

        JLabel title = new JLabel("GPA Calculator"); //title of program
        
        /**menu(tabs)**/
        menu = new JTabbedPane();
        p1 = new JPanel();
        p1.setLayout(layout1);
        p2 = new JPanel();
        p2.setLayout(layout2);
        p3 = new JPanel();
        p3.setLayout(layout3);
        menu.addTab("Add courses", p1);
        menu.addTab("Student information", p2);
        menu.addTab("Manage courses", p3);
        
        /**first tab - add courses**/
        hours = new JLabel("Enter course credit hours");
        letter = new JLabel("Select course grade");
        course = new JLabel("Enter course name");
        credits = new JTextArea();
        credits.setPreferredSize(new Dimension(100,20));
        String[] letters = {"A+","A","A-","B+","B","B-","C+","C","C-","D+","D","F","Unknown"}; //letter grades
        //unknown grade = optional grade
        grade = new JComboBox();
        grade.setModel(new DefaultComboBoxModel());
        grade.setPreferredSize(new Dimension(50,50));
        for(String l: letters) {
        	grade.addItem(l);
        } //drop down menu with grades from letters array 
        
        name = new JTextArea();//course name
        name.setPreferredSize(new Dimension(80,20));
        enter = new JButton("Add a course");//add course button 
        enter.setActionCommand("click");
        
        fifteen = new JButton("Add 15 credit hours");//add 15 credit hours
        fifteen.setActionCommand("click");
        
        /**second tab - student information**/
        tar = new JLabel("Enter a target GPA");
        targetGPA = new JTextArea();
        targetGPA.setPreferredSize(new Dimension(100,20));
        compCurrent = new JButton("Compute current GPA");
        compCurrent.setActionCommand("click");
        
        compRequired = new JButton("Compute required GPA");
        compRequired.setActionCommand("click");
        
        JLabel currentGPA = new JLabel("Current GPA: ");
        JLabel requiredGPA = new JLabel("Required GPA: ");
        recommend = new JLabel("Suggestion: None");
        
        /**third tab - delete courses**/
        /**scroll pane textarea**/
        a = new JTextArea("No courses");
        a.setSize(new Dimension(100,100));
        a.setEditable(false);
        
        /**scroll pane - course select menu**/
        scroll = new JScrollPane(a);
        scroll.setPreferredSize(new Dimension(100,100));
        select = new JComboBox(courseList.toArray());
        select.setModel(new DefaultComboBoxModel());
        select.setPreferredSize(new Dimension(100,100));
        
        list = new JLabel("Course List");
        delete = new JButton("Delete course"); //delete course button
        delete.setActionCommand("click");
        
        clear = new JButton("Remove all courses");//clear list button
        clear.setActionCommand("click");
        
        /**add objects to container and panels**/
        v.addobjects(title, container, layout1, gbc, 0, 0, 1, 1);
        v.addobjects(menu, container, thelayout, gbc, 0, 1, 1, 1);
        //Panel 1
        v.addobjects(hours, p1, layout1, gbc, 0, 0, 1, 1);
        v.addobjects(credits, p1, layout1, gbc, 1, 0, 1, 1);
        v.addobjects(letter, p1, layout1, gbc, 0, 1, 1, 1);
        v.addobjects(grade, p1, layout1, gbc, 1, 1, 1, 1);
        v.addobjects(course, p1, layout1, gbc, 0, 2, 1, 1);
        v.addobjects(name, p1, layout1, gbc, 1, 2, 1, 1);
        v.addobjects(enter, p1, layout1, gbc, 0, 3, 1, 1);
        v.addobjects(fifteen, p1, layout1, gbc, 1, 3, 1, 1);
        //Panel 2
        v.addobjects(currentGPA, p2, layout2, gbc, 0, 1, 1, 1);
        v.addobjects(requiredGPA, p2, layout2, gbc, 0, 2, 1, 1);
        v.addobjects(compCurrent, p2, layout2, gbc, 0, 3, 1, 1);
        v.addobjects(compRequired, p2, layout2, gbc, 0, 4, 1, 1);
        v.addobjects(tar, p2, layout2, gbc, 1, 1, 1, 1);
        v.addobjects(recommend, p2, layout2, gbc, 1, 3, 1, 1);
        v.addobjects(targetGPA, p2, layout2, gbc, 2, 1, 1, 1);
        //Panel 3
        v.addobjects(list, p3, layout3, gbc, 0, 0, 1, 1);
        v.addobjects(delete, p3, layout3, gbc, 2, 0, 1, 1);
        v.addobjects(select, p3, layout3, gbc, 1, 1, 1, 1);
        v.addobjects(scroll, p3, layout3, gbc, 0, 1, 1, 1);
        v.addobjects(clear, p3, layout3, gbc, 2, 1, 1, 1);

        
        class enterListener implements ActionListener /**add course button**/{
			public void actionPerformed(ActionEvent e) {
				String cs = "";
				if(e.getActionCommand().equals("click")) {
					if(name.getText().trim().equals("")) /**name not provided**/{
						name.setText("Unknown");
					} 
					/**course object equals inputs**/
					int hours = Integer.parseInt(credits.getText());
					Course c = new Course(hours, (String)grade.getSelectedItem(), name.getText()); 
					courseList.add(c);
					for(Course c1: courseList) {
						/**changes course label text to course name of course objects in course list**/
						cs += c1.toString() + "\n";
					}
					a.setText(cs);
					credits.setText("");
					name.setText("");
					select.addItem(c.toString());
				}
			}
		}
        
    	class fifteenListener implements ActionListener /**command for add 15 credit hours button**/ {
			public void actionPerformed(ActionEvent e) {
				String cs = "";
				if(e.getActionCommand().equals("click")) {/**assumption: 15 credit hours = 5 3 credit courses w/ unknown grade and name**/
					Course one = new Course(3,"Unknown", "Unknown");
					Course two = new Course(3,"Unknown", "Unknown");
					Course three = new Course(3,"Unknown", "Unknown");
					Course four = new Course(3,"Unknown", "Unknown");
					Course five = new Course(3,"Unknown", "Unknown");
					courseList.add(one);
					courseList.add(two);
					courseList.add(three);
					courseList.add(four);
					courseList.add(five);
					for(Course c1: courseList) {
					/**changes course label text to course name of course objects in course list**/
						cs += c1.toString() + "\n";
					}
					a.setText(cs);
					select.addItem(one.toString());
					select.addItem(two.toString());
					select.addItem(three.toString());
					select.addItem(four.toString());
					select.addItem(five.toString());
				}
			}
    	}
    	
    	class deleteListener implements ActionListener /**command for deleting one course**/ {
    		public void actionPerformed(ActionEvent e) {
    				String cs = "";
    				if(courseList.size() != 0) {
    					if(e.getActionCommand().equals("click")) {
    						courseList.remove(select.getSelectedIndex());
    						select.removeItem(select.getSelectedItem());
    					}
    					if(courseList.size() == 0) {
    						select.setSelectedItem("");
    					}
    					for(Course c1: courseList) {
						/**changes course label text to course name of course objects in course list**/
    					cs += c1.toString() + "\n";
    					}
    					a.setText(cs);
    					currentGPA.setText("Current GPA: ");
    					requiredGPA.setText("Required GPA: ");
    				}
    			}
    		}
    	
    	class clearListener implements ActionListener /**command for deleting all courses**/ {
			public void actionPerformed(ActionEvent e) {
				if(courseList.size() != 0) {
					if(e.getActionCommand().equals("click")) {
						removeAllCourses(courseList);
						a.setText(addedCourses(courseList));
						select.removeAllItems();
    					currentGPA.setText("Current GPA: ");
    					requiredGPA.setText("Required GPA: ");
    					/**clearing list resets program**/
					}
				}
			}
    	}
    	
		
		class currentListener implements ActionListener /**command for calculating current gpa**/ {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("click")) {
					Double avg = Math.round(gpaAvg(courseList) * 100)/100.00;
					String gpa = Double.toString(avg);
					currentGPA.setText("Current GPA: " + gpa);
				}
			}
		}
		
		class requiredListener implements ActionListener /**command for calculating required gpa**/ {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("click")) {
					if(untakenCredits(courseList) > 0) {
					Double req = Math.round(reqGpa(courseList) * 100)/ 100.00;
					String rgpa = Double.toString(req);
					requiredGPA.setText("Required GPA: " + rgpa);
					if(req > 4.0) {
						recommend.setText("You should add more credit hours");
					}
					if(req < 2.0) {
						recommend.setText("You can take less credit hours");
					}
				  }
					else {
						recommend.setText("You do not have any untaken credits");
					}
				}
				targetGPA.setText("");
			}
		}
    	
		/**action listeners**/
        enter.addActionListener(new enterListener());
        fifteen.addActionListener(new fifteenListener());
        delete.addActionListener(new deleteListener());
        clear.addActionListener(new clearListener());
        compCurrent.addActionListener(new currentListener());
        compRequired.addActionListener(new requiredListener());
        
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    	this.pack();
    	this.setVisible(true);
    	this.setSize(width/3, height/2);
        
    }
    
    public static void main(String[]args) {
    	calculator a = new calculator();
    }
} 
//https://stackoverflow.com//30656473/how-to-use-gridbaglayout
//https://www.javatpoint.com/java-jtabbedpane - JTabbedPane basics