public class Assignment { //class for assignments for Student class
	Score assignmentScore;
	Integer assignmentNumber;
	AssignmentWeight theAssignmentWeight;
	Boolean setWeight;
	Double finalWeight;
	Boolean finalWeightBool;
	
	public Assignment(Score inputScore, Integer startNumber, Boolean startSetWeight, Double startingWeight) {
		this.assignmentScore = inputScore;
		this.assignmentNumber = startNumber;
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
}	

