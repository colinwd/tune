import java.util.*;
import java.util.stream.Collectors;

/**
 * Representation of a Sudoku board
 */
public class Board {

    private List<List<Cell>> board;
    private boolean isSolved = false;

    public Board() {
        this.board = new ArrayList<>(9);

        //Have to manually init because Java
        //All cells start empty but initialized
        for (int i = 0; i < 9; i++) {
            List<Cell> row = new ArrayList<>(9);
            board.add(row);
            for (int j = 0; j < 9; j++) {
                row.add(new Cell(new Coordinates(j, i)));
            }
        }
    }

    /**
     * Get a cell via zero-indexed coordinates.
     * @param c the cell coordinates
     * @return the matching {@link Cell}
     */
    public Cell getCell(Coordinates c) {
        return board.get(c.getY()).get(c.getX());
    }

    /**
     * Set a cell via zero-indexed coordinates.
     * @param c the cell coordinates
     * @param value the value to set in the {@link Cell}
     */
    public void setCell(Coordinates c, int value) {
        board.get(c.getY()).set(c.getX(), new Cell(c, value));
    }

    public List<Cell> getCells() {
        return board.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<Cell> getRow(int y) {
        return board.get(y);
    }

    public List<Cell> getColumn(int x) {
        List<Cell> result = new ArrayList<>();
        for (List<Cell> rows : board) {
            result.add(rows.get(x));
        }

        return result;
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
        for (int i = xMin; i <= xMax; i++) {
            List<Cell> row = board.get(i);
            for (int j = yMin; j <= yMax; j++) {
                results.add(row.get(j));
            }
        }

        return results;
    }

    /**
     * Check if the board is solved
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

    @Override
    public String toString() {
        StringBuilder bb = new StringBuilder("---------------------\n");

        for (List<Cell> row : board) {
            StringBuilder rb = new StringBuilder("| ");

            for (Cell cell : row) {
                rb.append(cell.getValue().map(Object::toString).orElse("_"));
                rb.append(" ");
            }

            rb.append("|\n");

            bb.append(rb);
        }

        bb.append("---------------------");

        return bb.toString();
    }
}
