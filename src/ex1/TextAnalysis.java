package ex1;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.Comparator.comparingInt;

public class TextAnalysis {
    private static Stream<String> getWords(String text) {
        return Stream.of(text.replaceAll("[^a-zA-Z0-9ąćęłńóśźż\\s]", "").split("\\s+"));
    }

    protected static long getWordCount(String text) {
        return getWords(text).count();
    }

    protected static long getUniqueWordCount(String text) {
        return getWords(text).distinct().count();
    }

    protected static double getAverageWordLength(String text) {
        return getWords(text).mapToInt(String::length).average().orElse(0);
    }

    protected static String getLongestWord(String text) {
        return getWords(text).max(comparingInt(String::length)).orElse("");
    }

    protected static Map<String, Long> getWordFrequency(String text) {
        return getWords(text).collect(groupingBy(String::toString, counting()));
    }

    protected static String getMostFrequentWord(String text) {
        return getWordFrequency(text).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");
    }

}
