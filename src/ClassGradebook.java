import java.util.*;


public class ClassGradebook {
	public Integer studentCount;
	public static String owner;
	public String courseName;
	public Scanner sc;
	public String scanInput = "";
	public Student studentVar;
	public Double weightFactorNum = null;
	public static Boolean isWeighted;
	public Map<String, Student> studentMap = new HashMap<String, Student>(); //creates map of students

	public ClassGradebook(String courseName, String owner, Boolean startWeight) {
		ClassGradebook.owner = owner;
		this.courseName = courseName;
		this.studentCount = 0;
		ClassGradebook.isWeighted = startWeight;
	}	

	private String removeLeadingZeros(Double input) { //regex that removes leading zeros from double, applied to assignment weights
		String strInput = Double.toString(input);
		return strInput.replaceFirst("^0+(?!$)", "");
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
			theStudent.addAssignment(startScore[i], startName, weightBool, startWeight);
			i ++;
		}	
	}	
	
	public void removeStudent(String oldName) {
		studentMap.remove(oldName);
		studentCount --;
	}
	
	public Boolean addWeightFactor(Double inputWeight) {
		if(weightFactorNum == null) {
			weightFactorNum = inputWeight;
			return true;
		}
		if(weightFactorNum.equals(inputWeight)) {
			return true;
		}else if(weightFactorNum + inputWeight > 1) {
			System.out.println("Assignment weights cannot add up to more than 1\n"
					+ "Weights already in use are " + removeLeadingZeros(weightFactorNum) + " and " + removeLeadingZeros((double)Math.round((1 - weightFactorNum) * 100) / 100));
			return false;
		}else if(weightFactorNum + inputWeight < 1) {
			System.out.println("Assignment weights cannot add up to less than 1\n"
					+ "Weights already in use are " + removeLeadingZeros(weightFactorNum) + " and " + removeLeadingZeros((double)Math.round((1 - weightFactorNum) * 100) / 100));
			return false;
		}else if(weightFactorNum + inputWeight == 1) {
			return true;
		}
		return false;
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
