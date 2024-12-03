package bd1;
import dataStructures.*;

public class Student {
	public String code;
	public double GPA;
	public Chain lessons; 
	int count = 0;
	float creditCount = 0;
	double total = 0;
	public Student(String code) {
		this.code = code;
		lessons = new Chain();
	}
	
	public void addLesson(Lessons lesID) {
		lessons.add(lessons.size(),lesID); 
	}
	
	public void calcGPA(Lessons lesID) {
		int point = lesID.score;
		float credit = ((Subject)lesID.learned).credit;
		
		count++;
		creditCount+=credit;
		
		if(point>=96 && point<=100) {
			total += 4.0*credit;
		}
		else if(point<=95 && point>=91) {
			total += 3.7*credit;
		}
		else if(point<=90 && point>=88) {
			total += 3.4*credit;
		}
		else if(point<=87 && point>=84) {
			total += 3.0*credit;
		}
		else if(point<=83 && point>=81) {
			total += 2.7*credit;
		}
		else if(point<=80 && point>=78) {
			total += 2.4*credit;
		}
		else if(point<=77 && point>=74) {
			total += 2.0*credit;
		}
		else if(point<=73 && point>=71) {
			total += 1.7*credit;
		}
		else if(point<=70 && point>=68) {
			total += 1.4*credit;
		}
		else if(point<=67 && point>=64) {
			total += 1.0*credit;
		}
		else if(point<=63 && point>=61) {
			total += 0.7*credit;
		}
		else {
			total += 0*credit;
		}
		this.GPA = total/creditCount;
	}
}