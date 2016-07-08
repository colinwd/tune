/**
 * Representation of a Sudoku board
 */
public class Board {

    private Cell[][] board;

    public Board() {
        this.board = new Cell[9][9];

        //Have to manually init because Java
        //All cells start empty
        for (int i = 0; i < 9; i++) {
            board[i] = new Cell[9];
            for (int j = 0; j < 9; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Get a cell via one-indexed coordinates.
     * @param x the board column
     * @param y the board row
     * @return the matching {@link Cell}
     */
    public Cell getCell(int x, int y) {
        checkCoordinates(x, y);

        return board[y-1][x-1];
    }

    /**
     * Set a cell via one-indexed coordinates.
     * @param x the board column
     * @param y the board row
     * @param value the value to set in the {@link Cell}
     */
    public void setCell(int x, int y, int value) {
        checkCoordinates(x, y);

        board[y-1][x-1] = new Cell(value);
    }

    @Override
    public String toString() {
        StringBuilder bb = new StringBuilder("---------------------\n");

        for (Cell[] row : board) {
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

    private void checkCoordinates(int x, int y) {
        if (0 > x || x > 9 || 0 > y || y > 9) {
            throw new IllegalArgumentException("Cell coordinates are out of bounds [x: " + x + ", y: " + y +"]");
        }
    }
}
