package org.htmlwordscounter;

import org.htmlwordscounter.exceptions.NoClosingTagException;
import org.htmlwordscounter.helpers.ConsoleHelper;
import org.htmlwordscounter.helpers.Validate;
import org.htmlwordscounter.model.WordsCounter;
import org.htmlwordscounter.model.readers.ContentReader;
import org.htmlwordscounter.model.readers.URLContentReader;

import java.io.IOException;

/**
 * Created by MichaelMAC on 27.02.17.
 */
public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Не указан URL.");
            return;
        }
        String url = args[0];
        WordsCounter wordsCounter = new WordsCounter();

        try {
            Validate.matchesPattern(url, Validate.URL_PATTERN);
            ContentReader contentReader = new URLContentReader();
            contentReader.setPath(url);
            wordsCounter.execute(contentReader);
        }catch (IOException e){
            System.out.println("Системная ошибка. Повторите попытку.");
            return;
        }catch (NullPointerException | NoClosingTagException e){
            System.out.println("Данный URL – " + url + "- не содержит HTML содержания.");
            return;
        }catch (IllegalArgumentException e){
            System.out.println("Вы ввели неправильный URL.");
            return;
        }
        ConsoleHelper.printStatistics(wordsCounter.getWordsCount());
    }
}
