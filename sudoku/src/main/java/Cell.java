
import java.util.*;

/**
 * An individual cell on a Sudoku board.
 */
public class Cell {

    private Integer value;
    private List<Integer> candidates = new ArrayList<>();
    private Coordinates coordinates;

    public Cell(Coordinates coordinates, int value) {
        this(coordinates);
        checkValue(value);

        this.value = value;
    }

    public Cell(Coordinates coordinates) {
        this();
        this.coordinates = coordinates;
    }

    public Cell() {
        if (this.value == null) {
            for (int i = 1; i <= 9; i++) {
                candidates.add(i);
            }
        }
    }

    public Optional<Integer> getValue() {
        if (value == null) {
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }

    public void setValue(Integer value) {
        this.value = value;
        candidates = new ArrayList<>(); //No other candidates possible if we have a value!
    }

    public List<Integer> getCandidates() {
        if (value != null) {
            return new ArrayList<>();
        } else {
            return candidates;
        }
    }

    public void emptyCandidates() {
        candidates = new ArrayList<>();
    }

    public Coordinates getCoordinates() {
        return coordinates;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return !(value != null ? !value.equals(cell.value) : cell.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
