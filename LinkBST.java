package treelink;

import actividades.ItemDuplicated;
import actividades.ItemNoFound;
import actividades.Node;

public interface LinkBST<E> {
	void insert(E x) throws ItemDuplicated;
	void remove(E x);
	E search(E x) throws ItemNoFound;
	boolean isEmpty();
	E minRemove() throws ItemNoFound;
	int countNodes();
	int height(E x) throws ItemNoFound;
	int areaBST() throws ItemNoFound;
	void iterativePreOrden();
	String toString();
	int countTotalNodes();
}
