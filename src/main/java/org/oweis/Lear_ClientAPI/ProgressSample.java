package org.oweis.Lear_ClientAPI;
  
import javax.swing.JFrame;

import javax.swing.JProgressBar;


public class ProgressSample {
	JFrame f;
	public void hideBar(){
		f.setVisible(false);
		}
	
	public ProgressSample(){
	     f = new JFrame("Loading ...");
	     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	     JProgressBar progressBar = new JProgressBar();
	     progressBar.setValue(25);
	     progressBar.setStringPainted(true);
	     f.setSize(300, 100);
	     f.setVisible(true);
	}
}
