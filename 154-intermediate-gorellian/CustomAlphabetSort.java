import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CustomAlphabetSort implements Comparator<String> {
    private int[] letterRankings = new int[26];
    private List<String> words;

    public CustomAlphabetSort(String alphabet, List<String> words) {
        List<Character> missingLetters = new ArrayList<>();
        List<Character> duplicateLetters = new ArrayList<>();
        Arrays.fill(letterRankings, -1);

        String uppercaseAlphabet = alphabet.toUpperCase();
        for (int rank = 0; rank < uppercaseAlphabet.length(); rank++) {            
            if (letterRankings[uppercaseAlphabet.charAt(rank) - 'A'] >= 0) {
                duplicateLetters.add(alphabet.charAt(rank));
            }
            letterRankings[uppercaseAlphabet.charAt(rank) - 'A'] = rank;
        }

        for (int i = 0; i < letterRankings.length; i++) {
            if (letterRankings[i] == -1) {
                missingLetters.add((char)('A' + (char)i));
            }
        }

        if (!duplicateLetters.isEmpty()) {
            throw new IllegalArgumentException("Duplicate letters: " + duplicateLetters);
        } else if (!missingLetters.isEmpty()) {
            throw new IllegalArgumentException("Missing letters: " + missingLetters);
        }

        this.words = words;
    }

    public List<String> getSortedList() {
        Collections.sort(words, this);
        return words;
    }

    public int compare(String word1, String word2) {
        for (int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
            char c1 = Character.toUpperCase(word1.charAt(i));
            char c2 = Character.toUpperCase(word2.charAt(i));
            if (c1 != c2) {
                return letterRankings[c1 - 'A'] - letterRankings[c2 - 'A'];
            }
        }

        return word1.length() - word2.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numWords = scanner.nextInt();
        String alphabet = scanner.next();
        scanner.nextLine();

        List<String> words = new ArrayList<>();
        for (int i = 0; i < numWords; i++) {
            words.add(scanner.nextLine());
        }

        CustomAlphabetSort cas = new CustomAlphabetSort(alphabet, words);
        System.out.println(cas.getSortedList());
    }
}