//ITFP: C:/users/216003/java/eclipse windows/workbenchProjects/gradebook/savedData/data.ser

import java.io.*;

public class Serialize
	implements Serializable{

	private static final long serialVersionUID = 1L;
	String path = "C:/users/216003/java/eclipsewindows/workbenchprojects/gradebook/data/data.ser";
	Gradebook gradeBk;
	
	public void serialize(Object ... theObj) {
		try {
			File file = new File(path);
			if(!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fOut = new FileOutputStream(file);
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			for(int i = 0;i < theObj.length; i ++) {
				oOut.writeObject(theObj[i]);
			}
			oOut.close();
			fOut.close();
		}catch(IOException i) {
			i.printStackTrace();
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
			System.out.println("Error");
		}	
	}
}
