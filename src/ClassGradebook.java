import java.util.*;


public class ClassGradebook {
	public Integer studentCount;
	public static String owner;
	public String courseName;
	public List<Double> weightGroup = new ArrayList<Double>();
	public Boolean isWeighted;
	public Map<String, Student> studentMap = new HashMap<String, Student>(); //creates map of students

	public ClassGradebook(String courseName, String owner,final Boolean startWeight) {
		ClassGradebook.owner = owner;
		this.courseName = courseName;
		this.studentCount = 0;
		this.isWeighted = startWeight;
	}	

	private String removeLeadingZeros(Double input) { //regex that removes leading zeros from double, applied to assignment weights
		String strInput = Double.toString(input);
		return strInput.replaceFirst("^0+(?!$)", ""); //sexy
	}
	
	private Double weightGroupSum() {
		Double total = 0.0;
		for(Double iter : weightGroup) {
			total += iter;
		}
		return total;
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
		if(weightGroup.isEmpty()) {
			weightGroup.add(inputWeight);
			return true;
		}
		if(weightGroup.contains(inputWeight)) {
			return true;
		}else if(weightGroupSum() + inputWeight > 1) {
			System.out.println("Assignment weights cannot add up to more than 1\n"
					+ "Weights already in use are ");
					for(int counter = 0;counter < weightGroup.size();counter ++) {
						System.out.println(removeLeadingZeros((double)Math.round((weightGroup.get(counter)) * 100) / 100) + " ");
					}
			return false;
		}else if(weightGroupSum() + inputWeight < 1) {
			weightGroup.add(inputWeight);
			System.out.println("Assignment weights still add up to less than 1\n"
					+ "You will need to add another weight value so that the total adds up to 1\n"
					+ "Weights already in use are ");
					for(int counter = 0;counter < weightGroup.size();counter ++) {
						System.out.print(removeLeadingZeros((double)Math.round((weightGroup.get(counter)) * 100) / 100) + " ");
					}
			return true;
		}else if(weightGroupSum() + inputWeight == 1) {
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
