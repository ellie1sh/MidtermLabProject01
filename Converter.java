/**
 * Converter class for expression conversion algorithms
 * Implements infix to postfix and infix to prefix conversion using stack
 * 
 * @author Data Structures Group Project
 * @version 1.0
 */
public class Converter {
    private Stack stack;
    
    /**
     * Default constructor
     * Creates a new converter with an empty stack
     */
    public Converter() {
        this.stack = new Stack();
    }
    
    /**
     * Method to check if a character is an operator
     * 
     * @param ch the character to check
     * @return true if character is an operator, false otherwise
     */
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '%';
    }
    
    /**
     * Method to check if a character is an operand (letter or digit)
     * 
     * @param ch the character to check
     * @return true if character is an operand, false otherwise
     */
    private boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }
    
    /**
     * Method to get precedence of an operator
     * Higher precedence means higher priority
     * 
     * @param operator the operator character
     * @return precedence value (higher number = higher precedence)
     */
    private int getPrecedence(char operator) {
        switch (operator) {
            case '^':
                return 4;  // Highest precedence
            case '*':
            case '/':
            case '%':
                return 3;
            case '+':
            case '-':
                return 2;
            case '(':
                return 1;  // Lowest precedence for parentheses
            default:
                return 0;
        }
    }
    
    /**
     * Method to check if an operator is left-associative
     * 
     * @param operator the operator character
     * @return true if left-associative, false if right-associative
     */
    private boolean isLeftAssociative(char operator) {
        return operator != '^';  // Only exponentiation is right-associative
    }
    
    /**
     * Method to convert infix expression to postfix notation
     * Algorithm (Shunting Yard Algorithm):
     * 1. Scan the infix expression from left to right
     * 2. If operand: add to output
     * 3. If left parenthesis: push to stack
     * 4. If right parenthesis: pop and add to output until left parenthesis
     * 5. If operator: pop operators with higher or equal precedence, then push current operator
     * 6. At end: pop all remaining operators from stack
     * 
     * Time Complexity: O(n) where n is the length of the expression
     * Space Complexity: O(n) for the stack and output
     * 
     * @param infix the infix expression to convert
     * @return the postfix expression
     */
    public String infixToPostfix(String infix) {
        // Clear the stack for new conversion
        stack.clear();
        
        // Remove spaces and convert to character array
        infix = infix.replaceAll("\\s+", "");
        StringBuilder postfix = new StringBuilder();
        
        System.out.println("Converting infix to postfix:");
        System.out.println("Infix: " + infix);
        System.out.println("Step-by-step conversion:");
        
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            
            System.out.print("Processing '" + ch + "': ");
            
            // If operand, add to output
            if (isOperand(ch)) {
                postfix.append(ch);
                System.out.println("Operand -> Add to output: " + postfix.toString());
            }
            // If left parenthesis, push to stack
            else if (ch == '(') {
                stack.push(String.valueOf(ch));
                System.out.println("Left parenthesis -> Push to stack");
            }
            // If right parenthesis, pop until left parenthesis
            else if (ch == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove the left parenthesis
                }
                System.out.println("Right parenthesis -> Pop until '(': " + postfix.toString());
            }
            // If operator
            else if (isOperator(ch)) {
                while (!stack.isEmpty() && 
                       isOperator(stack.peek().charAt(0)) &&
                       ((isLeftAssociative(ch) && getPrecedence(ch) <= getPrecedence(stack.peek().charAt(0))) ||
                        (!isLeftAssociative(ch) && getPrecedence(ch) < getPrecedence(stack.peek().charAt(0))))) {
                    postfix.append(stack.pop());
                }
                stack.push(String.valueOf(ch));
                System.out.println("Operator -> Push to stack: " + stack.toString());
            }
        }
        
        // Pop all remaining operators
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        
        System.out.println("Final postfix: " + postfix.toString());
        return postfix.toString();
    }
    
    /**
     * Method to convert infix expression to prefix notation
     * Algorithm:
     * 1. Reverse the infix expression
     * 2. Replace '(' with ')' and vice versa
     * 3. Convert to postfix using modified algorithm
     * 4. Reverse the result
     * 
     * Time Complexity: O(n) where n is the length of the expression
     * Space Complexity: O(n) for the stack and output
     * 
     * @param infix the infix expression to convert
     * @return the prefix expression
     */
    public String infixToPrefix(String infix) {
        // Clear the stack for new conversion
        stack.clear();
        
        // Remove spaces
        infix = infix.replaceAll("\\s+", "");
        
        System.out.println("Converting infix to prefix:");
        System.out.println("Original infix: " + infix);
        
        // Step 1: Reverse the infix expression
        String reversed = new StringBuilder(infix).reverse().toString();
        System.out.println("Reversed: " + reversed);
        
        // Step 2: Replace parentheses
        StringBuilder modified = new StringBuilder();
        for (char ch : reversed.toCharArray()) {
            if (ch == '(') {
                modified.append(')');
            } else if (ch == ')') {
                modified.append('(');
            } else {
                modified.append(ch);
            }
        }
        String modifiedInfix = modified.toString();
        System.out.println("Modified (swapped parentheses): " + modifiedInfix);
        
        // Step 3: Convert to postfix
        StringBuilder postfix = new StringBuilder();
        
        for (int i = 0; i < modifiedInfix.length(); i++) {
            char ch = modifiedInfix.charAt(i);
            
            // If operand, add to output
            if (isOperand(ch)) {
                postfix.append(ch);
            }
            // If left parenthesis, push to stack
            else if (ch == '(') {
                stack.push(String.valueOf(ch));
            }
            // If right parenthesis, pop until left parenthesis
            else if (ch == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove the left parenthesis
                }
            }
            // If operator
            else if (isOperator(ch)) {
                while (!stack.isEmpty() && 
                       isOperator(stack.peek().charAt(0)) &&
                       getPrecedence(ch) < getPrecedence(stack.peek().charAt(0))) {
                    postfix.append(stack.pop());
                }
                stack.push(String.valueOf(ch));
            }
        }
        
        // Pop all remaining operators
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        
        // Step 4: Reverse the result
        String prefix = postfix.reverse().toString();
        System.out.println("Postfix of modified: " + postfix.toString());
        System.out.println("Final prefix: " + prefix);
        
        return prefix;
    }
    
    /**
     * Method to evaluate a postfix expression
     * Algorithm:
     * 1. Scan the postfix expression from left to right
     * 2. If operand: push to stack
     * 3. If operator: pop two operands, perform operation, push result
     * 4. At end: the remaining value in stack is the result
     * 
     * Time Complexity: O(n) where n is the length of the expression
     * Space Complexity: O(n) for the stack
     * 
     * @param postfix the postfix expression to evaluate
     * @return the result of the evaluation
     */
    public double evaluatePostfix(String postfix) {
        stack.clear();
        
        System.out.println("Evaluating postfix: " + postfix);
        
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            
            // If operand, push to stack
            if (isOperand(ch)) {
                stack.push(String.valueOf(ch));
                System.out.println("Operand '" + ch + "' -> Push to stack");
            }
            // If operator, pop two operands and perform operation
            else if (isOperator(ch)) {
                if (stack.size() < 2) {
                    throw new RuntimeException("Invalid postfix expression: insufficient operands");
                }
                
                double operand2 = Double.parseDouble(stack.pop());
                double operand1 = Double.parseDouble(stack.pop());
                double result = performOperation(operand1, operand2, ch);
                
                stack.push(String.valueOf(result));
                System.out.println("Operator '" + ch + "' -> " + operand1 + " " + ch + " " + operand2 + " = " + result);
            }
        }
        
        if (stack.size() != 1) {
            throw new RuntimeException("Invalid postfix expression: too many operands");
        }
        
        double result = Double.parseDouble(stack.pop());
        System.out.println("Final result: " + result);
        return result;
    }
    
    /**
     * Method to perform arithmetic operations
     * 
     * @param operand1 first operand
     * @param operand2 second operand
     * @param operator the operator
     * @return the result of the operation
     */
    private double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new RuntimeException("Division by zero error");
                }
                return operand1 / operand2;
            case '^':
                return Math.pow(operand1, operand2);
            case '%':
                return operand1 % operand2;
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
    }
    
    /**
     * Method to validate if an infix expression has balanced parentheses
     * 
     * @param expression the expression to validate
     * @return true if parentheses are balanced, false otherwise
     */
    public boolean isBalanced(String expression) {
        stack.clear();
        
        for (char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(String.valueOf(ch));
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                
                String top = stack.pop();
                if ((ch == ')' && !top.equals("(")) ||
                    (ch == ']' && !top.equals("[")) ||
                    (ch == '}' && !top.equals("{"))) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}