//Jince Shi HW3
import java.util.ArrayList;

public class IDLList<E> {
	
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    private static class Node<E> {
         E data;
         Node<E> next;
         Node<E> prev;

         Node(E elem) {
            this.data = elem;
            this.prev = null;
			this.next = null;
        }

         Node (E elem, Node<E> prev, Node<E> next) 
         {
            this.data = elem;
            this.prev = prev;
            this.next = next;
        }
    }
    

  
    public IDLList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<Node<E>>();
    }

  
    public boolean add(int index, E elem) {

        
        if (index < 0 || index >= indices.size()) {
            throw new IndexOutOfBoundsException(Integer.toString(index));

        }

        if (index == 0) {
         
            add(elem);

            return true;

        } else {
            Node<E> node = indices.get(index);

            Node<E> newNode = new Node<>(elem);

            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
            size++;

            indices.add(index, newNode);

            return true;


        }

    }

    
    public boolean add(E elem) {

        if (head == null) {
            head = new Node<>(elem);
            tail = head;
            size++;

          
            indices.add(0,head);

            return true;

        } else {

           
            Node<E> firstNode = new Node<>(elem);

            firstNode.next = head;
            head.prev = firstNode;
            head = firstNode;
            size++;

            
            indices.add(0, firstNode);

            return true;

        }
    }

  
    public boolean append(E elem) {

       
        if (head == null) {
            add(elem);
            return true;
        }
        else {
        Node<E> lastNode = new Node<>(elem);

        tail.next = lastNode;
        lastNode.prev = tail;
        tail = lastNode;
        size++;

      
        indices.add(lastNode);
        return true;
    }}

   
    public E get(int index) {

        return indices.get(index).data;

    }

  
    public E getHead () {

    	Node<E> Head = indices.get(0);
		return Head.data;
    }

    
    public E getLast() {

    	Node<E> Tail = indices.get(size-1);
		return Tail.data;
    }

   
    public int size() {

        return indices.size();
    }

   
    public E remove() {

        if (head == null) return null;


        Node<E> temp = head;

       
        head = head.next;
        head.prev = null;
        size--;
        indices.remove(temp);

        return temp.data;

    }

    
    public E removeLast() {

        if (tail == null) return null;

        Node<E> temp = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        indices.remove(temp);

        return temp.data;

    }

    
    public E removeAt(int index) {

        
        if (index < 0 || index >= indices.size()) {
            throw new IndexOutOfBoundsException(Integer.toString(index));

        }

        if (index == 0) return remove();
        if (index == indices.size() - 1) return removeLast();

       
        Node<E> removeNode = indices.get(index);
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        size--;


        indices.remove(removeNode);

        return removeNode.data;

    }

    public boolean remove(E elem) {

        for (int i = 0; i < indices.size(); i++) {

            if (indices.get(i).data == elem) {
                removeAt(i);
                return true;
            }


        }

        return false;

    }


    public String toString() {

      
        Node<E> current = head;
        String resultlist = "";
        while ( current!= null) {
            resultlist += " " + current.data.toString();
            current = current.next;
        }

        return resultlist;
    }


}