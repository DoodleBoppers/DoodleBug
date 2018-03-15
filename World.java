/*
Jarod Owen
World.java
14/MAR/2018
Assignment #20
This program manages interaction
between Bug objects.
*/
package doodle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class World implements ActionListener {
	public final int size = 20; //Size of the world.
	Bug grid[][] = new Bug[size][size]; //Keeps track of all the "cells" with and without bugs.
	JFrame gridFrame = new JFrame("Doodle"); //Swing representation of the world.
	JFrame btnFrame = new JFrame("Next Button"); //Swing frame for the button to step through time.
	JLabel labels[][] = new JLabel[size][size]; //Keeps track of the Swing representation of each "cell"
	int tempX, tempY; //Keeps track of where a bug was.
	Random rand = new Random(); //Random number generator for movement.
	int numAnt = 0; //Number of ants
	int numDoodle = 0; //Number of Doodlebugs
	JButton next = new JButton("Next"); //The button to step through time
	int dirPerms[][] = {{1,2,3,4},{1,2,4,3},{1,3,2,4},{1,3,4,2},{1,4,2,3},{1,4,3,2}, //Every permutation of the directions.
						{2,1,3,4},{2,1,4,3},{2,3,1,4},{2,3,4,1},{2,4,3,1},{2,4,1,3}, //Used to help with random direction choosing.
						{3,1,2,4},{3,1,4,2},{3,2,1,4},{3,2,4,1},{3,4,1,2},{3,4,2,1},
						{4,1,2,3},{4,1,3,2},{4,2,1,3},{4,2,3,1},{4,3,1,2},{4,3,2,1}};
	
	World() { //What main uses to get started
		next.addActionListener(this); //Starts listening to the next button
		populate(); //Putting bugs onto the grid
	}
	
	void populate() { //Fills the grid with the appropriate number of bugs randomly
		for(int i = 0; i < size; i++) {
			for(int i2 = 0; i2 < size; i2++) {
				grid[i][i2] = new Bug(0);
				labels[i][i2] = new JLabel("[ ]");
				gridFrame.getContentPane().add(labels[i][i2]);
			}
		}
		while(numAnt < 75) { //Adds the ants
			int r;
			for(int i = 0; i < size; i++) {
				for(int i2 = 0; i2 < size; i2++) {
					r = rand.nextInt(150);
					if((r % 5 == 0) & (!isTaken(i,i2)) & (numAnt < 75)) {
						addAnt(i,i2);
						numAnt += 1;
					}
				}
			}
		}
		while(numDoodle < 25) { //Adds the doodlebugs
			int r;
			for(int i = 0; i < size; i++) {
				for(int i2 = 0; i2 < size; i2++) {
					r = rand.nextInt(150);
					if((r % 5 == 0) & (!isTaken(i,i2)) & (numDoodle < 25)) {
						addDoodle(i,i2);
						numDoodle += 1;
					}
				}
			}
		}
		//Setup for the Swing frames
		gridFrame.setVisible(true);
		gridFrame.setSize(700,700);
		gridFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gridFrame.setLayout(new GridLayout(size,size));
		btnFrame.getContentPane().add(next);
		btnFrame.setVisible(true);
		btnFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnFrame.setSize(125, 65);
		
		//toConsole();
	}
	
	boolean isTaken(int x, int y) throws IndexOutOfBoundsException { //Checks if a cell is inhabited.
		try { 														 //Also checks if cell is in grid.
			if(grid[x][y].getType() == 0) {
				return false;
			}
			else return true;
		}
		catch(IndexOutOfBoundsException ex) {
			return true;
		}
	}
	
	void toConsole() { //Prints the world to the console with ASCII.
		for(int i = 0; i < size; i++) {
			for(int i2 = 0; i2 < size; i2++) {
				System.out.print("[" + grid[i][i2].getType() + "]");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void bugMove(Bug b, int x, int y, int dir) { //Finds the appropriate method to move a bug
		tempX = x;
		tempY = y;
		
		switch(b.getType())
		{
		case(1): {
			antMove(b, dir);
			break;
		}
		case(2): {
			doodleMove(b, dir);
			break;
		}
		default: {
			System.out.println("Invalid Bug type (" + b.getType() + ")");
			return;
		}
		}
		return;
	}
	
	void antMove(Bug b, int dir) { //Method for moving ants
		int newX;
		int newY;
		
		for(int i = 0; i < 4; i++) {
			if(dirPerms[dir][i] == 1) {
				newX = tempX - 1;
				newY = tempY;
				if(!isTaken(newX, newY)) {
					copy(b, newX, newY);
					grid[newX][newY].incBR();
					grid[newX][newY].setHM(true);
					if(grid[newX][newY].getBreed() >= 3) {
						breed(newX, newY, grid[newX][newY]);
					}
					break;
				}
			}
			else if(dirPerms[dir][i] == 2) {
				newX = tempX;
				newY = tempY - 1;
				if(!isTaken(newX, newY)) {
					copy(b, newX, newY);
					grid[newX][newY].incBR();
					grid[newX][newY].setHM(true);
					if(grid[newX][newY].getBreed() >= 3) {
						breed(newX, newY, grid[newX][newY]);
					}
					break;
				}
			}
			else if(dirPerms[dir][i] == 3) {
				newX = tempX + 1;
				newY = tempY;
				if(!isTaken(newX, newY)) {
					copy(b, newX, newY);
					grid[newX][newY].incBR();
					grid[newX][newY].setHM(true);
					if(grid[newX][newY].getBreed() >= 3) {
						breed(newX, newY, grid[newX][newY]);
					}
					break;
				}
			}
			else if(dirPerms[dir][i] == 4) {
				newX = tempX;
				newY = tempY + 1;
				if(!isTaken(newX, newY)) {
					copy(b, newX, newY);
					grid[newX][newY].incBR();
					grid[newX][newY].setHM(true);
					if(b.getBreed() >= 3) {
						breed(newX, newY, grid[newX][newY]);
					}
					break;
				}
			}
			else return;
		}
		return;
	}
	
	void doodleMove(Bug b, int dir) { //Method to move doodlebugs
		int newX;
		int newY;
		
		if(foodScan(b, tempX, tempY)) {
			return;
		}
		
		if(b.getBelly() >= 3) {
			squish(b);
		}
		
		for(int i = 0; i < 4; i++) {
			if(dirPerms[dir][i] == 1) {
				newX = tempX - 1;
				newY = tempY;
				if(!isTaken(newX, newY)) {
					copy(b, newX, newY);
					grid[newX][newY].incB();
					grid[newX][newY].setHM(true);
					break;
				}
			}
			else if(dirPerms[dir][i] == 2) {
				newX = tempX;
				newY = tempY - 1;
				if(!isTaken(newX, newY)) {
					copy(b, newX, newY);
					grid[newX][newY].incB();
					grid[newX][newY].setHM(true);
					break;
				}
			}
			else if(dirPerms[dir][i] == 3) {
				newX = tempX + 1;
				newY = tempY;
				if(!isTaken(newX, newY)) {
					copy(b, newX, newY);
					grid[newX][newY].incB();
					grid[newX][newY].setHM(true);
					break;
				}
			}
			else if(dirPerms[dir][i] == 4) {
				newX = tempX;
				newY = tempY + 1;
				if(!isTaken(newX, newY)) {
					copy(b, newX, newY);
					grid[newX][newY].incB();
					grid[newX][newY].setHM(true);
					break;
				}
			}
			else return;
		}
		return;
	}
	
	boolean foodScan(Bug b, int x, int y) { //How the doodlebug searches for food
		int r = rand.nextInt(23);
		for(int i = 0; i < 4; i++) {
			if(dirPerms[r][i] == 1 & x - 1 >= 0 && grid[x - 1][y].getType() == 1) {
				b.clearBE();
				copy(b, x - 1, y);
				if(grid[x - 1][y].getBreed() >= 8) {
					breed(x - 1, y, grid[x - 1][y]);
				}
				return true;
			}
			else if(dirPerms[r][i] == 2 & y - 1 >= 0 && grid[x][y - 1].getType() == 1) {
				b.clearBE();
				copy(b, x, y - 1);
				if(grid[x][y - 1].getBreed() >= 8) {
					breed(x, y - 1, grid[x][y - 1]);
				}
				return true;
			}
			else if(dirPerms[r][i] == 3 & x + 1 < size && grid[x + 1][y].getType() == 1) {
					b.clearBE();
					copy(b, x + 1, y);
					if(grid[x + 1][y].getBreed() >= 8) {
						breed(x + 1, y, grid[x + 1][y]);
					}
					return true;
				}
			else if(dirPerms[r][i] == 4 & y + 1 < size && grid[x][y + 1].getType() == 1) {
				b.clearBE();
				copy(b, x, y + 1);
				if(grid[x][y + 1].getBreed() >= 8) {
					breed(x, y + 1, grid[x][y + 1]);
				}
				return true;
			}
		}
		return false;
	}
	
	void breed(int x, int y, Bug b) { //How bugs multiply
		for(int i = 0; i < 4; i++) {
			if( i == 0 & b.getType() == 1 & !isTaken(x - 1,y)) {
				b.clearBR();
				addAnt(x - 1, y);
			}
			else if(i == 0 & b.getType() == 2 & !isTaken(x - 1,y)) {
				b.clearBR();
				addDoodle(x - 1, y);
			}
			else if(i == 1 & b.getType() == 1 & !isTaken(x,y - 1)) {
				b.clearBR();
				addAnt(x, y - 1);
			}
			else if(i == 1 & b.getType() == 2 & !isTaken(x,y - 1)) {
				b.clearBR();
				addDoodle(x, y - 1);
			}
			else if(i == 2 & b.getType() == 1 & !isTaken(x + 1,y)) {
				b.clearBR();
				addAnt(x + 1, y);
			}
			else if(i == 2 & b.getType() == 2 & !isTaken(x + 1,y)) {
				b.clearBR();
				addDoodle(x + 1, y);
			}
			else if(i == 3 & b.getType() == 1 & !isTaken(x,y + 1)) {
				b.clearBR();
				addAnt(x, y + 1);
			}
			else if(i == 3 & b.getType() == 2 & !isTaken(x,y + 1)) {
				b.clearBR();
				addDoodle(x, y + 1);
			}
		}
	}
	
	void copy(Bug b, int x, int y) { //Overwrites a cell with another cell's data, clearing the original.
		grid[x][y].setBE(b.getBelly());
		grid[x][y].setBR(b.getBreed());
		grid[x][y].setType(b.getType());
		labels[x][y].setText(labels[tempX][tempY].getText());
		squish(b);
	}
	
	void squish(Bug b) { //Clears a cell
		b.kill();
		labels[tempX][tempY].setText("[ ]");
	}
	
	void worldMove() { //Moves all bugs
		int r;
		
		for(int i = 0; i < size; i++) {
			for(int i2 = 0; i2 < size; i2++) {
				if(grid[i][i2].getHM()) {
					grid[i][i2].setHM(false);
				}
			}
		}
		for(int i = 0; i < size; i++) {
			for(int i2 = 0; i2 < size; i2++) {
				if((grid[i][i2].getType() == 2) & (!grid[i][i2].getHM())) {
					r = rand.nextInt(23);
					bugMove(grid[i][i2], i, i2, r);
				}
			}
		}
		for(int i = 0; i < size; i++) {
			for(int i2 = 0; i2 < size; i2++) {
				if((grid[i][i2].getType() == 1) & (!grid[i][i2].getHM())) {
					r = rand.nextInt(23) + 0;
					bugMove(grid[i][i2], i, i2, r);
				}
			}
		}
		
		//toConsole();
	}
	
	void addAnt(int x, int y) { //Adds an ant (shocking, I know).
		grid[x][y] = new Bug(1);
		labels[x][y].setText("[A]");
	}
	
	void addDoodle(int x, int y) { //Adds a doodlebug.
		grid[x][y] = new Bug(2);
		labels[x][y].setText("[D]");
	}

	public void actionPerformed(ActionEvent ae) { //Makes the button execute worldMove()
		if(ae.getActionCommand().equals("Next")) worldMove();
	}
	
}