/**  
 * Represents a node in a linked list.
 */
public class Node<T> {
    private T element;
    private Node<T> next;
    
    /** 
     * Sets {@code element} and {@code next} to null.  
     */
    public Node() {
        element = null;
        next = null;
    } // Node

    /**
     * Creates a node to stores {@code element} with no {@code next} value.
     * 
     * @param tt element stored in the node
     */
    public Node(T tt) {
        element = tt;
        next = null;
    } // Node

    /**
     * Creates a node to store {@code element} and {@code next} values.
     * 
     * @param tt element stored in node
     * @param node node that follows current node
     */
    public Node(T tt, Node<T> node) {
        element = tt;
        next = node;
    } // Node

    /**
     * Returns the element stored in the current node.
     * 
     * @return element stored in node
     */
    public T getElement() {
        return element;
    } // getElement

    /**
     * Sets the current node's element.
     * 
     * @param tt element to be set in the node
     */
    public void setElement(T tt) {
        element = tt;
    } // setElement

    /**
     * Returns a reference to the node that follows current node.
     * 
     * @return reference to the next node
     */
    public Node<T> getNext() {
        return next;
    } // getNext

    /**
     * Sets {@code next} to the specified node.
     * 
     * @param node node that follows current node
     */
    public void setNext(Node<T> node) {
        next = node;
    } // setNext

} // Node
