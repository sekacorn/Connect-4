import java.util.Scanner;

	public class Connect4 {
	// Cornelius Sekamatte
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		char[][] Grid = new char[7][7];
		int count = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		for (int a = 0; a < 7; ++a)
			for (int b = 0; b < 7; ++b)
				Grid[a][b] = ' ';

		ShowGrid(Grid);
		boolean goAhead = true;
		while (true) {
			if (goAhead)
				System.out.print("Drop a red disk at  column (1-7):");
			else
				System.out.print("Drop a yellow disk at  column (1-7):");

			int column = input.nextInt();

			if (column < 1 || column > 7) {
				System.out.println("Column should be from 1 to 7");
				continue;
			}

			if (!AddChip(Grid, column - 1, goAhead ? 'R' : 'Y')) {
				System.out.println("Choose another one.");
				continue;

			}
			if (CheckGrid(Grid, count, count2, count3, count4)==true){
			    System.exit(0);
			
			}
			ShowGrid(Grid);
			goAhead = !goAhead;
		}
	}

	public static boolean CheckGrid(char[][] Grid, int count, int count2,
			int count3, int count4) {
		// CHECKS FOR WINNERS IN COLUMN
		
		for (int column = 0; column < 7; ++column) {
			
			for (int row5 = 0; row5 < 4; ++row5) {
				if (Grid[row5][column] != ' '
						&& Grid[row5][column] == Grid[row5+1][column]
						&& Grid[row5+1][column] == Grid[row5+2][column]
						&& Grid[row5+2][column] == Grid[row5+3][column]){
					
				
					if (Grid[row5][column] == 'Y'){
						System.out.println("The Yellow player won!");
					        return true;
					}
					if (Grid[row5][column] == 'R'){
						System.out.println("The Red player won!");
					    return true;
					}
						}
				

			}

		}

		// CHECKS FOR WINNERS IN DIAGONAL

		
		
		// checking ascending diagonal for red player
    	for (int a5=3; a5<7; a5++){
            for (int b=0; b<7-3; b++){
                if (Grid[a5][b] == 'R' && Grid[a5-1][b+1] == 'R' && Grid[a5-2][b+2] == 'R' && Grid[a5-3][b+3] == 'R'){
                    System.out.println("The Red player won!");
                    return true;}
            }
        }
    // checking descending diagonal for red player
        for (int a5=3; a5<7; a5++){
            for (int b=3; b<7; b++){
                if (Grid[a5][b] == 'R' && Grid[a5-1][b-1] == 'R' && Grid[a5-2][b-2] == 'R' && Grid[a5-3][b-3] == 'R'){ 
                    System.out.println("The Red player won!");
                        return true;}
            }
        }
        
        // checking ascending diagonal for yellow player
    	for (int a5=3; a5<7; a5++){
            for (int b=0; b<7-3; b++){
                if (Grid[a5][b] == 'Y' && Grid[a5-1][b+1] == 'Y' && Grid[a5-2][b+2] == 'Y' && Grid[a5-3][b+3] == 'Y'){
                    System.out.println("The yellow player won!");
                    return true;}
            }
        }
    // checking descending diagonal for Yellow player
        for (int a5=3; a5<7; a5++){
            for (int b=3; b<7; b++){
                if (Grid[a5][b] == 'Y' && Grid[a5-1][b-1] == 'Y' && Grid[a5-2][b-2] == 'Y' && Grid[a5-3][b-3] == 'Y'){ 
                    System.out.println("The Yellow player won!");
                        return true;}
            }
        }
    		
		// CHECK FOR WINNERS IN ROW
		

		for (int a5 = 0; a5 < 7; ++a5) {

			
			for (int i = 1; i < 7; ++i) {
				if (Grid[a5][i] != ' ' && Grid[a5][i] == Grid[a5][i - 1])
					++count;
				else
					count = 1;

				if (count >= 4) {
					if (Grid[a5][i] == 'Y'){
						System.out.println("The Yellow player won!");
						return true;
					}
					if (Grid[a5][i] == 'R'){
						System.out.println("The Red player won!");
						return true;

				    }   
				    
				}

			}

		}

		return false;
	}

	public static char CheckAllGrid(char[][] Grid) {

		for (int a = 0; a < Grid.length; ++a)
			for (int b = 0; b < Grid[a].length; ++b)
				if (Grid[a][b] == ' ')
					return ' ';

		return 'D';
	}

	// A function that accepts and updates the game grid
	public static boolean AddChip(char[][] Grid, int column, char disk) {

		if (Grid[0][column] != ' ')
			return false;

		// Check all chips in the column.
		for (int a = 0; a < 7; ++a)
			if (Grid[a][column] != ' ') {
				// Put the disk on top of the current one.
				Grid[a - 1][column] = disk;
				return true;
			}

		// If no other disks found, we place this diak at the bottom.
		Grid[6][column] = disk;
		return true;
	}

	// A function that accepts and prints the Grid
	public static void ShowGrid(char[][] Grid) {
		for (int a = 0; a < 7; ++a) {
			System.out.print("| ");
			for (int col = 0; col < 7; ++col)
				System.out.print(Grid[a][col] + "| ");
			System.out.println();
		}

	}

}
