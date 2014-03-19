import java.io.IOException;
import java.util.*;

public class GradebookRun {
	
	public static Boolean yesOrNo(String input) {
		while(true) { //loops until either y or n is input
			if(input.equals("y") || input.equals("Y"))	{
				return true;
			}else if(input.equals("n") || input.equals("N")) {
				return false;
			}else {
				System.out.println("Please pick either y or n");
			}
		}
	}	
			
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String scanInput = "";
		String scanInput2 = "";
		Boolean scanInput3;
		String condensedInput = "";
		Boolean editVar = false;
		String nameVar = "";
		String theOwner;
		try {
			System.out.println("Hello!  We will use this program to create and edit a gradebook.\n"
					+ "Who is the owner of this gradebook?");
			sleepLocal();
			theOwner = sc.nextLine();
			Gradebook gradeBk = new Gradebook(theOwner);
			System.out.println("You can now add a course by typing \"add course\" and add information by typing \"add information\".\n"
					+ "You can get the information of a course by typing \"get course info\" and get the name of all courses input\n"
					+ "by typing \"get courses\"");
			while(true) {
				sleepLocal();
				if(sc.hasNext()) {
					scanInput = sc.nextLine();
					condensedInput = scanInput.replace(" ", "").toLowerCase(); //formats input string, making lowercase and removing spaces
				}
				
				if(condensedInput.equals("addcourse")) {
					System.out.println("What is the name of the new course?");
					sleepLocal();
					scanInput = sc.nextLine();
					System.out.println("Would you like to be able to assign different weights to assignments? (y/n)");
					sleepLocal(); //sleeps briefly to prevent user input before print
					scanInput2 = sc.nextLine(); //gets initial input
					scanInput3 = yesOrNo(scanInput2); //checks if is right syntax and if not, loops until is
					gradeBk.addCourse(scanInput, scanInput2, scanInput3); //creates course
					nameVar = scanInput; //gets name of course
					System.out.println("\"" + scanInput + "\" has been added as a course\n");
					System.out.println("Would you like to add info \"" + nameVar +  "\" (y/n)?");
					sleepLocal();
					scanInput = sc.nextLine();
					scanInput3 = yesOrNo(scanInput);
					editVar = scanInput3;
				}	

				if(condensedInput.equals("addinformation") || (editVar.equals(true))) {
					if(condensedInput.equals("addinformation") == false) {
						scanInput = nameVar;
					}else {
						while(true) {
							System.out.println("What is the name of the course?");
							sleepLocal();
							scanInput = sc.nextLine();
							if(gradeBk.ClassMap.containsKey(scanInput)) {
								break;
							}else {
								System.out.println(scanInput + " is not a valid course" );
							}
						}
					}	
					
					EditClassGradebook temp = new EditClassGradebook(gradeBk.ClassMap.get(scanInput)); //creates new instance of EditClassGradebook
					while(true) {
						editVar = null;
						sleepLocal();
						if(sc.hasNext()) {
							scanInput = sc.nextLine();
							condensedInput = scanInput.replace(" ", "").toLowerCase(); //formats input string, making lowercase and removing spaces
						}
						if(condensedInput.equals("addstudent")) {
							temp.addStudent();
						
						}else if(condensedInput.equals("getcourse")) {
							temp.getCourse();
						
						}else if(condensedInput.equals("changecoursename")) {
							temp.changeCourseName();
						
						}else if(condensedInput.equals("removestudent")) {
							temp.removeStudent();
						
						}else if(condensedInput.equals("getstudentprofile")) {
							temp.getStudentProfile();
						
						}else if(condensedInput.equals("addassignment")) {
							while(true) {
								System.out.println("For an individual or all students? (i/a)");
								scanInput = sc.nextLine();
								if(scanInput.equals("i") || scanInput.equals("I")) {
									temp.addAssignment();
									break;
								}else if(scanInput.equals("a") || scanInput.equals("A")) {
									temp.addAssignmentAll();
									break;
								}else {
									System.out.println("Please pick either \"a\" for all students or \"i\" for one");
								}
							}	
						
						}else if(condensedInput.equals("getgrade")) {
							temp.getGrade();
						
						}else if(condensedInput.equals("getallstudents")) {
							temp.getAllStudents();
						
						}else if(condensedInput.equals("getallinfo")) {
							temp.getAllInfo();
						
						}else if(condensedInput.equals("rescoreassignment")) {
							temp.rescoreAssignment();
						
						}else if(condensedInput.equals("help")) {
							temp.help();
						
						}else if(condensedInput.equals("exit")) {
							if(temp.exit()) {  //prompts user to make sure they want to exit, returns boolean
								break;
							}	
						}else {
							System.out.println("Please input a valid command");
						}
					}
					System.out.println("Remember:\n"
							+ "Add a course by typing \"add course\"\n"
							+ "Add information by typing \"addinformation\"\n"
							+ "Get a courses info by typing \"get course info\"\n"
							+ "Get the names of all courses by typing \"get courses\"\n");

					
				}else if(condensedInput.equals("getcourses")) { //prints courses that are currently entered
					for(String courseName : gradeBk.ClassMap.keySet()) {
						System.out.println(courseName);
					}
					
				
				}else if(condensedInput.equals("getcourseinfo")) { //gets all info of course
					try {
						System.out.println("For which course?");
						scanInput = sc.nextLine();
						if(gradeBk.ClassMap.containsKey(scanInput)) {
							for(Student theStudent : gradeBk.ClassMap.get(scanInput).studentMap.values()) {
								System.out.println(theStudent.getAllInfo());
							}
						}else {
							throw new NullPointerException();
						}
					}catch(NullPointerException e) {
						System.out.println("There is no course named " + scanInput + "\n"
								+ "Please input a valid course");
					}
					
				
				}else if(editVar.equals(false) || editVar.equals(false)) {
					continue;
					
					
				}else {
					System.out.println("Please input a valid command");
				}
				editVar = null;
			}
		}finally { //closes scanner on exit
			sc.close();
		}	
	}
	
	private static void sleepLocal() {
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
		}
	}
}
