import java.util.List;
import java.lang.Object;

public class BinTree<E> {
	private BinTreePos<E> root;
	public BinTree() {
		root = null;
	}

	public BinTreePos<E> root() { return root; }
	public boolean isEmpty() { return root == null; }
	public BinTreePos<E> setRoot(BinTreePos<E> r) {
		root = r;
		return root;
	}
	public String toString() {
		return "Root: " +
				(( root == null ) ? "null" : "\n" + root.toString());
	}
}