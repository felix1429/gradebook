import java.util.*;
import java.io.Serializable;

public class Gradebook 
	implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String owner;
	Boolean isWeighted;
	public Map<String, ClassGradebook> ClassMap = new HashMap<String, ClassGradebook>(); //creates map of students
	
	public Gradebook(String startOwner) {
		this.owner = startOwner;
	}
	
	public void addCourse(String courseName, String x, Boolean isWeighted) {
		x = this.owner;
		ClassGradebook gradeBk = new ClassGradebook(courseName, x, isWeighted);
		ClassMap.put(gradeBk.courseName, gradeBk);
	}
}	