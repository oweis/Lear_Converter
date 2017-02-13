package org.oweis.Lear_ClientAPI;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.oweis.Lear_ClientAPI.model.*;

public class RestAPIClientDesktop {
	
	Family family = new Family();
	Cable cable;
	Connector connector;

	Wire wire;
	Splice splice;
	
	ArrayList<Wire> wires = new ArrayList<>();
	ArrayList<Cable_Connector> cable_Connector = new ArrayList<>();
	
	Client client = ClientBuilder.newClient();
	WebTarget baseTarget = client.target("http://localhost:8081/Lear_API/webapi/");
	WebTarget entityTarget = baseTarget.path("{entityName}");
	WebTarget functionTarget = entityTarget.path("{functionName}");
	WebTarget attributTarget = functionTarget.path("{attributName}");
	WebTarget valueTarget = attributTarget.path("{attributValue}");
	WebTarget attributTarget2 = valueTarget.path("{attributName2}");
	WebTarget valueTarget2 = attributTarget2.path("{attributValue2}");
	
	public RestAPIClientDesktop() {
		
	}
	
	public Family getFamily(String namePassByUser){
		family = valueTarget.
				resolveTemplate("entityName","familys").
				resolveTemplate("functionName", "search").
				resolveTemplate("attributName", "namePassByUser").
				resolveTemplate("attributValue",namePassByUser).
				request(MediaType.APPLICATION_JSON).
				get(Family.class);
	
	return family;
	}
	
	public Boolean assertNamePassByUserExist(String namePassByUser){
		Family newFamily = new Family();
		newFamily = getFamily(namePassByUser);
		
		return newFamily != null;
	//	return getFamily(namePassByUser).equals(null);	
			}
	
	public Family getFamily(int id){
		family = valueTarget.
				resolveTemplate("entityName","familys").
				resolveTemplate("functionName","search").
				resolveTemplate("attributName","idFamily").
				resolveTemplate("attributValue",id).
				request(MediaType.APPLICATION_JSON).
				get(Family.class);
		return family;
	}
	
	public Cable getCables(int idFamily,String nameUsedInLear){
		cable = valueTarget2.
				resolveTemplate("entityName","cables").
				resolveTemplate("functionName", "search").
				resolveTemplate("attributName", "idFamily").
				resolveTemplate("attributValue",idFamily).
				resolveTemplate("attributName2","nameUsedInLear").
				resolveTemplate("attributValue2",nameUsedInLear).
				request(MediaType.APPLICATION_JSON).
				get(Cable.class);
	
		return cable;
	}
	
	public Connector getConnector(int idFamily,String nameConnector){
		connector = valueTarget2.
				resolveTemplate("entityName","connectors").
				resolveTemplate("functionName", "search").
				resolveTemplate("attributName", "idFamily").
				resolveTemplate("attributValue",idFamily).
				resolveTemplate("attributName2","nameConnector").
				resolveTemplate("attributValue2",nameConnector).
				request(MediaType.APPLICATION_JSON).
				get(Connector.class);
	
		return connector;
	}
	

	public ArrayList<Wire> getAllWiresByIdCable(int idCable){
		wires = valueTarget.
				resolveTemplate("entityName","wires").
				resolveTemplate("functionName", "search").
				resolveTemplate("attributName", "idCable").
				resolveTemplate("attributValue",idCable).
				request(MediaType.APPLICATION_JSON).
				get(new GenericType<ArrayList<Wire>>(){});
	
		return wires;
	}
	
	public ArrayList<Cable_Connector> addAllCable_Connectors(int idFamily,int idCable){
			cable_Connector = valueTarget2.
				resolveTemplate("entityName","cable_connectors").
				resolveTemplate("functionName", "search").
				resolveTemplate("attributName", "idFamily").
				resolveTemplate("attributValue",idFamily).
				resolveTemplate("attributName2", "idCable").
				resolveTemplate("attributValue2",idCable).
				request(MediaType.APPLICATION_JSON)
				.get(new GenericType<ArrayList<Cable_Connector>>(){});
	return cable_Connector;
	}
	
	
	public Response addFamily(Family newFamily){
		return  entityTarget.resolveTemplate("entityName","familys").request().post(Entity.json(newFamily));
	}
	
	public Response addCable(Cable newCable){
		return	entityTarget.resolveTemplate("entityName","cables").request().post(Entity.json(newCable));
	}
	
	public Response addWire(Wire newWire){
		return	entityTarget.resolveTemplate("entityName","wires").request().post(Entity.json(newWire));
	}

	public Response addSplice(Splice newSplice){
		return	entityTarget.resolveTemplate("entityName","splices").request().post(Entity.json(newSplice));
	}

	public Response addConnector(Connector newConnector){
		return	entityTarget.resolveTemplate("entityName","connectors").request().post(Entity.json(newConnector));
	}
	
	

	
}

