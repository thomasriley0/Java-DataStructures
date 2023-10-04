import java.lang.Comparable;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class BinSearchTree<E extends Comparable<E>>
        implements Iterable<E> {
    private BinTreePos<E> root;
    public BinSearchTree() {
        root = null;
    }

    /** root()
     * @return root of tree
     */
    public BinTreePos<E> root() { return root; }

    /** isEmpty() returns whether the tree is empty (that is, root == null)
     *
     * @return root == null
     */
    public boolean isEmpty() { return root == null; }

    /** setRoot sets the root node
     * @param r new root node
     * @return r
     */
    public BinTreePos<E> setRoot(BinTreePos<E> r) {
        root = r;
        return root;
    }

    /** toString -- returns a human-readable representation of the tree (nesting via indentation)
     */
    public String toString() {
        return "Root: " +
                (( root == null ) ? "null" : "\n" + root.toString());
    }

    /** findPos returns position where node with element e,
     or the position where node for e should be inserted,
     Returns null for an empty tree
     */
    protected BinTreePos<E> findPos(E e) {
        if ( isEmpty() )
            return null;
        BinTreePos<E> lt, rt, pos = root();
        while ( true ) {
            int comparison = e.compareTo(pos.element());
            if ( comparison == 0 )
                return pos; // this is the right position!
            if ( comparison < 0 ) {
                lt = pos.left();
                if ( lt != null )
                    pos = lt;
                else
                    break;
            } else {
                rt = pos.right();
                if ( rt != null )
                    pos = pos.right();
                else
                    break;
            } // if ... else ...
        } // while
        return pos;
    }

    /** makeNode() creates a new node of the proper type
     * @return new node with given element, parent, left & right children
     */
    public BinTreePos<E> makeNode(E e, BinTreePos<E> par,
                                  BinTreePos<E> lt, BinTreePos<E> rt) {
        return new BinTreeNode<E>(e,par,lt,rt);
    }

    /** add(E e) inserts a node with entry e provided e is not already in the tree
     * @param e element to insert into tree
     */
    public void add(E e) {
        if ( isEmpty() )
            setRoot(makeNode(e,null,null,null));
        else {
            BinTreePos<E> pos = findPos(e);
            int comparison = e.compareTo(pos.element());
            if ( comparison == 0 ) // found e in tree!
                return;
            else if ( comparison < 0 )
                pos.setLeft(makeNode(e,pos,null,null));
            else // comparison > 0
                pos.setRight(makeNode(e,pos,null,null));
        }
    }

    /** iterator() returns an in-order iterator over the elements of the nodes of this tree
     * @return iterator
     */
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private BinTreePos<E> pos = root();
            private boolean atStart = true;

            public boolean hasNext() {
                return pos != null;
            }

            public E next() {
                // invariant: all to the left of pos is already done
                if ( pos == null )
                    throw(new NoSuchElementException("BinSearchTree in-order traversal"));
                if ( atStart ) {
                    // move to left-most position before starting
                    while ( pos.left() != null )
                        pos = pos.left();
                    atStart = false;
                }
                E elt = pos.element();
                if ( pos.right() != null )
                {
                    pos = pos.right();
                    while ( pos.left() != null )
                        pos = pos.left();
                }
                else
                {
                    while ( pos.parent() != null && pos == pos.parent().right() )
                        pos = pos.parent();
                    if ( pos.parent() == null || pos.parent() == pos ) // at root
                        pos = null;
                    else
                        pos = pos.parent();
                }
                return elt;
            }
        };
        return it;
    }


    /** main() -- a simple test */
    public static void main(String[] args) {
        BinSearchTree<String> bst = new BinSearchTree<String>();
        String[] words = { "g", "c", "k", "u", "a", "w", "e", "b" };
        for ( String s : words ) {
            System.out.println("Adding word: "+s);
            bst.add(s);
        }
        System.out.println();
        System.out.println(bst);

        System.out.println("Words in order:");
        for ( String w : bst )
            System.out.print(w + ", ");
        System.out.println("That's all folks!");
    }
}

