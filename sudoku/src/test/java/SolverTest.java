import board.Board;
import org.junit.Assert;
import org.junit.Test;

public class SolverTest {

    @Test
    public void testBoardSolution() {
        Board board = TestUtils.readBoard("easy-test-board.txt");
        Board solution = TestUtils.readBoard("easy-test-board-solved.txt");

        new Solver().solve(board);

        Assert.assertEquals(solution.toString(), board.toString());
    }

    @Test
    public void testHarderBoard() {
        Board board = TestUtils.readBoard("hard-test-board.txt");
        Board solution = TestUtils.readBoard("hard-test-board-solved.txt");

        new Solver().solve(board);

        Assert.assertEquals(solution.toString(), board.toString());
    }
}
