import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmartTelephoneWords {
    public static final String[] LETTERS = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String...args) {
        List<String> segments = new ArrayList<>();
        for (int n = 0; n < args[0].length(); n++) {
            int digit = Integer.parseInt(Character.toString(args[0].charAt(n)));
            segments.add(LETTERS[digit - 2]);
        }
        
        List<String> candidateWords = buildCandidateWords(segments);
        int index = candidateWords.get(0).length();

        try (BufferedReader reader = new BufferedReader(new FileReader("wordlist.txt"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= index && candidateWords.contains(line.substring(0, index))) {
                    System.out.println(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static List<String> buildCandidateWords(List<String> segments) {
        return buildCandidateWords(segments.get(0), segments.subList(1, segments.size()));
    }

    private static List<String> buildCandidateWords(String head, List<String> tail) {
        if (tail.size() == 0) {
            return Arrays.asList(head.split("")).subList(1, head.length());
        } else {
            List<String> results = buildCandidateWords(tail.get(0), tail.subList(1, tail.size()));
            List<String> words = new ArrayList<>();
            for (int i = 0; i < head.length(); i++) {
                for (String result : results) {
                    words.add(head.charAt(i) + result);
                }
            }

            return words;
        }
    }
}
