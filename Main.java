package bd1;
import dataStructures.ArrayLinearList;
import java.text.*;
import java.lang.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Registration regist = new Registration();
        Scanner inp = new Scanner(System.in);
        int choice = -1;

        while (choice != 7) {
            System.out.println("1. Hicheeluudiin jagsaalt haruulah");
            System.out.println("2. Mergejluudiin jagsaalt haruulah");
            System.out.println("3. Niit oyutnii golch dun haruulah");
            System.out.println("4. 3-s deesh hicheeld F unelgee avsan hasagdah oyutnii jagsaalt haruulah");
            System.out.println("5. Hicheel bureer dungiin jagsaalt haruulah");
            System.out.println("6. Mergejil bureer dungiin jagsaalt haruulah");
            System.out.println("7. Garah");
            System.out.print("Songoltoo oruulna uu: ");
            
            choice = inp.nextInt();

            switch (choice) {
                case 1:
                    regist.printSubList();
                    break;
                case 2:
                    regist.printMajorList();
                    break;
                case 3:
                    double gpa = regist.calcAverageGPA();
                    if (gpa == 0.0) {
                        System.out.println("Oyutnii golch tootsoh bolomjgui.");
                    } else {
                        DecimalFormat df = new DecimalFormat("0.00");
                        System.out.println("Niit golch: " + df.format(gpa));
                    }
                    break;
                case 4:
                    regist.DeductStudents();
                    break;
                case 5:
                    regist.scoreListByLesson();
                    break;
                case 6:
                    regist.scoreListByMajor();
                    break;
                case 7:
                    System.out.println("Program duuslaa");
                    break;
                default:
                    System.out.println("Zov songolt oruulna uu!");
                    break;
            }
        }

        inp.close(); 
    }
}
