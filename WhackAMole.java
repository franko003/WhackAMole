import java.util.*;

public class WhackAMole {
    // instance variables
    int score = 0;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;
   
    // constructor WhackAMole(int numAttempts, int gridDimension)
    WhackAMole(int numAttempts, int gridDimension) {
	attemptsLeft = numAttempts;
	moleGrid = new char[gridDimension][gridDimension];
	for (int i = 0; i < gridDimension; i++) {
	    for (int j = 0; j < gridDimension; j++) {
		moleGrid[i][j] = '*';
	    }
	}
    }
    // methods
    boolean place(int x, int y) {
	if (moleGrid[x][y] == '*') {
	    moleGrid[x][y] = 'M';
	    molesLeft++;
	    return true;
	} else {
	    return false;
	}
    }
    
    void whack(int x, int y) {
	if (moleGrid[x][y] == 'M') {
	    score += 1;
	    attemptsLeft -= 1;
	    molesLeft -= 1;
	    moleGrid[x][y] = 'W';
	} else {
	    attemptsLeft -= 1;
	}
    }
    
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
    
    void printGrid() {
	System.out.println(Arrays.deepToString(moleGrid));
    }
    // main method
    public static void main(String[] args) {
	WhackAMole myWhack = new WhackAMole(50, 10);
	// Hard code for placing the 10 moles
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
	
	// while loop to play the game
	while (myWhack.attemptsLeft > 0) {
	    System.out.println("Whacks remaining: " + myWhack.attemptsLeft);
	    System.out.println("Enter coordinates in x,y syntax:");
		
	    // Read in string of coordinates and split
	    String userCoords = scanner.next();
	    String[] parts = userCoords.split(",");
	    String xCoord = parts[0];
	    String yCoord = parts[1];
		
	    // Convert to strings to integers
	    int x = Integer.parseInt(xCoord);
	    int y = Integer.parseInt(yCoord);
	    
	    if (x == -1 && y == -1) {
		System.out.println("You Quit");
		myWhack.printGrid();
		break;
	    } else {
		myWhack.whack(x, y);
		if (myWhack.molesLeft == 0) {
		    System.out.println("You Win");
		    break;
		}
		
	    }
	    
	}
	
	scanner.close();
	
    }
    
}




