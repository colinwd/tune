import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        //read stdin
        String boardPath = getInput();
        List<String> rows = readBoard(boardPath);

        //parse to board
        Board board = new BoardParser().parse(rows);

        //solve the puzzle
        Board solved = new Solver(board).solve();

        System.out.println(solved.toString());
    }

    protected static String getInput() {
        return new Scanner(System.in).nextLine();
    }

    protected static List<String> readBoard(String path) {
        if (path == null) {
            throw new IllegalArgumentException("Cannot read board from null path.");
        }

        List<String> rows = null;
        try {
            rows = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Unable to find file at path: " + path);
        }
        return rows;
    }
}
