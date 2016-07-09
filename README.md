# Sudoku Solver #

This is a Java sudoku solver utilizing depth-first search to find puzzle solutions.

## Project Setup ##

### Dependencies ###

Java 8

### Compilation ###

```
cd sudoku/
./gradlew make
```

### Execution ###

The program can read on STDIN like so:
```
java -jar solve.jar < /path/to/sudoku/board
```
or take a file path as an argument:
```
java -jar solve.jar /path/to/sudoku/board
```

## The Algorithm ##

The primary method for the solution search is a recursive depth-first search through the game state. From the initial board state, we loop over each empty cell in the board and try each possible candidate. As each attempt is made, we propagate those changes to the potential candidates of the other cells in the board, and recurse. If we reach an illegal board state, we back up and move to the next possible candidate.

## Complexity ##

Since this is a naive depth-first search, time complexity is O(n) where n is the number of nodes in our graph (every possible move in the sudoku board). Best case would be O(1), where the first move we attempt is the correct solution. On average, it should be somewhere between those two, but I'm not sure about a precise complexity (perhaps log n?)

## Design ##

For my initial attempt at a solution, I tried to have my program solve the puzzle as a human would. While this could be a useful approach if the goal of the program is to demonstrate solution strategies, it proved to be overly complex to model, and I scrapped it. Once I realized the game states could be modeled as a tree, depth-first search felt like a natural choice.

Once again, I attempted a more complex solution first: iterative depth-first search. However, the extra bookkeeping associated with an iterative approach proved cumbersome as well, and a recursive solution emerged.

Outside of the `Solver` class, the other code is fairly trivial. `Board` holds our collection of cells and their current state, as well as provides a few methods for introspection of board state.

## Other Considerations ##

While the naive depth-first search algorithm has proven to be rather quick for my (admittedly limited) test cases, I think some improvement could be had with path selection through the game tree to improve efficiency. Calculating potential values and using that to limit the breadth of our search could prove useful for trickier puzzles.

In addition, check for move legality also feels inefficient. If a cell's neighbors were kept track of in memory you could effectively trade a rather small amount of memory for larger potential runtime gains.