import java.util.*;

public interface BinTreePos<E> {
    public BinTreePos<E> parent();
    public BinTreePos<E> left();
    public BinTreePos<E> right();
    public E element();
    public void setParent(BinTreePos<E> par);
    public void setLeft(BinTreePos<E> lt);
    public void setRight(BinTreePos<E> rt);
    default public boolean isRoot() {
        return (parent() == null) || (parent() == this);
    }
}
