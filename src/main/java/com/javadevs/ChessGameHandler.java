package com.javadevs;

//Author: @FRBFStudios
//Version: 10. 3. 2025
//See INFORMATION.md for more info

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class ChessGameHandler {
  //This 2D array contains the chess position to be displayed on the board
  String[][] position;
  //This 1D array contains info such as En Passant target squares, the last move, castling availability, etc.
  int[] positionMeta;
  private boolean[] castlingWhiteInfo;
  private boolean[] castlingBlackInfo;
  private

  //These integers contain the difference in files and ranks for a move
  int fileDiff;
  int rankDiff;
  List<String> emptySquares = Arrays.asList("-","[", "]", "%");

  //Constructor
  public ChessGameHandler() {
    newDefaultPosition();
  }

  //Main method starts a text-controlled test game
  public static void main(String[] args) {
    ChessGameHandler testGame = new ChessGameHandler();
    String playerToMove = "w";
    String moveMade;
    try (Scanner input = new Scanner(System.in)) {
      //Testgame loop
      while(true) {
        //The current position is printed out in the terminal
        System.out.println("Current position:");
        System.out.println(testGame.positionToString());
        System.out.println("Enter your move in the format \"piece startSquare targetSquare\". " + playerToMove + " to move.");
          
        //This will loop until a valid move is made
        while(true) {
          moveMade = input.nextLine();
          String[] moveComponents = moveMade.split(" ");
          if (testGame.isMovePossible(moveComponents[0], moveComponents[1], moveComponents[2], playerToMove)) {
            testGame.makeMove(moveComponents[0], moveComponents[1], moveComponents[2]);
            testGame.castlingAvailabilityUpdate(moveComponents[0], moveComponents[1], moveComponents[2]);
            break;
          } else if(!testGame.isMovePossible(moveComponents[0], moveComponents[1], moveComponents[2], playerToMove)) {
            System.out.println("Illegal move!");
            System.out.println("Try again:");
          }
        }
        
        //The player is switched
        if ("w".equals(playerToMove)) {
          playerToMove = "B";
        } else {
          playerToMove = "w";
        }
      }
    }
  }

  //Every array is assigned the values of the default chess position
  private void newDefaultPosition() {
    //position array is filled with the default chess position (Uppercase = black pieces, lowercase = white pieces, board is inverted to make the first rank row 0)
    position = new String[][] {
      {"r", "n", "b", "q", "k", "b", "n", "r"},
      {"p", "p", "p", "p", "p", "p", "p", "p"},
      {"-", "-", "-", "-", "-", "-", "-", "-"},
      {"-", "-", "-", "-", "-", "-", "-", "-"},
      {"-", "-", "-", "-", "-", "-", "-", "-"},
      {"-", "-", "-", "-", "-", "-", "-", "-"},
      {"P", "P", "P", "P", "P", "P", "P", "P"},
      {"R", "N", "B", "Q", "K", "B", "N", "R"}
    };
    
    //positionMeta array is filled with the default chess position's meta
    //For true-or-false values, use "1" for true (such as if white can castle or not) and "0" for false.
    //Order: white O-O, white O-O-O, black O-O, black O-O-O, halfmoves, check
    positionMeta = new int[] {0, 0, 0, 0, 0, 0};
    //Has king moved, has rook a moved, has rook h moved
    castlingWhiteInfo = new boolean[] {false, false, false};
    castlingBlackInfo = new boolean[] {false, false, false};
  }

  //Method to run AFTER making a move to update all castling info
  public void castlingAvailabilityUpdate(String piece, String startSquare, String targetSquare) {
    //If the player just castled, the availabilities are set to 0 and the method is stopped
    if (piece.equals("o-o") || piece.equals("o-o-o")) {
      castlingWhiteInfo[0] = true;
      positionMeta[0] = 0;
      positionMeta[1] = 0;
      return;
    } else if (piece.equals("O-O") || piece.equals("O-O-O")) {
      castlingBlackInfo[0] = true;
      positionMeta[2] = 0;
      positionMeta[3] = 0;
      return;
    }
    
    switch (piece) {
      case "k":
        castlingWhiteInfo[0] = true;
        break;
      
      case "K":
        castlingBlackInfo[0] = true;
        break;
      
      case "r":
        if (startSquare.charAt(0) == 'a') { 
          castlingWhiteInfo[1] = true;
        } else if (startSquare.charAt(0) == 'h') {
          castlingWhiteInfo[2] = true;
        }       break;
      
      case "R":
        if (startSquare.charAt(0) == 'a') {
          castlingBlackInfo[1] = true;
        } else if (startSquare.charAt(0) == 'h') {
          castlingBlackInfo[2] = true;
        }       break;
      
      default:
        break;
    }
    //Now the availabilities are updated
    //Did the white king move?
    if (castlingWhiteInfo[0] == false) {
      //White castle queenside
      if (castlingWhiteInfo[1] == false &&
          position[0][1].equals("-") &&
          position[0][2].equals("-") &&
          position[0][3].equals("-")) {
        positionMeta[1] = 1;
      }
      //White castle kingside
      if (castlingWhiteInfo[2] == false &&
          position[0][5].equals("-") &&
          position[0][6].equals("-")) {
        positionMeta[0] = 1;
      }
    }
    //Did the black king move?
    if (castlingBlackInfo[0] == false) {
      //Black castle queenside
      if (castlingBlackInfo[1] == false &&
          position[7][1].equals("-") &&
          position[7][2].equals("-") &&
          position[7][3].equals("-")) {
        positionMeta[3] = 1;
      }
      //Black castle kingside
      if (castlingBlackInfo[2] == false &&
          position[7][5].equals("-") &&
          position[7][6].equals("-")) {
          positionMeta[2] = 1;
      }
    }
  }

  public void makeMove(String piece, String startSquare, String targetSquare) {
      //Is white castling kingside?
      switch (piece) {
          case "o-o" -> {
              //Former rook and king squares are cleared
              position[0][4] = "-";
              position[0][7] = "-";
              //King and rook are moved to their new position
              position[0][6] = "k";
              position[0][5] = "r";
              //Castling is banned for the rest of the game
              positionMeta[0] = 0;
              positionMeta[1] = 0;
          }
          //Is white castling queenside?
          case "o-o-o" -> {
              position[0][4] = "-";
              position[0][0] = "-";
              position[0][2] = "k";
              position[0][3] = "r";
              positionMeta[0] = 0;
              positionMeta[1] = 0;
          }
          //Is black castling kingside?
          case "O-O" -> {
              position[7][4] = "-";
              position[7][7] = "-";
              position[7][6] = "K";
              position[7][5] = "R";
              positionMeta[2] = 0;
              positionMeta[3] = 0;
          }
          //Is black castling queenside?
          case "O-O-O" -> {
              position[7][4] = "-";
              position[7][0] = "-";
              position[7][2] = "K";
              position[7][3] = "R";
              positionMeta[2] = 0;
              positionMeta[3] = 0;
          }
          default -> {
              try {
                  int startSquareFile = ((startSquare.charAt(0) - 'a' + 1) - 1);
                  int startSquareRank = (startSquare.charAt(1) - '0') - 1;
                  
                  int targetSquareFile = ((targetSquare.charAt(0) - 'a' + 1) - 1);
                  int targetSquareRank = (targetSquare.charAt(1) - '0') - 1;
                  
                  position[startSquareRank][startSquareFile] = "-";
                  position[targetSquareRank][targetSquareFile] = piece;
              } catch (Exception invalidMoveInput) {
                  System.err.println("Invalid move input");
              }
          }
      }
  }

  //Method for checking all the squares covered by a piece after it moves and setting those to "[" for white coverage,"]" for black coverage and "%" for both
  //This needs to be run BEFORE the player to move is changed!
  //Reverting squares no longer covered isn't implemented yet
  private void updateCoveredSquares(String piece, String square) {
    //Iterates through every file
    for (int fileInt = 0; file < 8; file++) {
      String file = Character.toString((char) ('a' + fileInt));
      System.out.println("DEBUG: Checking file " + file + " translated from integer " + fileInt);
      //Iterates through every rank
      for (int rank = 0; rank < 8; rank++) {
        String checkSquare = file + (rank + 1)
        System.out.println("DEBUG: Checking rank " + rank + " combined with file " + file + " resulting in Square " + checkSquare);
        if (isMovePossible(piece, square,  checkSquare)) {
          //If the square isn't covered by any pieces yet, it is set to "[" or "]" respectively
          if (position[fileInt][rank].equals("-")) {
            if (piece.isLowerCase) {
              position[fileInt][rank] = "[";
            } else {
              position[fileInt][rank] = "]";
            }
          } else if (position[fileInt][rank].equals("[") || position[fileInt][rank].equals("]")) {
            position[fileInt][rank] = "%";
          }
        }
      }
    }
  }

  public String positionToString() {
    String positionString = "";
    
    for (int i = 7; i > -1; i--) {
      for (int n = 0; n < 8; n++) {
        positionString += position[i][n];
      }
      positionString += "\n";
    }
    return positionString;
  }

  //Checks whether or not two pieces are of the same color
  public boolean isPieceSameColor(String piece1, String piece2) {
    if (piece2.equals("w") || piece2.equals("B")) {
        // piece2 is the playerToMove
        if (piece2.equals("w")) {
            return Character.isLowerCase(piece1.charAt(0)); // White's pieces are lowercase
        } else if (piece2.equals("B")) {
            return Character.isUpperCase(piece1.charAt(0)); // Black's pieces are uppercase
        }
    } else {
        // piece2 is another piece
        return (Character.isUpperCase(piece1.charAt(0)) == Character.isUpperCase(piece2.charAt(0)));
    }
    return false;
  }
  
  //This method can check if a move is possible. So far, castling, absolute pins, promotions and en passant are not included. For playerToMove, use "w" and "B" respectively.
  //If the player is castling, simply enter "O-O" or "O-O-O" (in lowercase if it's white) for the piece parameter and enter "-" for all of the square parameters.
  public boolean isMovePossible(String piece, String startSquare, String targetSquare, String playerToMove) {
    //Avoids nullPointerExceptions
    if (piece == null || startSquare == null || targetSquare == null) {
      System.err.println("DEBUG: An input equals null");
      return false;
    }
    boolean isMovePossible = false;

    if ((piece.equalsIgnoreCase("o-o") || piece.equalsIgnoreCase("o-o-o")) && canCastleHere(piece)) {
      return true;
    }
    
    //The startSquare string is seperated and turned into two integers
    //The integers need to be reduced by 1 since array coordinates start at 0
    int startSquareFile = ((startSquare.charAt(0) - 'a' + 1) - 1);
    int startSquareRank = (startSquare.charAt(1) - '0') - 1;


    //The targetSquare string is seperated
    int targetSquareFile = (targetSquare.charAt(0) - 'a' + 1) - 1;
    int targetSquareRank = (targetSquare.charAt(1) - '0') - 1;

    
    //fileDiff and rankDiff is calculated (Math.abs = "absolute value" (Betrag))
    fileDiff = Math.abs(startSquareFile - targetSquareFile);
    rankDiff = Math.abs(startSquareRank - targetSquareRank);

    //Checks if it's the right player to move, since capitalization = color
    if (isPieceSameColor(piece, playerToMove)) {
      //Checks if the target square exists to avoid crashes
      if (targetSquareRank >= 0
          && targetSquareRank < 8
          && targetSquareFile >= 0
          && targetSquareFile < 8) {
        
        //Does such a piece even exist on the start square?
        if (!"o-o".equalsIgnoreCase(piece)
            && !"o-o-o".equalsIgnoreCase(piece)
            && !piece.equals(position[startSquareRank][startSquareFile])) {
          return false;
        }
          
        //Which piece is it?
        switch (piece.toLowerCase()) {
          //Is the piece a pawn?
          case "p" -> {
            if(checkPawnMove(piece, startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
              isMovePossible = true;
            }
            break;
          }
            
          //Is the piece a knight?
          case "n" -> {
            //If one of fileDiff and rankDiff is 1 and the other is 2, it's an L-shaped movement
            if ((fileDiff == 2 && rankDiff == 1)
                || (fileDiff == 1 && rankDiff == 2)) {
              if (isEmptyOrOpposed(piece, targetSquareFile, targetSquareRank)) {
                isMovePossible = true;
              }
            }
            break;
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
            break;
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
            break;
          }

          //Is the piece a queen?
          case "q" -> {
            //The properties of the rook and bishop movement are combined
            if (fileDiff == rankDiff) {
              System.out.println("DEBUG: Queen is moving diagonally.");
              if (isDiagonalClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                System.out.println("DEBUG: Diagonal clear.");
                if (isEmptyOrOpposed(piece, targetSquareFile, targetSquareRank)) {
                  isMovePossible = true;
                } else {System.out.println("DEBUG: Target square occupied by a same-colored piece.");}
              }
            } else if (fileDiff == 0 ^ rankDiff == 0) {
              System.out.println("DEBUG: Queen is moving in a straight line.");
              if(isStraightClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                if (isEmptyOrOpposed(piece, targetSquareFile, targetSquareRank)) {
                  isMovePossible = true;
                } else {System.out.println("DEBUG: Target square occupied by a same-colored piece.");}
              }
            }
            break;
          }

          //Is the piece a king? Checks aren't implemented yet
          case "k" -> {
            //If there is at least one difference that equals 1, it's a 1 square queen movement
            if (fileDiff == 1 || rankDiff == 1) {
              //The king is the only piece that doesn't use isEmptyOrOpposed, as that would allow him to move to squares controlled by the enemy
              //If it's a white king, it can only move to "-" and "[" squares
              if (piece.equals("k")) {
                if (position[targetSquareFile][targetSquareRank].equals("-")
                    || position[targetSquareFile][targetSquareRank].equals("[")
                    || !isPieceSameColor(piece, position[targetSquareFile][targetSquareRank])) {
                  isMovePossible = true;
                }
              //If it's a black king, it can only move to "-" and "]" squares
              } else if (piece.equals("K")) {
                if (position[targetSquareFile][targetSquareRank].equals("-")
                    || position[targetSquareFile][targetSquareRank].equals("]")
                    || !isPieceSameColor(piece, position[targetSquareFile][targetSquareRank])) {
                  isMovePossible = true;
                }
              }
            }
            break;
          }

          //Is the move castling?


          //If the piece isn't  recognized, the move is obviously not legal
          default -> {
            isMovePossible = false;
            System.err.println("DEBUG: The piece ID has not been recognized.");
          }
        }
      } else {System.err.println("DEBUG: One of the squares doesn't exist.");}
    } else {System.err.println("DEBUG: Wrong player to move");}

    //If the piece is being moved to the same square it starts from, the move is declared as illegal last-minute
    if (startSquare.equals(targetSquare)) {
      isMovePossible = false;
      System.err.println("DEBUG: targetSquare and startSquare are equal.");
    }

    //The boolean is returned
    return isMovePossible;
  }
  
  //Since pawn logic is extra weird, it is seperated into a different method
  private boolean checkPawnMove(String piece, int startSquareFile, int startSquareRank, int targetSquareFile, int targetSquareRank) {
    boolean isPawnMovePossible = false;

    // Determine move direction and starting rank for pawns
    int moveDirection = "P".equals(piece) ? -1 : 1;
    int startingRank = "P".equals(piece) ? 6 : 1;  // Adjusted starting rank for white and black pawns

    // Is the pawn staying on its file? Is the target square empty?
    if (targetSquareFile == startSquareFile && "-".equals(position[targetSquareRank][targetSquareFile])) {

        // Is it moving 1 square forward?
        if (startSquareRank + moveDirection == targetSquareRank) {
            isPawnMovePossible = true;
        }
        // Is it moving 2 squares forward from the starting rank?
        else if (startSquareRank == startingRank && startSquareRank + 2 * moveDirection == targetSquareRank) {
            // Check if the square in between is also empty
            int intermediateRank = startSquareRank + moveDirection;
            if ("-".equals(position[intermediateRank][startSquareFile])) {
                isPawnMovePossible = true;
            } else {
                System.err.println("DEBUG: Square in between is not empty.");
            }
        }
    }
    // Is the pawn capturing a piece? (^ = XOR)
    else if ((startSquareFile - 1 == targetSquareFile ^ startSquareFile + 1 == targetSquareFile)
        && startSquareRank + moveDirection == targetSquareRank
        && !isPieceSameColor(piece, position[targetSquareRank][targetSquareFile])) {
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
      if (!"-".equals(position[currentRank][currentFile])) {
        System.err.println("DEBUG: Diagonal is not clear.");
        return false;
      }
      
      //If that's not the case, the loop moves on to the next square on the path
      currentFile += fileDirection;
      currentRank += rankDirection;
    }
    
    //If the loop hasn't triggered the return false statement, every square on the path is empty and true is returned
    return true;
  }

  /* Method for checking if a file or rank (straight) is clear for a rook or queen
  This needs to be done using two seperate loops, one for vertical movement and one for horizontal movement
  Other than that, it's the exact same principle as for diagonals */
  private boolean isStraightClear(int startSquareFile, int startSquareRank, int targetSquareFile, int targetSquareRank) {
    //If the file remains constant, the movement is vertical
    if (startSquareFile == targetSquareFile) {
      int rankDirection = (targetSquareRank - startSquareRank) > 0 ? 1 : -1;
      int currentRank = startSquareRank + rankDirection;

      while (currentRank != targetSquareRank) {
        if (!"-".equals(position[currentRank][startSquareFile])) {
          System.err.println("DEBUG: Vertical straight is not clear.");
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
        if (!"-".equals(position[targetSquareRank][currentFile])) {
          System.err.println("DEBUG: Horizontal straight is not clear.");
          return false;
        }

        currentFile += fileDirection;
      }
    }
    return true;
  }

  //Method for checking every way of castling
  private boolean canCastleHere(String castleNotation) {
    if ("o-o".equals(castleNotation) && positionMeta[0] == 1) {return true;}
    else if ("o-o-o".equals(castleNotation) && positionMeta[1] == 1) {return true;}
    else if ("O-O".equals(castleNotation) && positionMeta[2] == 1) {return true;}
    else if ("O-O-O".equals(castleNotation) && positionMeta[3] == 1) {return true;}
    
    return false;
  }

  //Small method for checking whether or not a square is empty or occupied by an opposing piece
  private boolean isEmptyOrOpposed(String piece, int targetSquareFile, int targetSquareRank) {
    if(emptySquares.contains(position[targetSquareFile][targetSquareRank]) || !isPieceSameColor(position[targetSquareFile][targetSquareRank], piece)) {
      return true;
    } else {return false;}
  }
}
