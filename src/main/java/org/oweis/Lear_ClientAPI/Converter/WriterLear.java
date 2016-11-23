package org.oweis.Lear_ClientAPI.Converter;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.oweis.Lear_ClientAPI.RestAPIClientDesktop;
import org.oweis.Lear_ClientAPI.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WriterLear {
	
	// Input From Home
	
	//Input From Connexion(after passing by home of course)
//	static String pathFile = "C:/Files/txtXML.xml";
	

	static String idFamily = "0";
	static String idCable = "0" ;
	static String nameCable = "nameCable";
	static String nameConnector = "nameConnector";
	static String idConnector = "0";
	File file;
	String namePassByUser;
	static ArrayList<Integer> arrayListIdCable = new ArrayList<>();

	
	public WriterLear(File file,String namePassByUser){
		this.file = file;
		this.namePassByUser = namePassByUser;
	}
	
	
	
	public void writeToDataBase(){  
	    try {
			
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
								.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		if (doc.hasChildNodes()) {
			printNote(doc.getChildNodes());

		}
	  }catch (Exception e){
		System.out.println(e.getMessage());
	    }
	    RestAPIClientDesktop restAPIClientDesktop = new RestAPIClientDesktop();
	    for(int idCable : arrayListIdCable){
	    restAPIClientDesktop.addAllCable_Connectors(Integer.parseInt(idFamily), idCable);
	    	}
	    }

  private void printNote(NodeList nodeList) {
	  Balise balise = new Balise();
	  
	  String entityName = null;
    for (int count = 0; count < nodeList.getLength(); count++) {

	Node tempNode = nodeList.item(count);

	// make sure it's element node.
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

		String nodeName = tempNode.getNodeName();
		balise.check(nodeName);
		
		//In our xml file we will not need tempNode.getTextContent()(nodeValue)
		//cause every balise type is empty

		if (tempNode.hasAttributes()) {

			// get attributes names and values
			NamedNodeMap nodeMap = tempNode.getAttributes();

			for (int i = 0; i < nodeMap.getLength(); i++) {

				Node node = nodeMap.item(i);
			
				entityName = tempNode.getNodeName(); 
				String attributName = node.getNodeName();
				String attributValue = node.getNodeValue();
				
				setIdsWhenPossible(entityName,attributName,attributValue);
					
			 	balise.getValuesForBalise("namePassByUser", namePassByUser);
				balise.getValuesForBalise("idFamily", idFamily);
				balise.getValuesForBalise("idPartNumber", idCable);
				balise.getValuesForBalise("idFixture",idConnector);
				
			
				balise.getValuesForBalise(attributName,attributValue);

				}	
		}
		balise.showValues();	
		balise.saveBalise();
		
		if (tempNode.hasChildNodes()) {
			// loop again if has child nodes
			printNote(tempNode.getChildNodes());
			}
		}
    }
  }
	public  void setIdsWhenPossible(String entityName,String attributName,String attributValue){		
		RestAPIClientDesktop racd = new RestAPIClientDesktop();
		 	
		if(entityName.equals("PARTNUMBER") & attributName.equals("NAME")){
			Integer idFamilyInt =  racd.getFamily(namePassByUser).getId();
			idFamily = idFamilyInt.toString();
			}
		if(entityName.equals("PN") & attributName.equals("PNname") ){
			nameCable = attributValue;
			Integer idPartNumberInt = racd.getCables(Integer.parseInt(idFamily),nameCable).getId();
			idCable = idPartNumberInt.toString();
			arrayListIdCable.add(idPartNumberInt);

			}
		if(entityName.equals("FIXTURE") & attributName.equals("FIXTUREID")){
			nameConnector = attributValue;
			} 
		
		}
 
}
