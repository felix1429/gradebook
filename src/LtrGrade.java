import java.io.Serializable;

public class LtrGrade 
	implements Serializable { //letter grade, letter and its pronoun
	
	public String gradePronoun; // 'a' or 'an'
	public String actualLetter;
	
	public LtrGrade(Double numGrade) { // gets a letter from percentage
		if (numGrade >= .90) {
			actualLetter = "A";
			gradePronoun = "an";
		} else if (.8 <= numGrade && numGrade < .9) {
			actualLetter = "B";
			gradePronoun = "a";
		} else if (.7 <= numGrade && numGrade < .8) {
			actualLetter = "C";
			gradePronoun = "a";
		} else if (.6 <= numGrade && numGrade < .7) {
			actualLetter = "D";
			gradePronoun = "a";
		} else {
			actualLetter = "F";
			gradePronoun = "an";
		}
	}
}