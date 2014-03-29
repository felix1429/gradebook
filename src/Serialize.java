//ITFP: C:/users/216003/java/eclipse windows/workbenchProjects/gradebook/data/data.ser
//HOME: C:/Users/Hennig/workspace/Gradebook/data/data.ser

import java.io.*;
import java.util.*;

public class Serialize
	implements Serializable{

	private static final long serialVersionUID = 1L;
	String homePath = "C:/Users/Hennig/workspace/Gradebook/data/data.ser";
	String ITFPPath = "C:/users/216003/java/eclipse windows/workbenchProjects/gradebook/data/data.ser";
	List<String> pathList = new ArrayList<String>(); //list which will contain paths of various places on different computers I save crap in
	Gradebook gradeBk;
	String path;
	
	public void main(String[] args) {
		pathList.add(homePath); 
		pathList.add(ITFPPath);
		
		for(String i : pathList) { //tests all paths in list and if finds legit one, sets it to 'path' var
			File file = new File(i);
			if(file.exists()) {
				path = i;
				break;
			}
		}	
	}
	
	public void serialize(Object ... theObj) {
		try {
			FileOutputStream fOut = new FileOutputStream(path);
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			for(int i = 0;i < theObj.length; i ++) {
				oOut.writeObject(theObj[i]);
			}
			oOut.close();
			fOut.close();
		}catch(IOException io) {
			io.printStackTrace();
			System.out.println("Error, project not saved");
		}
	}
	
	public void deserialize() {
		try {
			FileInputStream fIn  = new FileInputStream(path);
			ObjectInputStream oIn = new ObjectInputStream(fIn);
			this.gradeBk = (Gradebook) oIn.readObject();
			fIn.close();
			oIn.close();
		}catch(IOException i) {
			i.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
			System.out.println("Error in decoding information");
		}	
	}
}