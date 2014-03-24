import java.util.Scanner;
import java.util.Stack;

public class Brackets {
  public static final String OPEN_BRACKETS = "[{(";
  public static final String CLOSE_BRACKETS = "]})";

  public static void main(String[] args) {
    String input = new Scanner(System.in).nextLine();
    Stack<StringBuilder> stack = new Stack<>();
    Stack<Character> bracketStack = new Stack<>();
    StringBuilder output = new StringBuilder();
    String error = null;

    for (int i = 0; i < input.length() && error == null; i++) {
      char ch = input.charAt(i);
      if (OPEN_BRACKETS.indexOf(ch) >= 0) {
        stack.push(new StringBuilder());
        bracketStack.push(ch);
      } else if (CLOSE_BRACKETS.indexOf(ch) >= 0) {
        output.append(stack.pop().append(" ").toString());
        char openingBracket = bracketStack.pop();
        if (OPEN_BRACKETS.indexOf(openingBracket) != CLOSE_BRACKETS.indexOf(ch)) {
          error = "Mismatched bracket: " + ch + " instead of " + CLOSE_BRACKETS.charAt(OPEN_BRACKETS.indexOf(openingBracket));
        }
      } else {
        stack.peek().append(ch);
      }
    }

    if (!bracketStack.isEmpty()) {
      error = "Missing closing bracket";
    }

    System.out.println(error != null ? error : output.toString().replaceAll("\\s+", " "));
  }
}