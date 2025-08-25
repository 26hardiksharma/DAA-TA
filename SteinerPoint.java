class Point {
    double x, y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class SteinerPoint {

    static double dist(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    static Point steinerPoint(Point A, Point B, Point C) {
        double a = dist(B, C);
        double b = dist(A, C);
        double c = dist(A, B);

        double angleA = Math.acos((b*b + c*c - a*a) / (2*b*c));
        double angleB = Math.acos((a*a + c*c - b*b) / (2*a*c));
        double angleC = Math.acos((a*a + b*b - c*c) / (2*a*b));

        if (angleA >= 2*Math.PI/3) return A;
        if (angleB >= 2*Math.PI/3) return B;
        if (angleC >= 2*Math.PI/3) return C;

        double sqrt3 = Math.sqrt(3);
        double px = (A.x + B.x + C.x + sqrt3 * (B.y - A.y + C.y - B.y + A.y - C.y)) / 3;
        double py = (A.y + B.y + C.y - sqrt3 * (B.x - A.x + C.x - B.x + A.x - C.x)) / 3;
        return new Point(px, py);
    }

    public static void main(String[] args) {
        Point A = new Point(0, 0);
        Point B = new Point(10, 0);
        Point C = new Point(5, 8);

        Point S = steinerPoint(A, B, C);
        System.out.printf("Steiner Point: (%.3f, %.3f)\n", S.x, S.y);
    }
}