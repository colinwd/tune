import org.junit.Assert;
import org.junit.Test;

public class SolverTest {

    @Test
    public void testBoardSolution() {
        Board board = TestUtils.readBoard("test-board.txt");
        Board solution = TestUtils.readBoard("test-board-solved.txt");

        Board solved = new Solver(board).solve();

        Assert.assertEquals(solution, solved);
    }
}
