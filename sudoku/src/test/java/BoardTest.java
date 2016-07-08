import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void getAndSetCell() {
        Board board = new Board();
        Integer value = 3;
        board.setCell(1, 4, value);

        System.out.println(board);

        Cell retrieved = board.getCell(1, 4);
        Assert.assertEquals(value, retrieved.getValue().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void badCoordinates() {
        Board board = new Board();
        Integer value = 3;
        board.setCell(10, 10, value);
    }
}
