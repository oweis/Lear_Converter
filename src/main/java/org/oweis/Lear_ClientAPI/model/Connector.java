package org.oweis.Lear_ClientAPI.model;

import javax.ws.rs.core.Response;

import org.oweis.Lear_ClientAPI.RestAPIClientDesktop;


public class Connector implements BaliseState{
	
	private int id;
	private int idFamily;
	private String nameConnector;

	
	public Connector() {
		
	}
	
	public Connector(int id,int idFamily, String nameConnector) {
		this.id = id;
		this.idFamily = idFamily;
		this.nameConnector = nameConnector;
	
	}
	
	public int getIdFamily() {
		return idFamily;
	}

	public void setIdFamily(int idFamily) {
		this.idFamily = idFamily;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameConnector() {
		return nameConnector;
	}

	public void setNameConnector(String nameConnector) {
		this.nameConnector = nameConnector;
	}




	public void getMyName() {
		// TODO Auto-generated method stub	
	}
	public void getValuesForBalise(String attribut,String value){
		if(attribut.equals("FIXTUREID")) nameConnector = value;
		if(attribut.equals("idFamily")) idFamily=Integer.parseInt(value);
	}
	
	public void showValues(){		
	}
	
	public Response saveBalise() {
		RestAPIClientDesktop racd = new RestAPIClientDesktop();
		return  racd.addConnector(this);
	};
	
}