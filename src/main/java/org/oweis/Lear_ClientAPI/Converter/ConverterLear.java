package org.oweis.Lear_ClientAPI.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ConverterLear {
	String text;

	ConverterLear(String text) {
	this.text = text;
	}
	
	public String convertToXML(){
			
    String XMLtext =  convertStringToXml(text);
    XMLtext = addModifications(XMLtext);

    return XMLtext;
   
	}
	

		private static String convertStringToXml(String text){
			
			String replacedString = text
					.replaceAll("0\\]\"", "0\">")
					.replaceAll("1\\]\"", "1\">")
					.replaceAll("2\\]\"", "2\">")
					.replaceAll("3\\]\"", "3\">")
					.replaceAll("4\\]\"", "4\">")
					.replaceAll("5\\]\"", "5\">")
					.replaceAll("6\\]\"", "6\">")
					.replaceAll("7\\]\"", "7\">")
					.replaceAll("8\\]\"", "8\">")
					.replaceAll("9\\]\"", "9\">")
					.replaceAll("\\]\"", " ")
					.replaceAll("\\]\"", " ")
					.replaceFirst("\\[","<")
					.replaceAll("\\[","/><")
					.replaceAll("=","=\"")
					.replaceAll("/><ENDPARTNUMBER","")
					.replaceAll("/><ENDHEADER","")
					.replaceAll("/><CONNECTION","/><CONNECTION>")
					.replaceAll("/><PN=\"", "<PN PNname=\"")
					.replaceAll("/><ENDPN","</PN>")
					.replaceAll("/><ENDWIRE","/>")			
					.replaceAll("/><WIRE","<WIRE")
					.replaceAll("/><ENDCONNECTION", "</CONNECTION>")
					.replaceAll("/><CONNECTOR", "<CONNECTOR>")
					.replaceAll("/><FIXTURE", "<FIXTURE")
					.replaceAll("/><ENDFIXTURE", "</FIXTURE>")
					.replaceAll("/><ENDPIN", "/>")
					.replaceAll("/><PIN", "><PIN")
					.replaceAll("/><SPLICE", "<SPLICE")
					.replaceAll("/><ENDSPLICE", "/>")
					.replaceAll("/><ENDCONNECTOR","</CONNECTOR>");
				
				replacedString = "<LEAR>"+replacedString+"</LEAR>";

			return replacedString;
		}

		
		public static String addModifications(String text){
		    String[] partsINI = text.split("<SPLICE");
			String[] parts = partsINI[0].split("<FIXTURE");
			
			for(int i=1;i<parts.length;i++)
			{
			String part = parts[i].trim();
			int size = CountOccurence(part,"NAME");
			String partUpdate = part;
			//partUpdate = partUpdate.replace("/>", "SIZE=\""+size+"\" />");

			partUpdate = partUpdate.replaceFirst("NAME","FIXTUREID");
				for(int j=1;j<=size;j++){
					partUpdate = partUpdate.replaceFirst("NAME", "VOIE"+j);
				}
			text =  text.replace(part,partUpdate);
			} 
			return text;
		 
		}
		
		private static int CountOccurence(String text,String word)
		{ 
		    Pattern p = Pattern.compile(word);
		    Matcher m = p.matcher(text);
		    int count = 0;
		    while (m.find()){
		    	count +=1;
		    }
		 return count-1;
	    }

	}
