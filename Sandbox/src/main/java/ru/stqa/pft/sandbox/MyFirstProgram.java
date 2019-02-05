package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point a = new Point (1,4);
        Point b = new Point (1,5);

        System.out.println("Расстояние между точками x = "  + a.x + ", y = " + a.y + " и x = " + b.x + ", y = " + b.y + " = " +a.distance(b));
    }


}