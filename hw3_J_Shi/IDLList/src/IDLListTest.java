public class IDLListTest {

    public static void main(String[] args) {

       
        IDLList<Integer> List = new IDLList<Integer>();

 //test add(E)
        List.add(10);
        System.out.println("Currentlist is" + List.toString());

//test add(index, E)
        List.add(0,1);
        System.out.println("Currentlist is" + List.toString());

        List.add(1,2);
        System.out.println("Currentlist is" + List.toString());
        
//test append
        List.append(20);
        System.out.println("Currentlist is" + List.toString());
      
//test get        
        System.out.println("Number at Index 1 is " + List.get(1));
        
//test getHead        
        System.out.println("Head is " + List.getHead());
     
//test getLast
        System.out.println("Last element in the list is " + List.getLast());
        
//test size
        System.out.println("List size is " + List.size());

        System.out.println("Currentlist is" + List.toString());
        
//test remove()        
        System.out.println("Element " + List.remove() + " in the list was removed");
        
        
        System.out.println("Currentlist is" + List.toString());

//test removeLast()
        System.out.println("Remove " + List.removeLast() + " which is the last element in the list");
        
        System.out.println("Currentlist is" +List.toString());
        
        List.append(30);
        
        System.out.println("Currentlist is" +List.toString());
        
 //test removeAt()       
        List.removeAt(1);
        
        System.out.println("Currentlist is" + List.toString());

//test remove(boolean)
        System.out.println(List.remove(2));
        System.out.println("Currentlist is" + List.toString());
        System.out.println(List.remove(2));
    }

}