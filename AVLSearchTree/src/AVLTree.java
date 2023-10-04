import java.lang.Comparable;

public class AVLTree<E extends Comparable<E>> extends BinSearchTree<E> {
    @Override
    public BinTreePos<E> makeNode(E e, BinTreePos<E> par, BinTreePos<E> lt, BinTreePos<E> rt) {
        return new AVLTreeNode<E>(e,par,lt,rt);
    }

    public void rotateRight(AVLTreeNode<E> n) {
        AVLTreeNode<E> nleft = (AVLTreeNode<E>)(n.left());
        AVLTreeNode<E> lrChild = (AVLTreeNode<E>)nleft.right();

        if ( n.isRoot() ) {
            setRoot(nleft);
            nleft.setParent(null);
        } else {
            AVLTreeNode<E> par = (AVLTreeNode<E>)n.parent();
            if ( n == par.left() ) {
                par.setLeft(nleft);
            } else if ( n == par.right() ) {
                par.setRight(nleft);
            }
            nleft.setParent(par);
        }
        nleft.setRight(n);
        n.setParent(nleft);
        n.setLeft(lrChild);
        if ( lrChild != null ) {
            lrChild.setParent(n);
            lrChild.updateHeight();
        }
        n.updateHeight();
    }

    public void rotateLeft(AVLTreeNode<E> n) {
        AVLTreeNode<E> nright = (AVLTreeNode<E>)(n.right());
        AVLTreeNode<E> rlChild = (AVLTreeNode<E>)nright.left();

        if ( n.isRoot() ) {
            setRoot(nright);
            nright.setParent(null);
        } else {
            AVLTreeNode<E> par = (AVLTreeNode<E>)(n.parent());
            if ( n == par.right() ) {
                par.setRight(nright);
            } else if ( n == par.left() ) {
                par.setLeft(nright);
            }
            nright.setParent(par);
        }
        nright.setLeft(n);
        n.setParent(nright);
        n.setRight(rlChild);
        if ( rlChild != null ) {
            rlChild.setParent(n);
            rlChild.updateHeight();
        }
        n.updateHeight();
    }

    public void rebalance(BinTreePos<E> pos) {
        if ( pos == null )
            return;
        AVLTreeNode<E> avlpos = (AVLTreeNode<E>)pos;
        avlpos.updateHeight();
        if (avlpos.getBalance() == -2) {
            AVLTreeNode<E> child = (AVLTreeNode<E>)(pos.right());
            if (child.getBalance()== 1)
                rotateRight(child);
            rotateLeft(avlpos);
        } else if (avlpos.getBalance() == 2) {
            AVLTreeNode<E> child = (AVLTreeNode<E>)(pos.left());
            if (child.getBalance()== -1)
                rotateLeft(child);
             rotateRight(avlpos);
        }
    }

    @Override
    public void add(E e) {
        if ( isEmpty() ) {
            setRoot(makeNode(e,null,null,null));
        }
        else {
            AVLTreePos<E> pos = (AVLTreePos<E>)findPos(e);
            int comparison = e.compareTo(pos.element());
            if ( comparison == 0 )
                return; // found e; nothing to add
            if (comparison < 0)
                pos.setLeft(makeNode (e, pos,null,null));
            else
                pos.setRight(makeNode(e,pos,null,null));
            pos.updateHeight();
            pos = (AVLTreePos<E>)pos.parent();
            while (pos != null) {
                rebalance(pos);
                pos = (AVLTreePos<E> )pos.parent();
            }
        }
    }

    @Override
    public String toString() {
        return "AVLRoot: " +
                (( root() == null ) ? "null" : "\n" + root().toString());
    }

    public static void main(String[] args) {
        String[] birds = { "heron", "eagle", "woodpecker", "kookaburra", "cardinal",
                "swallow", "puffin", "ostritch", "flamingo", "goose",
                "duck", "budgerigar", "magpie", "loon", "toucan", "ibis", "vulture",
                "yellowthroat", "quail", "gull", "raven", "jay", "albatross" };
        AVLTree<String> avlt = new AVLTree<String>();
        for ( String bird : birds ) {
            System.out.println("Adding "+bird);
            avlt.add(bird);
            System.out.println(avlt);
        }
        System.out.println();
        System.out.println("Alphabetical list of birds:");
        for ( String bird : avlt )
            System.out.println(bird);
    }
}
