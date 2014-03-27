import java.util.*;
import java.io.Serializable;


public class Teacher 
	implements Serializable {
	
	Gradebook gradeBk;
	String name;
	Map<String, Gradebook> gradebookMap = new HashMap<String, Gradebook>();
	
	Teacher(String startName, Gradebook startGradebook) {
		this.name = startName;
		
	}
}
