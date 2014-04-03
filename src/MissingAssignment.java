import java.io.Serializable;

public class MissingAssignment
	extends Assignment 
	implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Boolean isExcused; //if is an excused assignment or not  
	 // if assignment is excused, will be put in as "missing" but no points will be docked
	 // if not excused, put in as zero, counting towards total grade

	public MissingAssignment(Score inputScore, Integer startNumber, String startName, //constructor on multiple lines
			Boolean startSetWeight, Double startingWeight, Boolean isExcused) {		  // because of length
		super(inputScore, startNumber, startName, startSetWeight, startingWeight);  //super for Assignment() class
		this.isExcused = isExcused;
	}
	
	public Boolean getExcusedBool() {
		return this.isExcused;
	}
	
	public void setExcused(Boolean bool) {
		this.isExcused = bool;
	}

}
