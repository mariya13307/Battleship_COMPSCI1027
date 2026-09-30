/*
CS 1027B – Assignment 1
Name: Mariya Maksymenko
Student Number: 251521248
Email: mmaksym@uwo.ca
Created: January 26, 2026
*/
public class Game {
	private Board board; 
	private GameGUI gui;
	private boolean isTesting;
	private int activeShipCount; 
	private int numGuesses; 
	private String[] guesses; 

	//initializes all the variables based on the given values
	public Game (int boardHeight, int boardLength, boolean testing) {
		board = new Board(boardHeight, boardLength);
		isTesting = testing; 
		if (testing == false) {
			ShipRandomizer.placeShips(this);
		}
		numGuesses = 0; 
		guesses = new String[10];
	}
	//returns the Board
	public Board getBoard() {
		return board;
	}
	//returns the numbers of active ships
	public int getNumActiveShips() {
		return activeShipCount;
	}
	//returns the array of the guesses made
	public String[] getGuesses() {
		return guesses; 
	}
	//intializes GUI
	public void intializeGUI() {
		gui = new GameGUI(board.getGrid(), isTesting);
	}
	//adds ship to the board
	public boolean addShip(Ship ship, char row, int col) {
		try {
			//tries to add the Ship given at the position given using the addShip method from Board
			board.addShip(ship, row, col);
			//increments active ship count
			activeShipCount ++;
			return true;
		}
		//if the ship can't be added, its caught and false is returned
		catch(Exception e) {
			return false;
		}
	}
	//shoots down the target at given row and column
	public int shootTarget(char row, int col) {
		//converts the position into a string 
	    String target = row + String.valueOf(col);
	    //checks if this position has been guessed already, if so returns -2
	    for (int i = 0; i < guesses.length; i++) {
	        if (target.equals(guesses[i])) {
	            return -2;
	        }
	    }
	    //uses the getCell method from the Board class to get the value at the given row and column
	    int cell = board.getCell(row, col);
	    //if the cell is out of the bounds of the Board, returns -1
	    if (cell == -5) return -1;
	    //the boolean keeps track if the guess has been added to the array
	    boolean added = false;
	    //adds the guess to the end of the guesses array, the breaks out of the for loop after
	    for (int i = 0; i < guesses.length; i++) {
	        if (guesses[i] == null) {
	            guesses[i] = target;
	            added = true;
	            break;
	        }
	    }
	    //if it wasn't able to be added(because the guesses array is full), it extends the array by 10 and adds the value
	    if (!added) {
	    	//creates a new array that is 10 spaces longer
	        String[] newGuesses = new String[guesses.length + 10];
	        //copies the value from the previous array into the new one
	        for (int i = 0; i < guesses.length; i++) {
	            newGuesses[i] = guesses[i];
	        }
	        //adds the guess to the end of the of the new array
	        newGuesses[guesses.length] = target;
	        //replaces the old array with the new one
	        guesses = newGuesses;
	    }
	    //variable to keep track of the value to be returned
	    int res;
	    //if the cell is -1, there is no ship so its a miss and 0 is returned
	    if (cell == -1) {
	        res = 0;
	    } 
	    //anything else means a ship has been hit
	    else {
	    	//variable that calls checkDestroyedShip which eliminates one cell from the ship
	        boolean destroyed = board.checkDestroyedShip(cell);
	        //if destroyed is false, meaning there are more cells left in the ship so returns 1
	        if (!destroyed) {
	            res = 1;
	            //anything else means that the ship has been fully destroyed
	            // next it must be checked if all the ships have been eliminated
	        } else if (activeShipCount > 1) {
	        	//if not the active ship count is decremented and 2 is returned
	            activeShipCount--;
	            res = 2;
	        } else {
	        	//if yesthe active ship count is decremented and 3 is returned
	            activeShipCount--;
	            res = 3; 
	        }
	    }
	    //upates gui
	    if (gui != null) {
	        gui.updateCell(row, col);
	    }
	    //returns the res value
	    return res;
	}


}

