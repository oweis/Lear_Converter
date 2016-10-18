package org.oweis.Lear_ClientAPI.Converter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

public class SaverLear {
	
	private String text;
	private String fileInput;
	public SaverLear(String text,String fileInput) {
		this.text = text;
		this.fileInput = fileInput;
	}
	

	public String saveFileFromString() throws FileNotFoundException {
		 	
		 	String fileOutput = setFileName(fileInput);
			PrintWriter out;
		
			out = new PrintWriter(fileOutput);
		    out.println(text);
		    out.close();
		return fileOutput;
		    
	}
	
	private String setFileName(String fileInput){
		 Date date = new Date();
		 
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
		    int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH);
		    int day = cal.get(Calendar.DAY_OF_MONTH);
		    int hour = cal.get(Calendar.HOUR_OF_DAY);
		    int minute = cal.get(Calendar.MINUTE);
		    int second = cal.get(Calendar.SECOND);
		    
		    
		    String fileOutput = removeExtension(fileInput) +""+ year+""+month+""+day+""+hour+""+minute+""+second +".xml";
		    	return  fileOutput;
		}
		


		private String removeExtension(String str) {
	        String nvStr =  str.substring(0,str.length()-1).
	         substring(0,str.length()-2).
	         substring(0,str.length()-3).
	         substring(0,str.length()-4);
	         return nvStr;
	    }
}
