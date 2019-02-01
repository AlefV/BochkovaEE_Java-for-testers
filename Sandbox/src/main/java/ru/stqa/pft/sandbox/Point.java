package ru.stqa.pft.sandbox;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance (Point a, Point b) {
        double xa = (a.x - b.x) * (a.x - b.x);
        double yb = (a.y - b.y) * (a.y - b.y);
        double d = Math.sqrt(xa + yb);
        return d;

    }
}
