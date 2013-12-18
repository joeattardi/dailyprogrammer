import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class AdjacencyMatrix {
  public static void main(String...args) {
    Scanner scanner = new Scanner(System.in);
    int numVertices = scanner.nextInt();
    int numLines = scanner.nextInt();
    scanner.nextLine();

    int[][] matrix = new int[numVertices][numVertices];

    for (int edge = 0; edge < numLines; edge++) {
      String transition = scanner.nextLine();
      Matcher m = Pattern.compile("((?:\\d+ )+)-> ((?:\\d+ ?)+)").matcher(transition);
      if (m.matches()) {
        for (String s : m.group(1).split(" ")) {
          for (String v : m.group(2).split(" ")) {
            matrix[Integer.parseInt(s)][Integer.parseInt(v)] = 1;
          }
        }
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j]);
      }
      System.out.println();
    }
  }
}