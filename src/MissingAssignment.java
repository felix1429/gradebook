
public class MissingAssignment
	extends Assignment {
	
	Boolean isExcused;

	public MissingAssignment(Score inputScore, Integer startNumber, String startName, //constructor on multiple lines
			Boolean startSetWeight, Double startingWeight, Boolean isExcused) {		  // because of length
		super(inputScore, startNumber, startName, startSetWeight, startingWeight);
		this.isExcused = isExcused;
	}

}
