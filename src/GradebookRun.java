import java.io.*;
import java.util.*;

public class GradebookRun 
	implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String input;
	public static String list = "";
	static Scanner sc = new Scanner(System.in);
	public static ClassGradebook classGradebookVar;
	public static Serialize serObj = new Serialize();
	public static Gradebook gradeBk;
	public static Map<Integer, String> loadedAssignments = new HashMap<Integer, String>();
	
	
	public static Boolean yesOrNo(String input) { //loops until either y or n is input
<<<<<<< HEAD
		while(true) {
            switch(input.toLowerCase()) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Please pick either y or n");
                    input = sc.nextLine();
            }
=======
		while(true) { 
			if(input.equals("y") || input.equals("Y"))	{
				return true;
			}else if(input.equals("n") || input.equals("N")) {
				return false;
			}else {
				System.out.println("Please pick either y or n");
				input = sc.nextLine();
			}
>>>>>>> 4fb9597d7628765b6471cb66e1db67ed04765f0a
		}
	}
	
	private static String getCourses() {
		for(String courseName : gradeBk.ClassMap.keySet()) {
			list += courseName + "\n";
		}
		return list;
	}
	
	private static ClassGradebook getClass(Gradebook bkVar) { //gets classGradebook FROM NAME
		String scanInput;
		while(true) { //if invalid course, prompts for another
			System.out.println("What is the name of the course?");
			sleepLocal();
			scanInput = sc.nextLine();
			if(bkVar.ClassMap.containsKey(scanInput)) { //if is in map of classes
				for(Map.Entry<String,ClassGradebook> theEntry : bkVar.ClassMap.entrySet()) { //loops over entries in collection of classes
					if(scanInput.equals(theEntry.getKey())) { //if input name of class equals map key
						return theEntry.getValue();
					}
				}
			}else {
				System.out.println(scanInput + " is not a valid course");
				if(gradeBk.ClassMap.isEmpty()) {
					System.out.println("There are no courses entered in the gradebook");
				}else {
					System.out.println("Courses entered in gradebook are");
					System.out.println(getCourses());
				}
			}
		}
	}
	
	public static void save() { //methods for serializing data
<<<<<<< HEAD
=======
		
>>>>>>> 4fb9597d7628765b6471cb66e1db67ed04765f0a
		System.out.println("What name would you like to save the file as?");
		input = sc.nextLine();
		if(serObj.serialize(input, gradeBk)) { //notice if
			System.out.println("Data saved");
		}else {
			System.out.println("whoops, sorry bout that");
		}
	}
	
	public static void load() { //for loading data
		if(serObj.dir.list().length > 0) {
			System.out.println("The following files are saved");
			Integer count = 1;
			for(File files : serObj.dir.listFiles()) {
				loadedAssignments.put(count, files.getName());
				System.out.println(count + ": " + files.getName());
				count ++;
			}
			System.out.println("Which file would you like to load?");
			while(true) {
				input = sc.nextLine();
				try {
					if(loadedAssignments.containsValue(input) || loadedAssignments.containsValue(input + ".ser")) {
						serObj.deserialize(input.replaceAll(".ser", ""));
						gradeBk = serObj.gradeBk;
						System.out.println("Data loaded");
						break;
					}else if(loadedAssignments.containsKey(Integer.parseInt(input))) {
						String temp = loadedAssignments.get(Integer.parseInt(input));
						if (temp.indexOf(".") > 0)
						    temp = temp.substring(0, temp.lastIndexOf("."));
						serObj.deserialize(temp);
						gradeBk = serObj.gradeBk;
						System.out.println("Data loaded");
						break;
					}else {
						throw new NumberFormatException();
					}
				}catch(NumberFormatException blah) {
					System.out.println("Not a valid file\nPick another");
				}
			}	
		}else {
			System.out.println("There are no files saved");
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
					+ "Would you like to load an existing gradebook (y/n)?"); //asks for load or new
			sleepLocal();
			scanInput3 = yesOrNo(sc.nextLine());
			if(scanInput3) { // checks if user wants to load gradebook
				load();
				if(!(serObj.dir.list().length > 0)) {
					scanInput3 = false;
				}
			}
			if(!scanInput3) {
				System.out.println("Creating new gradebook\n"
				+ "Who is the owner of the gradebook?");
				theOwner = sc.nextLine();
				gradeBk = new Gradebook(theOwner); //sets  GradebookRun()'s gradeBk var
			}	
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
<<<<<<< HEAD
							if(theEntry.getKey().equals(nameVar)){
=======
							if(theEntry.getKey() == nameVar){
>>>>>>> 4fb9597d7628765b6471cb66e1db67ed04765f0a
								classGradebookVar = theEntry.getValue();
								break;
							}
						}
					}
				}	
				
				if(condensedInput.equals("addinformation") || (editVar.equals(true))) { //if user has prompted to edit right after creating, editVar will be true
					if(!gradeBk.ClassMap.isEmpty()) {	
						if(editVar.equals(false)) {
							classGradebookVar = getClass(gradeBk);
						}	
						EditClassGradebook temp = new EditClassGradebook(classGradebookVar); //creates new instance of EditClassGradebook
<<<<<<< HEAD
                        boolean running = true;
						while(running) {
=======
						while(true) {
>>>>>>> 4fb9597d7628765b6471cb66e1db67ed04765f0a
							editVar = null;
							sleepLocal();
							if(sc.hasNext()) {
								scanInput = sc.nextLine();
								condensedInput = scanInput.replace(" ", "").toLowerCase(); //formats input string, making lowercase and removing spaces
							}
<<<<<<< HEAD
                            switch(condensedInput) {
                                case "addstudent":
                                    temp.addStudent();
                                    break;

                                case "getcourse":
                                    temp.getCourse();
                                    break;

                                case "changecoursename":
                                    temp.changeCourseName();
                                    break;

                                case "removestudent":
                                    temp.removeStudent();
                                    break;

                                case "getstudentprofile":
                                    temp.getStudentProfile();
                                    break;

                                case "addassignment":
                                    if(classGradebookVar.getNumStudents() == 1) { //if only one student added, does not prompt
                                        temp.addAssignmentAll();
                                    }else {
                                        boolean subRun = true;
                                        while(subRun) {
                                            System.out.println("For an individual or all students? (i/a)");
                                            scanInput = sc.nextLine();
                                            switch(scanInput.toLowerCase()) {
                                                case "i":
                                                    temp.addAssignment();
                                                    subRun = false;
                                                    break;
                                                case "a":
                                                    temp.addAssignmentAll();
                                                    subRun = false;
                                                    break;
                                                default:
                                                    System.out.println("Please pick either \"a\" for all students or \"i\" for one");
                                            }
                                        }
                                    }
                                    break;

                                case "getgrade":
                                    temp.getGrade();
                                    break;

                                case "getallstudents":
                                    temp.getAllStudents();
                                    break;

                                case "getallinfo":
                                    temp.getAllInfo();
                                    break;

                                case "rescoreassignment":
                                    temp.rescoreAssignment();
                                    break;

                                case "isweighted":
                                    temp.isWeighted();
                                    break;

                                case "help":
                                    temp.help();
                                    break;

                                case "exit":
                                    if(temp.exit()) {  //prompts user to make sure they want to exit, returns boolean
                                        temp = null; //destroys editClassGradebook object
                                        running = false; //breaks from input loop
                                    }
                                    break;

                                default:
                                    System.out.println("Please input a valid command");
                                }
=======
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
								if(classGradebookVar.getNumStudents() == 1) { //if only one student added, does not prompt
									temp.addAssignmentAll();
								}else {
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
									temp = null; //destroys editClassGradebook object
									break; //breaks from input loop
								}	
							}else {
								System.out.println("Please input a valid command");
							}
>>>>>>> 4fb9597d7628765b6471cb66e1db67ed04765f0a
						}
						System.out.println("Remember:\n"
								+ "Add a course by typing \"add course\"\n"
								+ "Add information by typing \"addinformation\"\n"
								+ "Get a courses info by typing \"get course info\"\n"
								+ "Get the names of all courses by typing \"get courses\"\n"
								+ "Get the name of the teacher by typing \"get teacher name\"\n"
								+ "Change the owner of the gradebook by typing \"change owner\"");
					}else {
						System.out.println("There are no courses entered in the gradebook");
					}
						
				}else if(condensedInput.equals("getcourses")) { //prints courses that are currently entered
					if(gradeBk.ClassMap.isEmpty()) {
						System.out.println("There are no courses entered in the gradebook");
					}else {
						System.out.println(getCourses());
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
							System.out.println("This is a list of all of the courses input\n"
									+ getCourses());
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
					save();	
					
				}else if(condensedInput.equals("load")) {
					load();
					
				}else {
					System.out.println("Please input a valid command");
				}
				editVar = false; //resets values of vars for each iteration
<<<<<<< HEAD
				condensedInput = "";
=======
				condensedInput.equals("");
>>>>>>> 4fb9597d7628765b6471cb66e1db67ed04765f0a
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