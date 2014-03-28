import java.io.*;
import java.util.*;

public class GradebookRun 
	implements Serializable {

	private static final long serialVersionUID = 1L;
	static Scanner sc = new Scanner(System.in);
	public static ClassGradebook classGradebookVar;
	
	
	public static Boolean yesOrNo(String input) {
		while(true) { //loops until either y or n is input
			if(input.equals("y") || input.equals("Y"))	{
				return true;
			}else if(input.equals("n") || input.equals("N")) {
				return false;
			}else {
				System.out.println("Please pick either y or n");
				input = sc.nextLine();
			}
		}
	}
	
	private static ClassGradebook getClass(Gradebook bkVar) {
		String scanInput;
		while(true) {
			System.out.println("What is the name of the course?");
			sleepLocal();
			scanInput = sc.nextLine();
			if(bkVar.ClassMap.containsKey(scanInput)) {
				for(Map.Entry<String,ClassGradebook> theEntry : bkVar.ClassMap.entrySet()) {
					if(scanInput.equals(theEntry.getKey()));
					return theEntry.getValue();
				}
			}else {
				System.out.println(scanInput + " is not a valid course\n"
						+ "Courses entered in gradebook are");
				for(Map.Entry<String, ClassGradebook> theEntry : bkVar.ClassMap.entrySet()) {
					System.out.println(theEntry.getKey());
				}
			}
		}
	}
			
	
	public static void main(String[] args) throws IOException {
		
		Boolean editVar = false;
		String nameVar = "";
		String theOwner;
		String scanInput = "";
		String scanInput2 = "";
		Boolean scanInput3;
		String condensedInput = "";
		try {
			System.out.println("Hello!  We will use this program to create and edit a gradebook.\n"
					+ "Who is the owner of this gradebook?");
			sleepLocal();
			theOwner = sc.nextLine();
			Gradebook gradeBk = new Gradebook(theOwner);
			System.out.println("You can now add a course by typing \"add course\" and add information by typing \"add information\".\n"
					+ "You can get the information of a course by typing \"get course info\" and get the name of all courses input\n"
					+ "by typing \"get courses.\"  To see the name of the teacher the gradebook belongs to, \n"
					+ "type \"get teacher name,\" and if you want to give the gradebook to a different owner, type \"change owner\"");
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
					scanInput3 = yesOrNo(sc.nextLine()); //checks if is right syntax and if not, loops until is
					gradeBk.addCourse(scanInput, scanInput2, scanInput3); //creates course
					nameVar = scanInput; //gets name of course
					System.out.println("\"" + scanInput + "\" has been added as a course\n" +
					"Would you like to add info \"" + nameVar +  "\" (y/n)?");
					sleepLocal();
					scanInput = sc.nextLine();
					editVar = yesOrNo(scanInput); //if yes, user has prompted to edit the gradebook
					if(editVar.equals(true)) { //if editVar is true, gets ClassGradebook from name
						for(Map.Entry<String, ClassGradebook> theEntry : gradeBk.ClassMap.entrySet()) {
							if(theEntry.getKey() == nameVar){
								classGradebookVar = theEntry.getValue();
								break;
							}
						}
					}
				}	
				
				if(condensedInput.equals("addinformation") || (editVar.equals(true))) { //if user has prompted to edit right after creating, editVar will be true
					if(editVar.equals(false)) {
						classGradebookVar = getClass(gradeBk);
					}	
					EditClassGradebook temp = new EditClassGradebook(classGradebookVar); //creates new instance of EditClassGradebook
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
						
						}else if(condensedInput.equals("isweighted")) {	
							temp.isWeighted();
							
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
							+ "Get the names of all courses by typing \"get courses\"\n"
							+ "Get the name of the teacher by typing \"get teacher name\"\n"
							+ "Change the owner of the gradebook by typing \"change owner\"");

					
				}else if(condensedInput.equals("getcourses")) { //prints courses that are currently entered
					if(gradeBk.ClassMap.isEmpty()) {
						System.out.println("There are no courses entered in the gradebook");
					}else {
						for(String courseName : gradeBk.ClassMap.keySet()) {
							System.out.println(courseName);
						}
					}	
					
				
				}else if(condensedInput.equals("getcourseinfo")) { //gets all info of course
					if(gradeBk.ClassMap.isEmpty()) {
						System.out.println("There are no courses in the gradebook");
					}else {
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
					}	
					
				
				}else if(condensedInput.equals("getteachername")) {
					System.out.println("This gradebook belongs to " + gradeBk.owner);
					
				
				}else if(condensedInput.equals("changeowner")) {	
					System.out.println("What is the new teacher's name?");
					scanInput = sc.nextLine();
					gradeBk.owner = scanInput;
					System.out.println("Owner changed");
				
				}else if(condensedInput.equals("addcourse")) {
					continue;
					
				}else if(condensedInput.equals("save")) {
					Serialize serObj = new Serialize();
					serObj.serialize(gradeBk);
					System.out.println("done");
				
				}else if(condensedInput.equals("load")) {
					Serialize serObj = new Serialize();
					serObj.deserialize();
					gradeBk = serObj.gradeBk;
					System.out.println("Done");
				}else {
					System.out.println("Please input a valid command");
				}
				editVar = false; //resets values of vars for each iteration
				condensedInput.equals("");
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
