import java.util.Scanner;

public class Checksum {
  public static void main(String...args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.nextLine();

    String[] input = new String[n];
    StringBuilder output = new StringBuilder();

    for (int i = 0; i < n; i++) {
      input[i] = scanner.nextLine();
      output.append(String.format("%d %X\n", i + 1, calcChecksum(input[i])));
    }

    System.out.println(output.toString());
  }

  private static int calcChecksum(String input) {
    int sum1 = 0;
    int sum2 = 0;

    for (byte b : input.getBytes()) {
      sum1 = (sum1 + b) % 255;
      sum2 = (sum2 + sum1) % 255;
    }

    return (sum1 << 8) + sum2;
  }
}