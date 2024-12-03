package bd1;
import dataStructures.*;

public class Subject {
	public String code;
	public String name;
	public float credit;
	 public Subject(String code) {
		this.code = code;
	} 
	public Subject(String code, String name, float credit) {
		this.code = code;
		this.name = name;
		this.credit = credit;
	}
}
