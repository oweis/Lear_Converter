package org.oweis.Lear_ClientAPI.Converter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;

public class ReaderLear  {

	String filePath;
	
	public ReaderLear(String filePath){
	
		this.filePath = filePath;
	}
	
	public String ReadFromFile(){
	   
	InputStream inputStream = null;
	String text = null;

	try {
		inputStream = new FileInputStream(filePath);
		text = FixEndLineProblem(inputStream);
		inputStream.close();
		
		} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	catch (IOException e) {
		e.printStackTrace();
		} 
    return text;
	}
   
	private static String FixEndLineProblem(InputStream inputStream){
	    InputStreamReader r = new InputStreamReader(inputStream);
	    BufferedReader bufferedReader = new BufferedReader(r);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {	
				stringBuffer.append(line);
				stringBuffer.append("\" ");
				stringBuffer.append("\n");
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
		}
	}