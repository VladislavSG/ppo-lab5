package graph;

import drawing.Point;

public class EllipsPointsFactory {
    private double pointAngle;
    private final double angleDelta;
    private final Point center;
    private final double a;
    private final double b;

    public EllipsPointsFactory(int pointsCount, Point center, double a, double b) {
        if (pointsCount == 0) {
            throw new IllegalArgumentException("Empty graph");
        }
        this.center = center;
        this.a = a;
        this.b = b;
        this.pointAngle = 0;
        this.angleDelta = 2 * Math.PI / pointsCount;
    }

    public Point nextPoint() {
        double x = a * Math.cos(pointAngle) + center.getX();
        double y = b * Math.sin(pointAngle) + center.getY();
        Point point = new Point(x, y);
        pointAngle += angleDelta;
        return point;
    }

}
