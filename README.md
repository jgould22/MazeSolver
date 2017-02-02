# MazeSolver
A simple java program to implement a Graph ADT and solve a maze

This program was completed as part of CS 2210 Introduction to algorithsm, Board.java and DrawLab.java were prvided as part of the assignment while I wrote the rest

### Compile 

To compile use "javac *.java" in the source directory

### Solve Maze

To solve a maze run java Solve *mazeFile.txt*

### Maze File Format

Two examples are provided sampleLab1.txt nd sampleLab2.txt

They are formatted as follows
* Line 1 - Scale, for drawing the Maze
* Line 2 -Maze Width
* Line 3 - Maze Length
* Line 4 - Number of maze wall the algorithm can break
* Line 5+- The maze
  * "o" are nodes 
  * "-" and "|" are connectors
  * "h" and "v" are horizontial and vertical walls
  * "s" and "e" are start and exit
  
### Test Graph ADT
To test the graph run "java Test" fromt the src folder
