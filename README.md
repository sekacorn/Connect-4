# Connect4 Game


This is a Java implementation of the Connect4 game. It allows two players to take turns dropping their colored disks into a grid and tries to connect four disks of the same color either horizontally, vertically, or diagonally.

## How to Play

1) Run the `Connect4` class.
2)The game starts with an empty grid displayed on the console.
3)Each player takes turns dropping their disk into a column by entering a column number from 1 to 7 when prompted.
4) The players alternate turns, with the red player ('R') going first and the yellow player ('Y') going second.
5) The game continues until one of the players connects four disks of their color in a row or if the grid is completely filled without any winning sequence.
6) The game ends with the result displayed on the console, indicating the winner or a tie.

## How to Compile and Run

To compile and run the Connect4 game, follow these steps:

1} Make sure you have Java installed on your system.
2) Open a command prompt or terminal.
3) Navigate to the directory where the `Connect4.java` file is located.
4) Compile the code by running the following command:

```
javac Connect4.java
```

5. After successful compilation, run the game using the following command:

```
java Connect4
```

6. Follow the on-screen instructions to play the game.

=============================================
## Gameplay Rules

- The grid has 7 columns and 7 rows.
- Players take turns dropping their disks into a column of their choice.
- Disks stack on top of each other, occupying the lowest available position in the chosen column.
- The game continues until a player connects four of their disks in a row (horizontally, vertically, or diagonally) or if the grid is completely filled without any winning sequence.
- The game will display the winner (either the red player 'R' or the yellow player 'Y') or indicate a tie if there is no winner.
