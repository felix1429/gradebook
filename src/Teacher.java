import java.util.HashMap;
import java.util.Map;


public class Teacher {
	Gradebook gradeBk;
	String name;
	Map<String, Gradebook> gradebookMap = new HashMap<String, Gradebook>();
	
	Teacher(String startName, Gradebook startGradebook) {
		this.name = startName;
		
	}
}
