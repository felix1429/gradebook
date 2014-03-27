import java.io.*;

public class Weight 
	implements Serializable {
	
	public Boolean isWeighted;
	
	public Weight(Boolean startWeight) {
		this.isWeighted = startWeight;
	}
	
	
	public Boolean getWeight() {
		return isWeighted;
	}	
}