一个“book”，内容是按 list of pages 给的，比如：{"mary has a", "little lamb", "little lamb"} 
这样的，分别是这本书的前三页。然后给定一个单词，
让找它的neighbors。比如给"little", 返回 {"a", "lamb", "lamb", "lamb"}. 
  FU是处理标点符号和一些edge cas‍‍‍‍‍‍‍‍‌‍‌‌‍‌‌‌‌‌e。还一个FU忘记了。


import java.util.*;

public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
        
        String[] books = {"mary has a", "little lamb", "little lamb"} ;
        
        List<String> result = findNeighbors(books, "little");
        
        for (String str : result){
            System.out.println(str);
        }
     }
     
     static  Node head = new Node("");
     static  Node tail = new Node("");
     
     public static List<String> findNeighbors(String[] books, String target){
         List<String> result = new ArrayList<>();
         

         head.prev = null;
         head.next = tail;
         tail.prev = head;
         tail.next = null;
         
         
         for (int i = 0; i < books.length; i++){
             String[] curr =  books[i].split(" ");
             
             for (int j = 0; j < curr.length; j++){
                 Node newNode = new Node(curr[j]);
                 addNode(newNode); 
              }
         }
         Node currNode = head;
         while (currNode != null){
             if (currNode.val.equals(target) && !currNode.prev.equals("") && !currNode.next.equals("")){
                 result.add(currNode.prev.val);
                 result.add(currNode.next.val);
                 
                
                // System.out.println(currNode.prev.val + "==="+currNode.next.val);
             }
             // System.out.println(currNode.val + "---");
             currNode = currNode.next;
         }
         
         return result;
     
     }
     
     public static void addNode(Node node){
         Node last = tail.prev;
         
         node.next = tail;
         tail.prev = node;
         
         last.next = node;
         node.prev = last;
         
     }
    static  class Node{  
        String val;  
        Node prev;  
        Node next;  
  
        public Node(String val) {  
            this.val = val;  
        }  
    }  
}

