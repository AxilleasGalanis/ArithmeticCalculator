package ce326.hw1;
import java.util.Scanner;

class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}

public class ArithmeticCalculator {
    public Node root;

    public ArithmeticCalculator(String expression) {
        checker(expression);
        String new_exp = expression.replaceAll("\\s", "");
        root = buildTree(new_exp);
    }

    // Checks if a char is operator
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == 'x' || c == '/' || c == '^';
    }

    // Finds the precedence of the operators
    private static int getPrecedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/' || c == 'x') {
            return 2;
        } else {
            return 3;
        }
    }

    public Node buildTree(String expression) {
        if (expression == null || expression.length() == 0) {
            return null;
        }

        int parenthesesCount = 0;
        int operatorIndex = -1;
        int lowestPrecedence = 5;

        // Iterate through the expression from left to right
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If we encounter an opening parenthesis, increment the count
            if (c == '(') {
                parenthesesCount++;
            }
            // If we encounter a closing parenthesis, decrement the count
            else if (c == ')') {
                parenthesesCount--;
            }
            // If we're not inside any parentheses and we encounter an operator with lower
            // precedence,
            // update the lowest precedence and operator index
            else if (parenthesesCount == 0 && isOperator(c)) {
                int currentPrecedence = getPrecedence(c);
                if (currentPrecedence <= lowestPrecedence) {
                    lowestPrecedence = currentPrecedence;
                    operatorIndex = i;
                }
            }
        }

        // If we didn't find any operator, the expression is a single number or a
        // parentheses-enclosed expression
        if (operatorIndex == -1) {
            if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')') {
                // If the expression is enclosed in parentheses, remove the parentheses and
                // build a tree for the enclosed expression
                return buildTree(expression.substring(1, expression.length() - 1));
            }
            // Otherwise, the expression is a single number
            return new Node(expression);
        }

        // Split the expression into left and right subtrees based on the operator index
        Node root = new Node(expression.substring(operatorIndex, operatorIndex + 1));
        root.left = buildTree(expression.substring(0, operatorIndex));
        root.right = buildTree(expression.substring(operatorIndex + 1));

        return root;
    }

    public String toDotString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ArithmeticExpressionTree {\n");
        toDotStringHelper(root, sb);
        sb.append("}");
        return sb.toString();
    }

    // Returns the dotGraphviz string
    private void toDotStringHelper(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.hashCode());
            sb.append("[label=\"");
            sb.append(node.data);
            sb.append("\"];\n");
            if (node.left != null) {
                sb.append(node.hashCode());
                sb.append(" -> ");
                sb.append(node.left.hashCode());
                sb.append(";\n");
                toDotStringHelper(node.left, sb);
            }
            if (node.right != null) {
                sb.append(node.hashCode());
                sb.append(" -> ");
                sb.append(node.right.hashCode());
                sb.append(";\n");
                toDotStringHelper(node.right, sb);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringHelper(root, sb);
        return sb.toString().trim();
    }

    // Gives the postfix expression
    private void toStringHelper(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        toStringHelper(node.left, sb);
        toStringHelper(node.right, sb);
        sb.append(node.data).append(" ");
    }

    public double calculate() {
        return evaluate(root);
    }

    public double evaluate(Node node) {
        if (node.isLeaf()) {
            return Double.parseDouble(node.data);
        } else {
            double leftVal = evaluate(node.left);
            double rightVal = evaluate(node.right);
            switch (node.data) {
                case "+":
                    return leftVal + rightVal;
                case "-":
                    return leftVal - rightVal;
                case "*":
                    return leftVal * rightVal;
                case "/":
                    return leftVal / rightVal;
                case "^":
                    return Math.pow(leftVal, rightVal);
                default:
                    throw new RuntimeException("Invalid operator: " + node.data);
            }
        }
    }

    public void opened_paranthesis_checker(String expression) {
        int parenthesesCount = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                // Increment the counter for opening parentheses
                parenthesesCount++;
            } else if (c == ')') {
                // Decrement the counter for closing parentheses
                parenthesesCount--;
            }
        }
        if (parenthesesCount > 0) {
            // There are unmatched opening parentheses
            System.out.println("\n[ERROR] Not closing opened parenthesis");
            System.exit(0);
        }
    }

    public void closing_paranthesis_checker(String expression) {
        int openParentheses = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                // Increment the counter for opening parentheses
                openParentheses++;
            } else if (expression.charAt(i) == ')') {
                // There is a closing parenthesis without an opening one
                if (openParentheses == 0) {
                    System.out.println("\n[ERROR] Closing unopened parenthesis");
                    System.exit(0);
                }
                openParentheses--;
            }
        }
    }

    public void character_checker(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!(Character.isDigit(c) || c == '.' || Character.isWhitespace(c) || c == '(' || c == ')' || c == '+'
                    || c == '-' || c == '*' || c == '/' || c == 'x' || c == '^')) {
                System.out.println("\n[ERROR] Invalid character");
                System.exit(0);
            }
        }
    }

    public void consecutive_operand_checker(String expression) {
        String pattern = ".*\\d+\\s+\\d+.*"; 
        
        if (expression.matches(pattern)) {
            System.out.println("\n[ERROR] Consecutive operands");
            System.exit(0);
        }
    }

    public void start_end_checker(String exp) {
        String expression = exp.replaceAll("\\s", "");
        // Check if the expression starts or ends with an operator
        if (expression.matches("^[+\\-*/^x].*") || expression.matches(".*[+\\-*/^x]$")) {
            System.out.println("\n[ERROR] Starting or ending with operator");
            System.exit(0);
        }
    }

    public void consecutive_operator_checker(String exp) {
        String expression = exp.replaceAll("\\s", "");
        String operators = "+-/*x^";
        // checks if there are consecutive operands
        for (int i = 1; i < expression.length() - 1; i++) {
            char currentChar = expression.charAt(i);
            char prevChar = expression.charAt(i - 1);
            char nextChar = expression.charAt(i + 1);
            if (operators.contains(Character.toString(currentChar))
                    && operators.contains(Character.toString(prevChar))) {
                System.out.println("\n[ERROR] Consecutive operators");
                System.exit(0);
            } else if (operators.contains(Character.toString(currentChar))
                    && operators.contains(Character.toString(nextChar))) {
                System.out.println("\n[ERROR] Consecutive operators");
                System.exit(0);
            }
        }
    }

    // Checks if there is an operator before or after parentheses
    public void operator_parenthesis_checker(String exp) {
        String expression = exp.replaceAll("\\s", "");
        for (int i = 0; i < expression.length() - 1; i++) {
            char currentChar = expression.charAt(i);
            char nextChar = expression.charAt(i + 1);

            if (currentChar == '(' && isOperator(nextChar)) {
                System.out.println("\n[ERROR] Operator appears after opening parenthesis");
                System.exit(0);
            }

            if (isOperator(currentChar) && nextChar == ')') {
                System.out.println("\n[ERROR] Operator appears before closing parenthesis");
                System.exit(0);
            }
        }
    }

    public boolean isNumber(char c) {
        return Character.toString(c).matches("^[0-9.]$");
    }

    // Checks if there is an operand before or after parentheses
    public void operand_parenthesis_checker(String exp) {
        String expression = exp.replaceAll("\\s", "");
        for (int i = 0; i < expression.length() - 1; i++) {
            char currentChar = expression.charAt(i);
            char nextChar = expression.charAt(i + 1);

            if (nextChar == '(' && isNumber(currentChar)) {
                System.out.println("\n[ERROR] Operand before opening parenthesis");
                System.exit(0);
            }

            if (isNumber(nextChar) && currentChar == ')') {
                System.out.println("\n[ERROR] Operand after closing parenthesis");
                System.exit(0);
            }
        }
    }

    // Checks if ther is ')' before '('
    public void parentesis_before_parenthesis_checker(String exp) {
        String expression = exp.replaceAll("\\s", "");
        for (int i = 0; i < expression.length() - 1; i++) {
            char currentChar = expression.charAt(i);
            char nextChar = expression.charAt(i + 1);
            if (currentChar == ')' && nextChar == '(') {
                System.out.println("\n[ERROR] ')' appears before opening parenthesis");
                System.exit(0);
            }
        }
    }

    public void checker(String expression) {
        start_end_checker(expression);
        opened_paranthesis_checker(expression);
        closing_paranthesis_checker(expression);
        character_checker(expression);
        consecutive_operand_checker(expression);
        consecutive_operator_checker(expression);
        operator_parenthesis_checker(expression);
        parentesis_before_parenthesis_checker(expression);
        operand_parenthesis_checker(expression);
    }

    public static void main(String[] args) {
        // Create a Scanner object for reading from the keyboard
        Scanner scanner = new Scanner(System.in);
        System.out.print("Expression: ");
        String expression = scanner.nextLine();
        ArithmeticCalculator ac = new ArithmeticCalculator(expression);

        String option = scanner.nextLine();
        switch (option) {
            case "-d":
                System.out.println("\n" + ac.toDotString());
                break;
            case "-s":
                System.out.println("\nPostfix: " + ac.toString());
                break;
            case "-c":
                System.out.printf("\nResult: %.6f", ac.calculate());
                break;
            default:
                System.err.println("Invalid option: " + option);
        }
        System.out.print("\n");
        scanner.close();
    }
}