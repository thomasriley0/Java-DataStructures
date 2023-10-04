import java.lang.Math;

public class AVLTreeNode<E> extends BinTreeNode<E> implements AVLTreePos<E> {
    private int height; // height of node
    public int height() {
        return height;
    }
    public void setHeight(int ht) {
        height = ht;
    }
    public AVLTreeNode(E e, BinTreePos<E> par, BinTreePos<E> lt, BinTreePos<E> rt) {
        super(e,par,lt,rt);
        height = 0;
    }

    public String toString() {
        return toStringRec(1);
    }

    private String toStringRec(int depth) {
        String spaces = new String(new char[2*depth]).replace('\0', ' ');
        String s = spaces + "Element: " + element().toString() + ";  Height: " + height + "\n";
        String leftstring  = ( left()  == null ) ? "" :
                ((AVLTreeNode<E>) left()).toStringRec(depth+1);
        String rightstring = ( right() == null ) ? "" :
                ((AVLTreeNode<E>)right()).toStringRec(depth+1);
        if ( left() != null )
            s += spaces + "Left:\n" + leftstring;
        if ( right() != null )
            s += spaces + "Right:\n" + rightstring;
        return s;
    }
}