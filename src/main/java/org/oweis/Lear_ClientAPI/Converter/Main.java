package org.oweis.Lear_ClientAPI.Converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import org.oweis.Lear_ClientAPI.RestAPIClientDesktop;

	public class Main {

		static String fileOutputPath;
		//input user
		static File fileInput = new File("C:/Files/txtTST.tst");
		static File file = new File("C:/Files/txtXML.xml");
		static String textTST = null;
		static String textXML = null;

	public static void main(String[] args) {
			RestAPIClientDesktop rest = new RestAPIClientDesktop();
				
	
			
			//input user
			String namePassByUser = "namePassByUser2";
			
			
			if(assertTesterFile(fileInput)){
				String filePath = fileInput.getAbsolutePath();
				ReaderLear readerLear = new ReaderLear(filePath);
				textTST = readerLear.ReadFromFile();
			}
			else{
				//syso ayrj3u des alerts apres
				System.out.println("le fichier choisi n'est pas un tester file!try again");
			}
			
			ConverterLear converterLear = new ConverterLear(textTST);
			textXML = converterLear.convertToXML();
			 
			setFileOutputPath(fileInput);
			
			
			 SaverLear saverLear = new SaverLear(textXML,fileOutputPath);	
			 try {
				saverLear.saveFileFromString();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			if(!rest.assertNamePassByUserExist(namePassByUser)){
			WriterLear writerLear = new WriterLear(file,namePassByUser);
			writerLear.writeToDataBase();
			}
			else{
				System.out.println("Choisir un autre nom pour ton famille svp! ou cocher generate auto.");
			}
		
	}
	//n9adha apres
	public static  boolean assertTesterFile(File file){
		String fileName = file.getName();
		String extension = getFileExtension(fileName);
		if(extension.equals("tst") ){return true;}
			return false;
		}
	
	public static String getFileName(String fileName){
		String[] fileNamePart = fileName.split("\\.");
		if(fileNamePart.length==2)
		{
			return fileNamePart[0];
		}
		return "no_name";
	}
	
	public static String getFileExtension(String fileName){
		String[] fileNamePart = fileName.split("\\.");
		if(fileNamePart.length==2)
		{
			return fileNamePart[1];
		}
		return "no_extension";
	}
	
	public static void setFileOutputPath(File fileInput){

		String fileInputPath = fileInput.getName();
		String outputParent = fileInput.getParent();
		String outputName = getFileName(fileInputPath);
		Date now = new Date();
		if(!outputName.equals("no_extension")){ 
			fileOutputPath = outputParent + "\\" +outputName + now.toString()+ ".xml";
			System.out.println(fileOutputPath);}
		else System.out.println("kayn chi Moxkil");
		}
	
	public boolean assertCheckKeepXml(){return true;}
	}