import java.util.*;
import java.io.Serializable;


public class ClassGradebook 
	implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public Integer studentCount;
	public String owner;
	public String courseName;
	public List<Double> weightGroup = new ArrayList<Double>();
	public Boolean boolVar;
	public Boolean isWeighted;
	public Boolean isFirstEdit; //bool for editClassGradebook to see if is first time editing
	public Map<String, Student> studentMap = new HashMap<String, Student>(); //creates map of students

	public ClassGradebook(String courseName, String owner,final Boolean startWeight) {
		this.owner = owner;
		this.courseName = courseName;
		this.studentCount = 0;
		this.isWeighted = startWeight;
		this.isFirstEdit = true;
	}	

	private String removeLeadingZeros(Double input) { //regex that removes leading zeros from double, applied to assignment weights for printing
		String strInput = Double.toString(input);
		return strInput.replaceFirst("^0+(?!$)", ""); //sexy
	}
	
	public void changeOwner(String newOne) {
		this.owner = newOne;
	}
	
	public void changeCourseName(String newName) {
		this.courseName = newName;
	}
	
	public int getNumStudents() {
		return this.studentCount;
	}
	
	public void addStudent(String startName) {
		Student newStudent = new Student(startName);
		studentMap.put(startName, newStudent); //adds new student to the map
		studentCount ++;
	}
	
	public void addAssignmentAll(Double startWeight, String startName, Boolean weightBool, String ... startScore) { //adds assignment to all students
		Integer i = 0;
		for(Student theStudent : this.studentMap.values()) { //loops through given scores
			if(startScore[i].equals("0") || startScore[i].equals("-1")) {
				switch(startScore[i]) {
					case "0":
						boolVar = true;
					case "-1":
						boolVar = false;
				}
				theStudent.addAssignment("0", startName, weightBool, startWeight, boolVar);
			}else {
				theStudent.addAssignment(startScore[i], startName, weightBool, startWeight, true);
			}	
			i ++;
		}	
	}	
	
	public void removeStudent(String oldName) {
		studentMap.remove(oldName);
		studentCount --;
	}
	
	public Boolean addWeightFactor(Double inputWeight) {
		if(weightGroup.isEmpty()) {
			weightGroup.add(inputWeight);
			weightGroup.add(1 - inputWeight);
			return true;
		}
		
		if(weightGroup.contains(inputWeight)) {
			return true;
		}else {
			System.out.println("Please pick between the following weights");
			for(int counter = 0;counter < 2;counter ++) {
				System.out.println(removeLeadingZeros((double)Math.round((weightGroup.get(counter)) * 100) / 100) + " ");
			}
			return false;
		}	

	}
	
	public String getStudents() {
		String output = "";
		for(Map.Entry<String, Student> theEntry : this.studentMap.entrySet()) {
			output += theEntry.getKey() + "\n";
		}
		return output;
	}
	
	public String getAllStudentInfo() {
		String allInfo = "";
		for(Student s : studentMap.values()) {
			allInfo += s.getAllInfo() + "\n\n";
		}
		return allInfo;
	}
}
