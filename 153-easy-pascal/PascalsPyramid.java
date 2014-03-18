public class PascalsPyramid {
    public static void main(String[] args) {
        int level = Integer.parseInt(args[0]) - 1;

        for (int row = 0; row <= level; row++) {
            for (int col = 0; col <= row; col++) {
                System.out.print(getDigit(row - col, level - row, col) + " ");
            }
            System.out.println();
        }
    }	

    public static int getDigit(int a, int b, int c) {
        return factorial(a + b + c) / (factorial(a)*factorial(b)*factorial(c));
    }

    public static int factorial(int n) {
        return (n == 0) ? 1 : factorial(n - 1) * n;
    }
}