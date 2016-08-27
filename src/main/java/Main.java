import java.util.ArrayList;

import org.oweis.Lear_ClientAPI.RestAPIClientDesktop;
import org.oweis.Lear_ClientAPI.model.Wire;


public class Main {

	public static void main(String[] args) {
		ArrayList<Wire> wires = new ArrayList<>();
		ArrayList<Wire> wiresUpdate = new ArrayList<>();
		
		RestAPIClientDesktop restAPIClientDesktop = new RestAPIClientDesktop();
		
				wires = restAPIClientDesktop.getAllWiresByIdPartNumber(1);
				AdaptWires adaptWires = new AdaptWires(wires);

				System.out.println("Wires : " + wires.size());

		
			//	for(Wire wire : wires){System.out.println(wire.getNameWire());}
				
				wiresUpdate = adaptWires.updateWiresList();
				System.out.println("Wires After Update : " + wiresUpdate.size());
			for(Wire wire : wiresUpdate){
					//System.out.println(wire.getNameWire());
					System.out.print("Splice A : " + wire.getSplice_A());
					System.out.print("Splice B : " + wire.getSplice_B());

					
				}
			 
	}

}
