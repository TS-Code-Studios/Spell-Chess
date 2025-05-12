# About spell chess
A chess variation with different spells to use during games, similar to the existing spell chess variation by chess.com and Supercell, or AniChess.

# Developers
- **Simon Korten/simonkdev:** Frontend coding and graphic design.
- **Theodor Römpp/FRBF Studios:** Backend coding and class management.
- **Aaron Filsinger/hitback16:** Miscellaneous coding and testing.

# Information
Please follow these guidelines while programming.

- Code is mostly written in Java. This is also a Maven project, so please install Maven. Ask copilot for help if necessary before messing around!
- Please add frequent comments to help other developers understand your code.
- When you want to make a major change, such as adding a new feature, please make a new branch and open a pull request to merge it into #main after you are done. It will be reviewed and merged.
- When you find a major bug, please open a new issue.
- Run mvn compile and then either run the *SpellChess.java* file or run *mvn clean package* followed by *jar -jar target/*jar file name*.jar*.
- *TODO.md* contains a To Do list for every developer.
- *INFORMATION.md* contains more information for developers, such as a list of classes and important objects, as well as guidelines for coding. **PLEASE READ IT!**
- *src/main/java/com/javadevs* contains all source code (sometimes in subfolders).
- *src/main/java/com/javadevs/gui/* contains all GUI-related *.java* files. Make *package com.javadevs.gui* the first line of code for every class in it!
- *src/main/resources/* contains all media - such as textures - for the code to use.

# Roadmap
Cursive things are already done.

**Step 1: Planning (everyone)**
- *Fill out .md files*
- *Model basic classes*
- *Convert to Maven project*
- *Find ways of doing the basics*
- *Create basic files and structures*
- *Assign tasks to developers*

**Step 2: Develop vanilla chess**
- Create a board (Simon)
- *Create pieces (Theodor)*
- Implement rules and turns (Theodor)
- Implement notation (Aaron)
- Optional: Develop an engine

**Step 3: Add a Main Menu (Simon)**

**Step 4: Develop spell chess**
- Come up with rules and spells (Aaron, Simon)
- Implement spell- and energy system (Theodor)
- Implement rules and spells (everyone)

**Step 5: Compile into .exe or an app**

# Branches
- **master**: The default branch only containing fully developed code.
- **vanilla-chess-development**: Branch for Theodor and Aaron to work on vanilla chess.




#### ------------------------------ [ CHESS GUI README STARTS HERE ] ---------------------------
FIRST OF ALL: NO BS IN MY GUI!!!!!!!!!
second of all: welcome to simonkdevs piece of work
any hints and suggested improvements are to be directed to elon.musk@gmail.com so I can ignore them thx

**Disclaimer:** I migrated from Arch to nixOS recently so I might switch IDEs and notations from time to time

## ----- [ explanation of components ] ---------
Chessboard.java (based on JPanel)
- contains all 64 chessButtons, which have the attribute "name". This String contains their X and Y position in the Chessboard

y
1|                                      
2|                       x
3|
4|
5|
6|
7|
8|
   -- -- -- -- -- -- -- --
    a  b  c  d  e  f  g  h  x

example: buttonh2

- HINT: the position[][] array uses format[y][x], so in constructor, the args from chessButton(X, Y), are converted to Y, X. 

example: we call chessButton("h","2") to create the button for fiel h2. During call, the name is generated ("buttonh2"), and the posX and posY attributes are set to the different one. (posY contains the function argument X etc.). This is done so we can later call the array using position[posX][posY]

ChessBoardGUI.java (based on JFrame)
- contains the Board Panel as well as other UI elements, such as quit or emoji buttons

MainMenuGUI.java (based on JFrame)
- contains elements needed to load a game, as well as friends etc.


