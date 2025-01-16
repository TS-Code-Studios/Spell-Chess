package com.javadevs;
//Author: @FRBFStudios
//Version: 16/1/2024
//See INFORMATION.md for more info

public class ChessGameDHC {
  //This 2D array contains the chess position to be displayed on the board
  String[][] position;
  //This 1D array contains info such as En Passant target squares, the last move, castling availability, etc.
  String[] positionMeta;

  //These integers contain the difference in files and ranks for a move
  int fileDiff;
  int rankDiff;

  //Constructor
  ChessGameDHC() {
    newDefaultPosition();
  }

  public static void main(String[] args) {
      @SuppressWarnings("unused")
      ChessGameDHC test = new ChessGameDHC();
  }

  //Every array is assigned the values of the default chess position
  private void newDefaultPosition() {
    //position array is filled with the default chess position (Uppercase = black pieces, lowercase = white pieces)
    position = new String[][] {
      {"R", "N", "B", "Q", "K", "B", "N", "R"},
      {"P", "P", "P", "P", "P", "P", "P", "P"},
      {"-", "-", "-", "-", "-", "-", "-", "-"},
      {"-", "-", "-", "-", "-", "-", "-", "-"},
      {"-", "-", "-", "-", "-", "-", "-", "-"},
      {"-", "-", "-", "-", "-", "-", "-", "-"},
      {"p", "p", "p", "p", "p", "p", "p", "p"},
      {"r", "n", "b", "q", "k", "b", "n", "r"}
    };
    
    //positionMeta array is filled with the default chess position's meta
    //Order: white O-O, white O-O-O, black O-O, black O-O-O, en passant square, halfmoves, check
    positionMeta = new String[] {"-", "-", "-", "-", "-", "-", "-"};
  }

  public String[][] getPosition() {
    return position;
  }

  public String[] getPositionMeta() {
    return positionMeta;
  }

  //Checks whether or not two pieces are of the same color
  public boolean isPieceSameColor(String piece1, String piece2) {
    boolean isOfSameColor = false;

    if (Character.isUpperCase(piece1.charAt(0)) == Character.isUpperCase(piece2.charAt(0))) {
      isOfSameColor = true;
    }

    return isOfSameColor;
  }
  
  //This method can check if a move is possible. So far, pins, promotions and en passant are not included. For playerToMove, use "w" and "B" respectively.
  public boolean isMovePossible(String piece, String startSquare, String targetSquare, String playerToMove) {
    boolean isMovePossible = false;
    
    //The startSquare string is seperated
    int startSquareFile = startSquare.charAt(0) - 'a' + 1;
    int startSquareRank = startSquare.charAt(1);

    //The targetSquare string is seperated
    int targetSquareFile = targetSquare.charAt(0) - 'a' + 1;
    int targetSquareRank = targetSquare.charAt(1);
    
    //fileDiff and rankDiff is calculated (Math.abs = "absolute value" (Betrag))
    fileDiff = Math.abs(startSquareFile - targetSquareFile);
    rankDiff = Math.abs(startSquareRank - targetSquareRank);

    //Checks if it's the right player to move, since capitalization = color
    if (isPieceSameColor(piece, playerToMove)) {
      //Checks if the target square exists to avoid crashes
      if (targetSquareRank > 0
          && targetSquareRank < 9
          && targetSquareFile > 0
          && targetSquareFile < 9) {
        // Is the piece a pawn?
        if ("p".equals(piece.toLowerCase())) {
          isMovePossible = checkPawnMove(piece, startSquareFile, startSquareRank, targetSquareFile, targetSquareRank);
        }
        
        // Is the piece a knight?
        else if ("n".equals(piece.toLowerCase())) {
          if ((fileDiff == 2 && rankDiff == 1)
          || (fileDiff == 1 && rankDiff == 2)) {
            //Is the target square empty or contains an opposing piece?
            if ("-".equals(position[targetSquareFile][targetSquareRank])
                || !isPieceSameColor(piece, position[targetSquareFile][targetSquareRank])) {
              isMovePossible = true;
            }
          }
        }
      }
    }
    return isMovePossible;
  }
  
  //Since pawn logic is extra complicated, it is seperated into a different method
  private boolean checkPawnMove(String piece, int startSquareFile, int startSquareRank, int targetSquareFile, int targetSquareRank) {
    boolean isPawnMovePossible = false;
    
    //Determine move direction and starting rank for pawns
    int moveDirection = "P".equals(piece) ? -1 : 1;
    int startingRank = "P".equals(piece) ? 7 : 2;
    
    //Is the pawn staying on its file? Is the target square empty?
    if (targetSquareFile == startSquareFile
        && "-".equals(position[targetSquareFile][targetSquareRank])) {
      //Is it moving 1 square forward?
      if (startSquareRank + moveDirection == targetSquareRank) {
        isPawnMovePossible = true;
      }
      
      //Is it moving 2 squares forward from the starting rank?
      else if (startSquareRank == startingRank
          && startSquareRank + 2 * moveDirection == targetSquareRank) {
        isPawnMovePossible = true;
      }
    }

    //Is the pawn capturing a piece? (^ = XOR)
    else if ((startSquareFile - 1 == targetSquareFile ^ startSquareFile + 1 == targetSquareFile)
        && startSquareRank + moveDirection == targetSquareRank
        && !isPieceSameColor(piece, position[targetSquareFile][targetSquareRank])) {
      isPawnMovePossible = true;
    }
    return isPawnMovePossible;
  }
}