package org.htmlwordscounter.helpers;

import java.util.Map;

/**
 * Created by MichaelMAC on 28.02.17.
 */
public class ConsoleHelper {

    public static void printStatistics(Map<String,Integer> wordsCount){
        System.out.println("Количество слов = " + wordsCount.size());
        for(Map.Entry<String, Integer> entry : wordsCount.entrySet()){
            System.out.println(String.format("%25s %3d", entry.getKey(), entry.getValue()));
        }
    }
}
