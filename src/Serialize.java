//ITFP: C:/users/216003/java/eclipse windows/workbenchProjects/gradebook/data/data.ser
//HOME: C:/Users/Hennig/workspace/Gradebook/data/data.ser

import java.io.*;
import java.util.*;

public class Serialize
	implements Serializable{

	private static final long serialVersionUID = 1L;
	String homePath = "C:/Users/Hennig/workspace/Gradebook/data/";
	String ITFPPath = "C:/Users/216003/Java/eclipseWindows/WorkbenchProjects/Gradebook/data/";
    String laptopPath = "D:/Workspace/gradebook/data";
	List<String> pathList = new ArrayList<String>(); //list which will contain paths of various places on different computers I save crap in
	Gradebook gradeBk;
	String path;
	File dir;
	
	public Serialize() { //initializes var
		pathList.add(homePath); 
		pathList.add(ITFPPath);
        pathList.add(laptopPath);
		for(String i : pathList) { //tests all paths in list and if finds legit one, sets it to 'path' var
			File file = new File(i);
			file.mkdir();
			if(file.exists()) {
				this.path = i; //sets PATH var
				this.dir = file;
				break; //exits
			}
		}
	}

	
	public Boolean serialize(String fileName, Object theObj) { //method to serialize....not sure if keeping varArgs or not
		try {
			File file = new File(this.dir.getAbsolutePath() + ("\\" + fileName + ".ser")); //allows user to select name for file to be saved as
			if(!file.exists()) { //creates file if it does not exist
				file.createNewFile();
			}	
			FileOutputStream fOut = new FileOutputStream(file.getPath()); //FOS for writing .ser file to .txt file
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(theObj);
			oOut.close();
			fOut.close();
			return true; //returns true if completed successfully
		}catch(IOException io) {
			io.printStackTrace();
			System.out.println("Error, project not saved");
			return false;
		}
	}
	
	public Boolean deserialize(String fileName) {
		try {
			FileInputStream fIn  = new FileInputStream(this.path.concat(fileName + ".ser"));
			ObjectInputStream oIn = new ObjectInputStream(fIn);
			this.gradeBk = (Gradebook) oIn.readObject(); //sets gradeBk object to deserialized obj
			fIn.close();
			oIn.close();
			return true;
		}catch(IOException io) { //exception for reading file
			io.printStackTrace();
			return false;
		}catch(ClassNotFoundException c) { //exception for loaded file
			c.printStackTrace();
			System.out.println("Error in loading project");
			return false;
		}	
	}
}