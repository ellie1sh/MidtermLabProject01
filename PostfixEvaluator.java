/**
 * PostfixEvaluator class - implements the exact algorithm from PDF
 * Evaluates postfix expressions with detailed table output
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class PostfixEvaluator {
    
    private Stack<Double> operandStack;
    
    /**
     * Constructor
     */
    public PostfixEvaluator() {
        this.operandStack = new Stack<>();
    }
    
    /**
     * Evaluate postfix expression with detailed table output
     * Algorithm from PDF:
     * 1. Initialize empty operand stack
     * 2. For each symbol in postfix expression:
     *    - If operand: push to stack
     *    - If operator: pop two operands, perform operation, push result
     * 3. Final result is the only element left in stack
     * 
     * @param postfixExpression The postfix expression to evaluate
     * @return EvaluationResult containing the result and step table
     */
    public EvaluationResult evaluateWithTable(String postfixExpression) {
        // Reset for new evaluation
        operandStack.clear();
        
        EvaluationResult result = new EvaluationResult();
        
        try {
            // Validate input
            if (postfixExpression == null || postfixExpression.trim().isEmpty()) {
                throw new IllegalArgumentException("Empty postfix expression");
            }
            
            System.out.println("\n=== POSTFIX EXPRESSION EVALUATION ===");
            System.out.println("Input Expression: " + postfixExpression);
            System.out.println("\nStep-by-step evaluation:");
            System.out.printf("%-8s %-10s %-10s %-10s %-20s%n", 
                            "Symbol", "Operand1", "Operand2", "Value", "Operand Stack");
            System.out.println("--------------------------------------------------------");
            
            // Split the postfix expression by spaces
            String[] tokens = postfixExpression.trim().split("\\s+");
            
            for (String token : tokens) {
                if (token.isEmpty()) continue;
                
                char symbol = token.charAt(0);
                
                if (isOperand(token)) {
                    // If symbol is an operand, push to stack
                    double operand = parseOperand(token);
                    operandStack.push(operand);
                    
                    // Print step for operand
                    System.out.printf("%-8s %-10s %-10s %-10s %-20s%n", 
                                    token, "", "", "", getStackContents());
                    
                } else if (isOperator(symbol)) {
                    // Symbol is an operator
                    if (operandStack.size() < 2) {
                        throw new IllegalArgumentException("Insufficient operands for operator: " + symbol);
                    }
                    
                    // Pop two operands
                    double operand2 = operandStack.pop();
                    double operand1 = operandStack.pop();
                    
                    // Perform operation
                    double value = performOperation(operand1, operand2, symbol);
                    
                    // Push result back to stack
                    operandStack.push(value);
                    
                    // Print step for operator
                    System.out.printf("%-8s %-10.0f %-10.0f %-10.0f %-20s%n", 
                                    String.valueOf(symbol), operand1, operand2, value, getStackContents());
                    
                } else {
                    throw new IllegalArgumentException("Invalid symbol: " + token);
                }
            }
            
            // Final result should be the only element in stack
            if (operandStack.size() != 1) {
                throw new IllegalArgumentException("Invalid postfix expression - stack should contain exactly one element");
            }
            
            double finalResult = operandStack.pop();
            result.setResult(finalResult);
            result.setSuccess(true);
            
            System.out.println("\nFinal Result: " + finalResult);
            
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorMessage("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Check if token is an operand (number)
     * 
     * @param token Token to check
     * @return true if operand, false otherwise
     */
    private boolean isOperand(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            // Check if it's a single letter (variable)
            return token.length() == 1 && Character.isLetter(token.charAt(0));
        }
    }
    
    /**
     * Check if character is a valid operator
     * 
     * @param ch Character to check
     * @return true if valid operator, false otherwise
     */
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }
    
    /**
     * Parse operand from string
     * For this implementation, we'll use numeric values
     * Variables would need to be substituted with actual values
     * 
     * @param token Token to parse
     * @return Numeric value
     */
    private double parseOperand(String token) {
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            // For single letter variables, we'll need actual values
            // For demonstration, assign some default values
            char var = token.charAt(0);
            switch (var) {
                case 'A': case 'a': return 1;
                case 'B': case 'b': return 2;
                case 'C': case 'c': return 3;
                case 'D': case 'd': return 4;
                case 'E': case 'e': return 5;
                case 'F': case 'f': return 6;
                default: return 0;
            }
        }
    }
    
    /**
     * Perform arithmetic operation on two operands
     * 
     * @param operand1 First operand
     * @param operand2 Second operand
     * @param operator The operator to apply
     * @return Result of the operation
     * @throws IllegalArgumentException if division by zero or invalid operator
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
                    throw new IllegalArgumentException("Division by zero");
                }
                return operand1 / operand2;
            case '^':
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    /**
     * Get current contents of operand stack as string
     * 
     * @return String representation of stack contents
     */
    private String getStackContents() {
        if (operandStack.isEmpty()) {
            return "empty";
        }
        
        Object[] stackArray = operandStack.toArray();
        StringBuilder sb = new StringBuilder();
        for (int i = stackArray.length - 1; i >= 0; i--) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            // Format numbers to remove unnecessary decimal places
            double value = (Double) stackArray[i];
            if (value == (int) value) {
                sb.append((int) value);
            } else {
                sb.append(value);
            }
        }
        return sb.toString();
    }
    
    /**
     * Inner class to hold evaluation results
     */
    public static class EvaluationResult {
        private double result;
        private boolean success;
        private String errorMessage;
        
        public double getResult() { return result; }
        public void setResult(double result) { this.result = result; }
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        public String getErrorMessage() { return errorMessage; }
        public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    }
}