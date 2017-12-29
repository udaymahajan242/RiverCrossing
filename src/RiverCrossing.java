import java.io.IOException;

public class RiverCrossing {
	public static void main(String[] args) throws IOException {
		// The following variables encode position
		// (the real state of the world)
		// false means on right bank
		// initialized with initial locations
		boolean wolfOnLeftBank = true; 
		boolean goatOnLeftBank = true;
		boolean cabbageOnLeftBank = true;
		boolean farmerOnLeftBank = true;
		while (wolfOnLeftBank 
				|| goatOnLeftBank 
				|| cabbageOnLeftBank 
				|| farmerOnLeftBank) {
			// Variables that represent an attempt at a move
			// (attempted state of the world)
			// Initialize to existing state of the world
			boolean tryWolfOnLeftBank = wolfOnLeftBank; 
			boolean tryGoatOnLeftBank = goatOnLeftBank;
			boolean tryCabbageOnLeftBank = cabbageOnLeftBank;
			boolean tryFarmerOnLeftBank = farmerOnLeftBank;
			
			System.out.println("wolfOnLeftBank: "+wolfOnLeftBank);
			System.out.println("goatOnLeftBank: "+goatOnLeftBank);
			System.out.println("cabbageOnLeftBank: "+cabbageOnLeftBank);
			System.out.println("farmerOnLeftBank: "+farmerOnLeftBank);
			System.out.println("Make a move (0, 1, 2, 3)");
			// 1 means moving the wolf
			// 2 means moving the goat
			// 3 means moving the cabbage
			// 0 means moving nothing (just the farmer)
			int thingToMove = System.in.read();
			while (System.in.read() != '\n'); // throw away characters until new line
			System.out.println("You typed "+thingToMove);
			
			if (thingToMove == '0') {
				// Attempt to move the farmer alone
				tryFarmerOnLeftBank = !tryFarmerOnLeftBank;
			} else if (thingToMove == '1') {
				// Attempt to move the wolf and farmer
				// First, check to see if the wolf and farmer
				// are on the same side of the river
				if (farmerOnLeftBank != wolfOnLeftBank) {
					System.out.println("Wolf must be on same side of river as farmer!");
					continue;
				}
				tryFarmerOnLeftBank = !tryFarmerOnLeftBank;
				tryWolfOnLeftBank = !tryWolfOnLeftBank;
			} else if (thingToMove == '2') {
				// Attempt to move the goat and farmer
				// First, check to see if they are on the same side
				if (farmerOnLeftBank != goatOnLeftBank) {
					System.out.println("Goat must be on same side of river as farmer!");
					continue;
				}
				tryFarmerOnLeftBank = !tryFarmerOnLeftBank;
				tryGoatOnLeftBank = !tryGoatOnLeftBank;				
			} else if (thingToMove == '3') {
				// Attempt to move the cabbage and farmer
				// First, check to see if they are on the same side
				if (farmerOnLeftBank != cabbageOnLeftBank) {
					System.out.println("Cabbage must be on same side of river as farmer!");
					continue;
				}
				tryFarmerOnLeftBank = !tryFarmerOnLeftBank;
				tryCabbageOnLeftBank = !tryCabbageOnLeftBank;				
			} else {
				System.out.println("Invalid value!");
			}
			// At this point, we need to validate that the new
			// attempt works out and nothing gets eaten.
			if (tryCabbageOnLeftBank == tryGoatOnLeftBank &&
					tryFarmerOnLeftBank != tryGoatOnLeftBank) {
				// Disallow leaving goat alone with cabbage.
				// If goat is on the same side of the river as the
				// cabbage AND the farmer is not on the same side of the
				// river as the cabbage...
				System.out.println("This move would allow the goat to eat the cabbage!");
			} else if (tryGoatOnLeftBank == tryWolfOnLeftBank &&
					tryFarmerOnLeftBank != tryWolfOnLeftBank) {
				// Disallow leaving wolf alone with goat.
				System.out.println("This move would allow the wolf to eat the goat!");				
			} else {	
				// Move was successful. Copy the "try" world back into
				// the real world.
				wolfOnLeftBank = tryWolfOnLeftBank; 
				goatOnLeftBank = tryGoatOnLeftBank;
				cabbageOnLeftBank = tryCabbageOnLeftBank;
				farmerOnLeftBank = tryFarmerOnLeftBank;			
			}
		}
		System.out.println("You won!");
	}
}
