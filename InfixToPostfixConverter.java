/**
 * InfixToPostfixConverter class - implements the exact algorithm from PDF
 * Converts infix expressions to postfix expressions with detailed table output
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class InfixToPostfixConverter {
    
    private Stack<Character> operatorStack;
    private StringBuilder postfixExpression;
    
    /**
     * Constructor
     */
    public InfixToPostfixConverter() {
        this.operatorStack = new Stack<>();
        this.postfixExpression = new StringBuilder();
    }
    
    /**
     * Convert infix expression to postfix with detailed table output
     * Algorithm from PDF:
     * 1. Initialize empty postfix string and operator stack
     * 2. For each symbol in infix expression:
     *    - If operand: concatenate to postfix
     *    - If operator: handle based on precedence and parentheses
     * 3. Pop remaining operators from stack
     * 
     * @param infixExpression The infix expression to convert
     * @return ConversionResult containing postfix expression and step table
     */
    public ConversionResult convertWithTable(String infixExpression) {
        // Reset for new conversion
        operatorStack.clear();
        postfixExpression = new StringBuilder();
        
        ConversionResult result = new ConversionResult();
        
        try {
            // Validate input
            if (!isValidInfixExpression(infixExpression)) {
                throw new IllegalArgumentException("Invalid infix expression");
            }
            
            System.out.println("\n=== INFIX TO POSTFIX CONVERSION ===");
            System.out.println("Input Expression: " + infixExpression);
            System.out.println("\nStep-by-step conversion:");
            System.out.printf("%-8s %-20s %-15s%n", "Symbol", "Postfix Expression", "Operator Stack");
            System.out.println("------------------------------------------------");
            
            // Process each character in the infix expression
            for (int i = 0; i < infixExpression.length(); i++) {
                char symbol = infixExpression.charAt(i);
                
                // Skip spaces
                if (symbol == ' ') continue;
                
                if (isOperand(symbol)) {
                    // If symbol is an operand, concatenate to postfix
                    if (postfixExpression.length() > 0) {
                        postfixExpression.append(" ");
                    }
                    postfixExpression.append(symbol);
                } else {
                    // Symbol is an operator
                    processOperator(symbol);
                }
                
                // Print current state
                printStep(symbol, postfixExpression.toString(), getStackContents());
            }
            
            // Get all remaining operators from the stack
            while (!operatorStack.isEmpty()) {
                char topSymbol = operatorStack.pop();
                if (topSymbol != '(') { // Don't add parentheses to output
                    if (postfixExpression.length() > 0) {
                        postfixExpression.append(" ");
                    }
                    postfixExpression.append(topSymbol);
                }
                printStep('-', postfixExpression.toString(), getStackContents());
            }
            
            result.setPostfixExpression(postfixExpression.toString().trim());
            result.setSuccess(true);
            
            System.out.println("\nFinal Postfix Expression: " + result.getPostfixExpression());
            
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorMessage("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Process an operator according to the algorithm from PDF
     * 
     * @param symbol The operator symbol to process
     */
    private void processOperator(char symbol) {
        if (symbol == '(') {
            // If symbol is open parenthesis, push onto stack
            operatorStack.push(symbol);
        } else if (symbol == ')') {
            // If symbol is close parenthesis, pop until open parenthesis
            while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                char topSymbol = operatorStack.pop();
                if (postfixExpression.length() > 0) {
                    postfixExpression.append(" ");
                }
                postfixExpression.append(topSymbol);
            }
            // Pop the open parenthesis and discard it
            if (!operatorStack.isEmpty()) {
                operatorStack.pop();
            }
        } else {
            // Symbol is an operator (+, -, *, /, ^)
            while (!operatorStack.isEmpty() && 
                   operatorStack.peek() != '(' &&
                   hasHigherOrEqualPrecedence(operatorStack.peek(), symbol)) {
                char topSymbol = operatorStack.pop();
                if (postfixExpression.length() > 0) {
                    postfixExpression.append(" ");
                }
                postfixExpression.append(topSymbol);
            }
            operatorStack.push(symbol);
        }
    }
    
    /**
     * Check if operator1 has higher or equal precedence than operator2
     * Based on PDF specifications:
     * 1. Exponentiation (^) - highest precedence, right-to-left associativity
     * 2. Multiplication (*) / Division (/) - left-to-right associativity  
     * 3. Addition (+) / Subtraction (-) - lowest precedence, left-to-right associativity
     * 
     * @param operator1 First operator
     * @param operator2 Second operator
     * @return true if operator1 has higher or equal precedence
     */
    private boolean hasHigherOrEqualPrecedence(char operator1, char operator2) {
        int prec1 = getPrecedence(operator1);
        int prec2 = getPrecedence(operator2);
        
        // For exponentiation (right-to-left associativity)
        if (operator2 == '^') {
            return prec1 > prec2;
        }
        
        // For other operators (left-to-right associativity)
        return prec1 >= prec2;
    }
    
    /**
     * Get precedence value for an operator
     * Higher number = higher precedence
     * 
     * @param operator The operator
     * @return Precedence value
     */
    private int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
    
    /**
     * Check if character is an operand (letter or digit)
     * 
     * @param ch Character to check
     * @return true if operand, false otherwise
     */
    private boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
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
     * Validate infix expression format
     * 
     * @param expression Expression to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidInfixExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return false;
        }
        
        // Check for balanced parentheses
        int parenthesesCount = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                parenthesesCount++;
            } else if (ch == ')') {
                parenthesesCount--;
                if (parenthesesCount < 0) {
                    return false;
                }
            } else if (!isOperand(ch) && !isOperator(ch) && ch != ' ') {
                return false; // Invalid character
            }
        }
        
        return parenthesesCount == 0;
    }
    
    /**
     * Get current contents of operator stack as string
     * 
     * @return String representation of stack contents
     */
    private String getStackContents() {
        if (operatorStack.isEmpty()) {
            return "empty";
        }
        
        Object[] stackArray = operatorStack.toArray();
        StringBuilder sb = new StringBuilder();
        for (int i = stackArray.length - 1; i >= 0; i--) {
            sb.append(stackArray[i]);
        }
        return sb.toString();
    }
    
    /**
     * Print a step in the conversion table
     * 
     * @param symbol Current symbol being processed
     * @param postfix Current postfix expression
     * @param stack Current stack contents
     */
    private void printStep(char symbol, String postfix, String stack) {
        String symbolStr = (symbol == '-') ? "pop" : String.valueOf(symbol);
        System.out.printf("%-8s %-20s %-15s%n", symbolStr, postfix, stack);
    }
    
    /**
     * Inner class to hold conversion results
     */
    public static class ConversionResult {
        private String postfixExpression;
        private boolean success;
        private String errorMessage;
        
        public String getPostfixExpression() { return postfixExpression; }
        public void setPostfixExpression(String postfixExpression) { this.postfixExpression = postfixExpression; }
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }
        public String getErrorMessage() { return errorMessage; }
        public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    }
}