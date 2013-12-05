public class Circles {

  public static void main(String...args) {
    double x = Double.parseDouble(args[0]);
    double y = Double.parseDouble(args[1]);
    double u = Double.parseDouble(args[2]);
    double w = Double.parseDouble(args[3]);

    double combinedArea = 2 * Math.PI;

    // distance between centers
    double distance = Math.sqrt(Math.pow(u - x, 2) + Math.pow(w - y, 2));

    if (distance < 2) {
      double overlap = 2 - distance;
      double d = 1 - (overlap / 2);
      double angle = 2 * Math.acos(d);
      double overlapArea = 2 * (0.5 * (angle - Math.sin(angle)));
      combinedArea -= overlapArea;
    }

    System.out.printf("Combined area: %.4f\n", combinedArea);
  }
}