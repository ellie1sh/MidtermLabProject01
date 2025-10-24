import java.util.Scanner;

/**
 * Main class - Menu-based console application for Infix to Postfix conversion
 * and Postfix expression evaluation as specified in the PDF requirements
 * 
 * Data Structures Midterm Laboratory Project 1
 * Activity: Conversion of infix to postfix expression and Evaluation of a postfix expression
 * 
 * NOTE: This provides the basic menu structure. The actual conversion and evaluation
 * logic in the Converter class needs to be implemented by the next coder.
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Main method - entry point of the program
     * Provides menu-based interface as required by PDF
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        displayWelcomeMessage();
        displaySupportedOperators();
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            displayMainMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    handleInfixToPostfixConversion();
                    break;
                case 2:
                    handlePostfixEvaluation();
                    break;
                case 3:
                    displaySupportedOperators();
                    break;
                case 4:
                    runTestExamples();
                    break;
                case 5:
                    continueProgram = false;
                    System.out.println("\nThank you for using the Expression Converter!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            if (continueProgram) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Display welcome message and project information
     */
    private static void displayWelcomeMessage() {
        System.out.println("===============================================");
        System.out.println("    DATA STRUCTURES MIDTERM PROJECT 1");
        System.out.println("===============================================");
        System.out.println("Activity: Conversion of infix to postfix expression");
        System.out.println("          and Evaluation of postfix expression");
        System.out.println("===============================================\n");
    }
    
    /**
     * Display information about supported operators
     */
    private static void displaySupportedOperators() {
        System.out.println("SUPPORTED OPERATORS:");
        System.out.println("  + : Addition");
        System.out.println("  - : Subtraction");
        System.out.println("  * : Multiplication");
        System.out.println("  / : Division");
        System.out.println("  ^ : Exponentiation");
        System.out.println("  ( ) : Parentheses for grouping");
        System.out.println("\nOPERATOR PRECEDENCE (highest to lowest):");
        System.out.println("  1. ^ (Exponentiation) - Right-to-left associativity");
        System.out.println("  2. *, / (Multiplication, Division) - Left-to-right");
        System.out.println("  3. +, - (Addition, Subtraction) - Left-to-right");
        System.out.println();
    }
    
    /**
     * Display the main menu
     */
    private static void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Convert Infix to Postfix Expression");
        System.out.println("2. Evaluate Postfix Expression");
        System.out.println("3. View Supported Operators");
        System.out.println("4. Run Test Examples from PDF");
        System.out.println("5. Exit");
        System.out.print("\nEnter your choice (1-5): ");
    }
    
    /**
     * Get menu choice from user with input validation
     * 
     * @return Valid menu choice (1-5)
     */
    private static int getMenuChoice() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    System.out.print("Please enter a number between 1 and 5: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    
    /**
     * Handle infix to postfix conversion
     * TODO: Next coder needs to implement the actual conversion logic in Converter class
     */
    private static void handleInfixToPostfixConversion() {
        System.out.println("\n=== INFIX TO POSTFIX CONVERSION ===");
        System.out.println("NOTE: Input expression should not have spaces between characters.");
        System.out.println("Example: A+B*C or (A+B)*C");
        System.out.print("\nEnter infix expression: ");
        
        String infixExpression = scanner.nextLine().trim();
        
        if (infixExpression.isEmpty()) {
            System.out.println("Error: Empty expression entered.");
            return;
        }
        
        // TODO: Next coder should implement this method in Converter class
        String result = Converter.convertInfixToPostfixWithTable(infixExpression);
        
        System.out.println("Result: " + result);
        System.out.println("\nNOTE: The next coder needs to implement the full conversion algorithm");
        System.out.println("with step-by-step table output as specified in the PDF.");
    }
    
    /**
     * Handle postfix expression evaluation
     * TODO: Next coder needs to implement the actual evaluation logic in Converter class
     */
    private static void handlePostfixEvaluation() {
        System.out.println("\n=== POSTFIX EXPRESSION EVALUATION ===");
        System.out.println("NOTE: Operands and operators must be separated by spaces.");
        System.out.println("Example: 6 2 3 + - 3 8 2 / + * 2 ^ 3 +");
        System.out.print("\nEnter postfix expression: ");
        
        String postfixExpression = scanner.nextLine().trim();
        
        if (postfixExpression.isEmpty()) {
            System.out.println("Error: Empty expression entered.");
            return;
        }
        
        // TODO: Next coder should implement this method in Converter class
        double result = Converter.evaluatePostfixWithTable(postfixExpression);
        
        System.out.println("Result: " + result);
        System.out.println("\nNOTE: The next coder needs to implement the full evaluation algorithm");
        System.out.println("with step-by-step table output as specified in the PDF.");
    }
    
    /**
     * Run test examples from the PDF
     * TODO: Next coder should implement the actual algorithms to make these tests work
     */
    private static void runTestExamples() {
        System.out.println("\n=== TEST EXAMPLES FROM PDF ===");
        System.out.println("NOTE: These are placeholder tests. The next coder needs to implement");
        System.out.println("the actual conversion and evaluation algorithms in the Converter class.");
        
        // Test 1: Infix to Postfix conversion example from PDF
        System.out.println("\n1. Infix to Postfix Conversion Test:");
        System.out.println("   PDF Example: ((A-(B+C))*D)^(E+F)");
        System.out.println("   Expected Result: A B C + - D * E F + ^");
        
        String pdfInfixExample = "((A-(B+C))*D)^(E+F)";
        String conversionResult = Converter.convertInfixToPostfixWithTable(pdfInfixExample);
        
        // Test 2: Postfix evaluation example from PDF
        System.out.println("\n2. Postfix Evaluation Test:");
        System.out.println("   PDF Example: 6 2 3 + - 3 8 2 / + * 2 ^ 3 +");
        System.out.println("   Expected Result: 52");
        
        String pdfPostfixExample = "6 2 3 + - 3 8 2 / + * 2 ^ 3 +";
        double evaluationResult = Converter.evaluatePostfixWithTable(pdfPostfixExample);
        
        // Additional test cases for the next coder
        System.out.println("\n3. Additional Test Cases for Next Coder:");
        
        String[] additionalInfixTests = {
            "A+B*C",      // Expected: A B C * +
            "(A+B)*C",    // Expected: A B + C *
            "A+B*C-D",    // Expected: A B C * + D -
            "A^B+C*D"     // Expected: A B ^ C D * +
        };
        
        for (String infix : additionalInfixTests) {
            System.out.println("\nTest Input: " + infix);
            String result = Converter.convertInfixToPostfixWithTable(infix);
        }
        
        System.out.println("\n=== INSTRUCTIONS FOR NEXT CODER ===");
        System.out.println("1. Implement convertInfixToPostfixWithTable() in Converter class");
        System.out.println("2. Implement evaluatePostfixWithTable() in Converter class");
        System.out.println("3. Follow the exact algorithms from PDF pages 4-7");
        System.out.println("4. Include step-by-step table output as shown in PDF examples");
        System.out.println("5. Handle operator precedence and associativity correctly");
    }
}