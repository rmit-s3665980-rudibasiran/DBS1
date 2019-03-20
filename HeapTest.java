import java.util.Scanner;

/** Class HeapTest **/
public class HeapTest
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Heap Test\n\n");
        System.out.println("Enter size of heap");
        Heap h = new Heap(scan.nextInt() );
 
        char ch;
        /**  Perform Heap operations  **/
        do    
        {
            System.out.println("\nHeap Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete item with max key ");
            System.out.println("3. check empty");
 
            boolean chk;       
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter integer element to insert");
                chk = h.insert( scan.nextInt() ); 
                if (chk)
                    System.out.println("Insertion successful\n");
                else
                    System.out.println("Insertion failed\n");                    
                break;                          
            case 2 : 
                System.out.println("Enter integer element to delete");
                if (!h.isEmpty())
                    h.remove();
                else
                    System.out.println("Error. Heap is empty\n");   
                break;                         
            case 3 : 
                System.out.println("Empty status = "+ h.isEmpty());
                break;         
            default : 
                System.out.println("Wrong Entry \n ");
                break;       
            }
 
            /** Display heap **/
            h.displayHeap();  
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}