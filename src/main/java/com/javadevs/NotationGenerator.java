package com.javadevs;

//Author: @FRBFStudios
//Version: 10. 4. 2025
//Generates notation of a game and saves it into a file.

import java.util.List;

public static class ChessNotation {
    public static String toAlgebraicNotation(String piece, String startSquare, String targetSquare, String[8][8] position) {
        List<String> emptySquares = Arrays.asList("-","[", "]", "%");

        String move = "x";

        //Is the move castling?
        if(piece.equalsIgnoreCase("O-O") || piece.equalsIgnoreCase("O-O-O")) {move = piece; return move;}

        int targetSquareFile = (targetSquare.charAt(0) - 'a' + 1) - 1;
        int targetSquareRank = (targetSquare.charAt(1) - '0') - 1;

        if(emptySquares.contains(position[targetSquareRank][targetSquareFile])) {
            move = "";
        }
        
        if (piece.equalsIgnoreCase("p")) {
            move = startSquare.charAt(0) + move + targetSquare;
        } else {
            move = piece.toUpperCase() + move + targetSquare;
        }
        return move;
    }
}
