/*
CS 1027B – Assignment 1
Name: Mariya Maksymenko
Student Number: 251521248
Email: mmaksym@uwo.ca
Created: January 26, 2026
*/
public class Ship {
	private int ID;
	private int length;
	private int height;
	private int remainingCells;
	
	//initializes all the variables based on the given values
	public Ship (int shipID, int shipLength, int shipHeight) {
		ID = shipID;
		length = shipLength;
		height = shipHeight;
		remainingCells = shipLength * shipHeight; 
	}
	// returns the ship ID
	public int getID() {
		return ID;
	}
	//returns the length of the ship
	public int getLength() {
		return length;
	}
	//returns the height of the ship
	public int getHeight() {
		return height;
	}
	//returns the number of the remaining cells left in the ship
	public int getRemainingCells() {
		return remainingCells;
	}
	//removes a cell from remaining cells of the ship when called
	public void takeHit() {
		remainingCells = remainingCells-1;
	}
	//expresses the Ship object as a string using the ship ID, length and height  
	public String toString() {
		return "Ship "+ID+" is "+length+"x"+height;
	}

}
