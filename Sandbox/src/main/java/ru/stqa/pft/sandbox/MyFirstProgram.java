package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point a = new Point (1,4);
        Point b = new Point (1,5);

        System.out.println("Расстояние между точками x = "  + a.x + ", y = " + a.y + " и x = " + b.x + ", y = " + b.y + " = " +distance(a,b));
    }

    public static double distance (Point a, Point b){
        double xa = (a.x - b.x)*(a.x - b.x);
        double yb = (a.y - b.y)*(a.y - b.y);
        double d = Math.sqrt(xa + yb);
        return d;
    }
}