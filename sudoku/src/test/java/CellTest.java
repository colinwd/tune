import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class CellTest {

    @Test(expected = IllegalArgumentException.class)
    public void valueTooSmallTest() {
        Cell cell = new Cell(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void valueTooLargeTest() {
        Cell cell = new Cell(10);
    }

    @Test
    public void okayValueIsSet() {
        Integer value = 8;
        Cell cell = new Cell(value);
        Optional<Integer> retrieved = cell.getValue();

        Assert.assertEquals(value, retrieved.get());
    }
}
