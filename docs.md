# ChessGameHandler

**Relevant Attributes:**\
**String[][] position** (private, getter method: String[][] getPosition())\
Stores the position of the chess game being handled by this instance of ChessGameHandler. A specific square of the chessboard can be accessed using [rank][file].\
Pieces are stored as a single-character String on each square using the following scheme, with white pieces being lowercase and black pieces being uppercase:
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
**void makeMove(String piece, String startSquare, String targetSquare):**\
Changes the *position* array based on the parameters. *startSquare* is set to a dash, *targetSquare* is set to piece, the coverage is simulated and the castling availability is updated.\
Castling is done by entering "o-o", "O-O", "O-O-O" or "o-o-o" as the *piece* parameter respectively and entering dashes for the rest of the parameters (the latter isn't required, but strongly recommended).\
This method does NOT check whether the move is possible, so make sure everything is entered properly and capitalize the pieces the right way (uppercase = black)!
