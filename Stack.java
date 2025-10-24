/**
 * Stack class implementation using linked list (Node-based)
 * This class implements the Last-In-First-Out (LIFO) data structure
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class Stack<T> {
    
    // Instance variables
    private Node<T> top;      // Reference to the top node of the stack
    private int size;         // Current size of the stack
    
    /**
     * Default constructor
     * Initializes an empty stack
     */
    public Stack() {
        this.top = null;
        this.size = 0;
    }
    
    /**
     * Push operation - adds an element to the top of the stack
     * Algorithm:
     * 1. Create a new node with the given data
     * 2. Set the new node's next reference to current top
     * 3. Update top to point to the new node
     * 4. Increment size
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @param data The data to be pushed onto the stack
     */
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
        size++;
    }
    
    /**
     * Pop operation - removes and returns the top element from the stack
     * Algorithm:
     * 1. Check if stack is empty, throw exception if true
     * 2. Store the data from top node
     * 3. Update top to point to the next node
     * 4. Decrement size
     * 5. Return the stored data
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return The data from the top of the stack
     * @throws RuntimeException if stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty. Cannot pop from empty stack.");
        }
        
        T data = top.getData();
        top = top.getNext();
        size--;
        return data;
    }
    
    /**
     * Peek operation - returns the top element without removing it
     * Algorithm:
     * 1. Check if stack is empty, throw exception if true
     * 2. Return the data from top node
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return The data from the top of the stack
     * @throws RuntimeException if stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty. Cannot peek empty stack.");
        }
        
        return top.getData();
    }
    
    /**
     * Check if the stack is empty
     * Algorithm:
     * 1. Return true if top is null, false otherwise
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }
    
    /**
     * Get the current size of the stack
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * 
     * @return The number of elements in the stack
     */
    public int size() {
        return size;
    }
    
    /**
     * Clear all elements from the stack
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
     * Display all elements in the stack from top to bottom
     * Algorithm:
     * 1. Start from top node
     * 2. Traverse through each node and print data
     * 3. Continue until reaching null
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        
        System.out.print("Stack (top to bottom): ");
        Node<T> current = top;
        while (current != null) {
            System.out.print(current.getData());
            if (current.getNext() != null) {
                System.out.print(" -> ");
            }
            current = current.getNext();
        }
        System.out.println();
    }
    
    /**
     * Convert stack to array representation
     * Algorithm:
     * 1. Create array of size equal to stack size
     * 2. Traverse stack from top to bottom
     * 3. Store each element in array
     * 4. Return the array
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @return Array representation of stack elements
     */
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }
        
        Object[] array = new Object[size];
        Node<T> current = top;
        int index = 0;
        
        while (current != null) {
            array[index++] = current.getData();
            current = current.getNext();
        }
        
        return array;
    }
    
    /**
     * Search for an element in the stack
     * Algorithm:
     * 1. Start from top node
     * 2. Compare each node's data with target
     * 3. Return position if found (1-based from top)
     * 4. Return -1 if not found
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param target The element to search for
     * @return Position from top (1-based) if found, -1 otherwise
     */
    public int search(T target) {
        if (isEmpty() || target == null) {
            return -1;
        }
        
        Node<T> current = top;
        int position = 1;
        
        while (current != null) {
            if (current.getData().equals(target)) {
                return position;
            }
            current = current.getNext();
            position++;
        }
        
        return -1;
    }
}