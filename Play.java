import java.util.Scanner;

public class Play {
	
	private Game game;
	
	/**
	 * Constructor for the Play class to play a game of Battleship.
	 */
	public Play () {
		game = new Game(10, 10, false);
		game.initializeGUI();
		beginGame();
	}
	
	/**
	 * Runs the user input system for playing a game.
	 */
	public void beginGame () {

		String target;   
		
		try {

			Scanner in = new Scanner(System.in);

			do {

				System.out.println("Enter a cell (i.e. A1) to try to hit a ship, or type \"quit\" to end the game: ");
				target = in.nextLine();

				if (!target.equals("quit")) {
					if (isValidTarget(target)) {
						// Get row/col from target string.
						Character targetRow = Character.toUpperCase(target.charAt(0));
						int targetCol = Integer.parseInt(target.substring(1));

						int result = game.shootTarget(targetRow, targetCol);
						switch (result) {
							case -2:
								System.out.println("Already guessed");
								break;
							case -1:
								System.out.println("Out of bounds");
								break;
							case 0:
								System.out.println("Miss");
								break;
							case 1:
								System.out.println("Hit!");
								break;
							case 2:
								System.out.println("You sunk a ship!");
								break;
							case 3:
								System.out.println("You win!");
								break;
						}
					} else {
						System.out.println("Invalid target cell");
					}
				}

				System.out.println();
			} while (!target.equalsIgnoreCase("quit"));
			
			in.close();
            System.exit(0);
			
		} catch (Exception IOException) {
			System.out.println("Input exception reported");
			IOException.printStackTrace();
		}
	}

	private boolean isValidTarget (String target) {
		if (target.length() < 2) return false;
		
		try {
		
			target = target.toUpperCase();
			
			char c1 = target.charAt(0);
			String c2 = target.substring(1);
			int i2 = Integer.parseInt(c2);
	
			
			if ((c1 >= 'A' && c1 <= 'J') && (i2 >= 1 && i2 <= 10)) {
				return true;
			} else {
				return false;
			}
		
		} catch (NumberFormatException e) {
			return false;
		}

	}
	
	/**
	 * The entry point for the game mode.
	 * @param args
	 */
	public static void main(String[] args) {
		new Play();
	}
	

}

