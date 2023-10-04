public interface AVLTreePos<E> extends BinTreePos<E> {
    int height();
    void setHeight(int h);
    default void updateHeight() {
        int leftHeight  = (left()  == null) ? -1 : ((AVLTreePos<E>)
                left()).height();
        int rightHeight = (right() == null) ? -1 :
                ((AVLTreePos<E>)right()).height();
        int newHeight = Math.max(leftHeight,rightHeight)+1;
        if ( height() != newHeight ) {
            setHeight(newHeight);
            if ( ! isRoot() )
                ((AVLTreePos<E>)parent()).updateHeight();
        }
    }
    default int getBalance() {
        int leftHeight  = (left()  == null) ? -1 : ((AVLTreePos<E>)
                left()).height();
        int rightHeight = (right() == null) ? -1 :
                ((AVLTreePos<E>)right()).height();
        /**
         Calculate the balance factor and return it
         */
        return leftHeight -  rightHeight;
    }
}