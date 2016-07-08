import java.util.Optional;

/**
 * An individual cell on a Sudoku board.
 */
public class Cell {

    private Integer value;

    public Cell(int value) {
        checkValue(value);

        this.value = value;
    }

    public Cell() {
    }

    public Optional<Integer> getValue() {
        if (value == null) {
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }

    private void checkValue(int value) {
        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("Value must be between 1 and 9.");
        }
    }

    @Override
    public String toString() {
        return "[Cell: " + value + "]";
    }
}
