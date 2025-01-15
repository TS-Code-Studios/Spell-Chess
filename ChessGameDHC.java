//Author: @FRBFStudios
//Version: 15/1/2024
//See INFORMATION.md for more info

public class ChessGameDHC {
  //This 2D array contains the chess position to be displayed on the board
  @SuppressWarnings("unused")
  String[][] position;
  //This 1D array contains info such as En Passant target squares, the last move, castling availability, etc.
  @SuppressWarnings("unused")
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
}
