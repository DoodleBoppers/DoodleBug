package doodle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class World {
	public final int size = 5; //Size of the world.
	Bug grid[][] = new Bug[size][size]; //Keeps track of all the "cells" with and without bugs.
	JFrame gridFrame = new JFrame("Doodle"); //Swing representation of the world.
	JLabel labels[][] = new JLabel[size][size]; //Keeps track of the Swing representation of each "cell"
	int tempX, tempY; //Keeps track of where a bug was.
	Random rand = new Random(); //Random number generator for movement.
	
	World() { //What main uses to get started
		gridFrame.setLayout(new GridLayout(size,size));
		populate();
		toConsole();
		worldMove(1);
		toConsole();
	}
	
	void populate() { //Fills the grid with the appropriate number of bugs randomly, or at least it will.
		for(int i = 0; i < size; i++) {
			for(int i2 = 0; i2 < size; i2++) {
				grid[i][i2] = new Bug(0);
				labels[i][i2] = new JLabel("[ ]");
				gridFrame.getContentPane().add(labels[i][i2]);
			}
			addAnt(2,2);
		}
	}
	
	boolean isTaken(Bug b) { //Checks if a cell is inhabited.
		if(b.getType() == 0) {
			return false;
		}
		else return true;
	}
	
	void toConsole() { //Prints the world to the console with ASCII art.
		for(int i = 0; i < size; i++) {
			for(int i2 = 0; i2 < size; i2++) {
				System.out.print(labels[i][i2].getText());
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void bugMove(Bug b, int x, int y, int dir) { //Moves the bug
		tempX = x;
		tempY = y;
		int newX;
		int newY;
		switch(b.getType()) {
		case(0): return; //If nothing is there
		case(1): {switch(dir) { //If it's an ant
					case(1): { //Up
						newX = tempX - 1;
						newY = tempY;
						if(!(isTaken(grid[newX][newY]))) {
							grid[newX][newY] = b;
							labels[newX][newY].setText("[A]");
							grid[tempX][tempY].kill(); //Clear the start cell
							labels[tempX][tempY].setText("[ ]"); //Same here
						}
						break;
					}
					case(2): {
					//right
						break;
					}
					case(3): { //Down
						newX = tempX - 1;
						newY = tempY;
						if(!(isTaken(grid[newX][newY]))) {
							grid[newX][newY] = b;
							labels[newX][newY].setText("[A]");
							grid[tempX][tempY].kill(); //Clear the start cell
							labels[tempX][tempY].setText("[ ]"); //Same here
						}
						break;
					}
					case(4): {
					//left
						break;
					}
					default: {
						System.out.println("Error! Invalid moveCode.");
					}
				} break; }
		case(2): {switch(dir) { //if it's a doodlebug
				case(1): {
					//move the bug up
					break;
				}
				case(2): {
				//right
					break;
				}
				case(3): {
					//down
					break;
				}
				case(4): {
					//left
					break;
				}
				default: {
					System.out.println("Error! Invalid moveCode.");
				}
			} break; }
		}
	}
	
	void worldMove(int r) {
		//int r = rand.nextInt(4) + 1;
		for(int i = 0; i < size; i++) {
			for(int i2 = 0; i2 < size; i2++) {
				if(grid[i][i2].getType() == 1) {
					bugMove(grid[i][i2], i, i2, r);
				}
			}
		}
	}
	
	void addAnt(int x, int y) {
		grid[x][y] = new Bug(1);
		labels[x][y] = new JLabel("[A]");
	}
	
	void addDoodle(int x, int y) {
		grid[x][y] = new Bug(2);
		labels[x][y] = new JLabel("[D]");
	}
	
}