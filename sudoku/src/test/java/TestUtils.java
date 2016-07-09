import org.junit.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class TestUtils {

    public static Board readBoard(String path) {
        URL resource = ParserTest.class.getClassLoader().getResource(path);

        try {
            URI uri = resource.toURI();
            List<String> boardRows = Application.readBoard(uri.getPath());
            return new BoardParser().parse(boardRows);
        } catch (URISyntaxException e) {
            Assert.fail("Unable to read board at path: " + path);
            return new Board();
        }
    }
}
