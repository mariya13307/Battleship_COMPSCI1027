/*
CS 1027B – Assignment 1
Name: Mariya Maksymenko
Student Number: 251521248
Email: mmaksym@uwo.ca
Created: January 26, 2026
*/
public class Board {
	private int length;
	private int height;
	private int shipNum;
	private Ship[] ships; 
	private int[][] grid;
	public static final char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	//initializes all the variables based on the given values	
	public Board(int theHeight, int theLength) {
		 height = theHeight; 
		 length = theLength;
		 shipNum = 0;
		 ships = new Ship[5]; 
		 grid = new int [height][length];
		 for (int i=0; i<height;i++) {
			 for(int j=0; j<length; j++) {
				 grid[i][j] = -1; 
			 }
		 }
	}
	//returns the length of the Board
	public int getLength() {
		return length; 
	}
	//returns the height of the Board
	public int getHeight() {
		return height; 
	}
	//returns the Board grid
	public int[][] getGrid() {
		return grid; 
	}
	//gets the value of the cell at the row and column given
	public int getCell(char row, int col) {
		//converts row letter to a number
		int r = row - 'A';
		//converts column number to an index to used in the grid array later
		int c = col - 1;
		//checks if the row number and column number are in the grid, if not returns -5
		if (r < 0 || r >= height || c < 0 || c >= length) {
		    return -5;
		}
		//if the row and column given are in the grid, it returns the value 
		return grid[r][c];
	}
	
	//adds a Ship to the Board at the given row and column
	public boolean addShip(Ship ship, char row, int col) {
		//converts row letter to a number
		int r = row - 'A';
		//converts column number to an index to used in the grid array later
		int c = col - 1;
		//checks if the row number and column number not in the grid, if not returns false 
	    if (r < 0||r >= height||c < 0||c >= length) {return false;}
	    //checks if there are 5 or more ships, the maximum allowed, if yes returns false
	    if (shipNum == 5) {return false;}
	    //checks if the ship is horizontal
	    if (ship.getHeight() == 1) {
	    	//if it is checks that the length of the ship would fit within the constraints of the board, if not returns false
	        if (c + ship.getLength() > length) {return false;}
	        //checks that there are no other ship at the given row and column already, if there is returns false 
	        for (int i = 0; i < ship.getLength(); i++) {
	            if (grid[r][c + i] != -1) {return false;}
	        }
	        //the ship has been confirmed to fit on the board so its added
	        for (int i = 0; i < ship.getLength(); i++) {
	            grid[r][c + i] = ship.getID();
	        }
	        //adds the ship to Ships array
	        ships[shipNum] = ship;
	        //incriments the ship count and returns true
	        shipNum++;
	        return true;
	    }
	    //checks if the ship is vertical 
	    if (ship.getLength() == 1) {
	    	//if it is checks that the height of the ship would fit within the constraints of the board, if not returns false
	        if (r + ship.getHeight() > height) return false;
	        for (int i = 0; i < ship.getHeight(); i++) {
	        	//checks that there are no other ship at the given row and column already, if there is returns false 
	            if (grid[r + i][c] != -1) return false;
	        }
	      //the ship has been confirmed to fit on the board so its added
	        for (int i = 0; i < ship.getHeight(); i++) {
	            grid[r + i][c] = ship.getID();
	        }
	      //adds the ship to Ships array
	        ships[shipNum] = ship;
	        //incriments the ship count and returns true
	        shipNum++;
	        return true;
	    }
	    //if its neither returns false, since the dimensions in the Ship object are incorrect
	    return false;
	}
	
	//checks whether or not a Ship with given ID has been fully destroyed or has remaining cells, if it has remaining cells it takes a hit 
	public boolean checkDestroyedShip(int shipID) {
	    for (int i = 0; i < ships.length; i++) {
	    	//goes through the ships array to find the Ship object that matches the given ID
	        if (ships[i] != null && ships[i].getID() == shipID) {
	        	//calls takeHit which removes a cell
	            ships[i].takeHit(); 
	            //then checks if there are there are remaining cells in the ship, if there aren't any returns true 
	            if (ships[i].getRemainingCells() == 0) {return true;} 
	            //if not returns false
	            else {return false;}
	        }
	    }
	    //returns false if the ship ID isn't in the array
	    return false;
	}
	
	//expresses the Board object as a string
	public String toString() {
		//creates an empty string that will be added to
	    String s = " ";
	    //prints the column numbers 
	    for (int i = 1; i <= length; i++) {
	        s += i + " ";
	    }
	    s += "\n";
	    //goes through row by row and prints the row numbers
	    for (int r = 0; r < height; r++) {
	        s += letters[r] + " ";
	        for (int c = 0; c < length; c++) {
	        	//prints each cell in the row
	            s += grid[r][c] + " ";
	        }
	        s += "\n";
	    }
	    //returns the string
	    return s;
	}	  
}