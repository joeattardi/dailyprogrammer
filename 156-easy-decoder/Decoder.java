import java.util.stream.IntStream;

public class Decoder {
    public static void main(String...args) {
        IntStream.of(0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x20, 0x57, 0x6f, 0x72, 0x6c, 0x64, 0x21, 0x21, 0x21, 0xa)
            .mapToObj(c -> Character.toString((char) c))
            .forEach(System.out::print);
    }
}