import board.Board;
import cell.Cell;
import cell.Coordinates;

import java.util.List;

public class Solver {

    public boolean solve(Board board) {
        return solve(board, 0, 0);
    }

    public boolean solve(Board board, int x, int y) {
        if (x == 9) {
            x = 0; //end of row, reset
            if (y == 8) {
                //hit the end of the board
                return true;
            }

            y++; //moving to next row
        }

        //If the cell is already populated, move to the next one
        Coordinates coordinates = new Coordinates(x, y);
        if (board.getCell(coordinates).getValue().isPresent()) {
            return solve(board, x + 1, y);
        }

        //If it's a legal move, set it and move on
        for (int i = 1; i <= 9; i++) {
            if (canPlace(new Cell(coordinates, i), board)) {
                board.setCell(new Cell(coordinates, i));
                if (solve(board, x + 1, y)) {
                    return true;
                }
            }
        }

        //Otherwise, we empty that cell out again and rewind the stack
        board.setCell(new Cell(coordinates));
        return false;
    }

    /**
     * Check a cell's neighbors (row, column, and box-mates) to see if we would be creating a duplicate
     * @param cell
     * @param board
     * @return
     */
    private boolean canPlace(Cell cell, Board board) {
        Coordinates coordinates = cell.getCoordinates();

        List<Cell> cellRow = board.getRow(coordinates.getY());
        List<Cell> cellColumn = board.getColumn(coordinates.getX());
        List<Cell> cellBox = board.getBox(coordinates);

        if (cellRow.contains(cell) || cellColumn.contains(cell) || cellBox.contains(cell)) {
            return false;
        } else {
            return true;
        }
    }
}
