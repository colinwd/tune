import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class CellTest {

    @Test(expected = IllegalArgumentException.class)
    public void valueTooSmallTest() {
        Cell cell = new Cell(new Coordinates(0, 0), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void valueTooLargeTest() {
        Cell cell = new Cell(new Coordinates(0, 0), 10);
    }

    @Test
    public void okayValueIsSet() {
        Integer value = 8;
        Cell cell = new Cell(new Coordinates(0, 0), value);
        Optional<Integer> retrieved = cell.getValue();

        Assert.assertEquals(value, retrieved.get());
    }
}
