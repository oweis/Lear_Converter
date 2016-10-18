package org.oweis.Lear_ClientAPI.Converter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.oweis.Lear_ClientAPI.RestAPIClientDesktop;
import org.oweis.Lear_ClientAPI.ProgressSample;;

public class HomeLear implements ActionListener {
	public String path; 
	
	JButton importButton,saveButton;
	JFrame frame;
	JPanel headerPanel,controlPanel,checkBoxPanel,buttonsPanel;
	JTextArea infoText,pathText,familyText;
	JLabel pathLabel,familyLabel;
	JCheckBox checkBox;

	String inputPath;
	String namePassByUser;
	File fileInput;
	public void hideFrame(){frame.setVisible(false);}
	public void showFrame(){frame.setVisible(false);}
	
	public HomeLear() {
		headerPanel = new JPanel();
		controlPanel = new JPanel();
		checkBoxPanel = new JPanel();
		buttonsPanel = new JPanel();
		
		headerPanel.setLayout(new GridLayout(1,1));
		headerPanel.setSize(50,200);
	
		controlPanel.setLayout(new GridLayout(2,2));
		buttonsPanel.setLayout(new GridLayout(1,2));
		
		frame = new JFrame("Lear BIP");
		frame.setSize(600, 200);
	frame.setLayout(new GridLayout(2,1));
		
		 infoText = new JTextArea("Choisir un fichier à importer");
		
		 pathLabel = new JLabel("  Chemin du fichier : ");
		
		 pathText = new JTextArea(" . . . ");
		
		 familyLabel = new JLabel("  Nom de la famille : ");
		
		 familyText = new JTextArea(" . . . ");
		 
		
		
		importButton = new JButton("Importer un fichier");
		importButton.addActionListener(this);
		
		saveButton = new JButton("Enregistrer");
		saveButton.addActionListener(this);
		
		headerPanel.add(infoText);
		
		controlPanel.add(pathLabel);
		controlPanel.add(pathText);
		controlPanel.add(familyLabel);
		controlPanel.add(familyText);
		
		
		buttonsPanel.add(importButton);
		buttonsPanel.add(saveButton);
		
		//frame.add(headerPanel);
		frame.add(controlPanel);
		//frame.add(checkBoxPanel);
		frame.add(buttonsPanel);
		frame.setVisible(true);


	}
	public static void main(String args[]){
		HomeLear home = new HomeLear();
	}

	public boolean assertTesterFile(String pathText){
		String extension = "";

		int i = pathText.lastIndexOf('.');
		if (i > 0) {
		    extension = pathText.substring(i+1);
		}
		
		return extension.equals("tst");
		
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == importButton ) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(frame);
			if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getName());
			pathText.setText(selectedFile.getName());	
			
			inputPath = selectedFile.getAbsolutePath();
			
			}
			}
		if(event.getSource() == saveButton ) {
			RestAPIClientDesktop restAPIClientDesktop = new RestAPIClientDesktop();
			namePassByUser = familyText.getText();
			
			if(inputPath == null){
				JOptionPane.showMessageDialog(frame, "S'il vous plait! choisissez une fichier.");

			}
			else if(!assertTesterFile(inputPath)){
				JOptionPane.showMessageDialog(frame, "Le format de fichier est incorrect. choisissez une autre fichier (*.tst)");
				}
			else if(namePassByUser.equals("") || namePassByUser.equals(null)){
				JOptionPane.showMessageDialog(frame, "S'il vous plait! choisissez un nom.");
			}
		//	else if(restAPIClientDesktop.assertNamePassByUserExist(namePassByUser)){
			//	JOptionPane.showMessageDialog(frame, "Le nom que vous avez choisi est deja existe. essayer avec un autre nom");
		//}
			else {

				ProgressSample progressSample = new ProgressSample();
				hideFrame();
			//String inputPath = "C:/Files/txtTST.tst";
				fileInput = new File(inputPath);
			//String namePassByUser = "Family 2";
			
			ReaderLear readerLear = new ReaderLear(inputPath);
			String textTST = readerLear.ReadFromFile();
			
			ConverterLear converterLear = new ConverterLear(textTST);
			String textXML = converterLear.convertToXML();
			
			SaverLear saverLear = new SaverLear(textXML, inputPath);
		
			String outputPath = null;
			try {
				 outputPath = saverLear.saveFileFromString();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			File fileOutput = new File(outputPath);
			
			WriterLear writerLear = new WriterLear(fileOutput, namePassByUser);
			writerLear.writeToDataBase();
			showFrame();
			progressSample.hideBar();
			}
		};
	}
}