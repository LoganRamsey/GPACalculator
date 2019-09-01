import java.util.ArrayList;

public class Course /**course object holds course information**/{
	private int credits;
	private String grade;
	private String name;
	public Course(int credits) {
		this.credits = credits;
	}
	public Course(int credits, String grade) {
		this.credits = credits;
		this.grade = grade;
	}
	public Course(int credits, String grade, String name) {
		this.credits = credits;
		this.grade = grade;
		this.name = name;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name + ", " + credits + ", " + grade;
	}
	public double letterToGpa() {
		double avg = 0.0;
		if(grade.equals("A+")) {
			avg = 4.0;
		}
		if(grade.equals("A")) {
			avg = 4.0;
		}
		if(grade.equals("A-")) {
			avg = 3.7;
		}
		if(grade.equals("B+")) {
			avg = 3.3;
		}
		if(grade.equals("B")) {
			avg = 3.0;
		}
		if(grade.equals("B-")) {
			avg = 2.7;
		}
		if(grade.equals("C+")) {
			avg = 2.3;
		}
		if(grade.equals("C")) {
			avg = 2.0;
		}
		if(grade.equals("C-")) {
			avg = 1.7;
		}
		if(grade.equals("D+")) {
			avg = 1.3;
		}
		if(grade.equals("D")) {
			avg = 1.0;
		}
		if(grade.equals("F")) {
			avg = 0.0;
		}
		return avg;
	}
}
