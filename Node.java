/**
 * Node class for implementing linked list data structure
 * Used as building blocks for Stack implementation
 * 
 * @author Data Structures Group Project
 * @version 1.0
 */
public class Node {
    // Data field to store the value
    private String data;
    
    // Reference to the next node in the linked list
    private Node next;
    
    /**
     * Default constructor
     * Creates an empty node
     */
    public Node() {
        this.data = null;
        this.next = null;
    }
    
    /**
     * Parameterized constructor
     * Creates a node with specified data
     * 
     * @param data the value to be stored in the node
     */
    public Node(String data) {
        this.data = data;
        this.next = null;
    }
    
    /**
     * Parameterized constructor
     * Creates a node with specified data and next node reference
     * 
     * @param data the value to be stored in the node
     * @param next reference to the next node
     */
    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }
    
    /**
     * Getter method for data field
     * 
     * @return the data stored in the node
     */
    public String getData() {
        return data;
    }
    
    /**
     * Setter method for data field
     * 
     * @param data the value to be stored in the node
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * Getter method for next node reference
     * 
     * @return reference to the next node
     */
    public Node getNext() {
        return next;
    }
    
    /**
     * Setter method for next node reference
     * 
     * @param next reference to the next node
     */
    public void setNext(Node next) {
        this.next = next;
    }
    
    /**
     * Method to check if the node has a next node
     * 
     * @return true if next node exists, false otherwise
     */
    public boolean hasNext() {
        return next != null;
    }
    
    /**
     * String representation of the node
     * 
     * @return string containing the node's data
     */
    @Override
    public String toString() {
        return "Node{" + "data='" + data + "'}";
    }
}