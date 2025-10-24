import java.util.Scanner;

/**
 * Main class - Menu-based console application for Infix to Postfix conversion
 * and Postfix expression evaluation as specified in the PDF requirements
 * 
 * Data Structures Midterm Laboratory Project 1
 * Activity: Conversion of infix to postfix expression and Evaluation of a postfix expression
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static InfixToPostfixConverter converter = new InfixToPostfixConverter();
    private static PostfixEvaluator evaluator = new PostfixEvaluator();
    
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
        
        InfixToPostfixConverter.ConversionResult result = converter.convertWithTable(infixExpression);
        
        if (result.isSuccess()) {
            System.out.println("\nCONVERSION SUCCESSFUL!");
            System.out.println("Postfix Expression: " + result.getPostfixExpression());
        } else {
            System.out.println("\nCONVERSION FAILED!");
            System.out.println(result.getErrorMessage());
        }
    }
    
    /**
     * Handle postfix expression evaluation
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
        
        PostfixEvaluator.EvaluationResult result = evaluator.evaluateWithTable(postfixExpression);
        
        if (result.isSuccess()) {
            System.out.println("\nEVALUATION SUCCESSFUL!");
            System.out.printf("Final Result: %.2f%n", result.getResult());
        } else {
            System.out.println("\nEVALUATION FAILED!");
            System.out.println(result.getErrorMessage());
        }
    }
    
    /**
     * Run test examples from the PDF
     */
    private static void runTestExamples() {
        System.out.println("\n=== RUNNING TEST EXAMPLES FROM PDF ===");
        
        // Test 1: Infix to Postfix conversion example from PDF
        System.out.println("\n1. Testing Infix to Postfix Conversion:");
        System.out.println("   Example from PDF: ((A-(B+C))*D)^(E+F)");
        
        String pdfInfixExample = "((A-(B+C))*D)^(E+F)";
        InfixToPostfixConverter.ConversionResult conversionResult = converter.convertWithTable(pdfInfixExample);
        
        // Test 2: Postfix evaluation example from PDF
        System.out.println("\n2. Testing Postfix Evaluation:");
        System.out.println("   Example from PDF: 6 2 3 + - 3 8 2 / + * 2 ^ 3 +");
        
        String pdfPostfixExample = "6 2 3 + - 3 8 2 / + * 2 ^ 3 +";
        PostfixEvaluator.EvaluationResult evaluationResult = evaluator.evaluateWithTable(pdfPostfixExample);
        
        // Additional test cases
        System.out.println("\n3. Additional Test Cases:");
        
        String[] additionalInfixTests = {
            "A+B*C",
            "(A+B)*C", 
            "A+B*C-D",
            "A^B+C*D"
        };
        
        for (String infix : additionalInfixTests) {
            System.out.println("\nTesting: " + infix);
            converter.convertWithTable(infix);
        }
    }
}