/**
 * Node class for implementing linked list-based data structures
 * This class represents a single node that can store data and reference to the next node
 * 
 * @author [Student Name]
 * @version 1.0
 * @since 2023
 */
public class Node<T> {
    
    // Instance variables
    private T data;           // Data stored in the node
    private Node<T> next;     // Reference to the next node
    
    /**
     * Default constructor
     * Initializes a node with null data and null next reference
     */
    public Node() {
        this.data = null;
        this.next = null;
    }
    
    /**
     * Parameterized constructor
     * Initializes a node with given data and null next reference
     * 
     * @param data The data to be stored in the node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Parameterized constructor
     * Initializes a node with given data and next node reference
     * 
     * @param data The data to be stored in the node
     * @param next Reference to the next node
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    
    /**
     * Getter method for data
     * 
     * @return The data stored in the node
     */
    public T getData() {
        return data;
    }
    
    /**
     * Setter method for data
     * 
     * @param data The data to be stored in the node
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * Getter method for next node reference
     * 
     * @return Reference to the next node
     */
    public Node<T> getNext() {
        return next;
    }
    
    /**
     * Setter method for next node reference
     * 
     * @param next Reference to the next node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    /**
     * toString method to display node data
     * 
     * @return String representation of the node's data
     */
    @Override
    public String toString() {
        return data != null ? data.toString() : "null";
    }
    
    /**
     * Method to check if the node has a next node
     * 
     * @return true if next node exists, false otherwise
     */
    public boolean hasNext() {
        return next != null;
    }
}