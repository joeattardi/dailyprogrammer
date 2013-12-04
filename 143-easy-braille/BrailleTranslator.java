import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrailleTranslator {
  public static final String ALPHABET =
    "i s jwt a kue ozb lvh r c mxd nyf p g q";

    public String translate(final List<String> input) {
      String[] letters = new String[input.get(0).split(" ").length];
      Arrays.fill(letters, "");

      for (String line : input) {
        int i = 0;
        for (String s : line.split(" ")) {
          letters[i++] += s;
        }
      }

      String translation = "";
      for (String letter : letters) {
        translation += ALPHABET.charAt(
          Integer.parseInt(letter.replaceAll("O", "1").replaceAll("\\.", "0"), 2) - 24);
      }
      return translation;
    }

    public static void main(String...args) throws IOException {
      Path inputFile = Paths.get(args[0]);
      List<String> input = Files.readAllLines(inputFile, Charset.defaultCharset());
      System.out.println(new BrailleTranslator().translate(input));
    }
}