import java.util.*;
// import java.util.Map.Entry;


public class ClassGradebook {
	public Integer studentCount;
	public static String owner;
	public String courseName;
	public Scanner sc;
	public String scanInput = "";
	public Student studentVar;
	Collection<Double> WeightFactorSet = new HashSet<Double>(0);
	public static Boolean isWeighted;
	public Map<String, Student> studentMap = new HashMap<String, Student>(); //creates map of students
	public List<Double> weightList = new ArrayList<Double>(2);

	public ClassGradebook(String courseName, String owner, Boolean startWeight) {
		ClassGradebook.owner = owner;
		this.courseName = courseName;
		this.studentCount = 0;
		ClassGradebook.isWeighted = startWeight;
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
	
	public void addAssignmentAll(Double startWeight, Boolean weightBool, String ... startScore) { //adds assignment to all students
		Integer i = 0;
		for(Student theStudent : this.studentMap.values()) { //loops through given scores
			theStudent.addAssignment(startScore[i], weightBool, startWeight);
			i ++;
		}	
	}	
	
	public void removeStudent(String oldName) {
		studentMap.remove(oldName);
		studentCount --;
	}
	
	public Boolean addWeightFactor(Double inputWeight) {
		if(weightList.isEmpty()) {
			weightList.add(inputWeight);
			return true;
		}
		for(Double iter : weightList) {
			if(iter.equals(inputWeight)) {
				return true;
			}else if(iter + inputWeight > 1) {
				return false;
			}else if(iter + inputWeight < 1) {
				return false;
			}else if(iter + inputWeight == 1) {
				weightList.add(inputWeight);
				return true;
			}
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
