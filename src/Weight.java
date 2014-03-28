import java.io.*;

public class Weight 
	implements Serializable {

	private static final long serialVersionUID = 1L;
	public Boolean isWeighted;
	
	public Weight(Boolean startWeight) {
		this.isWeighted = startWeight;
	}
	
	
	public Boolean getWeight() {
		return isWeighted;
	}	
}