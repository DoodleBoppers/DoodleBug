package doodle;

public class Bug {
	private int bugType; //0 = Empty, 1 = Ant, 2 = Doodle
	private int stepCtr = 0;
	private boolean hasEaten = false;
	
	Bug(int b) {
		if((b > -1) & (b < 3)) {
			bugType = b;
		}
		else {
			bugType = 0;
			System.out.println("Invalid bug type (" + b
					+ "), default value (0) used");
		}
	}
	public int getType() {return bugType;}
	public int getStep() {return stepCtr;}
	public boolean getHE() {return hasEaten;}
	
	public void setType(int b) {
		if((b > -1) & (b < 3)) {
			bugType = b;
		}
		else {
			bugType = 0;
			System.out.println("Invalid bug type (" + b
					+ "), default value (0) used");
		}
	}
	public void setHE(boolean e) {hasEaten = e;}
	public void clearStep() {stepCtr = 0;}
	public void incStep() {stepCtr++;}
	
	public void kill() {
		bugType = 0;
		stepCtr = 0;
		hasEaten = false;
	}
}
