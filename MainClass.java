/*
Jarod Owen
MainClass.java
14/MAR/2018
Assignment #20
This program starts the simulation
*/
package doodle;

import javax.swing.*;

public class MainClass { //This gets everything started
	  public static void main(String args[]) {  
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new World();
			} 
		}); 
	}
}
