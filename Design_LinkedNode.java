. Coding，另一个中国大哥。题目是设计一个linkedNode，每个node有一个长度小于等于4的数组，
类似于[10,2,3] -> [4,13,2,5] -> [30,7]，
实现node类以及get(index), insert(index, num)。
比如get(3)返回4，
insert(2, 20)返回[10,2,20,3] -> [4,13,2,5] -> [30,7]。
如果insert之后当前数组长度大于4，
要把当前node分成两个node，比如insert(4, 50)
  返回[10,2,3] -> [4,50,13,2] ->[5] -> [30,7]
  
  
  import java.util.*;

public class HelloWorld{

     static List<Integer> numberList = new LinkedList<>();
     static LinkedNode head = null;
     public static void main(String []args){
        System.out.println("Hello World");
       
        
        
        LinkedNode list = new LinkedNode(2);
        LinkedNode list1 = new LinkedNode(4);
        LinkedNode list2 = new LinkedNode(3);
        list.array = new int[]{10, 2, 3};
        list1.array = new int[]{4, 13, 2, 5};
        list2.array = new int[]{30, 7};
        
        head = list1;
        
        addElement(numberList, list.array);
        addElement(numberList, list1.array);
        addElement(numberList, list2.array);
        
        
        list.next = list1;
        list1.next = list2;
        
        System.out.println(get(3));
        
        //insert(2, 20);
        //System.out.println(get(3));
        
        insert(4, 50);
         System.out.println(get(4));
        
     }
     
     public static int get(int index){
         return numberList.get(index);
     }
     
     public static void insert(int index, int number){
         int currIndex = 0;
         LinkedNode node = head;
         
         while (node.next != null){
             int size = node.array.length;
             
             numberList.add(index, number);
             
             if (index < 4 && size < 4){
                 int[] newArr = new int[size + 1];
                 
                 for (int i = 0; i < index; i++){
                     newArr[i] = node.array[i];
                 }
                 newArr[index + 1] = number;
                 
                 for (int i = index + 1; i <= size; i++){
                     newArr[i] = node.array[i - 1];
                 }
                 node.array = newArr;
                 break;
             } else if (index < 4 && size == 4){
                 int[] newArr = new int[size + 1];
                 
                 for (int i = 0; i < index; i++){
                     newArr[i] = node.array[i];
                 }
                 newArr[index + 1] = number;
                 
                 for (int i = index + 1; i <= size; i++){
                     newArr[i] = node.array[i - 1];
                 }
                 node.array = newArr;
                 
                  LinkedNode newList = new LinkedNode(1);
                  
                  LinkedNode next = node.next;
                  
                  newList.next = next;
                  node.next = newList;
                  break;
                  
             } else if (index >= 4){
                 index -= index;
                 node = node.next;
             }
             
         }
         
     }
     
     
     public static void addElement(List<Integer> list, int[] arry){
         for (int num : arry){
             list.add(num);
         }
     }
     
     
     static class LinkedNode{
         int[] array;
         LinkedNode next;
         
         public LinkedNode(int size){
             this.array = new int[size];
         }
     }
}

