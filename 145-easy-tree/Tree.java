import java.util.Scanner;

public class Tree {
  public static void main(String...args) {
    Scanner scanner = new Scanner(System.in);
    int width = scanner.nextInt();
    String trunk = scanner.next();
    String leaf = scanner.next();

    StringBuilder builder = new StringBuilder();

    for (int n = 1; n <= width; n += 2) {
      builder.append(center(repeat(leaf, n), width)).append("\n");
    }

    builder.append(center(repeat(trunk, 3), width));

    System.out.println(builder.toString());
  }

  private static String center(String str, int width) {
    return pad(str, (width - str.length()) / 2);
  }

  private static String pad(String str, int padding) {
    return repeat(" ", padding) + str;
  }

  private static String repeat(String str, int times) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < times; i++) {
      builder.append(str);
    }
    return builder.toString();
  }
}