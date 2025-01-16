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
  
  //This method can check if a move is possible. So far, castling, absolute pins, promotions and en passant are not included. For playerToMove, use "w" and "B" respectively.
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
        //This avoids crashes
        if (null != piece.toLowerCase()) {
          switch (piece.toLowerCase()) {
            //Is the piece a pawn?
            case "p" -> isMovePossible = checkPawnMove(piece, startSquareFile, startSquareRank, targetSquareFile, targetSquareRank);
            
            //Is the piece a knight?
            case "n" -> {
              //If one of fileDiff and rankDiff is 1 and the other is 2, it's an L-shaped movement
              if ((fileDiff == 2 && rankDiff == 1)
                  || (fileDiff == 1 && rankDiff == 2)) {
                if (isEmptyOrOpposed(piece, targetSquareFile, targetSquareRank)) {
                  isMovePossible = true;
                }
              }
            }
            
            //Is the piece a bishop?
            case "b" -> {
              //If fileDiff == rankDiff, it's a diagonal movement
              if (fileDiff == rankDiff) {
                //Is the path clear of any other pieces?
                if (isDiagonalClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                  if(isEmptyOrOpposed(piece, targetSquareFile, targetSquareRank)) {
                    isMovePossible = true;
                  }
                }
              }
            }

            //Is the piece a rook?
            case "r" -> {
              //If exclusively one of the differences is 0, it's a straight movement
              if (fileDiff == 0 ^ rankDiff == 0) {
                //Is the path clear of any other pieces?
                if (isStraightClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                  if (isEmptyOrOpposed(piece, targetSquareFile, targetSquareRank)) {
                    isMovePossible = true;
                  }
                }
              }
            }

            //Is the piece a queen?
            case "q" -> {
              //The properties of the rook and bishop movement are combined
              if (fileDiff == rankDiff) {
                if (isDiagonalClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                  if (isEmptyOrOpposed(piece, targetSquareFile, targetSquareRank)) {
                    isMovePossible = true;
                  }
                }
              } else if (fileDiff == 0 ^ rankDiff == 0) {
                if(isStraightClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                  if (isEmptyOrOpposed(piece, targetSquareFile, targetSquareRank)) {
                    isMovePossible = true;
                  }
                }
              }
            }

            //If the piece isn't  recognized, the move is obviously not legal
            default -> isMovePossible = false;
          }
        }
      }
    }

    //If the piece is being moved to the same square it starts from, the move is declared as illegal last-minute
    if (startSquare.equals(targetSquare)) {
      isMovePossible = false;
    }

    //The boolean is returned
    return isMovePossible;
  }
  
  //Since pawn logic is extra weird, it is seperated into a different method
  private boolean checkPawnMove(String piece, int startSquareFile, int startSquareRank, int targetSquareFile, int targetSquareRank) {
    boolean isPawnMovePossible = false;
    
    //Determine move direction and starting rank for pawns, using a ternary operator like this: variable = condition ? ifTrue : ifFalse
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

  //Method for checking if a diagonal is clear for a bishop or queen
  private boolean isDiagonalClear(int startSquareFile, int startSquareRank, int targetSquareFile, int targetSquareRank) {
    //The file and rank direction of the movement is determined (Is the bishop moving right/up (1) or left/down (-1)?)
    int fileDirection = (targetSquareFile - startSquareFile) > 0 ? 1 : -1;
    int rankDirection = (targetSquareRank - startSquareRank) > 0 ? 1 : -1;

    //The position of the bishop after one square of movement is determined
    int currentFile = startSquareFile + fileDirection;
    int currentRank = startSquareRank + rankDirection;

    //Each square along the bishop's path is checked
    while(currentFile != targetSquareFile) {
      //If one of the squares isn't empty, the path is blocked and false is returned
      if (!"-".equals(position[currentFile][currentRank])) {
        return false;
      }
      
      //If that's not the case, the loop moves on to the next square on the path
      currentFile += fileDirection;
      currentRank += rankDirection;
    }
    
    //If the loop hasn't triggered the return false statement, every square on the path is empty and true is returned
    return true;
  }

  //Method for checking if a file or rank (straight) is clear for a rook or queen
  //This needs to be done using two seperate loops, one for vertical movement and one for horizontal movement
  //Other than that, it's the exact same principle as for diagonals
  private boolean isStraightClear(int startSquareFile, int startSquareRank, int targetSquareFile, int targetSquareRank) {
    //If the file remains constant, the movement is vertical
    if (startSquareFile == targetSquareFile) {
      int rankDirection = (targetSquareRank - startSquareRank) > 0 ? 1 : -1;
      int currentRank = startSquareRank + rankDirection;

      while (currentRank != targetSquareRank) {
        if (!"-".equals(position[startSquareFile][targetSquareRank])) {
          return false;
        }

        currentRank += rankDirection;
      }
    }

    //If the rank remains constant, the movement is horizontal
    else if (startSquareRank == targetSquareRank) {
      int fileDirection = (targetSquareFile - startSquareFile) > 0 ? 1 : -1;
      int currentFile = startSquareFile + fileDirection;
      
      while (currentFile != targetSquareFile) {
        if (!"-".equals(position[targetSquareFile][targetSquareRank])) {
          return false;
        }

        currentFile += fileDirection;
      }
    }
    return true;
  }

  //Small method for checking whether or not a square is empty or occupied by an opposing piece
  private boolean isEmptyOrOpposed(String piece, int targetSquareFile, int targetSquareRank) {
    return "-".equals(position[targetSquareFile][targetSquareRank])
            || !isPieceSameColor(piece, position[targetSquareFile][targetSquareRank]);
  }
}