package board;

import cell.Cell;
import cell.Coordinates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representation of a Sudoku board
 */
public class Board {

    private List<List<Cell>> board;
    private boolean isSolved = false;
    private boolean failed = false;

    public Board() {
        this.board = new ArrayList<>(9);

        //All cells start empty but initialized
        for (int i = 0; i < 9; i++) {
            List<Cell> row = new ArrayList<>(9);
            board.add(row);
            for (int j = 0; j < 9; j++) {
                row.add(new Cell(new Coordinates(j, i)));
            }
        }
    }

    public Board copy() {
        Board board = new Board();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinates c = new Coordinates(j, i);
                if (this.getCell(c).getValue().isPresent()) {
                    Cell cell = this.getCell(c);
                    board.setCell(new Cell(c, cell.getValue().get()));
                } else {
                    board.setCell(new Cell(c));
                }
            }
        }

        return board;
    }

    /**
     * Get a cell via zero-indexed coordinates.
     *
     * @param c the cell coordinates
     * @return the matching {@link Cell}
     */
    public Cell getCell(Coordinates c) {
        return board.get(c.getY()).get(c.getX());
    }

    /**
     * Set a cell on the board.
     *
     * @param cell     the cell to set
     */
    public void setCell(Cell cell) {
        board.get(cell.getCoordinates().getY()).set(cell.getCoordinates().getX(), cell);
    }

    public List<Cell> getCells() {
        return board.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<Cell> getRow(int y) {
        return board.get(y);
    }

    public List<Cell> getColumn(int x) {
        return board.stream().map(rows -> rows.get(x)).collect(Collectors.toList());
    }

    public List<Cell> getBox(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        //integer math gives us an implicit Math.floor() here
        int xMin = (x / 3) * 3;
        int xMax = xMin + 2;
        int yMin = (y / 3) * 3;
        int yMax = yMin + 2;

        List<Cell> results = new ArrayList<>();
        for (int i = yMin; i <= yMax; i++) {
            List<Cell> row = board.get(i);
            for (int j = xMin; j <= xMax; j++) {
                results.add(row.get(j));
            }
        }

        return results;
    }

    /**
     * Check if the board is solved
     *
     * @return true if every cell has a value set, otherwise false
     */
    public boolean isSolved() {
        //memoize if solved to short-circuit this board traversal
        if (isSolved) {
            return isSolved;
        } else {
            for (Cell cell : getCells()) {
                if (!cell.getValue().isPresent()) {
                    return false;
                }
            }
        }

        isSolved = true;
        return isSolved;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }



    @Override
    public String toString() {
        StringBuilder bb = new StringBuilder();

        for (List<Cell> row : board) {
            StringBuilder rb = new StringBuilder();

            for (Cell cell : row) {
                rb.append(cell.getValue().map(Object::toString).orElse("-"));
                rb.append(",");
            }

            rb.deleteCharAt(rb.lastIndexOf(","));

            bb.append(rb);
            bb.append("\n");
        }

        return bb.toString();
    }
}
