import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class ParserTest {

    @Test
    public void parseTest() throws URISyntaxException {
        URL resource = ParserTest.class.getClassLoader().getResource("test-board.txt");
        URI uri = resource.toURI();
        List<String> boardRows = Application.readBoard(uri.getPath());

        Board board = new BoardParser().parse(boardRows);

        Assert.assertNotNull(board);
        System.out.println(board);

        //a few spot checks for board integrity
        Assert.assertEquals(1, (int) board.getCell(4, 1).getValue().get()); //cast to avoid ambiguous overload
        Assert.assertEquals(9, (int) board.getCell(5, 6).getValue().get());
    }
}
