/**
 * Main class for Data Structures Midterm Laboratory Group Project
 * Demonstrates expression conversion using stack data structure
 * 
 * @author Data Structures Group Project
 * @version 1.0
 */
public class Main {
    private static Converter converter;
    
    /**
     * Main method - entry point of the program
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize converter
        converter = new Converter();
        
        // Display program header
        displayHeader();
        
        // Run demonstration
        runDemonstration();
        
        // Interactive mode
        runInteractiveMode();
    }
    
    /**
     * Method to display program header and information
     */
    private static void displayHeader() {
        System.out.println("=".repeat(80));
        System.out.println("           DATA STRUCTURES MIDTERM LABORATORY GROUP PROJECT");
        System.out.println("                    EXPRESSION CONVERSION SYSTEM");
        System.out.println("=".repeat(80));
        System.out.println("This program demonstrates:");
        System.out.println("1. Stack implementation using linked list");
        System.out.println("2. Infix to Postfix conversion");
        System.out.println("3. Infix to Prefix conversion");
        System.out.println("4. Postfix expression evaluation");
        System.out.println("5. Parentheses balancing validation");
        System.out.println("=".repeat(80));
        System.out.println();
    }
    
    /**
     * Method to run demonstration with predefined examples
     */
    private static void runDemonstration() {
        System.out.println("DEMONSTRATION MODE");
        System.out.println("-".repeat(50));
        
        // Test cases for demonstration
        String[] testExpressions = {
            "A+B*C",
            "(A+B)*C",
            "A*B+C*D",
            "A+B*C-D*E",
            "((A+B)*C-D)*E",
            "A^B^C",
            "A+B*C^D-E*F"
        };
        
        for (String expression : testExpressions) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Testing Expression: " + expression);
            System.out.println("=".repeat(60));
            
            // Check if expression is balanced
            if (!converter.isBalanced(expression)) {
                System.out.println("ERROR: Expression has unbalanced parentheses!");
                continue;
            }
            
            // Convert to postfix
            String postfix = converter.infixToPostfix(expression);
            System.out.println("\nPostfix Result: " + postfix);
            
            // Convert to prefix
            String prefix = converter.infixToPrefix(expression);
            System.out.println("\nPrefix Result: " + prefix);
            
            System.out.println("\n" + "-".repeat(60));
        }
    }
    
    /**
     * Method to run interactive mode for user input
     */
    private static void runInteractiveMode() {
        System.out.println("\n\nINTERACTIVE MODE");
        System.out.println("-".repeat(50));
        System.out.println("Enter expressions to convert (type 'quit' to exit)");
        System.out.println("Supported operators: +, -, *, /, ^, %");
        System.out.println("Use parentheses for grouping");
        System.out.println();
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        while (true) {
            System.out.print("Enter infix expression: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the Expression Conversion System!");
                break;
            }
            
            if (input.isEmpty()) {
                System.out.println("Please enter a valid expression.");
                continue;
            }
            
            try {
                processExpression(input);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    /**
     * Method to process a single expression
     * 
     * @param expression the expression to process
     */
    private static void processExpression(String expression) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Processing: " + expression);
        System.out.println("=".repeat(60));
        
        // Check if expression is balanced
        if (!converter.isBalanced(expression)) {
            System.out.println("ERROR: Expression has unbalanced parentheses!");
            return;
        }
        
        // Convert to postfix
        System.out.println("\n1. INFIX TO POSTFIX CONVERSION:");
        System.out.println("-".repeat(40));
        String postfix = converter.infixToPostfix(expression);
        System.out.println("Result: " + postfix);
        
        // Convert to prefix
        System.out.println("\n2. INFIX TO PREFIX CONVERSION:");
        System.out.println("-".repeat(40));
        String prefix = converter.infixToPrefix(expression);
        System.out.println("Result: " + prefix);
        
        // Try to evaluate if expression contains only numbers
        if (isNumericExpression(expression)) {
            System.out.println("\n3. POSTFIX EVALUATION:");
            System.out.println("-".repeat(40));
            try {
                double result = converter.evaluatePostfix(postfix);
                System.out.println("Evaluation result: " + result);
            } catch (Exception e) {
                System.out.println("Cannot evaluate: " + e.getMessage());
            }
        } else {
            System.out.println("\n3. POSTFIX EVALUATION:");
            System.out.println("-".repeat(40));
            System.out.println("Expression contains variables - cannot evaluate numerically");
        }
        
        // Display summary
        System.out.println("\nSUMMARY:");
        System.out.println("-".repeat(40));
        System.out.println("Infix:   " + expression);
        System.out.println("Postfix: " + postfix);
        System.out.println("Prefix:  " + prefix);
    }
    
    /**
     * Method to check if an expression contains only numeric operands
     * 
     * @param expression the expression to check
     * @return true if expression is numeric, false otherwise
     */
    private static boolean isNumericExpression(String expression) {
        // Remove spaces and operators
        String cleaned = expression.replaceAll("[+\\-*/^%()\\s]", "");
        
        // Check if all remaining characters are digits or decimal points
        for (char ch : cleaned.toCharArray()) {
            if (!Character.isDigit(ch) && ch != '.') {
                return false;
            }
        }
        
        return !cleaned.isEmpty();
    }
    
    /**
     * Method to run stack demonstration
     */
    private static void demonstrateStack() {
        System.out.println("\nSTACK DEMONSTRATION");
        System.out.println("-".repeat(50));
        
        Stack stack = new Stack();
        
        System.out.println("Creating a new stack...");
        System.out.println("Initial state: " + stack.toString());
        
        // Push some elements
        String[] elements = {"A", "B", "C", "D", "E"};
        
        System.out.println("\nPushing elements:");
        for (String element : elements) {
            stack.push(element);
            System.out.println("Pushed '" + element + "' -> " + stack.toString());
        }
        
        System.out.println("\nStack operations:");
        System.out.println("Size: " + stack.size());
        System.out.println("Is empty: " + stack.isEmpty());
        System.out.println("Peek: " + stack.peek());
        
        System.out.println("\nPopping elements:");
        while (!stack.isEmpty()) {
            String popped = stack.pop();
            System.out.println("Popped '" + popped + "' -> " + stack.toString());
        }
        
        System.out.println("\nFinal state: " + stack.toString());
        System.out.println("Is empty: " + stack.isEmpty());
    }
    
    /**
     * Method to display algorithm explanations
     */
    private static void displayAlgorithmExplanations() {
        System.out.println("\nALGORITHM EXPLANATIONS");
        System.out.println("=".repeat(80));
        
        System.out.println("\n1. STACK IMPLEMENTATION:");
        System.out.println("   - Uses linked list with Node class");
        System.out.println("   - LIFO (Last In, First Out) principle");
        System.out.println("   - Operations: push(), pop(), peek(), isEmpty()");
        System.out.println("   - Time Complexity: O(1) for all operations");
        
        System.out.println("\n2. INFIX TO POSTFIX CONVERSION (Shunting Yard Algorithm):");
        System.out.println("   - Scan expression from left to right");
        System.out.println("   - Operands: add to output");
        System.out.println("   - Left parenthesis: push to stack");
        System.out.println("   - Right parenthesis: pop until left parenthesis");
        System.out.println("   - Operators: pop higher precedence, then push");
        System.out.println("   - Time Complexity: O(n)");
        
        System.out.println("\n3. INFIX TO PREFIX CONVERSION:");
        System.out.println("   - Reverse the infix expression");
        System.out.println("   - Swap parentheses");
        System.out.println("   - Apply postfix algorithm");
        System.out.println("   - Reverse the result");
        System.out.println("   - Time Complexity: O(n)");
        
        System.out.println("\n4. POSTFIX EVALUATION:");
        System.out.println("   - Scan from left to right");
        System.out.println("   - Operands: push to stack");
        System.out.println("   - Operators: pop two operands, compute, push result");
        System.out.println("   - Time Complexity: O(n)");
        
        System.out.println("\n5. PRECEDENCE RULES:");
        System.out.println("   - ^ (exponentiation): 4");
        System.out.println("   - *, /, %: 3");
        System.out.println("   - +, -: 2");
        System.out.println("   - ( (parentheses): 1");
    }
}