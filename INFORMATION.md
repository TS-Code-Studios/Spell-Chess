# Types of classes
This is a way of quickly describing the purpose of a class.

- *Superior class*: A class which no object is ever created of. Instead, it creates objects of other classes to let them interact.
- *Array database class* (*ADC*): A class serving as an external storage for raw, dynamic information using arrays. It has no methods on its own.
- *Handler Class*
- *Graphical user interface class* (*GUI*): A class that creates a GUI window. It is also semi-superior, with the only thing ever creating an object of a GUI class being other superior classes.
# Class list
This is a list of every custom class of the project, along with its type and information about its purpose.

**SpellChess.java**

The ultimate superior class. Its only purpose is its *main()* function initiating and managing a new instance of the game.

**MainMenuGUI.java**

GUI class for the main menu. It's the secondmost superior class, with only *SpellChess* ever creating an object of it.

**ChessGameHandler.java**

"Toolbox" with pretty much every method required for a vanilla chess game. These can be used by the ChessboardGUI class.

**ChessboardGUI.java**

GUI class that creates a chessboard. It will be used for both vanilla and spell chess, with the elements that are imported being dependant on the variant that the players are playing.
# Important names
Names of the most important methods, variables, and objects.

**SpellChess**

- *static void main(String[] args)*: The method initiating everything.

**MainMenuGUI**

- *static void launchMainMenu()*: A method creating a new main menu window. 
- *JFrame mainMenuFrame*: *JFrame* object acting as the main menu's window.

**ChessGameHandler.java**

- *boolean isMovePossible(String piece, String startSquare, String targetSquare, String playerToMove)*: Method that checks whether or not a move is possible and returns it as a boolean. *piece* needs to be lowercase if white and uppercase if black. *playerToMove* needs to be "w" or "B".
- *String[][] position*: 2D array storing the current chess position.
- *int[] positionMeta*: Array storing additional position info. More information can be found in the ADC Orders chapter below.

# ADC orders
This is how the informations are arranged in the information arrays of ADCs.

**int[] positionMeta:**

0. White o-o
1. White o-o-o
2. Black O-O
3. Black O-O-O
4. Halfmoves counter for 50-move-rule
5. Check
# Naming schemes
These are the guidelines for naming **ANYTHING**, ranging from variables to classes.
Please always follow these for consistency and a smooth workflow.

- Names must be descriptive (e.g. "points" for a point stat). Boolean variables must have "is" as a prefix.
- For objects, the name must have the object's class as a suffix (e.g. "pointsPanel"). This excludes objects of our custom classes.
- For classes, the first letter of the name must be capitalized and have the acronym of its respective class type at the end. This excludes superior classes.
- Variables must have the their type (abbreviate if possible) as a suffix (e.g. "pointCounterInt", "statArray" or "isGameOverBool").
- Methods must have different prefixes describing their purpose (e.g. "get" for a getter, "set" for a setter or "launch" for initiating a new part of the program, such as opening a GUI window.
- If a method should be autmatically executed upon running a file, it must be called *public static void main (String[] args)*.
- Names can (obviously) not be ambiguous within the same class.
