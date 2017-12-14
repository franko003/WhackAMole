import java.util.*;

public class WhackAMole {
    // Instance Variables
    int score = 0;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;
   
    /**
     * Constructor that takes a number of attempts and dimensions for grid
     * 
     * @param numAttempts - the max number of attempts user gets to whack a mole
     * @param gridDimension - length of the side of a square grid
     */
    WhackAMole(int numAttempts, int gridDimension) {
	attemptsLeft = numAttempts;
	moleGrid = new char[gridDimension][gridDimension];
	for (int i = 0; i < gridDimension; i++) {
	    for (int j = 0; j < gridDimension; j++) {
		moleGrid[i][j] = '*';
	    }
	}
    }
    /**
     * Method to place a mole in the grid given x, y coordinates
     * 
     * @param x - x coordinate
     * @param y - y coordinate
     * @return - true if the mole was placed, false if the space was occupied and therefore could not place the mole
     */
    
    boolean place(int x, int y) {
	if (moleGrid[x][y] == '*') {
	    moleGrid[x][y] = 'M';
	    molesLeft++;
	    return true;
	} else {
	    return false;
	}
    }
    /**
     * Method to whack a mole given coordinates x, y
     * Performs the necessary updates to all variables based on a successful whack or a miss
     * 
     * @param x - x coordinate of intended whack
     * @param y - y coordinate of intended whack
     */
    void whack(int x, int y) {
	if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
	    if (moleGrid[x][y] == 'M') {
		    score += 1;
		    attemptsLeft -= 1;
		    molesLeft -= 1;
		    moleGrid[x][y] = 'W';
		    System.out.println("YOU WHACKED A MOLE");
		} else {
		    attemptsLeft -= 1;
		    System.out.println("SWING AND A MISS");
		}
	} else {
	    attemptsLeft -= 1;
	    System.out.println("SWUNG OUTSIDE OF THE GRID AND DEFINITELY MISSED");
	}
	
    }
    /**
     * Method to print the grid to the user
     * Displays "W" for a whacked mole and "*" for anything else
     */
    void printGridToUser() {
	char[][] userGrid = moleGrid;
	for (int i = 0; i < userGrid.length; i++) {
	    for (int j = 0; j < userGrid[i].length; j++) {
		if (userGrid[i][j] == 'M') {
		    userGrid[i][j] = '*';
		}
	    }
	}
	System.out.println(Arrays.deepToString(userGrid));
    }
    /**
     * Method to print the grid out exactly as is
     * Displays "W" for a whacked mole, "M" for an unwhacked mole, "*" for all else
     */
    void printGrid() {
	for (int i = 0; i < moleGrid.length; i++) {
	    for (int j = 0; j < moleGrid.length; j++) {
		System.out.print(moleGrid[i][j] + " ");
	    }
	    System.out.println();
	}
    }
    
    // Main Method
    public static void main(String[] args) {
	// Create a new game with 50 attempts on a 10x10 space
	WhackAMole myWhack = new WhackAMole(50, 10);
	// Randomly place 10 moles on the space
	int molesToPlace = 10;
	while (molesToPlace > 0) {
	    int randX = (int) (Math.random() * 9);
	    int randY = (int) (Math.random() * 9);
	    if (myWhack.place(randX, randY)) molesToPlace--;
	}
	
	// Create a scanner, give rules and get user info
	Scanner scanner = new Scanner(System.in);
	System.out.println("You have 50 whacks to get all 10 moles, enter -1,-1 to give up");
	System.out.println("Game ends when you whack all the moles or run out of attempts");
	
	// While loop to play the game while the user still has attempts left
	while (myWhack.attemptsLeft > 0) {
	    System.out.println("Whacks remaining: " + myWhack.attemptsLeft);
	    System.out.println("Moles remaining: " + myWhack.molesLeft);
	    System.out.println("Enter coordinates in x,y format, with x and y between 0-9:");
		
	    // Read in string of coordinates and split
	    String userCoords = scanner.next();
	    String[] parts = new String[2];
	    String xCoord = "";
	    String yCoord = "";
	    
	    try {
		parts = userCoords.split(",");
		xCoord = parts[0];
		yCoord = parts[1];

		// Convert to strings to integers
		int x = Integer.parseInt(xCoord);
		int y = Integer.parseInt(yCoord);

		if (x == -1 && y == -1) {
		    System.out.println("YOU QUIT");
		    myWhack.printGrid();
		    break;
		} else {
		    myWhack.whack(x, y);
		    if (myWhack.molesLeft == 0) {
			System.out.println("YOU WIN");
			break;
		    }

		}
	    } catch (Exception e) {
		System.out.println("Incorrect input syntax.  Please use x,y format with x and y between 0-9");
	    }

	}
	
	if (myWhack.attemptsLeft == 0) System.out.println("YOU LOSE");
	System.out.println("GAME OVER");

	scanner.close();

    }
    
}




