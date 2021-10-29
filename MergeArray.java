

import java.util.*;

第二题是给一个增序的数组，将相邻的数字merge在一起，输出merge之后的数组。比如input = [1,2,4,5,6,8,9]，输出["1->2", "4->6", "8->9"]。

public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
        
        int[] arr = {1, 2, 3, 4, 7, 8, 9, 13, 16, 17};
        
        List<String> result = merge(arr);
        
        for (String str : result){
            System.out.println(str);
        }
     }
     
     public static List<String> merge(int[] arr){
         List<String> list = new ArrayList<>();
         int index = 0;
         
         while (index < arr.length - 1){
             int start = index;
             
             while (index < arr.length - 1 && arr[index + 1] == arr[index] + 1){
                 index++;
             }
             if (start == index){
                 list.add(String.valueOf(arr[index]));
             } else {
                 list.add(arr[start] + "->" + arr[index]);
             }
             index++;
         }
         return list;
     }
     
}
