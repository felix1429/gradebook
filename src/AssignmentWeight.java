import java.io.Serializable;

public class AssignmentWeight 
	implements Serializable {

	public Double weightFactor;
	
	public AssignmentWeight(Double weightVar) {
		this.weightFactor = weightVar;
	}
	
	public Double getWeightFactor() {
		return this.weightFactor;
	}
}
