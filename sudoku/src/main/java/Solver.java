import java.util.Iterator;
import java.util.List;

public class Solver {

    private final Board board;
    private boolean boardChanged;

    public Solver(Board board) {
        this.board = board;
    }

    public Board solve() {
        while (!board.isSolved()) {
            boardChanged = false;

            System.out.println(board);

            reduceCandidates();

            setSolvedCells();

            if (!boardChanged) {
                return board;
            }
        }

        return board;
    }

    private void setSolvedCells() {
        for (Cell cell : board.getCells()) {
            if (!cell.getValue().isPresent()) {
                if (cell.getCandidates().size() == 1) {
                    cell.setValue(cell.getCandidates().iterator().next());
                    boardChanged = true;
                }
            }
        }
    }

    private void reduceCandidates() {
        for (Cell cell : board.getCells()) {
            Coordinates coordinates = cell.getCoordinates();

            List<Cell> cellRow = board.getRow(coordinates.getY());
            List<Cell> cellColumn = board.getColumn(coordinates.getX());
            List<Cell> cellBox = board.getBox(coordinates);

            //Manual iterator here avoids ConcurrentModificationException
            Iterator<Integer> iterator = cell.getCandidates().iterator();
            while (iterator.hasNext()) {
                int candidate = iterator.next();

                if (candidateIsIn(candidate, cellRow) ||
                        candidateIsIn(candidate, cellColumn) ||
                        candidateIsIn(candidate, cellBox)) {
                    iterator.remove();
                }
            }
        }
    }

    private boolean candidateIsIn(int candidate, List<Cell> cells) {
        for (Cell cell : cells) {
            if (cell.getValue().isPresent() && cell.getValue().get() == candidate) {
                return true;
            }
        }

        return false;
    }
}
