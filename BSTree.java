package treelink;

import java.util.Stack;

import actividades.ItemDuplicated;
import actividades.ItemNoFound;
import actividades.Node;

public class BSTree<E extends Comparable<E>> implements LinkBST<E> {
	
	protected Node<E> root;
	public BSTree() {
		this.root = null;
	}

	@Override
	public void insert(E x) throws ItemDuplicated {
		if(isEmpty()) {
			Node<E> newNode = new Node<>(x);
			root = newNode;
		}
		else {
			Node<E> newNode = new Node<>(x);
			Node<E> ant = null;
			Node<E> current = root;
			while(current != null) {
				ant = current;
				if(current.getData().compareTo(x) == 0) {
					throw new ItemDuplicated("El nodo ya existe y no puede ser insertado");
				}
				else if(current.getData().compareTo(x)>0) {
					current = current.getLeft();
				}
				else {
					current = current.getRight();
				}
			}
			if(ant.getData().compareTo(x) > 0) {
				ant.setLeft(newNode);
			}
			else {
				ant.setRight(newNode);
			}
		}
		
	}

	@Override
	public void remove(E x) {
		Node<E> parent = null;
		Node<E> current = root;
		boolean isLeftChild = false;
		
		while(current != null && current.getData().compareTo(x) != 0) {
			parent = current;
			if(current.getData().compareTo(x) > 0) {
				current = current.getLeft();
				isLeftChild = true;
			}
			else {
				current = current.getRight();
				isLeftChild = false;
				
			}
		}
		
		if(current == null) {
			return;
		}
		
		//Caso 1:
		if(current.getLeft() == null && current.getRight() == null) {
			if(current == root) {
				root = null;
			}
			else if(isLeftChild) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		}
		
		//Caso 2:
		else if(current.getLeft() == null) {
			if(current == root) {
				root = current.getRight();
			} else if(isLeftChild) {
				parent.setLeft(current.getRight());
			} else {
				parent.setRight(current.getRight());
			}
		} else if(current.getRight() == null) {
			if(current == root) {
				root = current.getLeft();
			} else if(isLeftChild) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setRight(current.getLeft());
			}
		}
		
		//Caso 3:
		else {
            Node<E> successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
            successor.setLeft(current.getLeft());
        }
		
		
	}
	
	private Node<E> getSuccessor(Node<E> nodeToDelete) {
        Node<E> successorParent = nodeToDelete;
        Node<E> successor = nodeToDelete;
        Node<E> c2 = nodeToDelete.getRight();

        while (c2 != null) {
            successorParent = successor;
            successor = c2;
            c2 = c2.getLeft();
        }

        if (successor != nodeToDelete.getRight()) {
            successorParent.setLeft(successor.getRight());
            successor.setRight(nodeToDelete.getRight());
        }

        return successor;
    }

	@Override
	public E search(E x) throws ItemNoFound {
		Node<E> current = root;
		while(current != null) {
			if(current.getData().compareTo(x) == 0) {
				return current.getData();
			}
			if (current.getData().compareTo(x)<0) {
				current = current.getLeft();
			}
			else {
				current = current.getRight();
			}
		}
		throw new ItemNoFound("Dato no encontrado");
	}

	@Override
	public boolean isEmpty() {
		return this.root == null;
	}
	
	private Node<E> minRemove(Node<E> node){
		Node<E> parent = null;
		Node<E> current = node;
		
		while(current.getLeft() != null) {
			parent = current;
			current = current.getLeft();
		}
		return current;
	}
	
	public E minRemove()throws ItemNoFound {
		if(isEmpty()) {
			throw new ItemNoFound("El arbol esta vacio");
		}
		return minRemove(root).getData();
	}
	
	public int countNodes() {
		if (root == null) {
	        return 0;
	    }

	    int count = 0;
	    Stack<Node> stack = new Stack<>();
	    stack.push(root);

	    while (!stack.isEmpty()) {
	        Node<E> current = stack.pop();

	        if (current.getLeft() != null || current.getRight() != null) {
	            count++;
	        }

	        if (current.getRight() != null) {
	            stack.push(current.getRight());
	        }

	        if (current.getLeft() != null) {
	            stack.push(current.getLeft());
	        }
	    }
	    return count;
	}
	
	public int height(E x) throws ItemNoFound {
        Node<E> node = searchRec(root, x);
        if (node == null) {
            throw new ItemNoFound("Dato no encontrado");
        }

        int height = -1;
        Node<E> current = node;

        while (current != null) {
            height++;
            if (current.getLeft() != null) {
                current = current.getLeft();
            } else if (current.getRight() != null) {
                current = current.getRight();
            } else {
                break;
            }
        }

        return height;
    }
    
	private Node<E> searchRec(Node<E> node, E x) {
        while (node != null) {
            int cmp = x.compareTo(node.getData());
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }
	
	private int countLeafNode() {
		if (root == null) {
	        return 0;
	    }

	    int leafCount = 0;
	    Stack<Node> stack = new Stack<>();
	    stack.push(root);

	    while (!stack.isEmpty()) {
	        Node current = stack.pop();

	        if (current.getLeft() == null && current.getRight() == null) {
	            leafCount++;
	        } else {
	            if (current.getRight() != null) {
	                stack.push(current.getRight());
	            }
	            if (current.getLeft() != null) {
	                stack.push(current.getLeft());
	            }
	        }
	    }

	    return leafCount;
	}
	
	public int areaBST() throws ItemNoFound {
		return countLeafNode() * height(root.getData());
	}
	
	public void iterativePreOrden() {
	    if (root == null) {
	        return;
	    }

	    Stack<Node> stack = new Stack<>();
	    stack.push(root);

	    while (!stack.isEmpty()) {
	        Node<E> current = stack.pop();
	        System.out.print(current.getData() + " "); 

	        if (current.getRight() != null) {
	            stack.push(current.getRight());
	        }
	        if (current.getLeft() != null) {
	            stack.push(current.getLeft());
	        }
	    }
	}
	
	private int countTNodes(Node<E> node) {
		if(node == null) {
			return 0;
		}
		int left = countTNodes(node.getLeft());
		int right = countTNodes(node.getRight());
		return left + right + 1;
	}
	
	public int countTotalNodes() {
		return countTNodes(root);
	}
}
