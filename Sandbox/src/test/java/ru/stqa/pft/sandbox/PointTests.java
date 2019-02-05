package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void pointTest1(){
        Point a = new Point(1,3);
        Point b = new Point (1,4);
        Assert.assertEquals(a.distance(b),1.0);
    }
    @Test
    public void pointTest2(){
        Point a = new Point(1,3);
        Point b = new Point (2,3);
        Assert.assertEquals(a.distance(b),1.0);
    }

}
