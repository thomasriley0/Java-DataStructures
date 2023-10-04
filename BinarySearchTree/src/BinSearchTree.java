import java.lang.Comparable;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class BinSearchTree<E extends Comparable<E>> extends BinTree<E> implements Iterable<E> {

  /** findPos returns position where node with element e,
   or the position where node for e should be inserted,
   Returns null for an empty tree */
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

  /** add inserts a node with entry e
   provided e is not already in the tree*/
  public void add(E e) {
    if ( isEmpty() )
      setRoot(new BinTreeNode<E>(e,null,null,null));
    else {
      BinTreePos<E> pos = findPos(e);
      int comparison = e.compareTo(pos.element());
      if (comparison == 0)
        return;
      else if (comparison < 0)
        pos.setLeft(new BinTreeNode<>(e, pos, null, null));
      else
        pos.setRight(new BinTreeNode<E>(e, pos, null, null));
    }
  }

  /** iterator returns an iterator over the elements of the nodes
   of this tree */
  public Iterator<E> iterator() {
    Iterator<E> it = new Iterator<E>() {
      private BinTreePos<E> pos = root();
      private boolean atStart = true;


      @Override
      public boolean hasNext() {
        return pos != null;
      }

      @Override
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

  /** remove(e) removes the node with the element e if present;
   returns true if e present in tree at start of call */
  boolean remove(E e) {
    BinTreePos<E> pos = findPos(e);
    if ( pos == null )
      return false;
    if ( pos.element().equals(e) ) {
      if ( pos.left() == null && pos.right() == null ) {
        if ( pos.isRoot() ) {
          setRoot(null);
        }
        else {
          BinTreePos<E> p = pos.parent();
          if (p.left() == pos) p.setLeft (null);
          if (p.right() ==  pos) p.setRight(null);
        }
      }
      else if ( pos.left() == null && pos.right() != null ) {
        if (  pos.isRoot() ) {
          pos.right().setParent(null);
          setRoot(pos.right());
        } else {
          if ( pos == pos.parent().left() ) {
            pos.parent().setLeft(pos.right());
          } else {
            pos.parent().setRight(pos.right());
          }
          pos.right().setParent(pos.parent());
        }
      }
      else if ( pos.right() == null && pos.left() != null ) {
        if (  pos.isRoot() ) {
          // pos is root
          pos.left().setParent(null);
          setRoot(pos.left());
        } else {
          if ( pos == pos.parent().left() ) {
            pos.parent().setLeft(pos.left());
          } else {
            pos.parent().setRight(pos.left());
          }
          pos.left().setParent(pos.parent());
        }
      } else {  // both pos.left() & pos.right() not null
        // use right-focused version:
        // get leftmost position of right subtree
        BinTreePos<E> sub = pos.right(); // sub for "substitute" node
        while ( sub.left() != null ){
          //<YOUR CODE HERE>
        }

          if ( sub == pos.right() ) {
            sub.setLeft(pos.left());
            pos.left().setParent(sub);
          } else {
            BinTreePos<E> subpar = sub.parent();
            subpar.setLeft(sub.right());
            sub.setLeft (pos.left());
            sub.setRight(pos.right());
            pos.left ().setParent(sub);
            pos.right().setParent(sub);
          }
        // fix parent to pos
        if ( pos.isRoot() ) {
          sub.setParent(null);
          setRoot(sub);
        } else {
          sub.setParent(pos.parent());
          if ( pos == pos.parent().left() )
          {
            pos.parent().setLeft(sub);
          }
          else
          {
            pos.parent().setRight(sub);
          }
        }
      }
      return true;
    }
    else
      return false; // e not present
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

    System.out.println();
    System.out.println("Removing u from tree");
    System.out.println("Removed? " + bst.remove("u"));
    System.out.println(bst);
    System.out.println();
    System.out.println("Now removing g from tree");
    System.out.println("Removed? " + bst.remove("g"));
    System.out.println(bst);
    System.out.println();
    System.out.println("Now removing c from tree");
    System.out.println("Removed? " + bst.remove("c"));
    System.out.println(bst);
    System.out.println();
    System.out.println("Now removing z from tree");
    System.out.println("Removed? " + bst.remove("z"));
    System.out.println(bst);
    System.out.println();
    System.out.println("Now removing a from tree");
    System.out.println("Removed? " + bst.remove("a"));
    System.out.println(bst);
    System.out.println();
    System.out.println("Now removing w from tree");
    System.out.println("Removed? " + bst.remove("w"));
    System.out.println(bst);
    System.out.println();
    System.out.println("Now removing e from tree");
    System.out.println("Removed? " + bst.remove("e"));
    System.out.println(bst);
    System.out.println();
    System.out.println("Now removing k from tree");
    System.out.println("Removed? " + bst.remove("k"));
    System.out.println(bst);
  }
}