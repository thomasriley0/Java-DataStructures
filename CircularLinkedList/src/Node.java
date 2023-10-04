public class Node<E> {
    // Instance variables:
    private E element;
    private Node<E> next;
    /** Creates a node with null references to its element and next node. */
    public Node() {
        this(null, null);
    }
    /** Creates a node with the given element and next node. */
    public Node(E e, Node<E> n) {
        element = e;
        next = n;
    }
    /** Accessor methods: */
    // return the element in the current node
    public E getElement() {
        return element;
    }

    // returns the pointer to next node
    public Node<E> getNext() {
        return next;
    }

    /** Modifier methods: */
    // setter element = to the provided value
    public void setElement(E newElem) {
        element = newElem;
    }

    // set the next pointer to the provided node
    public void setNext(Node<E> newNext) {
        next = newNext;
    }
}

