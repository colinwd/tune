package cell;

import java.util.Optional;

/**
 * An individual cell on a Sudoku board.
 */
public class Cell {

    private Integer value;
    private Coordinates coordinates;

    public Cell(Coordinates coordinates, int value) {
        this(coordinates);

        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("Value must be between 1 and 9.");
        }

        this.value = value;
    }

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Optional<Integer> getValue() {
        if (value == null) {
            return Optional.empty();
        } else {
            return Optional.of(value);
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "[cell.Cell: " + value + "]";
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
