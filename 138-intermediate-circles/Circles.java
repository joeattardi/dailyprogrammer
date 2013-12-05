public class Circles {

  public static void main(String...args) {
    double x = Double.parseDouble(args[0]);
    double y = Double.parseDouble(args[1]);
    double u = Double.parseDouble(args[2]);
    double w = Double.parseDouble(args[3]);

    double combinedArea = 2 * Math.PI;
    double distance = Math.hypot(u - x, w - y);

    if (distance < 2) {
      double angle = 2 * Math.acos(distance/2);
      double overlapArea = angle - Math.sin(angle);
      combinedArea -= overlapArea;
    }

    System.out.printf("Combined area: %.4f\n", combinedArea);
  }
}