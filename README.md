# Connect 4 GUI Game

## Overview
This is a Java-based graphical implementation of the classic Connect 4 game. Two players (Red and Yellow) take turns dropping colored disks into a 6x7 grid, aiming to connect four disks in a row vertically, horizontally, or diagonally.

## Features
- Graphical user interface using Java Swing
- 6x7 game board with blue background and white slots
- Red and Yellow player disks
- Column drop buttons that change color based on current turn
- Score tracking for both players
- Quit button to exit the game
- Automatic game reset after a win
- Error message for full columns

## Requirements
- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) to run the compiled program

## How to Run
1. **Compile the code:**
   ```bash
   javac Connect4GUI.java
## Run the application:
  ```bash
  java Connect4GUI

## Gameplay
  The game starts with Red player's turn
  Click a "Drop" button (1-7) to place a disk in that column
  Buttons show Red or Yellow color indicating whose turn it is
  Players alternate turns
  First player to connect 4 disks wins
  Score updates after each win
  Game resets automatically after a win
  Click "Quit" to exit

## Implements:
  Custom graphics drawing for the game board
  Event handling for button clicks
  Win condition checking in all directions
  Score management
  Game state management
## Dependencies
    Standard Java libraries only (Swing, AWT)
    No external dependencies required
## Known Issues
    No tie game detection (game continues until a win)
    

