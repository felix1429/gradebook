import java.util.*;

public class Gradebook {
	
	
	static String owner;
	Boolean isWeighted;
	public Map<String, ClassGradebook> ClassMap = new HashMap<String, ClassGradebook>(); //creates map of students
	
	public Gradebook(String startOwner) {
		Gradebook.owner = startOwner;
	}
	
	public void addCourse(String courseName, String x, Boolean isWeighted) {
		x = Gradebook.owner;
		ClassGradebook gradeBk = new ClassGradebook(courseName, x, isWeighted);
		ClassMap.put(gradeBk.courseName, gradeBk);
	}
}	