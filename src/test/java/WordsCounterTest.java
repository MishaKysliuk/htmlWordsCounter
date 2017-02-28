import org.htmlwordscounter.helpers.ConsoleHelper;
import org.htmlwordscounter.model.WordsCounter;
import org.htmlwordscounter.model.parser.Parser;
import org.htmlwordscounter.model.readers.ContentReader;
import org.htmlwordscounter.model.readers.FileContentReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by MichaelMAC on 27.02.17.
 */
public class WordsCounterTest {


    @Test
    public void test() throws IOException {
        ContentReader contentReader = new FileContentReader();
        contentReader.setPath("src/test/java/resources/test.html");
        WordsCounter wordsCounter = new WordsCounter();
        wordsCounter.execute(contentReader);
        ConsoleHelper.printStatistics(wordsCounter.getWordsCount());

        assertEquals(wordsCounter.getWordsCount().size(), 49);
        assertEquals(wordsCounter.getWordsCount().get("the"), new Integer(5));
        assertEquals(wordsCounter.getWordsCount().get("any"), new Integer(1));
        assertEquals(wordsCounter.getWordsCount().get("content"), new Integer(2));
        assertEquals(wordsCounter.getWordsCount().get("about"), new Integer(3));
    }
}
