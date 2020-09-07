import cs1302.genlistadt.GenList;
import java.util.*;

/**
 * Iterator class for LinkedGenList objects.
 */
public class LinkedGenListIterator<T> implements Iterator<T> {
    private Node<T> value;

    /**
     * Makes a new {@code LinkedGenListIterator} object that iterates through {@code Node<T>} list.
     * 
     * @param list linked list for iterator
     */
    public LinkedGenListIterator(Node<T> list) {
        value = list;
    } // LinkedGenListIterator

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        if (value.getNext() != null) {
            return true;
        } else {
            return false;

        }
    } // hasNext

    /**
     * {@inheritDoc}
     */
    @Override
    public T next() {
        if (hasNext() == false) {
            throw new NoSuchElementException("No elements are left");
        } else {
            T next =  value.getNext().getElement();
            value =  value.getNext();
            return next;
        }
    } // next
} // LinkedGenListIterator
