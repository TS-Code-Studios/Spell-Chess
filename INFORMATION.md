# Types of classes
This is a way of quickly describing the purpose of a class.

- *Superior class*: A class which no object is ever created of. Instead, it creates objects of other classes to let them interact.
- *Database class* (*DC*): A class serving as an external storage for raw, dynamic information in the form of its attributes. It has no methods.
- *Array database class* (*ADC*): A special form of Database class for storing bundles of static information. It contains a single *ArrayList* method, returning an array with the set of values in a defined order that is given in one of the chapters below.
- *Database handler class* (*DHC*): A class creating objects of DCs. It is used by GUIs, which can use its methods to either ask for a specific type of information to be directly displayed by the GUI, such as a string containing a lot of chained variables, or for changing a dynamic set of informations, following user input, using parameters.
- *Graphical user interface class* (*GUI*): A class that creates a GUI window. It is also semi-superior, with the only thing ever creating an object of a GUI class being other superior classes.
# Class list
This is a list of every custom class of the project, along with its type and information about its purpose.

**SpellChess.java**

The ultimate superior class. Its only purpose is its *main()* function initiating and managing a new instance of the game.

**MainMenuGUI.java**

GUI class for the main menu. It's the secondmost superior class, with only *SpellChess* ever creating an object of it.

**ChessHandlerDHC.java**

DHC class that starts a game of standard chess. It's either launched from a "Play" button directly on the main menu, or (once implemented) from a "Play" button on a submenu where you can choose the settings for the game first (such as time controls).

**ChessboardGUI.java**

GUI class that creates a chessboard. It will be used for both vanilla and spell chess, with the elements that are imported being dependant on the variant that the players are playing.
# Important names
Names of the most important methods, variables, and objects.

**SpellChess**

- *main()*: The method initiating everything.

**MainMenuGUI**

- *launchMainMenu()*: A method creating a new main menu window. 
- *mainMenuFrame*: *JFrame* object acting as the main menu's window.

# ADC orders
This is how the informations are arranged in the information arrays of ADCs.

**positionMeta:**

0. White O-O
1. White O-O-O
2. Black O-O
3. Black O-O-O
4. En passant target square
5. Halfmoves
6. Check
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
