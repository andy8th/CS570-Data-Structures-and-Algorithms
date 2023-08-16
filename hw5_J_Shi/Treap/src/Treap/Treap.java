package Treap;

//Jince Shi HW5

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

	//class Node
	
	private static class Node<E> {

		// Data fields
		public E data; //key for the research
		public int priority; // random heap priority
		public Node<E> left;
		public Node<E> right;

		// Constructors
		public Node(E data, int priority) {
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
			if (this.data == null) {
				throw new NullPointerException("Data value is null");
			}
		}

		// Methods
		public Node<E> rotateRight() {
			if (left != null) {
				Node<E> result = left;
				left = left.right;
				result.right = this;
				return result;
			}
			else {
				return this;
			}
		}
		
		public Node<E> rotateLeft() {
			if (right != null) {
				Node<E> result = right;
				right = right.left;
				result.left = this;
				return result;
			}
			else {
				return this;
			}
		}
	
		
		
	
		public String toString() {
			String strResult = "(key=" + data + ", priority=" + priority + ")";
			return strResult;
		}
	}

	// Data fields
	private Random priorityGenerator;
	private Node<E> root;

	// Constructors
	
	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
	}

	
	public Treap(long seed) {
		this.root = null;
		this.priorityGenerator = new Random(seed);
	}

	// Methods
	
	 boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}

	
	 boolean add(E key, int priority) {
		Node<E> node = new Node<E>(key, priority);
        if (this.root == null) {
            this.root = node;
            return true;
        }
        Node<E> tempNode = this.root;
        Node<E> tempParent = null;
        Stack<Node<E>> tempStack = new Stack<Node<E>>();
        while (tempNode != null) {
            tempParent = tempNode;
            if (key.compareTo(tempNode.data) < 0) {
                tempStack.push(tempParent);
            	tempNode = tempNode.left;
            }
            else if (key.compareTo(tempNode.data) > 0) {
            	tempStack.push(tempParent);
            	tempNode = tempNode.right;
            }
            else {
            	return false;
            }
        }
        if (key.compareTo(tempParent.data) < 0) {
        	tempParent.left  = node;
        }
        else {
        	tempParent.right = node;
        }
        reheap(node, tempStack);
        return true;
    }

 //methods	
	private void reheap(Node<E> node, Stack<Node<E>> tempStack) {
        if(tempStack.isEmpty()) {
        	this.root = node;
        } else if(tempStack.peek().priority > node.priority) {
			return;
		} else {
			if (tempStack.peek().right == node) {
				Node<E> tempNode = tempStack.pop();
				tempNode.rotateLeft();
				if (!tempStack.isEmpty()) {
					if (tempStack.peek().right == tempNode) {
						tempStack.peek().right = node;
					}
					else {
						tempStack.peek().left = node;
					}
				}
			}
			else {
				Node<E> tempNode2 = tempStack.pop();
				tempNode2.rotateRight();
				if (!tempStack.isEmpty()) {
					if (tempStack.peek().right == tempNode2) {
						tempStack.peek().right = node;
					}
					else {
						tempStack.peek().left = node;
					}
				}
			}
			reheap(node, tempStack);
		}
	}

	//delete
	boolean delete(E key) {

		if (find(key) == false) {
			return false;
		}
		Node<E> r = root;
		Node<E> target = null;
		Node<E> parent = root;
		Node<E> new_head = null;
		Node<E> new_parent = null;
		if (root == null) {
			return false;
		} else if (key == r.data && r.right == null && r.left == null) {
			root = null;
			return true;
		}
		while (true) {
			if (key.compareTo(r.data) < 0) {
				parent = r;
				r = r.left;
			} else if (key.compareTo(r.data) > 0) {
				parent = r;
				r = r.right;
			} else if (key.compareTo(r.data) == 0) {
				target = r;
				break;
			}
		}
		if (target.right == null && target.left == null) {
			if (parent.left == target) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			return true;
		}
		while (target.right != null || target.left != null) {
			if (target.right != null && target.left != null) {
				if (target.right.priority > target.left.priority) {
					new_head = target.rotateLeft();
					target = new_head.left;
				} else {
					new_head = target.rotateRight();
					target = new_head.right;
				}
			} else if (target.right == null && target.left != null) {
				new_head = target.rotateRight();
				target = new_head.right;
			} else if (target.right != null && target.left == null) {
				new_head = target.rotateLeft();
				target = new_head.left;
			}
			if(target.data == parent.data) {
				root = new_head;
			}
			else if (parent.left == target) {
				parent.left = new_head;
			} else {
				parent.right = new_head;
			}
			parent = new_head;
			new_parent = new_head;
		}
		if (new_parent.left == target) {
			new_parent.left = null;
		} else {
			new_parent.right = null;
		}
		return true;
	}

    
    public boolean find(E key){
		if(key==null) {
			throw new NullPointerException("Key can not be null");
		}
		return find(root, key);
	}
	
    private boolean find(Node<E> root, E key) {
		if(root != null) {
			if(root.data.compareTo(key) == 0) {
				return true;
			}
			if(root.data.compareTo(key) < 0) {
				return find(root.right, key);
			}
			return find(root.left, key);
		} else {
			return false;
		}
    }

  
    public String toString(){
		StringBuilder strB = new StringBuilder();
		return getPreOrderTraverse(root, 1, strB);
	}
	
	private String getPreOrderTraverse(Node<E>node, int depth, StringBuilder strB){
		
		for(int i=1; i< depth; i++){
			strB.append("  ");
		}		
		if(node==null)
			strB.append("null\n");
		else{
			strB.append("(key="+node.data+",priority="+node.priority+")\n\n");
			getPreOrderTraverse(node.left, depth + 1,strB);
			getPreOrderTraverse(node.right, depth + 1,strB);
		}
		return strB.toString();
		}

	public static void main(String[] args){
	
		Treap<Integer> testTree = new Treap < Integer >();

		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		System.out.println(testTree.toString());
		
		System.out.println("Find node key'7' "+ testTree.find(7));
		System.out.println("Find node key '16' "+ testTree.find(16));
		System.out.println("Delete key '7',boolean result is: "+ testTree.delete(7));
		System.out.println("Delete key '16' boolean result is: "+ testTree.delete(16));
		System.out.println("Find key '7' after deleted? "+ testTree.find(7));

		Treap<Character> testTree2 = new Treap<Character>();
		testTree2.add('a',66);
		testTree2.add('b',88);
		testTree2.add('c',77);
		testTree2.add('d',99);
		testTree2.add('e',13);
		testTree2.add('f',15);
		testTree2.add('g',17);
		System.out.println(testTree2.toString());
		testTree2.add('k',36);
		System.out.println("Delete key with 'g' exists and the boolean result is: "+ testTree2.delete('g') +"\n");
		System.out.println(testTree2.toString());
		}
	}