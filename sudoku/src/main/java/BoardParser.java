import java.util.List;

public class BoardParser {
    public Board parse(List<String> rows) {
        if (rows.size() != 9) {
            throw new IllegalArgumentException("Board input has more than 9 rows - invalid board.");
        }

        Board result = new Board();

        int rowIndex = 1;
        for (String row : rows) {
            String[] cells = row.split(",");
            int cellIndex = 1;
            for (String cell : cells) {
                try {
                    int i = Integer.parseInt(cell);
                    result.setCell(cellIndex, rowIndex, i);
                } catch (NumberFormatException e) {
                    //Non-integer means we don't need to set the cell, so do nothing
                }

                cellIndex++;
            }
            rowIndex++;
        }

        return result;
    }
}
