import java.util.Scanner;

public class ScoreChecker {

    public static void main(String...args) {
      System.out.print("Enter score: ");
      int score = new Scanner(System.in).nextInt();
      System.out.println(validateScore(score) ? "Valid score" : "Invalid score");

    }

    private static boolean validateScore(int score) {
      int touchdowns = score / 6;
      score = score % 6;
      int fieldGoals = score / 3;
      score = score % 3;

      return (score > 0 && touchdowns > 0) || (score == 0);
    }

}