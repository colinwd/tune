import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

public class Solver {

    private Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public Board solve() {
        Stack<Board> stack = new Stack<>();
        stack.push(board);

        int candidate = 0;
        while (!stack.isEmpty()) {
            if (stack.peek().isSolved()) {
                return stack.pop();
            }

            try {
                Board next = iterate(stack.peek(), candidate);
                stack.push(next);
                candidate++;
            } catch (BadBoardException e) {
                stack.pop();
                candidate = 0;
            }
        }

        throw new RuntimeException("wtf");
    }

    private Board iterate(Board board, int candidate) {
        Board result = board.copy();

        result = reduceCandidates(result);

        //get cell with fewest candidates remaining over 1
        Optional<Cell> cell = result.getCells().stream()
                .filter(c -> c.getCandidates().size() > 1)
                .sorted((c1, c2) -> Integer.compare(c1.getCandidates().size(), c2.getCandidates().size())).findFirst();

        cell.ifPresent(c -> {
            if (c.getCandidates().size() > candidate) {
                c.setValue(c.getCandidates().get(candidate));
                c.emptyCandidates();
            }
        });

        System.out.println(result);

        return result;
    }

    private Board reduceCandidates(Board board) {
        for (Cell cell : board.getCells().stream().filter(c -> !c.getValue().isPresent()).collect(Collectors.toList())) {
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

            if (cell.getCandidates().size() == 0) {
                throw new BadBoardException("Got stuck!");
            }

            if (cell.getCandidates().size() == 1) {
                cell.setValue(cell.getCandidates().get(0));
                cell.emptyCandidates();
            }
        }

        return board;
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
