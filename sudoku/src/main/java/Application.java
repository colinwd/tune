import board.Board;
import board.BoardParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        try {
            List<String> rows;

            if (args.length == 1) {
                //read file from command line arg
                String path = args[0];
                rows = readBoard(path);
            } else {
                //read stdin
                rows = getInput();
            }

            if (rows.size() < 9) {
                System.err.println("Invalid board format - less than 9 rows.");
                System.exit(1);
            }

            //parse to board
            Board board = new BoardParser().parse(rows);

            //solve the puzzle
            boolean solved = new Solver().solve(board);

            if (solved) {
                System.out.println(board.toString());
            } else {
                System.err.println("Couldn't find a solution! Oh no!");
            }
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }

    protected static List<String> getInput() {
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            result.add(scanner.next());
        }

        return result;
    }

    protected static List<String> readBoard(String path) {
        if (path == null) {
            throw new IllegalArgumentException("Cannot read board from null path.");
        }

        List<String> rows = null;
        try {
            rows = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Unable to find file at path: " + path);
        }
        return rows;
    }
}
