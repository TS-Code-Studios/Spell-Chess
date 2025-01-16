package com.javadevs;
//Author: @FRBFStudios
//Version: 15/1/2024
//See INFORMATION.md for more info

public class ChessGameDHC {
  //This 2D array contains the chess position to be displayed on the board
  String[][] position;
  //This 1D array contains info such as En Passant target squares, the last move, castling availability, etc.
  String[] positionMeta;

  //Constructor
  @SuppressWarnings("unused")
  ChessGameDHC() {
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
    int startSquareFile = startSquare.charAt(0) - 'A' + 1;
    int startSquareRank = startSquare.charAt(1);

    //The targetSquare string is seperated
    int targetSquareFile = targetSquare.charAt(0) - 'A' + 1;
    int targetSquareRank = targetSquare.charAt(1);

    //Checks if it's the right player to move
    if (isPieceSameColor(piece, playerToMove)) {
      //Checks if the target square exists to avoid crashes
      if (targetSquareRank > 0 && targetSquareRank < 9 && targetSquareFile > 0 && targetSquareFile < 9) {
        //Is the piece a black pawn?
        if ("P".equals(piece)) {
          //Is the pawn staying on its file? Is the target square empty?
          if (targetSquareFile == startSquareFile && "-".equals(position[targetSquareFile][targetSquareRank])) {
            //Is it moving 1 square downwards?
            if (startSquareRank-- == targetSquareRank) {
              isMovePossible = true;
              //Is it moving 2 squares downwards from the 7th rank?
            } else if (startSquareRank == 7 && (startSquareRank - 2) == targetSquareRank) {
              isMovePossible = true;
            }
            //Is the pawn capturing a piece? (^ = XOR)
          } else if (startSquareFile-- == targetSquareFile ^ startSquareFile++ == targetSquareFile && startSquareRank-- == targetSquareRank && !isPieceSameColor("P", position[targetSquareFile][targetSquareRank])) {
            isMovePossible = true;
          }
          //Is the piece a white pawn?
        } else if ("p".equals(piece)) {
          //Is the pawn staying on its file? Is the target square empty?
          if (targetSquareFile == startSquareFile && "-".equals(position[targetSquareFile][targetSquareRank])) {
            //Is it moving 1 square upwards?
            if (startSquareRank++ == targetSquareRank) {
              isMovePossible = true;
              //Is it moving 2 squares upwards from the 2nd rank?
            } else if (startSquareRank == 2 && (startSquareRank + 2) == targetSquareRank) {
              isMovePossible = true;
            }
            //Is the pawn capturing a piece? (^ = XOR)
          } else if (startSquareFile-- == targetSquareFile ^ startSquareFile++ == targetSquareFile && startSquareRank++ == targetSquareRank && !isPieceSameColor("P", position[targetSquareFile][targetSquareRank])) {
            isMovePossible = true;
          }
        }
      }
    }
    
    return isMovePossible;
  };
}
