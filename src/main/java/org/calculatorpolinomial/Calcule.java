package org.example.calculatorpolinomial;

import java.util.HashMap;
import java.util.Map;

public class Calcule {
    public static HashMap<Integer, Double> add(HashMap<Integer, Double> p1, HashMap<Integer, Double> p2) {
        HashMap<Integer, Double> result = new HashMap<>();

        for (Map.Entry<Integer, Double> term : p1.entrySet()) {
            int exp = term.getKey();
            double coef = term.getValue();
            result.put(exp, coef);
        }

        for (Map.Entry<Integer, Double> term : p2.entrySet()) {
            int exp = term.getKey();
            double coef = term.getValue();
            if (result.containsKey(exp)) {
                coef += result.get(exp);
            }
            result.put(exp, coef);
        }

        return result;
    }

    public static HashMap<Integer, Double> subtract(HashMap<Integer, Double> p1, HashMap<Integer, Double> p2) {
        HashMap<Integer, Double> result = new HashMap<>();

        for (Map.Entry<Integer, Double> term : p1.entrySet()) {
            int exp = term.getKey();
            double coef = term.getValue();
            result.put(exp, coef);
        }

        for (Map.Entry<Integer, Double> term : p2.entrySet()) {
            int exp = term.getKey();
            double coef = term.getValue();
            if (result.containsKey(exp)) {
                coef -= result.get(exp);
            } else {
                coef *= -1;
            }
            result.put(exp, coef);
        }

        return result;
    }

    public static HashMap<Integer, Double> multiply(HashMap<Integer, Double> p1, HashMap<Integer, Double> p2) {
        HashMap<Integer, Double> result = new HashMap<>();

        for (Map.Entry<Integer, Double> term1 : p1.entrySet()) {
            for (Map.Entry<Integer, Double> term2 : p2.entrySet()) {
                int exp = term1.getKey() + term2.getKey();
                double coef = term1.getValue() * term2.getValue();
                if (result.containsKey(exp)) {
                    coef += result.get(exp);
                }
                result.put(exp, coef);
            }
        }

        return result;
    }

    public static HashMap<Integer, Double> differentiate(HashMap<Integer, Double> p) {
        HashMap<Integer, Double> result = new HashMap<>();

        for (Map.Entry<Integer, Double> term : p.entrySet()) {
            int exp = term.getKey();
            double coef = term.getValue();
            if (exp > 0) {
                result.put(exp - 1, exp * coef);
            }
        }

        return result;
    }

    public static HashMap<Integer, Double> integrate(HashMap<Integer, Double> p) {
        HashMap<Integer, Double> result = new HashMap<>();

        for (Map.Entry<Integer, Double> term : p.entrySet()) {
            int exp = term.getKey();
            double coef = term.getValue();
            result.put(exp + 1, coef / (exp + 1));
        }

        return result;
    }
    public static HashMap<Integer, Double> divide(HashMap<Integer, Double> dividend, HashMap<Integer, Double> divisor) throws ArithmeticException {
        if (divisor.isEmpty()) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        HashMap<Integer, Double> quotient = new HashMap<>();
        HashMap<Integer, Double> remainder = new HashMap<>(dividend);

        while (remainder.size() >= divisor.size()) {
            Map.Entry<Integer, Double> term1 = remainder.entrySet().iterator().next();
            int exp1 = term1.getKey();
            double coef1 = term1.getValue();

            Map<Integer, Double> temp = new HashMap<>();
            for (Map.Entry<Integer, Double> term2 : divisor.entrySet()) {
                int exp2 = term2.getKey();
                double coef2 = term2.getValue();
                temp.put(exp1 - exp2, coef1 / coef2);
            }

            Map.Entry<Integer, Double> term3 = temp.entrySet().stream().min(Map.Entry.comparingByKey()).get();
            int exp3 = term3.getKey();
            double coef3 = term3.getValue();

            quotient.put(exp3, coef3);

            Map<Integer, Double> temp2 = new HashMap<>();
            for (Map.Entry<Integer, Double> term4 : divisor.entrySet()) {
                int exp4 = term4.getKey() + exp3;
                double coef4 = term4.getValue() * coef3;
                temp2.put(exp4, coef4);
            }

            remainder = subtract(remainder, (HashMap<Integer, Double>) temp2);
        }

        return quotient;
    }

}
