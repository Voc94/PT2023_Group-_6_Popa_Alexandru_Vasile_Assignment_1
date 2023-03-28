package org.example.calculatorpolinomial;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parsePolinom {

    public static HashMap<Integer, Double> parse(String polinom) {
        HashMap<Integer, Double> PoliMap = new HashMap<>();
        Pattern pattern = Pattern.compile("[+-]?\\s*(\\d*\\w\\^\\d+|\\d*\\w|\\w|\\d+)");
        Matcher matcher = pattern.matcher(polinom);

        while (matcher.find()) {
            String term = "";
            if(matcher.group(0).charAt(0)=='-') {
                term+='-';
            }
            term += matcher.group(1);
            double coeficient = parseazaCoeficient(term);
            int exponent = parseazaExponent(term);
            PoliMap.put(exponent, PoliMap.getOrDefault(exponent, 0.0) + coeficient);
        }

        return PoliMap;
    }

    private static double parseazaCoeficient(String term) {
        Pattern pattern = Pattern.compile("([+-]?\\d*\\.?\\d*)[a-z]?(\\^\\d+)?");
        Matcher matcher = pattern.matcher(term);
        if (matcher.find()) {
            String stringCoeficient = matcher.group(1);
            if (stringCoeficient.equals("+") || stringCoeficient.equals("")) {
                return 1.0;
            } else if (stringCoeficient.equals("-")) {
                return -1.0;
            } else if (stringCoeficient.startsWith("-")) {
                return Double.parseDouble("-" + stringCoeficient.substring(1));
            } else {
                return Double.parseDouble(stringCoeficient);
            }
        }
        return 0.0;
    }


    private static int parseazaExponent(String term) {
        Pattern pattern = Pattern.compile("[+-]?\\d*\\.?\\d*[a-z]?\\^(\\d+)");
        Matcher matcher = pattern.matcher(term);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        pattern = Pattern.compile("[a-z](?!\\^)");
        matcher = pattern.matcher(term);
        if (matcher.find()) {
            return 1;
        }
        pattern = Pattern.compile("\\d+(?!\\^)");
        matcher = pattern.matcher(term);
        if (matcher.find()) {
            return 0;
        }
        return 0;
    }
    public static String toString(HashMap<Integer, Double> polynomial) {
        StringBuilder sb = new StringBuilder();

        boolean firstTerm = true;

        for (int exponent : polynomial.keySet()) {
            double coefficient = polynomial.get(exponent);

            if (coefficient == 0) {
                continue;
            }

            if (firstTerm) {
                firstTerm = false;
                if (coefficient < 0) {
                    sb.append("-");
                    coefficient = -coefficient;
                }
            } else {
                if (coefficient < 0) {
                    sb.append(" - ");
                    coefficient = -coefficient;
                } else {
                    sb.append(" + ");
                }
            }

            if (coefficient != 1 || exponent == 0) {
                sb.append(Double.toString(coefficient));
            }

            if (exponent != 0) {
                sb.append("x");
                if (exponent != 1) {
                    sb.append("^").append(Integer.toString(exponent));
                }
            }
        }

        if (sb.length() == 0) {
            sb.append("0");
        }

        return sb.toString();
    }



}
