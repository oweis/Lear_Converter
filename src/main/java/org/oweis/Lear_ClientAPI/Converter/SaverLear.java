package org.oweis.Lear_ClientAPI.Converter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaverLear {
	
	String text;
	String fileOutput;
	public SaverLear(String text,String fileOutput) {
		this.text = text;
		this.fileOutput = fileOutput;
	}
	

	public void saveFileFromString() throws FileNotFoundException {
		 PrintWriter out;
		
			out = new PrintWriter(fileOutput);
		    out.println(text);
		    out.close();
		
		    
	}
}
