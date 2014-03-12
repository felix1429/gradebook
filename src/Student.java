import java.util.*;

public class Student {

	Score grade;
	Integer testCountVar = 0;
	public String name;
	public Integer testCount;
	Boolean passingBool;
	Score scoreVar;
	Weight theWeight;
	ClassGradebook gradeBk;
	Map<Integer, Assignment> assignmentMap = new HashMap<Integer, Assignment>(); //map of assignments for each student


	public Student(String startName) { //initializes student
		Score aScore = new Score((double)0);
		this.name = startName;
		this.grade = aScore;
		this.testCount = 1;
	}
	
	
	public boolean testScore(String startScore) { //sees if string is valid score
		String tempNumStr = "";
		Integer firstInt = 0;
		Integer secondInt = 0;
		char[] aCharArray = startScore.toCharArray(); //turns input into array so it can be iterated over
		for(char scoreChar : aCharArray) { // parses through string to get int before the "" and the int after
			if(Character.toString(scoreChar).equals("/")) { //separates between first and second int
				try {
					firstInt = Integer.parseInt(tempNumStr);
				}catch(NumberFormatException e) {
					return false;
				}
				if(firstInt == null) { //makes sure that score input is valid
					return false;
				}
				tempNumStr = ""; //resets tempNumStr so it can get value of secondInt
			}else {
				tempNumStr += Character.toString(scoreChar); //concats integer in case numbers are 2 digit
			}
		}
		try {
			secondInt = Integer.parseInt(tempNumStr);
		}catch(NumberFormatException e) {
			return false;
		}
		if(secondInt == null) { //makes sure that score input is valid
			return false;
		}
		return true;
	}
	
	
	public Score getScore(String startScore) { //get score value from string x/y
		//run testScore() first
		String tempNumStr = "";
		Integer firstInt = 0;
		Integer secondInt = 0;
		char[] aCharArray = startScore.toCharArray(); //turns input into array so it can be iterated over
		for(char scoreChar : aCharArray) { // parses through string to get int before the "" and the int after
			if(Character.toString(scoreChar).equals("/")) { //separates between first and second int
				firstInt = Integer.parseInt(tempNumStr);
				tempNumStr = ""; //resets tempNumStr so it can get value of secondInt
			}else {
				tempNumStr += Character.toString(scoreChar); //concats integer in case numbers are 2 digit
			}
		}
		secondInt = Integer.parseInt(tempNumStr); //gets Integer from concated string tempNumStr
		Score aScore = new Score(((double)firstInt / (double)secondInt)); //calculates scoreAssignment theNewOne = new Assignment(testScore(startScore));
		return aScore;
	}	

	
	public void addAssignment(String startScore, Boolean weightOrNot, Double startWeight) { // score must be formated as x/y
		scoreVar = getScore(startScore); //gets score
		if(!weightOrNot) { //if not weighted overrides argument and sets weight to one
			startWeight = 1.0;
		}
		Assignment theNewOne = new Assignment(scoreVar, this.testCount, weightOrNot, startWeight); //creates new assignment
		this.assignmentMap.put(this.testCount, theNewOne); //adds assignment with number and score
		this.testCount ++; //increments variable that keeps track of what number the assignment is for the student
		this.grade = this.getGrade();
	}
	
	
	public Score getGrade() { //returns a grade as .xx, made to be formated as percent
		double weightAverage = 0;
		double weightCount = 0;
		double weightTotal = 0;
		double weight1Count = 0;
		double weight1Average = 0;
		double weight1Total = 0;
		double unweightAverage = 0;
		double unweightCount = 0;
		double unweightTotal = 0;
		double weightFactor = 0;
		double weightFactor1 = 0;
		Boolean finalOutput = false;
		double finalGradeVar;
		for(Map.Entry<Integer, Assignment> entry : this.assignmentMap.entrySet()) { //loops through entries in map
			if(entry.getValue().finalWeightBool.equals(true)) { //sees if assignment is weighted and loops to find weight value if so
				if(weightCount == 0) {
					weightFactor = entry.getValue().finalWeight; //gets weight value
					weightCount ++;
					weightTotal += entry.getValue().getScore().numGrade; //adds score to running total
				}else if(entry.getValue().finalWeight == weightFactor) { //if is first weight value, add
					weightCount ++;
					weightTotal += entry.getValue().getScore().numGrade;
				}else if(((entry.getValue().finalWeight) != weightFactor) || (entry.getValue().finalWeight == weightFactor1)) { //if is second weight value
					weightFactor1 = entry.getValue().finalWeight;
					weight1Count ++;
					weight1Total += entry.getValue().getScore().numGrade;
				}
			}else if(entry.getValue().finalWeightBool.equals(false)) { //if not weighted just takes average
				unweightTotal += entry.getValue().assignmentScore.numGrade;
				unweightCount ++;
				finalOutput = true;
			}
		}
		weight1Average = (weight1Total / weight1Count) * weightFactor1;
		weightAverage = (weightTotal / weightCount) * weightFactor;
		unweightAverage = unweightTotal / unweightCount;
		if(finalOutput == true) { //if assignments are not weighted returns unweightAverage
			finalGradeVar = unweightAverage;
		}else {
			finalGradeVar = weightAverage + weight1Average;
		}
		
		Score theGrade = new Score(finalGradeVar); //creates score
		this.grade = theGrade; //adds value to student's overall grade
		return this.grade;
	}
	
	
	public Boolean isPassing() { //returns true if grade is greater than 60%
		scoreVar = this.getGrade();
		if(this.scoreVar.numGrade >= .6) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public String getAllInfo() { //returns all the info of the current student
		String nameOutput = "";
		String gradeOutput;
		String assignmentOutput = "";
		String output;
		String passingOutput;
		String passingVar;
		Integer assignmentCount = 1;
		nameOutput = "Name: " + this.name + "\n"; //student's name
		if(this.assignmentMap.isEmpty()) { //if no assignments
			output = "No assignments added";
		}else {
			for(Assignment a : assignmentMap.values()) { //gets score of each assignment
				assignmentOutput += "Assignment " + assignmentCount + (ClassGradebook.isWeighted ? (" (" + (double)Math.round(a.theAssignmentWeight.weightFactor * 100) / 100 + ")") : "") + ": " //if weighted, prints weight factor
					+ a.assignmentScore.percentGrade + ", " + a.assignmentScore.letterGrade.gradePronoun + " " 
					+ a.assignmentScore.letterGrade.actualLetter + "\n"; 
				assignmentCount ++;
			}
			this.grade = this.getGrade();
			gradeOutput = this.grade.percentGrade + "\n"; //converts to string make next line easier
			if(this.isPassing()) {  //converts passing bool into strings
				passingVar = "passing";
			}else {
				passingVar = "failing";
			}
			passingOutput = (this.name + " is " + passingVar + "\n");
			output = nameOutput + assignmentOutput + gradeOutput + passingOutput; //printed output of all info
		}
		return output;
	}
}