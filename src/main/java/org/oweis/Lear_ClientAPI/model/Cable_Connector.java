package org.oweis.Lear_ClientAPI.model;


public class Cable_Connector {

		
		int id;
		int idCable;
		int idConnector;
		
	public Cable_Connector(){
		
	}

	public Cable_Connector(int idCable,int idConnector) {
			this.idCable = idCable;
			this.idConnector = idConnector;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCable() {
		return idCable;
	}

	public void setIdCable(int idCable) {
		this.idCable = idCable;
	}

	public int getIdConnector() {
		return idConnector;
	}

	public void setIdConnector(int idConnector) {
		this.idConnector = idConnector;
	}

}
