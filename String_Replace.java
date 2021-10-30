1. Coding。地里常见的面经题，给你一个String和一个词典map，
需要replaceString中花括号括起来的substring。例如String是ab{c}，词典是{"c": "hello"}, 那么结果就是abhello。
注意以前的面经没有提到的一点是有可能括号会有嵌套，
比如String是ab{{c}}，词典是{"c": "hello", "hello": "bye"}, 那么结果就是abbye。
用Stack解决，比较简单，注意corner case。


import java.util.*;

public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
    
        String[] input = {"c:hello", "hello:bye"};
        String target = "ab{c}";
        
        System.out.println(match(input, target));
     }
     
     public static String match(String[] input, String target){
         Map<String, String> map = new HashMap<>();
         
        
         
         for (String str : input){
             String[] curr = str.split(":");
             map.put(curr[0], curr[1]);
            // System.out.println(curr[0] + "===" + curr[1]);
         }
         
         Deque<String> stack = new ArrayDeque<>();
         StringBuilder sb = new StringBuilder();
         
         for (int i = 0; i < target.length();i++){
             char ch = target.charAt(i);
             if (ch >= 'a' && ch <= 'z'){
                 
                 if (stack.isEmpty() || (!stack.isEmpty() && !stack.peek().equals("{"))){
                     stack.push(String.valueOf(ch));
                 }
                 if (!stack.isEmpty() && stack.peek().equals("{")){
                     sb.append(ch);
                 } 
  
             } else if (ch == '{'){
                 stack.push(String.valueOf(ch));
             } else if (ch == '}'){
                 stack.pop();
                 sb = new StringBuilder(map.get(sb.toString()));
             }
         }
         stack.push(sb.toString());
         
         StringBuilder sb1 = new StringBuilder();
         
         while (!stack.isEmpty()){
             sb1.append(stack.pop());
         }
         return sb1.reverse().toString();
     }
}

