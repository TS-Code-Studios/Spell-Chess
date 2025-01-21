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
- Create pieces (Theodor)
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
