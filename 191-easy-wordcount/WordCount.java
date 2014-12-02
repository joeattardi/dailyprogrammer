import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {
    public static void main(String...args) throws IOException {
        Files.lines(Paths.get(args[0]))
            .flatMap(line -> Stream.of(
                line.replaceAll("[-\\.,;!\\?]", " ")
                    .replaceAll("[\"'()\\[\\]=_\\-:]", "")
                    .split("\\s+")))
            .filter(str -> !str.isEmpty())
            .map(String::toLowerCase)
            .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum))
            .entrySet()
            .stream()
            .sorted((a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue())
            .forEach(System.out::println);
    }
}