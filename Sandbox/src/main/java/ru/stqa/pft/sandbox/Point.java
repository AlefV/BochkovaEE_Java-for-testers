package ru.stqa.pft.sandbox;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance (Point a) {
        double xa = (a.x - this.x) * (a.x - this.x);
        double yb = (a.y - this.y) * (a.y - this.y);
        double d = Math.sqrt(xa + yb);
        return d;

    }
}
