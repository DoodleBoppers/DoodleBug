/*
Jarod Owen
Bug.java
14/MAR/2018
Assignment #20
This program is the template
for the bug object.
*/
package doodle;

public class Bug {
	private int bugType; //0 = Empty, 1 = Ant, 2 = Doodle
	private int belly = 0; //Steps since last meal
	private int breed = 0; //Steps since last reproduction
	private boolean hasMoved = false; //Has it moved yet?
	
	Bug(int b) { //Initializer
		if((b > -1) & (b < 3)) {
			bugType = b;
		}
		else {
			bugType = 0;
			System.out.println("Invalid bug type (" + b
					+ "), default value (0) used");
		}
	}
	
	public int getType() {return bugType;} //These are the accessor methods
	public int getBelly() {return belly;}
	public int getBreed() {return breed;}
	public boolean getHM() {return hasMoved;}
	
	public void setType(int b) { //These are the mutators
		if((b > -1) & (b < 3)) {
			bugType = b;
		}
		else {
			bugType = 0;
			System.out.println("Invalid bug type (" + b
					+ "), default value (0) used");
		}
	}
	public void setHM(boolean m) {hasMoved = m;}
	public void setBR(int i) {breed = i;}
	public void setBE(int i) {belly = i;}
	public void setB(int i) {breed = i; belly = i;}
	public void clearBE() {belly = 0;}
	public void clearBR() {breed = 0;}
	public void clearB() {breed = 0; belly = 0;}
	public void incBR() {breed += 1;}
	public void incBE() {belly += 1;}
	public void incB() {incBR(); incBE();}
	
	public void kill() { //This makes the squish() method in world simpler
		bugType = 0;
		hasMoved = false;
		clearB();
	}
}