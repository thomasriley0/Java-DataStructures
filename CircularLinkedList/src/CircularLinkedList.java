import java.util.Scanner;

public class CircularLinkedList <E> {
    // Instance variables. You cannot add or remove these starting variables
    private Node<E> tail;
    private int size;

    // Default Constructor sets the tail-->null and size-->0
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }
 
 
    /** Access Methods */
    // returns current size
    public int getSize() {
        return size;
    }

    // return true if the list is empty
    public boolean isEmpty() {
        return (size == 0);
    }

    // Returns the value of first element (head) of the list
    public E getFirst() {
        if(!isEmpty()) return tail.getNext().getElement();
        return null;
    }
    // Returns the value of last element (tail) of the list
    public E getLast() {
        if (!isEmpty()) return tail.getElement();
        return null;


    }
    /** Update methods */
    // Left Rotate the elements in the list. (Hint: Tail becomes tail.getNext())
    // list                 --> [1,2,3,4,5]
    // list_after_rotate_1  --> [2,3,4,5,1]
    // list_after_rotate_2  --> [3,4,5,1,2]
    public void rotate(){
        if (tail != null) {
            tail = tail.getNext();
        }
    }
    // Add an element at the start of the list. (Hint: The first element of a circular linked list is tail.getNext())
    public void addFirst(E e) {
        if (isEmpty()) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> item = new  Node<>(e, tail.getNext());
            tail.setNext(item);
        }
        size += 1;
    }
    // Adding the element at the tail. Question: Can you implement this function using addFirst and rotate?
    // list = [1,2,3,4,5]
    // list.addLast(6) => [1,2,3,4,5,6]
    public void addLast(E e) {
        addFirst(e);
        rotate();
    }
    // Remove the first element of the list and return the removed element.
    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> front = tail.getNext();
        if (size == 1) {
            E deleteElement = tail.getElement();
            tail = null;
            size -= 1;
            return deleteElement;
        } else {
            tail.setNext(front.getNext());
            size -= 1;
            return front.getElement();
        }
    }

    // Prints out the list elements.
    // IF these are the elements of the linked list, then they will be matched with the corresponding output
    // 1)--> "prius", "rav4", "subaru", "crv", "pilot"
    // 2)--> 1,2,3,4,5
    // 3)--> []
    // Outputs
    // 1)-->[prius, rav4, subaru, crv, pilot, prius, rav4, subaru, crv, pilot]
    // 2)-->[1, 2, 3, 4, 5, 1, 2, 3, 4, 5]
    // 3)-->[empty list]
    public String toString(){
        String elementString = "";
        Node<E> tail2;
        int i = 0;
        if (tail == null) return "emptyawq";
        tail2 = tail.getNext();
        if (tail2 == null) return "empty";
        while (tail2 != null && i < 2 * size) {
            elementString = elementString + tail2.getElement();
            if (tail2.getNext() != null) {
                elementString = elementString + ", ";
                tail2 = tail2.getNext();
            }
        }
        return elementString;
    }


    public static void main(String args[]){
        String[] cars = { "prius", "rav4", "subaru", "crv", "pilot"};

        CircularLinkedList<String> carsList = new CircularLinkedList<String>();
        for (String i: cars)
            carsList.addLast(i);

        System.out.println("linkedList:"+ carsList.toString());
        // Output for this should be --> linkedList:[prius, rav4, subaru, crv, pilot, prius, rav4, subaru, crv, pilot]
    }
}
