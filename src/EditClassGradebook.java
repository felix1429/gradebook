import java.util.*;
import java.io.*;

public class EditClassGradebook 
	implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public ClassGradebook theCourse;
	static Scanner sc = new Scanner(System.in);
	String scanInput = "";
	String stringVar = "";
	Assignment assignmentVar;
	Student studentVar;
	String condensedInput = "";
	String output = "";
	Weight weightVar;
	Score scoreVar;
	Boolean boolVar;
	List<Student> studentList = new ArrayList<Student>();
	List<String> assignmentNameList = new ArrayList<String>();
	List<Assignment> assignmentList = new ArrayList<Assignment>();
	List<Assignment> allAssignmentList = new ArrayList<Assignment>();
	List<String> allAssignmentNameList = new ArrayList<String>();
	
	
	public EditClassGradebook(ClassGradebook theStartCourse) {
		this.theCourse = theStartCourse;
		System.out.println("You are now editing the gradebook for \"" + this.theCourse.courseName + "\"\n"
				+ "To change the name of the course type \"change course name\"\n\n"
				+ "You can add a student by typing \"add student\", get the course name by typing \"get course\",\n"
				+ "remove a student by typing \"remove student\", get all of a student\'s information\n"
				+ "by typing \"get student profile\", add an assignment by typing \"add assignment\",\n"
				+ "get a student\'s grade by typing \"get grade\", get a list of all students by typing \"get all students\",\n"
				+ "find if the class has assignment weighting by typing \"is it weighted\",\n"
				+ "and get all the course\'s information by typing \"get all info\"\n"
				+ "For help type \"help\"\n"
				+ "Exit by typing \"exit\"");
		
		if(this.theCourse.isFirstEdit) { //if first time editing gb, prompts for students
			System.out.println("You can now add the initial batch of students.  Type \"done\" when finished"); //prompts user to input students
			while(true) {
				addStudent();
				System.out.println("Add another (y/n)?\n");
				scanInput = sc.nextLine();
				if(GradebookRun.yesOrNo(scanInput)) { //prompts for y or n
					continue;
				}else {
					System.out.println("You can now begin to use the gradebook as explained");
					break;
				}
			}
			this.theCourse.isFirstEdit = false;
		}	
	}
	
	private void sleepLocal() { //sleeps program so that nothing can be input until printing is finished
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
		}
	}
	
	private Student getStudent() { //gets Student object from name
		while(true) { //loops until valid student added
			try { //makes sure name of valid student is input
				System.out.println("What is the student's name?");
				sleepLocal();
				scanInput = sc.nextLine();
				if(this.theCourse.studentMap.containsKey(scanInput)) { //tests if the student is in map
					return this.theCourse.studentMap.get(scanInput);
				}else {
					throw new NoSuchElementException(); //exception thrown if not in map
				}
			}catch(NoSuchElementException e) { //catches student name exception
				System.out.println("There is not student named " + scanInput + "\n" //if not a valid student
				+ "The following students are entered into the gradebook"); 
				for(String name : this.theCourse.studentMap.keySet()) { //iterates over keys(student names) and prints them
					System.out.println(name);
				}
			}
		}
	}
	
	private Boolean isStudentInMap(Student theStudent) { //returns true if student is input into map, false otherwise
		if(this.theCourse.studentMap.containsValue(theStudent)) {
			return true;
		}else {
			return false;
		}
	}
	
	private String getStudentName(Student theStudent) { //gets student name from Student object
		try {
			for(Map.Entry<String, Student> theEntry : this.theCourse.studentMap.entrySet()) { //loops through entries in studentMap
				if(theEntry.getValue().equals(theStudent)) { //gets student name 
					return theEntry.getKey();
				}	
			}
			throw new NoSuchElementException();
		}catch(NoSuchElementException e) {
			return "The entered student does not exist";
		}
	}	
	
	private Boolean checkStudentMap() { //sees if map is empty
		try {  //checks if there are students added
			if(this.theCourse.studentMap.isEmpty()) { //throws exception if there are no students in the gradebook
				throw new NullPointerException();
			}else {
				return true;
			}	
		}catch(NullPointerException e) { 
			System.out.println("There are no students in the gradebook\n"
			+ "Please add a student first");
			return false;
		}
	}
	
	private Double getWeight() { //gets weight from user
		Double weightVar;
		while(true) {
			System.out.println("How would you like it weighted (formated .x, ie x%)?"); //asks for user input
			sleepLocal();
			while(true) {
				try {
					scanInput = sc.nextLine();
					weightVar = Double.parseDouble(scanInput); //converts String scanInput to a double, for use with weight
					break;
				}catch(NumberFormatException e) {
					System.out.println("Please input a valid number");
				}
			}	
			if(this.theCourse.addWeightFactor(weightVar)) {
				return weightVar;
			}else {
				System.out.println("Please input a different weight for the assignment");
			}
		}	
	}
	
	
	private String getScore() { //checks if string is valid score and returns it if it is
		while(true) { //loops until valid score is input
			try {
				System.out.println("What is the score (formated x/y)?");
				sleepLocal();
				scanInput = sc.nextLine();
				if(studentVar.testScore(scanInput)) { //if is valid score, is returned
					return scanInput;
				}else {
					throw new IllegalArgumentException();
				}
			}catch(IllegalArgumentException e) {	
				System.out.println("Please enter a valid score");
			}
		}
		
	}
	
	public static Boolean yesOrNo(String scanInput) { //loops until either y or n is input
		while(true) { 
			if(scanInput.equals("y") || scanInput.equals("Y"))	{
				return true;
			}else if(scanInput.equals("n") || scanInput.equals("N")) {
				return false;
			}else {
				System.out.println("Please pick either y or n");
				scanInput = sc.nextLine();
			}
		}
	}
	
	public void addStudent() { //gets student name and then creates object
		System.out.println("What is the name of the new student?");
		sleepLocal();
		scanInput = sc.nextLine();
		this.theCourse.addStudent(scanInput); //adds student to course
		System.out.println(scanInput + " has been added to the gradebook.\n");
	}
	
	public void getCourse() { //returns course
		System.out.println(this.theCourse.courseName);
	}
	
	public void changeCourseName() {
		System.out.println("What would you like to change the name to?");
		sleepLocal();
		scanInput = sc.nextLine();
		this.theCourse.changeCourseName(scanInput);
		System.out.println("The course name has been changed to " + scanInput);
	}
	
	public void getAllStudents() {
		String studentList = "";
		for(Student theStudent : this.theCourse.studentMap.values()) {
			studentList = studentList.concat((getStudentName(theStudent))) + "\n";
		}
		System.out.println(studentList);
	}
	
	public void removeStudent() { //deletes object
		if(checkStudentMap()) {
			studentVar = getStudent(); //gets student to be removed
			this.theCourse.studentMap.remove(getStudentName(studentVar)); //removes from map
			System.out.println("Student removed\n");
		}
	}	
	
	public void getStudentProfile() { //gets all info of student
		if(checkStudentMap()) {
			studentVar = getStudent();
			System.out.println(this.theCourse.studentMap.get(scanInput).getAllInfo());
		}
	}	
	
	public void addAssignmentAll() {  //gets list of scores and who they belong to and sends them to another method to assign them
		if(checkStudentMap()) {
			Boolean boolVar;
			Integer i = 0; //counter to place scores in array
			String[] scoreArray;
			System.out.println("What is the name of the assignment?");
			stringVar = sc.nextLine();
			scoreArray = new String[this.theCourse.studentMap.entrySet().size()]; //array to hold scores for input into method
			for(Map.Entry<String, Student> theEntry : this.theCourse.studentMap.entrySet()) { //loops over student names
				while(true) { //loops until valid score is input
					System.out.println("What is " + theEntry.getKey() + "\'s score?");
					sleepLocal();
					scanInput = sc.nextLine();
					if(theEntry.getValue().testScore(scanInput)) { //tests if is valid score or not
						if(scanInput.equals(0)) {
							System.out.println("Is the assignment excused?");
							boolVar = yesOrNo(sc.nextLine());
							if(!boolVar) {
								scanInput = Integer.toString(-1);
							}
						}
						scoreArray[i] = scanInput; //adds score to array
						i ++; //counter
						break;
					}else {
						System.out.println("Please input a valid score (formated x/y)");
					}
				}
			}
			
			boolVar = this.theCourse.isWeighted; 
			if(!boolVar){ //if gradebook is not weighted, sets weight values
				this.theCourse.addAssignmentAll(1.0, stringVar, false, scoreArray);
			}else {
				this.theCourse.addAssignmentAll(getWeight(), stringVar, boolVar, scoreArray); //add assignments with scoreArray scores
			}	
			System.out.println("Assignments added\n");
		}	
	}
	
	public void addAssignment(Student theStudent) { //adds assignment if student provided
		if(isStudentInMap(theStudent)) {
			scanInput = getScore(); //not scanner, just using variable that is a string
			if(scanInput.equals(0)) {
				System.out.println("Is the assignment excused?");
				boolVar = yesOrNo(sc.nextLine());
			}
			System.out.println("What is the name of the assignment?");
			stringVar = sc.nextLine();
			if(this.theCourse.isWeighted) {
				theStudent.addAssignment(scanInput, stringVar, true, getWeight(), boolVar);
			}else {
				theStudent.addAssignment(scanInput, stringVar, false, 1.0, boolVar);
			}
			//calls methods getScore() and getWeight()
			System.out.println("Assignment added\n");
		}	
	}
	
	public void addAssignment() { //gets student if there is not one provided
		if(checkStudentMap()) {  //checks if there are students added
			studentVar = getStudent();
			addAssignment(studentVar);
		}
	}	
	
	public void rescoreAssignment() {
		if(checkStudentMap()) {  //checks if there are students added
			System.out.println("Would you like to rescore the assignment for all students or an individual? (a/i)");
			scanInput = sc.nextLine();
			if(scanInput.equals("a") || scanInput.equals("A")) { //if for all students
				for(Map.Entry<String, Student> theEntry : this.theCourse.studentMap.entrySet()) {
					studentList.add(theEntry.getValue()); //add student to list
				}
				System.out.println("The students have the following assignments in common");
				int counter = 0;
				for(Student iter : studentList) {	//iterates over students so intersection of assignment groups can be computed
					for(Map.Entry<Integer, Assignment> theEntry : iter.assignmentMap.entrySet()) { //loops over student's assignments and prints number and grade
						if(counter == 0) { //if first student, assignment set is used as base
							allAssignmentNameList.add(theEntry.getValue().name);
							allAssignmentList.add(theEntry.getValue());
						}else { //otherwise sets assignment list to set compared to first
							assignmentNameList.add(theEntry.getValue().name);
							assignmentList.add(theEntry.getValue());
						}
					}
					if(counter != 0) {
						allAssignmentList.addAll(assignmentList);
						allAssignmentNameList.retainAll(assignmentNameList); //computes intersection
					}	
					assignmentNameList.clear();
					assignmentList.clear();
					counter ++; 
				}
				for(String name : allAssignmentNameList) { //prints names of assignments in common
					System.out.println(name);
				}
			}else if(scanInput.equals("i") || scanInput.equals("I")) { //if for individual 
				studentVar = getStudent();
				for(Map.Entry<Integer, Assignment> theEntry : studentVar.assignmentMap.entrySet()) { //loops over student's assignments and prints number and grade
					allAssignmentList.add(theEntry.getValue());
				}
				studentList.add(studentVar);
				System.out.println("The following assignments are entered for " + studentVar.name);
				for(Assignment iter : allAssignmentList) { //loops over student's assignments and prints number and grade
					System.out.println("Assignment " + iter.assignmentNumber + ", \"" + iter.name + "\" : "
							+ iter.assignmentScore.percentGrade); //lists assignments already in gradebook
				}
			}	
			System.out.println("Which assignment would you like to rescore?");
			scanInput = sc.nextLine();
			for(Student theStudent : studentList) {
				studentVar = theStudent;
				for(Assignment iter : allAssignmentList) {
					if(scanInput.equals(iter.name) || scanInput.equals(iter.assignmentNumber)) {
						assignmentVar = iter;
						break;
					}	
				}
				if(assignmentVar instanceof MissingAssignment) { //asks if user wants to decrease the worth of assignment
					System.out.println("This is a missing assignment for" + studentVar.name + "\n"
							+ "Would you like to decrease the amount of credit for it?");
					boolVar = yesOrNo(sc.nextLine());
					if(boolVar) { //works by reducing weight percentage
						System.out.println("What percentage would you like to take off?");
						scanInput = sc.nextLine();
						assignmentVar.setWeight(assignmentVar.finalWeight * Double.parseDouble(scanInput)); //sets weight of assignment to percentage less than originally specified
					}
				}
				if(studentList.size() > 1) { //if rescoring for all, prints name of student 
					System.out.println("Enter a score for " + studentVar.name);
				}
				
				stringVar = getScore(); //gets new score
				assignmentVar.rescoreAssignment(studentVar.getScore(stringVar)); //does actual rescoring
				System.out.println("Score saved");
			}	
		}
	}
	
	public void getGrade() { //sets student's grade and returns a percent
		if(checkStudentMap()) {
			studentVar = getStudent();
			studentVar.getGrade();
			System.out.println(studentVar.grade.percentGrade);
		}	
	}	
	
	public void getAllInfo() { //gets all assignments and total grade
		System.out.println(this.theCourse.isWeighted + "\'s gradebook " + this.theCourse.courseName + "\n"); //get course
		if(checkStudentMap()) {
			for(Student key : this.theCourse.studentMap.values()) {
				System.out.println(key.getAllInfo());
			}
		}	
	}
	
	public void isWeighted() {
		if(this.theCourse.isWeighted) {
			System.out.println(this.theCourse.courseName + " has assignment weighting");
		}else {
			System.out.println(this.theCourse.courseName + " does not have assignment weighting");
		}
	}
	
	public boolean exit() { //exits editing of course
		//called in gradebookRun(), if true is returned then breaks out of loop
		System.out.println("Are you finished editing " + this.theCourse.courseName + " (y/n)?");
		sleepLocal();
		scanInput = sc.nextLine();
		while(true) {
			if(scanInput.equals("y") || scanInput.equals("Y")) {
				System.out.println("Editing complete");
				return true;
			}else if(scanInput.equals("n") || scanInput.equals("N")) {
				System.out.println("Continue editing");
				return false;
			}else {
				System.out.println("Please pick \"y\" or \"n\"");
			}
		}
	}
	
	public void help() { //reprints instructions
		System.out.println("To add a student type \"add student\"\n"
				+ "To add an assignment type \"add assignemtn\"\n"
				+ "To get a student\'s grade type \"get grade\"\n"
				+ "To get all of a student\'s information type \"get student profile\"\n"
				+ "To remove a student type \"remove student\"\n"
				+ "To get the whole courses\' information type \"get all info\"\n"
				+ "To get the name of the course type \"get course\"");
	}	
}	