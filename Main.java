/**
 * Main class to demonstrate the functionality of Node, Stack, and Converter classes
 * This class contains the main method and various test methods to showcase
 * the implementation of data structures and algorithms
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class Main {
    
    /**
     * Main method - entry point of the program
     * Demonstrates the functionality of all implemented classes
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Data Structures Midterm Laboratory Project ===\n");
        
        // Test Node class
        testNodeClass();
        
        // Test Stack class
        testStackClass();
        
        // Test Converter class
        testConverterClass();
        
        System.out.println("\n=== All tests completed successfully! ===");
    }
    
    /**
     * Test the Node class functionality
     * Demonstrates node creation, data manipulation, and linking
     */
    private static void testNodeClass() {
        System.out.println("1. TESTING NODE CLASS");
        System.out.println("=====================");
        
        // Test default constructor
        Node<String> node1 = new Node<>();
        System.out.println("Default node: " + node1);
        
        // Test parameterized constructor
        Node<String> node2 = new Node<>("Hello");
        System.out.println("Node with data: " + node2);
        
        // Test linking nodes
        Node<String> node3 = new Node<>("World", node2);
        System.out.println("Linked node: " + node3 + " -> " + node3.getNext());
        
        // Test setters and getters
        node1.setData("First");
        node1.setNext(node2);
        System.out.println("Modified node: " + node1 + " -> " + node1.getNext());
        
        // Test hasNext method
        System.out.println("Node1 has next: " + node1.hasNext());
        System.out.println("Node2 has next: " + node2.hasNext());
        
        System.out.println();
    }
    
    /**
     * Test the Stack class functionality
     * Demonstrates all stack operations with detailed output
     */
    private static void testStackClass() {
        System.out.println("2. TESTING STACK CLASS");
        System.out.println("======================");
        
        // Create a new stack
        Stack<Integer> stack = new Stack<>();
        System.out.println("Created empty stack. Is empty: " + stack.isEmpty());
        System.out.println("Stack size: " + stack.size());
        
        // Test push operations
        System.out.println("\n--- Push Operations ---");
        for (int i = 1; i <= 5; i++) {
            stack.push(i * 10);
            System.out.println("Pushed: " + (i * 10) + ", Stack size: " + stack.size());
        }
        
        // Display stack
        System.out.println("\nStack contents:");
        stack.display();
        
        // Test peek operation
        System.out.println("\n--- Peek Operation ---");
        System.out.println("Top element (peek): " + stack.peek());
        System.out.println("Stack size after peek: " + stack.size());
        
        // Test pop operations
        System.out.println("\n--- Pop Operations ---");
        while (!stack.isEmpty()) {
            int popped = stack.pop();
            System.out.println("Popped: " + popped + ", Stack size: " + stack.size());
        }
        
        // Test with different data types
        System.out.println("\n--- Testing with Strings ---");
        Stack<String> stringStack = new Stack<>();
        String[] words = {"Java", "Data", "Structures", "Stack", "Implementation"};
        
        for (String word : words) {
            stringStack.push(word);
        }
        
        stringStack.display();
        
        // Test search functionality
        System.out.println("\nSearching for 'Stack': Position " + stringStack.search("Stack"));
        System.out.println("Searching for 'Python': Position " + stringStack.search("Python"));
        
        // Test toArray method
        System.out.println("\nStack as array:");
        Object[] stackArray = stringStack.toArray();
        for (int i = 0; i < stackArray.length; i++) {
            System.out.print(stackArray[i]);
            if (i < stackArray.length - 1) System.out.print(", ");
        }
        System.out.println();
        
        System.out.println();
    }
    
    /**
     * Test the Converter class functionality
     * Demonstrates various conversion algorithms
     */
    private static void testConverterClass() {
        System.out.println("3. TESTING CONVERTER CLASS");
        System.out.println("===========================");
        
        // Test number system conversions
        testNumberSystemConversions();
        
        // Test expression conversions
        testExpressionConversions();
        
        // Test expression evaluation
        testExpressionEvaluation();
        
        // Test parentheses balancing
        testParenthesesBalancing();
    }
    
    /**
     * Test number system conversion methods
     */
    private static void testNumberSystemConversions() {
        System.out.println("--- Number System Conversions ---");
        
        int[] testNumbers = {10, 25, 100, 255, 1024};
        
        for (int num : testNumbers) {
            System.out.println("Decimal: " + num);
            System.out.println("  Binary: " + Converter.decimalToBinary(num));
            System.out.println("  Octal: " + Converter.decimalToOctal(num));
            System.out.println("  Hexadecimal: " + Converter.decimalToHexadecimal(num));
            System.out.println();
        }
        
        // Test negative numbers
        System.out.println("Testing negative numbers:");
        System.out.println("Decimal: -42");
        System.out.println("  Binary: " + Converter.decimalToBinary(-42));
        System.out.println("  Octal: " + Converter.decimalToOctal(-42));
        System.out.println("  Hexadecimal: " + Converter.decimalToHexadecimal(-42));
        System.out.println();
    }
    
    /**
     * Test expression conversion methods
     */
    private static void testExpressionConversions() {
        System.out.println("--- Expression Conversions ---");
        
        String[] infixExpressions = {
            "A+B*C",
            "(A+B)*C",
            "A+B*C-D",
            "(A+B)*(C-D)",
            "A^B+C*D"
        };
        
        for (String infix : infixExpressions) {
            System.out.println("Infix: " + infix);
            System.out.println("  Postfix: " + Converter.infixToPostfix(infix));
            System.out.println("  Prefix: " + Converter.infixToPrefix(infix));
            System.out.println();
        }
    }
    
    /**
     * Test expression evaluation methods
     */
    private static void testExpressionEvaluation() {
        System.out.println("--- Expression Evaluation ---");
        
        String[] postfixExpressions = {
            "23+",      // 2 + 3 = 5
            "23*4+",    // 2 * 3 + 4 = 10
            "234*+",    // 2 + 3 * 4 = 14
            "52-3*",    // (5 - 2) * 3 = 9
            "92/3+"     // 9 / 2 + 3 = 7.5
        };
        
        for (String postfix : postfixExpressions) {
            try {
                double result = Converter.evaluatePostfix(postfix);
                System.out.println("Postfix: " + postfix + " = " + result);
            } catch (RuntimeException e) {
                System.out.println("Error evaluating " + postfix + ": " + e.getMessage());
            }
        }
        System.out.println();
    }
    
    /**
     * Test parentheses balancing method
     */
    private static void testParenthesesBalancing() {
        System.out.println("--- Parentheses Balancing ---");
        
        String[] expressions = {
            "(a+b)",
            "((a+b)*c)",
            "(a+b)*(c+d)",
            "((a+b)",
            "(a+b))",
            "{[a+b]*c}",
            "{[a+b]*c)",
            "((()))",
            "((())",
            ""
        };
        
        for (String expr : expressions) {
            boolean balanced = Converter.areParenthesesBalanced(expr);
            System.out.println("Expression: '" + expr + "' - Balanced: " + balanced);
        }
        System.out.println();
    }
    
    /**
     * Utility method to print a separator line
     * 
     * @param title The title to display in the separator
     */
    private static void printSeparator(String title) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(title);
        System.out.println("=".repeat(50));
    }
    
    /**
     * Method to demonstrate error handling in stack operations
     */
    private static void demonstrateErrorHandling() {
        System.out.println("--- Error Handling Demonstration ---");
        
        Stack<Integer> emptyStack = new Stack<>();
        
        try {
            emptyStack.pop();
        } catch (RuntimeException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        try {
            emptyStack.peek();
        } catch (RuntimeException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        try {
            Converter.evaluatePostfix("2+");
        } catch (RuntimeException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
        
        System.out.println();
    }
}