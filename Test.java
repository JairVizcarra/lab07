package treelink;

import actividades.ItemDuplicated;
import actividades.ItemNoFound;

public class Test {

	public static void main(String[] args) throws ItemDuplicated, ItemNoFound {
		// TODO Auto-generated method stub
		LinkBST<Integer> integerTreeLink = new BSTree<>();
		
		integerTreeLink.insert(10);
		integerTreeLink.insert(15);
		integerTreeLink.insert(7);
		integerTreeLink.insert(20);
		integerTreeLink.insert(5);
		integerTreeLink.insert(11);
		
		

		System.out.println("Nodo minimo: " + integerTreeLink.minRemove());
		System.out.println("Cantidad de nodos: " + integerTreeLink.countNodes());
		System.out.println("Altura del arbol: " + integerTreeLink.height(7));
		System.out.println("Area del arbol: " + integerTreeLink.areaBST());
		System.out.println("Cantidad total de nodos: " + integerTreeLink.countTotalNodes());
		integerTreeLink.iterativePreOrden();
	}
	
	public boolean sameArea(BSTree tree1,BSTree tree2 ) throws ItemNoFound {
		return tree1.areaBST() == tree2.areaBST();
	}

}
