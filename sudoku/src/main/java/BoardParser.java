import java.util.List;

public class BoardParser {
    public Board parse(List<String> rows) {
        if (rows.size() != 9) {
            throw new IllegalArgumentException("Board input does not have 9 rows - invalid board.");
        }

        Board result = new Board();

        for (int row = 0; row < 9; row++) {
            String[] cellValues = rows.get(row).split(",");

            if (cellValues.length != 9) {
                throw new IllegalArgumentException("Row does not have 9 columns - invalid board.");
            }

            for (int cell = 0; cell < 9; cell++) {
                try {
                    int i = Integer.parseInt(cellValues[cell]);
                    result.setCell(new Coordinates(cell, row), i);
                } catch (NumberFormatException e) {
                    //Non-integer means we don't need to set the cell, so do nothing
                }
            }
        }

        return result;
    }
}
