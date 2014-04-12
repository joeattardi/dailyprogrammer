import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char nextMove = scanner.nextLine().charAt(0);

        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < line.length(); j++) {
                board[i][j] = line.charAt(j);
            }
        }    

        findWinningMoves(board, nextMove);
    }

    private static void findWinningMoves(char[][] board, char nextMove) {
        int winners = 0;

        int[][] coordSet = {{0, 0, 0, 1, 0, 2}, {1, 0, 1, 1, 1, 2}, {2, 0, 2, 1, 2, 2}, {0, 0, 1, 0, 2, 0}, 
            {0, 1, 1, 1, 2, 1}, {0, 2, 1, 2, 2, 2}, {0, 0, 1, 1, 2, 2}, {0, 2, 1, 1, 2, 0}};

        for (int[] coords : coordSet) {
            char[] line = { board[coords[0]][coords[1]], board[coords[2]][coords[3]], board[coords[4]][coords[5]] };
            if (checkMove(line, nextMove)) {
                char[][] result = copyBoard(board);
                result[coords[0]][coords[1]] = nextMove;
                result[coords[2]][coords[3]] = nextMove;
                result[coords[4]][coords[5]] = nextMove;
                printBoard(result);
                winners++;
            }
        }

        if (winners == 0) {
            System.out.println("No winning moves.");
        } else {
            System.out.println(winners + " winning move" + (winners > 1 ? "s" : "") + ".");
        }
    }


    private static boolean checkMove(char[] line, char nextMove) {
        char[] winningMove = { '-', nextMove, nextMove };
        char[] sortedLine = new char[3];
        System.arraycopy(line, 0, sortedLine, 0, 3);
        Arrays.sort(sortedLine);
        return Arrays.equals(winningMove, sortedLine);
    }

    private static char[][] copyBoard(char[][] board) {
        char[][] copy = new char[3][3];
        System.arraycopy(board, 0, copy, 0, 3);
        return copy;
    }

    private static void printBoard(char[][] board) {
        System.out.println();
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

}