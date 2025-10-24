/**
 * Stack class implementation using linked list
 * Implements LIFO (Last In, First Out) data structure
 * Used for expression conversion algorithms
 * 
 * @author Data Structures Group Project
 * @version 1.0
 */
public class Stack {
    // Top of the stack (head of the linked list)
    private Node top;
    
    // Size of the stack
    private int size;
    
    /**
     * Default constructor
     * Creates an empty stack
     */
    public Stack() {
        this.top = null;
        this.size = 0;
    }
    
    /**
     * Method to push an element onto the stack
     * Algorithm:
     * 1. Create a new node with the given data
     * 2. Set the new node's next to current top
     * 3. Update top to point to the new node
     * 4. Increment size counter
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data the element to be pushed onto the stack
     */
    public void push(String data) {
        // Create new node
        Node newNode = new Node(data);
        
        // Set new node's next to current top
        newNode.setNext(top);
        
        // Update top to point to new node
        top = newNode;
        
        // Increment size
        size++;
    }
    
    /**
     * Method to pop an element from the stack
     * Algorithm:
     * 1. Check if stack is empty
     * 2. Store the data from top node
     * 3. Update top to point to next node
     * 4. Decrement size counter
     * 5. Return the stored data
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the element at the top of the stack
     * @throws RuntimeException if stack is empty
     */
    public String pop() {
        // Check if stack is empty
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty! Cannot pop from empty stack.");
        }
        
        // Store data from top node
        String data = top.getData();
        
        // Update top to point to next node
        top = top.getNext();
        
        // Decrement size
        size--;
        
        return data;
    }
    
    /**
     * Method to peek at the top element without removing it
     * Algorithm:
     * 1. Check if stack is empty
     * 2. Return the data from top node without modifying the stack
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return the element at the top of the stack
     * @throws RuntimeException if stack is empty
     */
    public String peek() {
        // Check if stack is empty
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty! Cannot peek at empty stack.");
        }
        
        return top.getData();
    }
    
    /**
     * Method to check if the stack is empty
     * 
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }
    
    /**
     * Method to get the size of the stack
     * 
     * @return the number of elements in the stack
     */
    public int size() {
        return size;
    }
    
    /**
     * Method to clear all elements from the stack
     * Algorithm:
     * 1. Set top to null
     * 2. Reset size to 0
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void clear() {
        top = null;
        size = 0;
    }
    
    /**
     * Method to display all elements in the stack
     * Algorithm:
     * 1. Create a temporary node starting from top
     * 2. Traverse through the stack
     * 3. Print each element
     * 
     * Time Complexity: O(n) where n is the size of the stack
     * Space Complexity: O(1)
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        
        System.out.print("Stack (top to bottom): ");
        Node current = top;
        
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
    
    /**
     * Method to check if a specific element exists in the stack
     * Algorithm:
     * 1. Create a temporary node starting from top
     * 2. Traverse through the stack
     * 3. Compare each element with the target
     * 4. Return true if found, false otherwise
     * 
     * Time Complexity: O(n) where n is the size of the stack
     * Space Complexity: O(1)
     * 
     * @param data the element to search for
     * @return true if element exists, false otherwise
     */
    public boolean contains(String data) {
        Node current = top;
        
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        
        return false;
    }
    
    /**
     * String representation of the stack
     * 
     * @return string containing stack elements from top to bottom
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack is empty!";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Stack (top to bottom): ");
        
        Node current = top;
        while (current != null) {
            sb.append(current.getData()).append(" ");
            current = current.getNext();
        }
        
        return sb.toString().trim();
    }
}