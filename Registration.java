package bd1;
import dataStructures.*;
import java.text.*;
import java.util.HashSet;
import java.io.*;


public class Registration {
	ArrayLinearList subjectList = new ArrayLinearList();
	ArrayLinearList majorList = new ArrayLinearList();
	ArrayLinearList studentList = new ArrayLinearList();
	
	public Registration() {
		
		String pathSubject = "Subject.txt";
		String pathMajor = "professions.txt";
		String pathExam = "exams.txt";
		
		//subjectiig file aas unshij split hiine
		try{
			FileReader fileSubject = new FileReader(pathSubject); 
			BufferedReader reader = new BufferedReader(fileSubject);
			String line="";
			
			while((line = reader.readLine())!=null) {
			String[] a=line.split("/");   
			Subject subject = new Subject(a[0],a[1],Float.parseFloat(a[2]));
			subjectList.add(subjectList.size(),subject); 
			} 
		} catch(Exception e) {
			 System.out.println("File not found: " + pathSubject);            
			 System.exit(1); 
		}
		
		
		//examiig file aas unshij split hiine
		try {
			FileReader fileExam = new FileReader(pathExam); 
			BufferedReader reader1 = new BufferedReader(fileExam);
			String line = "";
			
			while((line = reader1.readLine()) !=null ) {
				String[] c = line.split("/"); 
				int count = 0; 
				
				for(int j=0; j<studentList.size(); j++) {
					Student t = (Student) studentList.get(j);
					if(t.code.equals(c[0])) {
					count++;
					}
				}
				
				if(count == 0) {
					Student student = new Student(c[0]);
					studentList.add(studentList.size(),student);
				}  
				
				for(int i=0; i<studentList.size(); i++) {
					Student t = (Student)studentList.get(i);
					
					if(t.code.equals(c[0])) {
						for(int j=0; j<subjectList.size(); j++) {
							Subject sub = (Subject)subjectList.get(j);
							
							if(sub.code.equals(c[1])) {
								int c2 = Integer.parseInt(c[2]);
						        Lessons les = new Lessons(sub, c2);
								t.addLesson(les);
								t.calcGPA(les);
							}
						} 
					}
				}
			}
		}
		catch(IOException e){
			System.out.println("File not found: " + pathExam);
		}
		
		
		//majoriig file aas unshij split hiine
		try {
			FileReader fileMajor = new FileReader(pathMajor);
			BufferedReader reader1 = new BufferedReader(fileMajor);
			String line = "";
			while((line = reader1.readLine())!= null) {
				String[] a = line.split("/");
				Major major = new Major(a[0],a[1]);
				majorList.add(majorList.size(), major);
			}
		}
		catch(IOException e) {
			 System.out.println("File not found: " + pathMajor);            
			 System.exit(1); 
		}
	}
	
	//Hicheeluudiin listiig hevlene
	public void printSubList() {
		System.out.println("Hicheeluudiin jagsaalt: ");
		for(int i=0; i<subjectList.size(); i++) {
			System.out.println(((Subject)subjectList.get(i)).name);
		}
	}
	
	//Mergejluudiin listiig hevlene
	public void printMajorList() {
		System.out.println("Mergejluudiin jagsaalt: ");
		for(int i=0; i<majorList.size(); i++) {
			System.out.println(((Major)majorList.get(i)).name);
		}
	} 
	
	//niit oyutnuudiin dundaj golchiig tootsoolno
	public double calcAverageGPA() {
	    if (studentList == null || studentList.isEmpty()) {
	        System.out.println("Oyutnii jagsaalt hooson baina.");
	        return 0.0; 
	    }

	    double total = 0.0;
	    for (int i = 0; i < studentList.size(); i++) {
	        Student student = (Student) studentList.get(i); 
	        total += student.GPA; 
	    }

	    double score = total / studentList.size();
	    return score;
	}

	//3-aas ih F avsan oyutnuudiin code
	public void DeductStudents() {
		System.out.println("Hasagdah oyutnuud:");
		for(int i=0; i<studentList.size(); i++) {
			int count = 0; //
			Chain chain = ((Student)studentList.get(i)).lessons;
			for(int j=0; j<chain.size(); j++) {
				if(((Lessons)(chain.get(j))).score <= 61 ) {
					count ++;
				}
			}
			if(count >=2) {
				System.out.println(((Student)studentList.get(i)).code);
			}
		}
	}
	
	//Hicheel buriin dung hevlene
	public void scoreListByLesson() {
		System.out.println("Hicheel bur deerh dungiin medeelel: ");
		for(int i=0; i<subjectList.size(); i++) {
			Subject neededSub = ((Subject)subjectList.get(i)); 
			System.out.println();
			System.out.println(((Subject)subjectList.get(i)).name + ":");
			for(int j=0; j<studentList.size(); j++) {
				Student neededStu = ((Student)studentList.get(j)); 
				Chain chain = ((Student)studentList.get(j)).lessons;
				for(int k=0; k<chain.size(); k++) {
					Subject neededSub1 = ((Lessons)chain.get(k)).learned;
					if((neededSub.code).equals(neededSub1.code)) {
						System.out.println(neededStu.code + " " + ((Lessons)(chain.get(k))).score);
					}
				}
			}
		}
	}
	
	//Mergejil bur deh dungiin medeelel
	public void scoreListByMajor() {
	    System.out.println("Mergejil bur deerh dungiin medeelel: ");
	    
	    // Oyutnuudiin koduudiig davharduulakhgüi bailgah HashSet
	    HashSet<String> printedStudents = new HashSet<>();
	    
	    // Mergejliin jagsaalt
	    for (int i = 0; i < majorList.size(); i++) {
	        Major neededMajor = ((Major) majorList.get(i)); // Mergejil
	        System.out.println(neededMajor.name + ":");
	        
	        // Oyutnuudiig shalgah
	        for (int j = 0; j < studentList.size(); j++) {
	            Student neededStu = ((Student) studentList.get(j));
	            String stuCode = neededStu.code.substring(5, 7); // Oyutnii mergejiliin kod
	            
	            // Mergejil taarsan oyutnuudiig oloh
	            if (neededMajor.code.equals(stuCode)) {
	                
	                // Oyutnii kod davharduulahgüi bailgaj, hicheeliig ni haruulah
	                if (!printedStudents.contains(neededStu.code)) {
	                    printedStudents.add(neededStu.code);
	                    System.out.println(neededStu.code + ":");
	                }
	
	                Chain chain = neededStu.lessons; // Oyutnii hicheeliin jagsaalt
	                
	                // Oyutnii hicheeliin dunguudiig haruulah
	                for (int k = 0; k < chain.size(); k++) {
	                    Lessons lesson = (Lessons) chain.get(k);
	                    Subject neededSub = lesson.learned; // Hicheel
	                    System.out.println("             " + neededSub.code + " " + lesson.score);
	                }
	            }
	        }
	    }
	}
	
	
}