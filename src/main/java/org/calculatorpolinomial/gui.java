package org.example.calculatorpolinomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;

public class gui {
    private JFrame frame;
    private JTextField polynomial1Field;
    private JTextField polynomial2Field;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton differentiateButton;
    private JButton integrateButton;
    private JTextArea resultArea;

    public gui() {
        // create frame
        frame = new JFrame("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);

        // create panel for input fields
        JPanel inputPanel = new JPanel(new FlowLayout());

        // create text fields for input
        polynomial1Field = new JTextField("Enter first polynomial here", 30);
        polynomial2Field = new JTextField("Enter second polynomial here", 30);

        polynomial1Field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                polynomial1Field.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // do nothing
            }
        });

        polynomial2Field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                polynomial2Field.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // do nothing
            }
        });

        // add text fields to input panel
        inputPanel.add(polynomial1Field);
        inputPanel.add(polynomial2Field);

        // create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // create buttons for operations
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");
        differentiateButton = new JButton("Differentiate");
        integrateButton = new JButton("Integrate");

        // add buttons to button panel
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(differentiateButton);
        buttonPanel.add(integrateButton);

        // add action listeners to buttons
        addButton.addActionListener(new OperationButtonListener("add"));
        subtractButton.addActionListener(new OperationButtonListener("subtract"));
        multiplyButton.addActionListener(new OperationButtonListener("multiply"));
        divideButton.addActionListener(new OperationButtonListener("divide"));
        differentiateButton.addActionListener(new OperationButtonListener("differentiate"));
        integrateButton.addActionListener(new OperationButtonListener("integrate"));

        // create panel for result area
        JPanel resultPanel = new JPanel(new FlowLayout());

        // create result area
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);

        // add result area to result panel
        resultPanel.add(resultArea);

        // add input panel, button panel, and result panel to frame
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(resultPanel, BorderLayout.SOUTH);

        // make frame visible
        frame.setVisible(true);
    }

    private class OperationButtonListener implements ActionListener {
        private String operation;

        public OperationButtonListener(String operation) {
            this.operation = operation;
        }
        public void actionPerformed(ActionEvent e) {
            // get input polynomials from text fields
            HashMap<Integer, Double> polynomial1 = parsePolinom.parse(polynomial1Field.getText());
            HashMap<Integer, Double> polynomial2 = parsePolinom.parse(polynomial2Field.getText());

            // perform selected operation on input polynomials
            HashMap<Integer,Double> result;
            switch (operation) {
                case "add":
                    result = Calcule.add(polynomial1,polynomial2);
                    break;
                case "subtract":
                    result = Calcule.subtract(polynomial1,polynomial2);
                    break;
                case "multiply":
                    result = Calcule.multiply(polynomial1,polynomial2);
                    break;
                case "divide":
                    result = Calcule.divide(polynomial1,polynomial2);
                    break;
                case "differentiate":
                    result = Calcule.differentiate(polynomial1);
                    break;
                case "integrate":
                    result = Calcule.integrate(polynomial1);
                    break;
                default:
                    result = parsePolinom.parse(""); // default to empty polynomial
                    break;
            }

            // display result polynomial in output field
            resultArea.setText(parsePolinom.toString(result));
        }
    }
}