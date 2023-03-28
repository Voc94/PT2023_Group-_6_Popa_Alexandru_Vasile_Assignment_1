package org.calculatorpolinomial;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.calculatorpolinomial.Calcule;
import org.junit.Test;

public class CalculeTest {

    @Test
    public void testAdd() {
        HashMap<Integer, Double> p1 = new HashMap<>();
        HashMap<Integer, Double> p2 = new HashMap<>();
        HashMap<Integer, Double> expected = new HashMap<>();
        HashMap<Integer, Double> result;

        p1.put(0, 1.0);
        p1.put(1, 2.0);
        p1.put(2, 3.0);

        p2.put(1, 4.0);
        p2.put(2, 5.0);
        p2.put(3, 6.0);

        expected.put(0, 1.0);
        expected.put(1, 6.0);
        expected.put(2, 8.0);
        expected.put(3, 6.0);

        result = Calcule.add(p1, p2);

        assertEquals(expected, result);
    }

    @Test
    public void testSubtract() {
        HashMap<Integer, Double> p1 = new HashMap<>();
        HashMap<Integer, Double> p2 = new HashMap<>();
        HashMap<Integer, Double> expected = new HashMap<>();
        HashMap<Integer, Double> result;

        p1.put(0, 1.0);
        p1.put(1, 2.0);
        p1.put(2, 3.0);

        p2.put(1, 4.0);
        p2.put(2, 5.0);
        p2.put(3, 6.0);

        expected.put(0, 1.0);
        expected.put(1, -2.0);
        expected.put(2, -2.0);
        expected.put(3, -6.0);

        result = Calcule.subtract(p1, p2);

        assertEquals(expected, result);
    }

    @Test
    public void testMultiply() {
        HashMap<Integer, Double> p1 = new HashMap<>();
        HashMap<Integer, Double> p2 = new HashMap<>();
        HashMap<Integer, Double> expected = new HashMap<>();
        HashMap<Integer, Double> result;

        p1.put(0, 1.0);
        p1.put(1, 2.0);

        p2.put(0, 3.0);
        p2.put(1, 4.0);

        expected.put(0, 3.0);
        expected.put(1, 10.0);
        expected.put(2, 8.0);

        result = Calcule.multiply(p1, p2);

        assertEquals(expected, result);
    }

    @Test
    public void testDifferentiate() {
        HashMap<Integer, Double> p = new HashMap<>();
        HashMap<Integer, Double> expected = new HashMap<>();
        HashMap<Integer, Double> result;

        p.put(0, 1.0);
        p.put(1, 2.0);
        p.put(2, 3.0);

        expected.put(0, 2.0);
        expected.put(1, 6.0);

        result = Calcule.differentiate(p);

        assertEquals(expected, result);
    }

    @Test
    public void testIntegrate() {
        HashMap<Integer, Double> p = new HashMap<>();
        HashMap<Integer, Double> expected = new HashMap<>();
        HashMap<Integer, Double> result;

        p.put(0, 1.0);
        p.put(1, 2.0);

        expected.put(1, 1.0);
        expected.put(2, 1.0);

        result = Calcule.integrate(p);

        assertEquals(expected, result);
    }
}
