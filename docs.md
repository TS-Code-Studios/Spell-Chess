# ChessGameHandler

**Relevant Attributes:**\
**`char[][] position`** (private, getter method: `char[][] getPosition()`)\
Stores the position of the chess game being handled by this instance of the `ChessGameHandler` class. A specific square of the chessboard can be accessed using `[rank][file]`.\
Pieces are stored as a char on each square using the following scheme, with white pieces being lowercase and black pieces being uppercase:
|Piece|String|
|--------|-------|
|King|k/K|
|Queen|q/Q|
|Rook|r/R|
|Bishop|b/B|
|Knight|n/N|
|Pawn|p/P|
|*Empty square*|-|


**Relevant Methods:**\
**`void makeMove(String piece, String startSquare, String targetSquare, boolean isPromoting, char promotionPiece)`**\
Changes the `position` array based on the parameters. `startSquare` is set to a dash, `targetSquare` is set to piece, the coverage is simulated and the castling availability is updated.\
Castling is done by entering "o-o", "O-O", "O-O-O" or "o-o-o" as the `piece` parameter respectively and entering dashes for the rest of the parameters (the latter isn't required, but strongly recommended).\
Promotions can be done by calling this method with the `isPromoting` parameter set to true and the `promotionPiece` parameter set to the piece to promote to.\
This method does NOT check whether the move is possible, so make sure everything is entered properly and capitalize the pieces the right way (uppercase = black)!

**`boolean isMovePossible(@NotNull String piece, @NotNull String startSquare, @NotNull String targetSquare, boolean notSimulated)`**\
Checks if the move entered is possible in the current position. This currently accounts for everything except en passant.\
A valid input would be: `isMovePossible(p [account for capitalisation here!] e2 e4 true)`. The last parameter is only relevant for internal methods of the `chessGameHandler` and should usually be true when accessing the method from outside; setting it to `false` will allow moves that put/leave the king in check and also ignore what player is making the move. It will also skip logging the result at the end of the method.

**`boolean legalMovesLeft()`**\
Returns true if the player that is currently to move has any legal moves left. Recommended using directly after the player is switched.\
If this returns true and the player to move is in check, it's a checkmate, if this returns true but nobody is in check, it's a stalemate (see `ChessGameHandler` `main`method for example implementation.)