# ChessGameHandler

**void makeMove(String piece, String startSquare, String targetSquare):**
Changes the *position* array based on the parameters. *startSquare* is set to a dash, *targetSquare* is set to piece, the coverage is simulated and the castling availability is updated.
Castling is done by entering "o-o", "O-O", "O-O-O" or "o-o-o" as the *piece* parameter respectively and entering dashes for the rest of the parameters (the ladder isn't required, but strongly recommended).
This method does NOT check whether the move is possible, so make sure everything is entered properly and capitalize the pieces the right way (uppercase = black)!
