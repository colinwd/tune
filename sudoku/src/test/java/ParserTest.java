import board.Board;
import cell.Coordinates;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;

public class ParserTest {

    @Test
    public void parseTest() throws URISyntaxException {
        Board board = TestUtils.readBoard("easy-test-board.txt");

        Assert.assertNotNull(board);
        System.out.println(board);

        //a few spot checks for board integrity
        Assert.assertEquals(1, (int) board.getCell(new Coordinates(3, 0)).getValue().get()); //cast to avoid ambiguous overload
        Assert.assertEquals(9, (int) board.getCell(new Coordinates(4, 5)).getValue().get());
    }
}
