/**
 * Converter class for Infix to Postfix conversion and Postfix evaluation
 * This class provides the basic structure and some utility methods
 * 
 * NOTE: The main conversion and evaluation methods are left as placeholders
 * for the next coder to implement according to the PDF specifications
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class Converter {
    
    /**
     * Convert infix expression to postfix with detailed table output
     * 
     * TODO: Implement the exact algorithm from PDF pages 4-5
     * Algorithm should:
     * 1. Use a stack for operators
     * 2. Process each symbol in the infix expression
     * 3. Handle operator precedence: ^ (highest), multiply/divide, add/subtract (lowest)
     * 4. Handle parentheses correctly
     * 5. Print step-by-step table showing Symbol, Postfix Expression, Operator Stack
     * 
     * @param infixExpression The infix expression to convert (no spaces)
     * @return The postfix expression (with spaces between tokens)
     */
    public static String convertInfixToPostfixWithTable(String infixExpression) {
        // TODO: Implement this method for the next coder
        // This should follow the exact algorithm from the PDF
        
        System.out.println("TODO: Implement infix to postfix conversion with table output");
        System.out.println("Input: " + infixExpression);
        
        // Placeholder return - next coder should implement the full algorithm
        return "TODO: Implement conversion";
    }
    
    /**
     * Evaluate postfix expression with detailed table output
     * 
     * TODO: Implement the exact algorithm from PDF pages 6-7
     * Algorithm should:
     * 1. Use a stack for operands
     * 2. Process each token in the postfix expression
     * 3. Push operands to stack, perform operations with operators
     * 4. Print step-by-step table showing Symbol, Operand1, Operand2, Value, Stack
     * 
     * @param postfixExpression The postfix expression to evaluate (space-separated)
     * @return The final result of the evaluation
     */
    public static double evaluatePostfixWithTable(String postfixExpression) {
        // TODO: Implement this method for the next coder
        // This should follow the exact algorithm from the PDF
        
        System.out.println("TODO: Implement postfix evaluation with table output");
        System.out.println("Input: " + postfixExpression);
        
        // Placeholder return - next coder should implement the full algorithm
        return 0.0;
    }
    
    /**
     * Check if character is an operand (letter or digit)
     * 
     * @param ch Character to check
     * @return true if operand, false otherwise
     */
    public static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }
    
    /**
     * Check if character is a valid operator
     * Based on PDF requirements: +, -, *, /, ^
     * 
     * @param ch Character to check
     * @return true if valid operator, false otherwise
     */
    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }
    
    /**
     * Get operator precedence
     * Based on PDF specifications:
     * 1. ^ (Exponentiation) - highest precedence
     * 2. *, / (Multiplication, Division) - medium precedence  
     * 3. +, - (Addition, Subtraction) - lowest precedence
     * 
     * @param operator The operator character
     * @return Precedence value (higher number = higher precedence)
     */
    public static int getPrecedence(char operator) {
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
     * Check if operator1 has higher or equal precedence than operator2
     * Note: Exponentiation is right-to-left associative, others are left-to-right
     * 
     * TODO: Next coder should implement proper associativity rules from PDF
     * 
     * @param operator1 First operator
     * @param operator2 Second operator
     * @return true if operator1 has higher or equal precedence
     */
    public static boolean hasHigherOrEqualPrecedence(char operator1, char operator2) {
        // TODO: Implement proper precedence comparison with associativity rules
        return getPrecedence(operator1) >= getPrecedence(operator2);
    }
    
    /**
     * Perform arithmetic operation on two operands
     * 
     * @param operand1 First operand
     * @param operand2 Second operand
     * @param operator The operator to apply
     * @return Result of the operation
     * @throws RuntimeException if division by zero or invalid operator
     */
    public static double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new RuntimeException("Division by zero");
                }
                return operand1 / operand2;
            case '^':
                return Math.pow(operand1, operand2);
            default:
                throw new RuntimeException("Invalid operator: " + operator);
        }
    }
    
    /**
     * Validate infix expression format
     * 
     * TODO: Next coder should implement proper validation
     * - Check for balanced parentheses
     * - Check for valid characters only
     * - Check for proper operator/operand sequence
     * 
     * @param expression Expression to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidInfixExpression(String expression) {
        // TODO: Implement proper validation logic
        return expression != null && !expression.trim().isEmpty();
    }
    
    /**
     * Validate postfix expression format
     * 
     * TODO: Next coder should implement proper validation
     * - Check that tokens are space-separated
     * - Check for valid operands and operators only
     * - Check that expression can be evaluated
     * 
     * @param expression Expression to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPostfixExpression(String expression) {
        // TODO: Implement proper validation logic
        return expression != null && !expression.trim().isEmpty();
    }
}