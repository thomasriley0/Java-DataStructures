import java.util.Iterator;
import java.lang.Object;

public class BinTreeNode<E> implements BinTreePos<E> {
    private E elt;
    private BinTreePos<E> parent;
    private BinTreePos<E> left, right;
    public BinTreeNode(E e, BinTreePos<E> par,
                       BinTreePos<E> lt, BinTreePos<E> rt) {
        elt = e;
        parent = par;
        left = lt;
        right = rt;
    }

    /** Parent of current node */
    public BinTreePos<E> parent() { return parent; }
    /** Left child */
    public BinTreePos<E> left()   { return left; }
    /** Right child */
    public BinTreePos<E> right()  { return right; }
    /** Number of children */
    public int numChildren()
    {
        if ( left == null )
            return ( right == null ) ? 0 : 1;
        else
            return ( right == null ) ? 1 : 2;
    }
    /** Element of current element */
    public E element() { return elt;  }
    /** set parent pointer */
    public void setParent(BinTreePos<E> newpar)
    {
        BinTreeNode<E> temp = (BinTreeNode<E>)(newpar); // Is newpar a TreeNode?
        parent = newpar;
    }
    public void setLeft(BinTreePos<E> lt)
    {
        BinTreeNode<E> temp = (BinTreeNode<E>)(lt); // Is lt a TreeNode?
        left = lt;
    }
    public void setRight(BinTreePos<E> rt)
    {
        BinTreeNode<E> temp = (BinTreeNode<E>)(rt); // Is rt a TreeNode?
        right = rt;
    }

    public String toString() {
        return toStringRec(1);
    }

    private String toStringRec(int depth) {
        String spaces = new String(new char[2*depth]).replace('\0', ' ');
        String s = spaces + "Element: " + elt.toString() + "\n";
        String leftstring  = ( left  == null ) ? "" :
                ((BinTreeNode<E>) left).toStringRec(depth+1);
        String rightstring = ( right == null ) ? "" :
                ((BinTreeNode<E>)right).toStringRec(depth+1);
        if ( left != null )
            s += spaces + "Left:\n" + leftstring;
        if ( right != null )
            s += spaces + "Right:\n" + rightstring;
        return s;
    }
}

