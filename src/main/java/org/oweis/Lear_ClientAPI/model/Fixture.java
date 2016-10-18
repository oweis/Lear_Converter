package org.oweis.Lear_ClientAPI.model;

import javax.ws.rs.core.Response;

import org.oweis.Lear_ClientAPI.RestAPIClientDesktop;


public class Fixture implements BaliseState{
	
	private int id;
	private int idFamily;
	private String nameFixture;

	
	public Fixture() {
		
	}
	
	public Fixture(int id,int idFamily, String nameFixture) {
		this.id = id;
		this.idFamily = idFamily;
		this.nameFixture = nameFixture;
	
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

	public String getNameFixture() {
		return nameFixture;
	}

	public void setNameFixture(String nameFixture) {
		this.nameFixture = nameFixture;
	}




	public void getMyName() {
		// TODO Auto-generated method stub	
	}
	public void getValuesForBalise(String attribut,String value){
		if(attribut.equals("FIXTUREID")) nameFixture = value;
		if(attribut.equals("idFamily")) idFamily=Integer.parseInt(value);
	}
	
	public void showValues(){		
	}
	
	public Response saveBalise() {
		RestAPIClientDesktop racd = new RestAPIClientDesktop();
		return  racd.addFixture(this);
	};
	
}