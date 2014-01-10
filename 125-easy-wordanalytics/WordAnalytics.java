import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordAnalytics {
  private int numWords;
  private int numLetters;
  private int numSymbols;
  private Map<String, OccurrenceCount> wordOccurrence = new HashMap<>();;
  private Map<String, OccurrenceCount> letterOccurrence = new HashMap<>();

  public void analyze(String filePath) {
    try (Scanner scanner = new Scanner(new File(filePath))) {
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] words = line.split("\\s+");
        numWords += words.length;

        for (String word : words) {
          countWord(word);
        }
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    System.out.println("Words: " + numWords);
    System.out.println("Letters: " + numLetters);
    System.out.println("Symbols: " + numSymbols);
    System.out.println("Most common words: " + getMostCommon(wordOccurrence));
    System.out.println("Most common letters: " + getMostCommon(letterOccurrence));
  }

  private List<OccurrenceCount> getMostCommon(Map<String, OccurrenceCount> map) {
    List<OccurrenceCount> wordCounts = new ArrayList<>(map.values());
    Collections.sort(wordCounts);
    Collections.reverse(wordCounts);

    return wordCounts.subList(0, wordCounts.size() >= 3 ? 3 : wordCounts.size() - 1);
  }

  private void countWord(final String word) {
    countOccurrence(wordOccurrence, word);
    for (char c : word.toCharArray()) {
      countOccurrence(letterOccurrence, Character.toString(c));

      if (Character.isLetterOrDigit(c)) {
        numLetters++;
      } else {
        numSymbols++;
      }
    }
  }

  private void countOccurrence(final Map<String, OccurrenceCount> map, final String value) {
    OccurrenceCount count = map.get(value);
    if (count == null) {
      count = new OccurrenceCount(value);
      map.put(value, count);
    }

    count.increment();
  }

  private int countOccurrences(final Pattern pattern, final String line) {
    Matcher matcher = pattern.matcher(line);
    int count = 0;
    while (matcher.find()) {
      count++;
    }

    return count;
  }

  public static void main(String...args) {
    String filePath = args[0];
    WordAnalytics analytics = new WordAnalytics();
    analytics.analyze(filePath);
  }
}

class OccurrenceCount implements Comparable<OccurrenceCount> {
  private String word;
  private int count;

  public int compareTo(OccurrenceCount other) {
    return count - other.count;
  }

  public OccurrenceCount(String word) {
    this.word = word;
  }

  public void increment() {
    count++;
  }

  public int getCount() {
    return count;
  }

  public String getWord() {
    return word;
  }

  public String toString() {
    return String.format("%s (%d)", word, count);
  }
}