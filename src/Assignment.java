import java.io.Serializable;

public class Assignment 
	implements Serializable { //class for assignments for Student class
	
	private static final long serialVersionUID = 1L;
	public Score assignmentScore;
	public Integer assignmentNumber;
	public String name;
	public AssignmentWeight theAssignmentWeight;
	public Boolean setWeight;
	public Double finalWeight;
	public Boolean finalWeightBool;
	
	public Assignment(Score inputScore, Integer startNumber, String startName, Boolean startSetWeight, Double startingWeight) {
		this.assignmentScore = inputScore;
		this.assignmentNumber = startNumber;
		this.name = startName;
		if(startSetWeight) {
			finalWeight = startingWeight;
			finalWeightBool = true;
		}else {
			finalWeight = 1.0;
			finalWeightBool = false;
		}
		AssignmentWeight theAssignmentWeightVar = new AssignmentWeight(finalWeight);
		this.theAssignmentWeight = theAssignmentWeightVar;
		
	}
	
	public void rescoreAssignment(Score startScore) {
		this.assignmentScore = startScore;
	}
	
	public Double getWeight() {
		return theAssignmentWeight.getWeightFactor();
	}
	
	public Score getScore() {
		return assignmentScore;
	}
	
	public void setWeight(Double startWeight) {
		this.theAssignmentWeight.weightFactor = startWeight;
		this.finalWeight = startWeight;
	}
}