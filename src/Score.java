public class Score { //object with attributes number and letter grade
	public Double numGrade;
	public LtrGrade letterGrade;
	public String percentGrade;
	
	public Score(Double inputGrade) { //score has attributes grade and object lettergrade, which is a letter and a pronoun (a or an)
		LtrGrade theLetter = new LtrGrade(inputGrade);
		this.numGrade = inputGrade;
		this.letterGrade = theLetter;
		this.percentGrade = Long.toString(Math.round(inputGrade * 100)) + "%";
	}
	
	public Double getNumGrade() {
		return this.numGrade;
	}
	
	public LtrGrade getLetter() {
		return this.letterGrade;
	}
	
	public String getPercent() {
		return this.percentGrade;
	}
	
	public String getAll() {
		return getNumGrade().toString() + "\n" + getLetter().toString() + "\n" + getPercent().toString();
	}
}