
public class TestGame {

	public static void main(String[] args) {
		
		Game game = new Game(10, 10, true);
		
		// Check that ships are being added to the board properly.
		
		Ship ship1 = new Ship(93, 5, 1);
		boolean r1 = game.addShip(ship1, 'D', 7); // Expected false.

		Ship ship2 = new Ship(56, 1, 4);
		boolean r2 = game.addShip(ship2, 'C', 2); // Expected true.

		Ship ship3 = new Ship(12, 3, 1);
		boolean r3 = game.addShip(ship3, 'H', 3); // Expected true.

		Ship ship4 = new Ship(49, 5, 1);
		boolean r4 = game.addShip(ship4, 'Z', 3); // Expected false.

		Ship ship5 = new Ship(31, 1, 5);
		boolean r5 = game.addShip(ship5, 'E', 10); // Expected true.
		
		Ship ship6 = new Ship(86, 4, 1);
		boolean r6 = game.addShip(ship6, 'E', 7); // Expected false.
		
		Ship ship7 = new Ship(29, 1, 2);
		boolean r7 = game.addShip(ship7, 'C', 7); // Expected true.
		
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		System.out.println(r4);
		System.out.println(r5);
		System.out.println(r6);
		System.out.println(r7);
		
		game.initializeGUI();

		
		// Now try shooting targets.

		int r8 = game.shootTarget('C', 7); // Expected 1.
		int r9 = game.shootTarget('F', 12); // Expected -1.
		int r10 = game.shootTarget('F', 2); // Expected 1.

		game.shootTarget('E', 2);
		game.shootTarget('D', 2);
		int r11 = game.shootTarget('C', 2); // Expected 2.
		int r12 = game.shootTarget('E', 5); // Expected 0.
		int r13 = game.shootTarget('F', 2); // Expected -2.
		int r14 = game.shootTarget('G', 0); // Expected -1.


		System.out.println(r8);
		System.out.println(r9);
		System.out.println(r10);
		System.out.println(r11);
		System.out.println(r12);
		System.out.println(r13);
		System.out.println(r14);
	}

}

