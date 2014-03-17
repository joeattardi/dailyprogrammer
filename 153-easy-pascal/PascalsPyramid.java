import java.math.BigInteger;

public class PascalsPyramid {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        String[][] matrix = new String[size + 1][size + 1];

        int digits = 2;

        String resultStr = BigInteger.valueOf((long) (Math.pow(10, digits * (size + 1)) + Math.pow(10, digits) + 1))
            .pow(size)
            .toString();

        int index = 1;
        matrix[0][0] = resultStr.substring(0, 1);
        for (int z = 1; z < matrix.length; z++) {
            for (int y = 0; y < matrix.length; y++) {
                matrix[z][y] = resultStr.substring(index, index + 2);
                index += digits;
            }
        }

        System.out.println(matrix[0][0]);
        for (int row = 1; row < matrix.length; row++) {
            for (int col = matrix.length - 1 - row; col < matrix.length; col++) {
                System.out.print(Integer.parseInt(matrix[row][col]) + " ");
            }
            System.out.println();
        }
    }	
}