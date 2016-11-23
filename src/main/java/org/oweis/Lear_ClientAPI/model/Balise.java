package org.oweis.Lear_ClientAPI.model;

import javax.ws.rs.core.Response;
import org.oweis.Lear_ClientAPI.RestAPIClientDesktop;

public class Balise {
	
	BaliseState isFamily;
	BaliseState isCable;
	BaliseState isWire;
	BaliseState isConnector;
	BaliseState isSplice;
	BaliseState noBalise;
	BaliseState baliseState;
	
	RestAPIClientDesktop racd = new RestAPIClientDesktop();

	public Balise(){
		isFamily = new Family();
		isCable = new Cable();
		isWire = new Wire();
		isConnector = new Connector();
		isSplice = new Splice();
		noBalise = new NoBalise();
	}
	
	public void setBaliseState(BaliseState baliseState){
		this.baliseState = baliseState;
	}
		
	public BaliseState getBaliseState() {
		return baliseState;
	}
	
	public void check(String value){ 
		if(value.equals("HEADER")) this.setBaliseState(isFamily);
		else if(value.equals("PARTNUMBER"))  this.setBaliseState(isCable);
		else if(value.equals("WIRE"))  this.setBaliseState(isWire);
		else if(value.equals("FIXTURE"))  this.setBaliseState(isConnector);
		else if(value.equals("SPLICE"))  this.setBaliseState(isSplice);
		else this.setBaliseState(noBalise);
	}
	
	public void getValuesForBalise(String attribut,String value) {
		baliseState.getValuesForBalise(attribut,value);
	}

	public Response saveBalise(){
		return baliseState.saveBalise();
	}
	public void showValues(){
		baliseState.showValues();
	}
	
}
