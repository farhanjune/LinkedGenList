import cs1302.genlistadt.GenList;
import java.util.*;
import java.util.function.*;

/** 
 * Generic linked list object. 
 */
public class LinkedGenList<T> implements GenList<T> {
    private Node<T> list;

    /**
     * Makes a new {@code LinkedGenList} object with an empty node.
     */
    public LinkedGenList() {
        list = new Node<T>();
    } // LinkedGenList

    /**
     * Creates a {@code LinkedGenList} object with an empty node as its first node
     * and adds a deep copy of the specified list to the end.
     * 
     * @param <U>   type of the objects in list
     * @param other list of elements to be copied
     */
    public <U extends T> LinkedGenList(GenList<U> other) {
        list = new Node<T>();
        Node<T> value = list;
        for (int i = 0; i < other.size(); i++) {
            T tt = other.get(i);
            value.setNext(new Node<T>(tt, null));
            value = value.getNext();
        }
    } // LinkedGenList

    /**
     * Adds objects contained in the specified list to the end of this list.
     * {@inheritDoc}
     */
    @Override
    public <U extends T> boolean add(GenList<U> list) {
        boolean add = false;
        if (list == null) {
            throw new NullPointerException("Null list");
        }
        if (this == list) { 
            GenList<U> newList = new LinkedGenList<U>(list);
            add(newList); // Self reference
        } else {
            Node<T> value = getNode(size());
            for (int i = 0; i < list.size(); i++) {
                value.setNext(new Node<T>(list.get(i)));
                value = value.getNext();
            }
        }
        add = true;
        return add;
    } // add

    /**
     * Adds objects contained in the specified list to the specified position in
     * this list. 
     * {@inheritDoc}
     */
    @Override
    public <U extends T> boolean add(int index, GenList<U> list) {
        boolean add = false;
        if (list == null) {
            throw new NullPointerException("Null list");
        }
        if (this == list) { 
            GenList<U> newList = new LinkedGenList<U>(list);
            add(index, newList); // Self reference
        } else {
            if (index == size()) {
                add(list);
            } else {
                Node<T> front = this.list.getNext();
                for (int i = 0; i < index - 1; i++) {
                    front = front.getNext();
                }
                Node<T> back = front.getNext();
                for (int i = 0; i < list.size(); i++) {
                    front.setNext(new Node<T>(list.get(i)));
                    front = front.getNext();
                }
                front.setNext(back);
            }
            add = true;
        }
        return add;
    } // add

    /**
     * Adds specified object to specfied position in this list. 
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, T obj) {
        boolean add = false;
        if (obj == null) {
            throw new NullPointerException("Null object");
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            Node<T> front = new Node<T>();
            Node<T> temp = new Node<T>(obj, list.getNext());
            front.setNext(temp);
            list = front;
        } else if (index == size()) { 
            add(obj); // end
        } else { 
            Node<T> front = list.getNext(); // middle
            for (int i = 0; i < index - 1; i++) { 
                front = front.getNext();
            }
            Node<T> back = new Node<T>(obj, front.getNext());
            front.setNext(back); 
        }
        add = true;
        return add;
    } // add

    /**
     * Appends specified object to the end of this list. 
     * {@inheritDoc}
     */
    @Override
    public boolean add(T obj) {
        boolean add = false;
        if (obj == null) {
            throw new NullPointerException("Null Object");
        }
        Node<T>  value = list;
        while ( value.getNext() != null) {
            value =  value.getNext();
        }
        value.setNext(new Node<T>(obj));
        add = true;
        return add;
    } // add

    /**
     * Returns whether all elements of list pass the specified test. 
     * {@inheritDoc}
     */
    @Override
    public boolean allMatch(Predicate<T> p) {
        boolean allMatch = true;
        if (p == null) {
            throw new NullPointerException("Null predicate");
        } else {
            for (T tt : this) {
                if (p.test(tt) == false) {
                    allMatch = false;
                }
            }
        }
        return allMatch;
    } // allMatch

    /**
     * Returns whether at least one element of this list passes the specified test.
     * {@inheritDoc}
     */
    @Override
    public boolean anyMatch(Predicate<T> p) {
        boolean anyMatch = false;
        if (p == null) {
            throw new NullPointerException("Null predicate");
        } else {
            for (T tt : this) {
                if (p.test(tt)) {
                    anyMatch = true;
                }
            }
        }
        return anyMatch;
    } // anyMatch

    /**
     * Removes all objects from this list. 
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        Node<T> newList = new Node<T>();
        list = newList;
    } // clear

    /**
     * Returns true if list contains object. 
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T o) {
        boolean contains = false;
        if (o == null) {
            throw new NullPointerException("Null Object");
        }
        for (T tt : this) {
            if (o.equals(tt)) {
                contains = true;
            }
        }
        return contains;
    } // contains

    /**
     * Builds and returns a new {@code GenList<T>} from this list without any duplicate
     * objects. 
     * {@inheritDoc}
     */
    @Override
    public GenList<T> distinct() {
        GenList<T> newList = new LinkedGenList<T>();
        Node<T>  value = list.getNext();
        for (int i = 0; i < size(); i++) {
            if (newList.indexOf( value.getElement()) == -1) {
                newList.add( value.getElement());
            }
            value =  value.getNext();
        }
        return newList;
    } // distinct

    /**
     * Returns a new {@code Genlist<T>} with elements that pass the specified test.
     * {@inheritDoc}
     */
    @Override
    public GenList<T> filter(Predicate<T> p) {
        if (p == null) {
            throw new NullPointerException("Null predicate");
        }
        GenList<T> newList = new LinkedGenList<T>();
        for (T tt : this) {
            if (p.test(tt)) {
                newList.add(tt);
            }
        }
        return newList;
    } // filter

    /**
     * Returns a reference to object at specified position in this list.
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {
        T tt = null;
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T>  value = list.getNext(); 
        for (int i = 0; i < index; i++) {
            value =  value.getNext();
        }
        tt = value.getElement();
        return tt;
    } // get

    /**
     * Returns index of first occurrence of specified object in this list.
     * {@inheritDoc}
     */
    @Override
    public int indexOf(T obj) {
        int index = -1;
        if (obj == null) {
            throw new NullPointerException("Null Object");
        }
        for (int i = 0; i < size(); i++) {
            if (obj.equals(get(i))) {
                index = i;
            }
        }
        return index;
    } // indexOf

    /**
     * Returns true if list contains no elements. 
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    } // isEmpty

    /**
     * Returns a new iterator for elements in the list in proper sequence.
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new LinkedGenListIterator<T>(list);
        return iterator;
    } // iterator

    /**
     * Returns string representation of list with start, sep, and end. 
     * {@inheritDoc}
     */
    @Override
    public String makeString(String sep) {
        String stringStr = "";
        if (size() != 0) {
            for (int i = 0; i < size() - 1; i++) {
                stringStr = stringStr + get(i) + sep;
            }
            stringStr = stringStr + get(size() - 1);
        }
        return stringStr;
    } // makeString

    /**
     * Returns a new {@code GenList<R>} with list elements transformed by the given function. 
     * {@inheritDoc}
     */
    @Override
    public <R> GenList<R> map(Function<T, R> f) {
        if (f == null) {
            throw new NullPointerException("Null function");
        }
        GenList<R> newList = new LinkedGenList<R>();
        for (T tt : this) {
            if (f.apply(tt) == null) {
                throw new NullPointerException("Result of the function is null");
            }
            newList.add(f.apply(tt));
        }
        return newList;
    } // map

    /**
     * Returns maximal element of list. 
     * {@inheritDoc}
     */
    @Override
    public T max(Comparator<T> c) {
        if (c == null) {
            throw new NullPointerException("Null comparator");
        }
        T max = get(0);
        for (T tt : this) {
            if (c.compare(max, tt) < 0) {
                max = tt;
            }
        }
        return max;
    } // max

    /**
     * Returns minimal element of list. 
     * {@inheritDoc}
     */
    @Override
    public T min(Comparator<T> c) {
        if (c == null) {
            throw new NullPointerException("Null comparator");
        }
        T min = get(0);
        for (T tt : this) {
            if (c.compare(min, tt) > 0) {
                min = tt;
            }
        }
        return min;
    } // min

    /**
     * Returns the result of repeatedly applying some combining operation to list
     * elements. 
     * {@inheritDoc}
     */
    @Override
    public T reduce(T start, BinaryOperator<T> f) {
        if (f == null) {
            throw new NullPointerException("Null function");
        }
        T result = start;
        if (result == null) {
            throw new NullPointerException("Result of the function is null");
        }
        for (T tt : this) {
            result = f.apply(result, tt);
            if (result == null) {
                throw new NullPointerException("Result of the function is null");
            }
        }
        return result;
    } // reduce

    /**
     * Removes element at specified position in list. 
     * {@inheritDoc}
     */
    @Override
    public T remove(int index) {
        T tt = null;
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> front = list.getNext();
        if (index == 0) { 
            tt = front.getElement();
            list = front;
        } else {
            for (int i = 0; i < index - 1; i++) { 
                front = front.getNext();
            }
            tt = front.getNext().getElement();
            Node<T> back = front.getNext().getNext();
            front.setNext(back); 
        }
        return tt;
    } // remove

    /**
     * Build and returns a new {@code GenList<T>} with list objects in reverse order.
     * {@inheritDoc}
     */
    @Override
    public GenList<T> reverse() {
        GenList<T> newList = new LinkedGenList<T>();
        for (int i = size() - 1; i >= 0; i--) {
            newList.add(get(i));
        }
        return newList;
    } // reverse

    /**
     * Replaces object at specified position in the list with specified element.
     * {@inheritDoc}
     */
    @Override
    public T set(int index, T obj) {
        T tt = null;
        if (obj == null) {
            throw new NullPointerException("Null Object");
        } else if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T>  value = getNode(index + 1);
        tt =  value.getElement();
        value.setElement(obj);
        return tt;
    } // set

    /**
     * Returns number of elements in the list. 
     * {@inheritDoc}
     */
    @Override
    public int size() {
        int size = 0;
        Node<T>  value = list.getNext();
        while ( value != null) {
            size++;
            if (size > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            value =  value.getNext();
        }
        return size;
    } // size

    /**
     * Builds and returns a new {@code GenList<T>} that contains strings from list at
     * fromIndex between fromIndex and toIndex. 
     * {@inheritDoc}
     */
    @Override
    public GenList<T> splice(int fromIndex, int toIndex) {
        GenList<T> newList = new LinkedGenList<T>();
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        for (int i = fromIndex; i < toIndex; i++) {
            newList.add(get(i));
        }
        return newList;
    } // splice

    /**
     * Returns an array with all objects in this list in proper sequence from first
     * to last element. 
     * {@inheritDoc}
     */
    @Override
    public T[] toArray(IntFunction<T[]> gen) {
        T[] array = gen.apply(size());
        for (int i = 0; i < size(); i++) {
            array[i] = get(i);
        }
        return array;
    } // toArray

    /**
     * Returns node reference.
     * 
     * @param index node index
     * @return reference to node index
     */
    private Node<T> getNode(int index) {
        Node<T>  value;
        if (size() == 0) {
            value = list;
        } else {
            value = list.getNext();
            for (int i = 0; i < index - 1; i++) {
                value =  value.getNext();
            }
        }
        return value;
    } // getNode

} // LinkedGenList
