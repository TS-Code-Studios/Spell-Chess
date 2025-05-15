package com.javadevs;

//Author: @FRBFStudios
//Version: 29. 4. 2025
//See INFORMATION.md for more info

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChessGameHandler {
    private static String playerToMove = "w";
    private final List<String> emptySquares = Arrays.asList("-", "[", "]", "%");
    // All attributes are private for the sake of encapsulation
    private String[][] position;
    private String[][] coverageMap;

    private int[] positionMeta;
    private boolean[] castlingWhiteInfo, castlingBlackInfo;


    public ChessGameHandler() {
        newDefaultPosition();
    }

    //Main method starts a text-controlled test game
    public static void main(String[] args) {
        ChessGameHandler testGame = new ChessGameHandler();
        String moveMade;
        try (Scanner input = new Scanner(System.in)) {
            //Test game loop
            while (true) {
                //The current position is printed out in the terminal
                System.out.println("Current position:");
                System.out.println(testGame.stringArrayToString(testGame.position));

                // System.out.println("Coverage Map:");
                // System.out.println(testGame.stringArrayToString(testGame.coverageMap));

                System.out.println("Enter your move in the format \"piece startSquare targetSquare\". " + playerToMove + " to move.");

                //This will loop until a valid move is made
                while (true) {
                    //The player input is read and stored as moveMade
                    moveMade = input.nextLine();

                    System.out.println("DEBUG: Input read as " + moveMade);
                    //The components of the move (piece, startSquare, targetSquare) are stored in a string array.
                    String[] moveComponents = moveMade.split(" ");

                    if (moveComponents[0].equals("exit")) {
                        System.exit(0);
                    }

                    for (String component : moveComponents) {
                        System.out.println("DEBUG: " + component);
                    }

                    //If the move entered is possible
                    if (testGame.isMovePossible(moveComponents[0], moveComponents[1], moveComponents[2], true)) {
                        //Make the move and update castling availability, then break the while(true) loop
                        //System.out.println("DEBUG: Move is being made...");
                        testGame.makeMove(moveComponents[0], moveComponents[1], moveComponents[2]);

                        //System.out.println("DEBUG: Move made successfully.");
                        break;

                        //If it is illegal
                    } else if (!testGame.isMovePossible(moveComponents[0], moveComponents[1], moveComponents[2], true)) {
                        System.out.println("Illegal move!");

                        System.out.println("Current position:");
                        System.out.println(testGame.stringArrayToString(testGame.position));

                        System.out.println("Try again:");
                    }
                }

                //The player is switched
                playerToMove = Character.isLowerCase(playerToMove.charAt(0)) ? "B" : "w";
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

        // coverageMap array is filled with dashes ("-" = uncovered, "[" = covered by white, "]" = covered by black, "%" = covered by both sides)
        coverageMap = new String[][] {
                {"-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-"}
        };

        /*
        positionMeta array is filled with the default chess position's meta.
        1 = true, 0 = false
        Order: white O-O, white O-O-O, black O-O, black O-O-O, half moves, check
        */
        positionMeta = new int[] {0, 0, 0, 0, 0, 0};
        //Has king moved, has rook a moved, has rook h moved, is king in check
        castlingWhiteInfo = new boolean[]{false, false, false, false};
        castlingBlackInfo = new boolean[]{false, false, false, false};

        updateCoveredSquares();
    }

    private void updateCoveredSquares() {
        // Reset the coverage map
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                coverageMap[rank][file] = "-";
            }
        }

        // Iterate through the coverage map
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                String square = Character.toString((char) ('a' + file)) + (rank + 1);
                String piece = position[rank][file];

                // If there is a piece on a square, check where it can move
                if (piece != null && piece.charAt(0) != '-') {
                    simulateCoverage(piece, square);
                }
            }
        }
    }

    // This method can check if a move is possible. So far, promotions and en passant are not included. For playerToMove, use "w" and "B" respectively.
    // If the player is castling, simply enter "O-O" or "O-O-O" (in lowercase if it's white) for the piece parameter and enter "-" for all of the square parameters.

    //Method for checking all the squares a piece can cover from a square
    //This needs to be run BEFORE the player to move is changed or before the castling availability is updated!
    private void simulateCoverage(String piece, String square) {
        // System.out.println("DEBUG: Piece is " + piece);
        if (piece.equals("-")) {
            return;
        } // Fail save

        //Pawn squares are controlled differently
        if (Character.toLowerCase(piece.charAt(0)) == 'p') {
            // System.out.println("DEBUG: Piece is a pawn");
            int squareFile = (square.charAt(0) - 'a' + 1) - 1;
            int squareRank = (square.charAt(1) - '0') - 1;
            // System.out.println("DEBUG: Translated file " + square.charAt(0) + " to integer " + squareFile);
            // System.out.println("DEBUG: Translated rank " + square.charAt(1) + " to integer " + squareRank);

            int rankUp = squareRank + 1;
            int rankDown = squareRank - 1;
            int fileLeft = squareFile - 1;
            int fileRight = squareFile + 1;

            // System.out.println("DEBUG: Squares to modify are rank " + rankUp + " file " + fileRight + " and rank " + rankUp + " file " + fileLeft);
            // If the pawn is white
            if (Character.isLowerCase(piece.charAt(0)) && rankUp < 8) {
                // These four outer if clauses are there to ensure the program isn't trying to access any squares outside the 8×8 board
                if (fileRight < 8) {coverageMap[rankUp][fileRight] = coverageMap[rankUp][fileRight].replace('-', '[').replace(']', '%');}
                if (fileLeft > -1) {coverageMap[rankUp][fileLeft] = coverageMap[rankUp][fileLeft].replace('-','[').replace(']','%');}
            }

            // If the pawn is black
            else if (Character.isUpperCase(piece.charAt(0)) && rankDown > -1) {
                if (fileRight < 8) {coverageMap[rankDown][fileRight] = coverageMap[rankDown][fileRight].replace('-', ']').replace('[', '%');}
                if (fileLeft > -1) {coverageMap[rankDown][fileLeft] = coverageMap[rankDown][fileLeft].replace('-', ']').replace('[', '%');}
            }
            return;
        }

        //Iterates through every file
        for (int fileInt = 0; fileInt < 8; fileInt++) {
            String file = Character.toString((char) ('a' + fileInt));
            // System.out.println("DEBUG: Checking file " + file + " translated from integer " + fileInt);

            //Iterates through every rank
            for (int rank = 0; rank < 8; rank++) {
                String checkSquare = file + (rank + 1);
                if (square.equals(checkSquare)) {
                    continue;
                } // Rules out moves to the same square


                // If the move is possible, the square is set to covered
                if (isMovePossible(piece, square, checkSquare, false)) {
                    // If the piece that we're simulating is white
                    if (Character.isLowerCase(piece.charAt(0))) {
                        coverageMap[rank][fileInt] = coverageMap[rank][fileInt].replace('-', '[').replace(']', '%');

                        // If there is a black king on that square, black is in check
                        if (position[rank][fileInt].equals("K") && (coverageMap[rank][fileInt].equals("[") || coverageMap[rank][fileInt].equals("%"))) {
                            castlingBlackInfo[3] = true;
                            System.out.println("Black is in check!");
                        }
                    }

                    // If the piece that we're simulating is black
                    else {
                        coverageMap[rank][fileInt] = coverageMap[rank][fileInt].replace('-', ']').replace('[', '%');

                        // If there is a white king on that square, white is in check
                        if (position[rank][fileInt].equals("k") && (coverageMap[rank][fileInt].equals("]") || coverageMap[rank][fileInt].equals("%"))) {
                            castlingWhiteInfo[3] = true;
                            System.out.println("White is in check!");
                        }
                    }
                    // System.out.println("DEBUG: Movement of piece " + piece + " from square " + square + " to checkSquare " + checkSquare + " is possible.");
                }
            }
        }
    }

    public boolean isMovePossible(@NotNull String piece, @NotNull String startSquare, @NotNull String targetSquare, boolean notSimulated) {
        boolean isMovePossible = false;

        // Rules out castling
        if (piece.equalsIgnoreCase("o-o") || piece.equalsIgnoreCase("o-o-o")) {
            return canCastleHere(piece);
        }

        //The startSquare string is separated and turned into two integers
        //The integers need to be reduced by 1 since array coordinates start at 0
        int startSquareFile = ((startSquare.charAt(0) - 'a' + 1) - 1);
        int startSquareRank = (startSquare.charAt(1) - '0') - 1;


        //The targetSquare string is separated
        int targetSquareFile = (targetSquare.charAt(0) - 'a' + 1) - 1;
        // System.out.println("DEBUG: tSF is " + targetSquareFile);
        int targetSquareRank = (targetSquare.charAt(1) - '0') - 1;
        // System.out.println("DEBUG: tSR is " + targetSquareRank);


        // In case the move is not simulated, this rules out the move if the piece on the target square is of the same color
        if (notSimulated && isPieceSameColor(piece, position[targetSquareRank][targetSquareFile]) && !position[targetSquareRank][targetSquareFile].equals("-")) {
            System.err.println("DEBUG: Target square occupied by a same-colored piece.");
            return false;
        }

        //fileDiff and rankDiff is calculated (Math.abs = "absolute value" (Betrag))
        //These integers contain the difference in files and ranks for a move
        int fileDiff = Math.abs(startSquareFile - targetSquareFile);
        int rankDiff = Math.abs(startSquareRank - targetSquareRank);

        //Checks if it's the right player to move, since capitalization = color
        if (!isPieceSameColor(piece, playerToMove) && notSimulated) {
            System.err.println("DEBUG: Wrong player to move");
            return false;
        }

        //Checks if the target square exists to avoid crashes
        if (targetSquareRank < 0 || targetSquareRank > 7 || targetSquareFile < 0 || targetSquareFile > 7) {
            System.err.println("DEBUG: One of the squares doesn't exist.");
            return false;
        }

        //Does such a piece even exist on the start square?
        if (piece.charAt(0) != position[startSquareRank][startSquareFile].charAt(0)) {
            return false;
        }

        //Which piece is it?
        switch (piece.toLowerCase()) {
            //Is the piece a pawn?
            case "p" -> isMovePossible = checkPawnMove(piece, startSquareFile, startSquareRank, targetSquareFile, targetSquareRank);

            //Is the piece a knight?
            case "n" -> {
                //If one of fileDiff and rankDiff is 1 and the other is 2, it's an L-shaped movement
                if ((fileDiff == 2 && rankDiff == 1) || (fileDiff == 1 && rankDiff == 2)) {
                    isMovePossible = true;
                }
            }

            //Is the piece a bishop?
            case "b" -> {
                //If fileDiff == rankDiff, it's a diagonal movement
                if (fileDiff == rankDiff) {
                    //Is the path clear of any other pieces?
                    if (isDiagonalClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                        isMovePossible = true;
                    }
                }
            }

            //Is the piece a rook?
            case "r" -> {
                //If exclusively one of the differences is 0, it's a straight movement
                if (fileDiff == 0 ^ rankDiff == 0) {
                    //Is the path clear of any other pieces?
                    if (isStraightClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                        isMovePossible = true;
                    }
                }
            }

            //Is the piece a queen?
            case "q" -> {
                //The properties of the rook and bishop movement are combined
                if (fileDiff == rankDiff) {
                    //System.out.println("DEBUG: Queen is moving diagonally.");
                    if (isDiagonalClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                        //System.out.println("DEBUG: Diagonal clear.");
                        isMovePossible = true;
                    }
                } else if (fileDiff == 0 ^ rankDiff == 0) {
                    //System.out.println("DEBUG: Queen is moving in a straight line.");
                    if (isStraightClear(startSquareFile, startSquareRank, targetSquareFile, targetSquareRank)) {
                        isMovePossible = true;
                    }
                }
            }

            // Is the piece a king?
            case "k" -> {
                //If there is at least one difference that equals 1, it's a 1 square king movement
                if (fileDiff <= 1 && rankDiff <= 1) {
                    //If it's a white king, it can only move to "-" and "[" squares
                    if (piece.equals("k")) {
                        if (coverageMap[targetSquareRank][targetSquareFile].equals("-") || coverageMap[targetSquareRank][targetSquareFile].equals("["))
                        {
                            isMovePossible = true;
                        }
                    }

                    //If it's a black king, it can only move to "-" and "]" squares
                    else if (piece.equals("K")) {
                        if (coverageMap[targetSquareRank][targetSquareFile].equals("-") || coverageMap[targetSquareRank][targetSquareFile].equals("]"))
                        {
                            isMovePossible = true;
                        }
                    }
                }
            }
            //If the piece isn't recognized, the move is obviously not legal
            default -> {
                if(notSimulated){System.err.println("DEBUG: The piece ID has not been recognized.");}
            }
        }

        //The move is declared as illegal last-minute if startSquare and targetSquare are equal
        if (startSquare.equals(targetSquare)) {
            System.err.println("DEBUG: targetSquare and startSquare are equal: piece " + piece + " startSquare " + startSquare + " targetSquare " + targetSquare);
            isMovePossible = false;
        }

        // If the move is possible so far, this checks if the king of the player making the move would be in check after this move
        if (isMovePossible && notSimulated) {
            // Backs up the old position, position meta, coverage map and castling info arrays
            // We need to do deep copies or else there will be reference issues

            String[][] previousPositionBuffer = new String[position.length][];
            for (int i = 0; i < position.length; i++) {
                previousPositionBuffer[i] = position[i].clone();
            }

            // System.out.println("Previous position buffered as:");
            // System.out.println(stringArrayToString(previousPositionBuffer));

            int[] previousPositionMetaBuffer = positionMeta.clone();

            String[][] previousCoverageMapBuffer = new String[coverageMap.length][];
            for (int i = 0; i < coverageMap.length; i++) {
                previousCoverageMapBuffer[i] = coverageMap[i].clone();
            }

            boolean[] previousCastlingWhiteInfoBuffer = castlingWhiteInfo.clone();
            boolean[] previousCastlingBlackInfoBuffer = castlingBlackInfo.clone();

            // Makes the move, which includes an update of the check booleans
            makeMove(piece, startSquare, targetSquare);

            // Checks for checks
            if (playerToMove.equals("w") && castlingWhiteInfo[3]) {
                isMovePossible = false;
                System.err.println("White would be in check after this move!");

            } else if (playerToMove.equals("B") && castlingBlackInfo[3]) {
                isMovePossible = false;
                System.err.println("Black would be in check after this move!");
            }

            // All the arrays are reverted so the move was basically never made
            System.out.println("Previous position buffered as, reverting:");
            System.out.println(stringArrayToString(previousPositionBuffer));

            position = previousPositionBuffer;

            System.out.println("Position reverted to:");
            System.out.println(stringArrayToString(position));

            positionMeta = previousPositionMetaBuffer;

            coverageMap = previousCoverageMapBuffer;

            castlingWhiteInfo = previousCastlingWhiteInfoBuffer;
            castlingBlackInfo = previousCastlingBlackInfoBuffer;
        }

        //The boolean is returned
        if (notSimulated) {System.out.println("isMovePossible == " + isMovePossible);}
        return isMovePossible;
    }

    //Method for checking every way of castling
    private boolean canCastleHere(String castleNotation) {
        if ("o-o".equals(castleNotation) && positionMeta[0] == 1) {
            return true;
        } else if ("o-o-o".equals(castleNotation) && positionMeta[1] == 1) {
            return true;
        } else if ("O-O".equals(castleNotation) && positionMeta[2] == 1) {
            return true;
        } else return "O-O-O".equals(castleNotation) && positionMeta[3] == 1;
    }

    //Checks whether two pieces are of the same color
    public boolean isPieceSameColor(String piece1, String piece2) {
        if (piece2.equals("w") || piece2.equals("B")) {
            // piece2 is the playerToMove
            if (piece2.equals("w")) {
                return Character.isLowerCase(piece1.charAt(0)); // White's pieces are lowercase
            } else {
                return Character.isUpperCase(piece1.charAt(0)); // Black's pieces are uppercase
            }
        } else {
            // piece2 is another piece
            return (Character.isUpperCase(piece1.charAt(0)) == Character.isUpperCase(piece2.charAt(0)));
        }
    }

    //Since pawn logic is extra weird, it is separated into a different method
    private boolean checkPawnMove(String piece, int startSquareFile, int startSquareRank, int targetSquareFile, int targetSquareRank) {
        boolean isPawnMovePossible = false;

        // Determine move direction and starting rank for pawns
        int moveDirection = "P".equals(piece) ? -1 : 1;
        int startingRank = "P".equals(piece) ? 6 : 1;  // Adjusted starting rank for white and black pawns

        // Is the pawn staying on its file? Is the target square empty?
        if (targetSquareFile == startSquareFile && emptySquares.contains(position[targetSquareRank][targetSquareFile])) {

            // Is it moving 1 square forward?
            if (startSquareRank + moveDirection == targetSquareRank) {
                isPawnMovePossible = true;
            }
            // Is it moving 2 squares forward from the starting rank?
            else if (startSquareRank == startingRank && startSquareRank + 2 * moveDirection == targetSquareRank) {
                // Check if the square in between is also empty
                int intermediateRank = startSquareRank + moveDirection;
                if (emptySquares.contains(position[intermediateRank][targetSquareFile])) {
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
        while (currentFile != targetSquareFile) {
            //If one of the squares isn't empty, the path is blocked and false is returned
            if (!emptySquares.contains(position[currentRank][currentFile])) {
                //System.err.println("DEBUG: Diagonal is not clear.");
                return false;
            }

            //If that's not the case, the loop moves on to the next square on the path
            currentFile += fileDirection;
            currentRank += rankDirection;
            // System.out.println("DEBUG: currentFile is " + currentFile + ", currentRank is " + currentRank);
        }

        //If the loop hasn't triggered the return false statement, every square on the path is empty and true is returned
        return true;
    }

    /* Method for checking if a file or rank (straight) is clear for a rook or queen
    This needs to be done using two separate loops, one for vertical movement and one for horizontal movement
    Other than that, it's the exact same principle as for diagonals */
    private boolean isStraightClear(int startSquareFile, int startSquareRank, int targetSquareFile, int targetSquareRank) {
        //If the file remains constant, the movement is vertical
        if (startSquareFile == targetSquareFile) {
            int rankDirection = (targetSquareRank - startSquareRank) > 0 ? 1 : -1;
            int currentRank = startSquareRank + rankDirection;

            while (currentRank != targetSquareRank) {
                if (!emptySquares.contains(position[currentRank][startSquareFile])) {
                    //System.err.println("DEBUG: Vertical straight is not clear.");
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
                if (!emptySquares.contains(position[targetSquareRank][currentFile])) {
                    //System.err.println("DEBUG: Horizontal straight is not clear.");
                    return false;
                }

                currentFile += fileDirection;
            }
        }
        return true;
    }

    //Method to run AFTER making a move to update all castling info
    public void castlingAvailabilityUpdate(@NotNull String piece, String startSquare) {
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
                }
                break;

            case "R":
                if (startSquare.charAt(0) == 'a') {
                    castlingBlackInfo[1] = true;
                } else if (startSquare.charAt(0) == 'h') {
                    castlingBlackInfo[2] = true;
                }
                break;

            default:
                break;
        }
        //Now the availabilities are updated
        //Did the white king move?
        if (!castlingWhiteInfo[0] && !castlingWhiteInfo[3]) {
            //White castle queenside
            if (!castlingWhiteInfo[1] &&
                    (position[0][1].equals("-") || position[0][1].equals("[")) &&
                    (position[0][2].equals("-") || position[0][2].equals("[")) &&
                    (position[0][3].equals("-") || position[0][3].equals("["))) {
                positionMeta[1] = 1;
            }
            //White castle kingside
            if (!castlingWhiteInfo[2] &&
                    (position[0][5].equals("-") || position[0][5].equals("[")) &&
                    (position[0][6].equals("-") || position[0][6].equals("["))) {
                positionMeta[0] = 1;
            }
        }
        //Did the black king move?
        if (!castlingBlackInfo[0] && !castlingBlackInfo[3]) {
            //Black castle queenside
            if (!castlingBlackInfo[1] &&
                    (position[7][1].equals("-") || position[7][1].equals("]")) &&
                    (position[7][2].equals("-") || position[7][2].equals("]")) &&
                    (position[7][3].equals("-") || position[7][3].equals("]"))) {
                positionMeta[3] = 1;
            }
            //Black castle kingside
            if (!castlingBlackInfo[2] &&
                    (position[7][5].equals("-") || position[7][5].equals("]")) &&
                    (position[7][6].equals("-") || position[7][6].equals("]"))) {
                positionMeta[2] = 1;
            }
        }
    }

    //Method for changing the position and positionMeta arrays, THIS DOES NOT CHECK IF THE MOVE IS POSSIBLE!
    public void makeMove(@NotNull String piece, String startSquare, String targetSquare) {
        switch (piece) {
            //Is white castling kingside?
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
                return;
            }
            //Is white castling queenside?
            case "o-o-o" -> {
                position[0][4] = "-";
                position[0][0] = "-";
                position[0][2] = "k";
                position[0][3] = "r";

                positionMeta[0] = 0;
                positionMeta[1] = 0;
                return;
            }
            //Is black castling kingside?
            case "O-O" -> {
                position[7][4] = "-";
                position[7][7] = "-";
                position[7][6] = "K";
                position[7][5] = "R";

                positionMeta[2] = 0;
                positionMeta[3] = 0;
                return;
            }
            //Is black castling queenside?
            case "O-O-O" -> {
                position[7][4] = "-";
                position[7][0] = "-";
                position[7][2] = "K";
                position[7][3] = "R";

                positionMeta[2] = 0;
                positionMeta[3] = 0;
                return;
            }
        }

        System.out.println("DEBUG: Player is not castling.");
        try {
            int startSquareFile = ((startSquare.charAt(0) - 'a' + 1) - 1);
            int startSquareRank = (startSquare.charAt(1) - '0') - 1;

            int targetSquareFile = ((targetSquare.charAt(0) - 'a' + 1) - 1);
            int targetSquareRank = (targetSquare.charAt(1) - '0') - 1;

            position[startSquareRank][startSquareFile] = "-";
            position[targetSquareRank][targetSquareFile] = piece;

        } catch (Exception invalidMoveInput) {
            System.err.println("Invalid move input: " + invalidMoveInput);
        }

        //System.out.println("DEBUG: Updating covered squares...");
        updateCoveredSquares();
        //System.out.println("DEBUG: Covered squares updated successfully.");

        //System.out.println("DEBUG: Updating castling availabilities...");
        castlingAvailabilityUpdate(piece, startSquare);
        //System.out.println("DEBUG: Castling availabilities updated successfully. Looping...");
    }

    public boolean isCheckmate() {
        boolean isCheckmate = false;

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                if (position[rank][file].equals("-")) {continue;}
            }
        }

        return isCheckmate;
    }

    // Small utility method that can convert any String 2D array into a printable String (including line breaks). Only works for non-jagged 2D arrays.
    public String stringArrayToString(String @NotNull [][] input) {
        StringBuilder output = new StringBuilder();

        for (int i = input[0].length - 1; i > -1; i--) {
            for (int n = 0; n < input.length; n++) {
                output.append(input[i][n]);
            }
            output.append("\n");
        }
        return output.toString();
    }

    // Getters for all the read-only attributes
    public String[][] getPosition() {
        return position;
    }

    public int[] getPositionMeta() {
        return positionMeta;
    }

    public boolean[] getCastlingWhiteInfo() {
        return castlingWhiteInfo;
    }

    public boolean[] getCastlingBlackInfo() {
        return castlingBlackInfo;
    }

    public String getPlayerToMove() {
        return playerToMove;
    }

    public List<String> getEmptySquares() {
        return emptySquares;
    }
}