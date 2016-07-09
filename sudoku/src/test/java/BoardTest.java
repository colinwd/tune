import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BoardTest {

    @Test
    public void getAndSetCell() {
        Board board = new Board();
        Integer value = 3;
        board.setCell(new Coordinates(1, 4), value);

        System.out.println(board);

        Cell retrieved = board.getCell(new Coordinates(1, 4));
        Assert.assertEquals(value, retrieved.getValue().get());
    }

    @Test
    public void getBox() {
        Board board = TestUtils.readBoard("easy-test-board-solved.txt");
        List<Cell> cells = board.getBox(new Coordinates(4, 4));

        Assert.assertEquals(cells.get(0).getValue().get(), (Integer) 5);
        Assert.assertEquals(cells.get(8).getValue().get(), (Integer) 8);
    }

    @Test
    public void getRow() {
        Board board = TestUtils.readBoard("easy-test-board-solved.txt");
        List<Cell> cells = board.getRow(4);

        Assert.assertEquals(cells.get(0).getValue().get(), (Integer) 9);
        Assert.assertEquals(cells.get(8).getValue().get(), (Integer) 3);
    }

    @Test
    public void getColumn() {
        Board board = TestUtils.readBoard("easy-test-board-solved.txt");
        List<Cell> cells = board.getColumn(4);

        Assert.assertEquals(cells.get(0).getValue().get(), (Integer) 4);
        Assert.assertEquals(cells.get(8).getValue().get(), (Integer) 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badCoordinates() {
        Board board = new Board();
        Integer value = 3;
        board.setCell(new Coordinates(10, 10), value);
    }
}
