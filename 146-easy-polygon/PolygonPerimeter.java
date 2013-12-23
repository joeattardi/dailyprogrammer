import java.util.Scanner;

public class PolygonPerimeter {
    public static void main(String...args) {
        Scanner scanner = new Scanner(System.in);
        int sides = scanner.nextInt();
        double circumradius = scanner.nextDouble();
        double perimeter = sides * 2 * Math.sin(Math.PI / sides) * circumradius;
        System.out.printf("%.3f\n", perimeter);
    }
}
