import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ChessNotation {
    private String piece;           // The piece being moved (K, Q, R, B, N, or "" for pawn).
    private String startSquare;     // Starting square in algebraic format (e.g., "e2").
    private String targetSquare;    // Target square in algebraic format (e.g., "e4").
    private boolean isCapture;      // Whether the move involves capturing a piece.
    private boolean isCheck;        // Whether the move results in a check.
    private boolean isCheckmate;    // Whether the move results in checkmate.
    private boolean isCastling;     // Whether the move is a castling move.
    private String castlingType;    // Castling type ("O-O" for kingside, "O-O-O" for queenside).
    private String promotion;       // Promotion piece (e.g., "Q", "R", "B", "N").
    private String playerToMove;    // The player making the move ("White" or "Black").
    private Map<String, String> boardState;  // A map of the current board state for disambiguation.

    /**
     * Constructor for ChessNotation.
     *
     * @param piece         The piece being moved (K, Q, R, B, N, or "" for pawn).
     * @param startSquare   The square from which the piece is moved (e.g., "e2").
     * @param targetSquare  The square to which the piece is moved (e.g., "e4").
     * @param isCapture     Whether the move involves capturing an opponent's piece.
     * @param isCheck       Whether the move results in a check.
     * @param isCheckmate   Whether the move results in checkmate.
     * @param isCastling    Whether the move is a castling move.
     * @param castlingType  If castling, the type ("O-O" or "O-O-O").
     * @param promotion     The piece promoted to, if applicable (e.g., "Q", "R").
     * @param playerToMove  The player currently making the move ("White" or "Black").
     * @param boardState    The current board state for disambiguation (Map of positions).
     */
    public ChessNotation(String piece, String startSquare, String targetSquare, boolean isCapture,
                         boolean isCheck, boolean isCheckmate, boolean isCastling, String castlingType,
                         String promotion, String playerToMove, Map<String, String> boardState) {
        this.piece = piece;
        this.startSquare = startSquare;
        this.targetSquare = targetSquare;
        this.isCapture = isCapture;
        this.isCheck = isCheck;
        this.isCheckmate = isCheckmate;
        this.isCastling = isCastling;
        this.castlingType = castlingType;
        this.promotion = promotion;
        this.playerToMove = playerToMove;
        this.boardState = boardState;
    }

    /**
     * Converts the move to Standard Algebraic Notation (SAN).
     *
     * @return The move in algebraic notation format.
     */
    public String toAlgebraicNotation() {
        if (isCastling) {
            return castlingType; // Return "O-O" or "O-O-O" for castling.
        }

        StringBuilder notation = new StringBuilder();

        // Add the piece symbol (pawns are implicit, so no letter for pawns).
        if (!piece.isEmpty()) {
            notation.append(piece);
        }

        // Handle disambiguation for ambiguous moves (if multiple pieces of the same type can move).
        if (requiresDisambiguation()) {
            notation.append(disambiguateMove());
        }

        // Add "x" if it's a capture.
        if (isCapture) {
            if (piece.isEmpty()) {
                // Pawn captures include the starting file of the pawn.
                notation.append(startSquare.charAt(0));
            }
            notation.append("x");
        }

        // Add the target square.
        notation.append(targetSquare);

        // Add promotion, if applicable.
        if (promotion != null && !promotion.isEmpty()) {
            notation.append("=").append(promotion);
        }

        // Add "+" for check and "#" for checkmate.
        if (isCheckmate) {
            notation.append("#");
        } else if (isCheck) {
            notation.append("+");
        }

        return notation.toString();
    }

    /**
     * Determines if the move requires disambiguation.
     * Disambiguation occurs when multiple pieces of the same type can move to the target square.
     *
     * @return True if disambiguation is needed, false otherwise.
     */
    private boolean requiresDisambiguation() {
        if (piece.isEmpty()) {
            return false; // Pawns don't require disambiguation.
        }

        for (String square : boardState.keySet()) {
            if (!square.equals(startSquare) && boardState.get(square).equals(piece)) {
                if (canMoveToTarget(square, targetSquare)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Generates the disambiguation string for the move.
     *
     * @return The disambiguation string (file, rank, or both).
     */
    private String disambiguateMove() {
        boolean sameFile = false, sameRank = false;

        for (String square : boardState.keySet()) {
            if (!square.equals(startSquare) && boardState.get(square).equals(piece)) {
                if (canMoveToTarget(square, targetSquare)) {
                    if (square.charAt(0) == startSquare.charAt(0)) {
                        sameFile = true;
                    }
                    if (square.charAt(1) == startSquare.charAt(1)) {
                        sameRank = true;
                    }
                }
            }
        }

        if (sameFile && sameRank) {
            return startSquare; // Fully disambiguate using both file and rank.
        } else if (sameFile) {
            return String.valueOf(startSquare.charAt(1)); // Disambiguate using rank.
        } else {
            return String.valueOf(startSquare.charAt(0)); // Disambiguate using file.
        }
    }

    /**
     * Checks if a piece on the given start square can legally move to the target square.
     *
     * @param startSquare  The square from which the piece moves.
     * @param targetSquare The target square.
     * @return True if the move is legal, false otherwise.
     */
    private boolean canMoveToTarget(String startSquare, String targetSquare) {
        // Placeholder logic: Implement your own move validation logic based on piece type.
        return true; // Assume all moves are valid for simplicity.
    }

    /**
     * Logs the move to a text file to document the game history.
     *
     * @param fileName The name of the file to log the moves.
     * @throws IOException If writing to the file fails.
     */
    public void logMove(String fileName) throws IOException {
        String notation = toAlgebraicNotation();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(notation);
            writer.newLine();
        }
    }

    @Override
    public String toString() {
        return toAlgebraicNotation();
    }
}
