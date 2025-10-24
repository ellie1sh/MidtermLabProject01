/**
 * Converter class for Infix to Postfix conversion and Postfix evaluation
 * This class implements the algorithms specified in the PDF requirements
 * for Data Structures Midterm Laboratory Project 1
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class Converter {
    
    /**
     * Convert decimal number to binary using stack
     * Algorithm:
     * 1. Create a stack to store remainders
     * 2. Divide the number by 2 repeatedly
     * 3. Push each remainder onto the stack
     * 4. Pop all elements to get binary representation
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n)
     * 
     * @param decimal The decimal number to convert
     * @return Binary representation as a string
     */
    public static String decimalToBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        
        Stack<Integer> stack = new Stack<>();
        int number = Math.abs(decimal); // Handle negative numbers
        
        // Push remainders onto stack
        while (number > 0) {
            stack.push(number % 2);
            number = number / 2;
        }
        
        // Build binary string by popping from stack
        StringBuilder binary = new StringBuilder();
        if (decimal < 0) {
            binary.append("-");
        }
        
        while (!stack.isEmpty()) {
            binary.append(stack.pop());
        }
        
        return binary.toString();
    }
    
    /**
     * Convert decimal number to octal using stack
     * Algorithm:
     * 1. Create a stack to store remainders
     * 2. Divide the number by 8 repeatedly
     * 3. Push each remainder onto the stack
     * 4. Pop all elements to get octal representation
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n)
     * 
     * @param decimal The decimal number to convert
     * @return Octal representation as a string
     */
    public static String decimalToOctal(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        
        Stack<Integer> stack = new Stack<>();
        int number = Math.abs(decimal);
        
        // Push remainders onto stack
        while (number > 0) {
            stack.push(number % 8);
            number = number / 8;
        }
        
        // Build octal string by popping from stack
        StringBuilder octal = new StringBuilder();
        if (decimal < 0) {
            octal.append("-");
        }
        
        while (!stack.isEmpty()) {
            octal.append(stack.pop());
        }
        
        return octal.toString();
    }
    
    /**
     * Convert decimal number to hexadecimal using stack
     * Algorithm:
     * 1. Create a stack to store remainders
     * 2. Divide the number by 16 repeatedly
     * 3. Push each remainder onto the stack (convert 10-15 to A-F)
     * 4. Pop all elements to get hexadecimal representation
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n)
     * 
     * @param decimal The decimal number to convert
     * @return Hexadecimal representation as a string
     */
    public static String decimalToHexadecimal(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        
        Stack<String> stack = new Stack<>();
        int number = Math.abs(decimal);
        String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", 
                             "8", "9", "A", "B", "C", "D", "E", "F"};
        
        // Push remainders onto stack
        while (number > 0) {
            stack.push(hexDigits[number % 16]);
            number = number / 16;
        }
        
        // Build hexadecimal string by popping from stack
        StringBuilder hex = new StringBuilder();
        if (decimal < 0) {
            hex.append("-");
        }
        
        while (!stack.isEmpty()) {
            hex.append(stack.pop());
        }
        
        return hex.toString();
    }
    
    /**
     * Convert infix expression to postfix using stack (Shunting Yard Algorithm)
     * Algorithm:
     * 1. Create a stack for operators
     * 2. Scan the infix expression from left to right
     * 3. If operand, add to output
     * 4. If operator, pop operators with higher/equal precedence
     * 5. Handle parentheses appropriately
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param infix The infix expression as a string
     * @return Postfix expression as a string
     */
    public static String infixToPostfix(String infix) {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            
            // Skip whitespace
            if (ch == ' ') {
                continue;
            }
            
            // If operand (digit or letter), add to output
            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            }
            // If opening parenthesis, push to stack
            else if (ch == '(') {
                operatorStack.push(ch);
            }
            // If closing parenthesis, pop until opening parenthesis
            else if (ch == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty()) {
                    operatorStack.pop(); // Remove the opening parenthesis
                }
            }
            // If operator
            else if (isOperator(ch)) {
                while (!operatorStack.isEmpty() && 
                       precedence(operatorStack.peek()) >= precedence(ch)) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(ch);
            }
        }
        
        // Pop remaining operators
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }
        
        return postfix.toString();
    }
    
    /**
     * Convert infix expression to prefix using stack
     * Algorithm:
     * 1. Reverse the infix expression
     * 2. Replace '(' with ')' and vice versa
     * 3. Convert to postfix
     * 4. Reverse the result
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param infix The infix expression as a string
     * @return Prefix expression as a string
     */
    public static String infixToPrefix(String infix) {
        // Step 1: Reverse the infix expression
        StringBuilder reversed = new StringBuilder();
        for (int i = infix.length() - 1; i >= 0; i--) {
            char ch = infix.charAt(i);
            if (ch == '(') {
                reversed.append(')');
            } else if (ch == ')') {
                reversed.append('(');
            } else {
                reversed.append(ch);
            }
        }
        
        // Step 2: Convert to postfix
        String postfix = infixToPostfix(reversed.toString());
        
        // Step 3: Reverse the postfix to get prefix
        StringBuilder prefix = new StringBuilder();
        for (int i = postfix.length() - 1; i >= 0; i--) {
            prefix.append(postfix.charAt(i));
        }
        
        return prefix.toString();
    }
    
    /**
     * Evaluate postfix expression using stack
     * Algorithm:
     * 1. Create a stack for operands
     * 2. Scan postfix expression from left to right
     * 3. If operand, push to stack
     * 4. If operator, pop two operands, perform operation, push result
     * 5. Final result is the only element left in stack
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param postfix The postfix expression as a string
     * @return The evaluated result
     * @throws RuntimeException if expression is invalid
     */
    public static double evaluatePostfix(String postfix) {
        Stack<Double> operandStack = new Stack<>();
        
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            
            // Skip whitespace
            if (ch == ' ') {
                continue;
            }
            
            // If operand (digit), push to stack
            if (Character.isDigit(ch)) {
                operandStack.push((double)(ch - '0'));
            }
            // If operator, perform operation
            else if (isOperator(ch)) {
                if (operandStack.size() < 2) {
                    throw new RuntimeException("Invalid postfix expression");
                }
                
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                double result = performOperation(operand1, operand2, ch);
                operandStack.push(result);
            }
        }
        
        if (operandStack.size() != 1) {
            throw new RuntimeException("Invalid postfix expression");
        }
        
        return operandStack.pop();
    }
    
    /**
     * Check if a character is an operator
     * 
     * @param ch The character to check
     * @return true if the character is an operator, false otherwise
     */
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }
    
    /**
     * Get the precedence of an operator
     * Algorithm:
     * 1. Return precedence value based on operator type
     * 2. Higher number means higher precedence
     * 
     * @param operator The operator character
     * @return Precedence value (higher number = higher precedence)
     */
    private static int precedence(char operator) {
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
     * Perform arithmetic operation on two operands
     * 
     * @param operand1 First operand
     * @param operand2 Second operand
     * @param operator The operator to apply
     * @return Result of the operation
     * @throws RuntimeException if division by zero or invalid operator
     */
    private static double performOperation(double operand1, double operand2, char operator) {
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
     * Check if parentheses in an expression are balanced using stack
     * Algorithm:
     * 1. Create a stack for opening brackets
     * 2. Scan expression from left to right
     * 3. Push opening brackets to stack
     * 4. For closing brackets, check if matching opening bracket exists
     * 5. Expression is balanced if stack is empty at the end
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param expression The expression to check
     * @return true if parentheses are balanced, false otherwise
     */
    public static boolean areParenthesesBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            // Push opening brackets
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // Check closing brackets
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Check if two brackets form a matching pair
     * 
     * @param opening The opening bracket
     * @param closing The closing bracket
     * @return true if they form a matching pair, false otherwise
     */
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '[' && closing == ']') ||
               (opening == '{' && closing == '}');
    }
}