import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelephoneWords {
    public static final String[] LETTERS = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String...args) {
        StringBuilder wordBuilder = new StringBuilder();
        for (String digits : args) {
            int digit = Integer.parseInt(digits.substring(0, 1));
            wordBuilder.append(LETTERS[digit - 2].charAt(digits.length() - 1));
        }
        String word = wordBuilder.toString();

        try (BufferedReader reader = new BufferedReader(new FileReader("wordlist.txt"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(word)) {
                    System.out.println(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}