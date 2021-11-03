指南针 已经file了s-1 来一波现场表演的面经，希望能帮助大家
一共5轮
1. tech deep dive 聊以前的项目，最challenge的地方
2. coding 题目是： 假设输入时长的字符串，里面会有连续重复的字符，
举个例子：bbbbbtxxxzz，那么让你是想一个encode function，
要求是你encode的string也可以背decode回原来的string。

这题要注意提出clarify的问题。比如输入字符里面是不是只有letter？如果含有digit怎么办？
第一问先假设只有digit和letter，实现一个encode。followup，
如果可以是任意字符怎么办？最后实现decode的function。这题主要考应变能力。



import java.util.*;
public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
         String str = "wwwwaaadexxxxxxywww";
          enCode(str);
          decode("w4a3dex6yw3");
     }
     
     public static void enCode(String str){
    
          StringBuilder sb = new StringBuilder();
          
          for (int i = 0; i < str.length(); i++) {
                int count = 1;
                
                while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                    count++;
                     i++;
                }
               sb.append(str.charAt(i));
               //sb.append(count);
               
               // to avoid 1
              if (count > 2){
                  sb.append(count);
              }
 
          }
                  
          
          
          System.out.println(sb.toString());
      }
        
    public static void decode(String str){
        StringBuilder sb = new StringBuilder();
        int num = 0;
        
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char ch : str.toCharArray()){
            stack.push(ch);
        }
        
        
        while (!stack.isEmpty()){
            char curr = stack.pop();
            
            if (curr >= '1' && curr <= '9'){
               num = curr - '0';
                
                while (!stack.isEmpty() && stack.peek() >='0' && stack.peek() <= '9'){
                    int currNum = stack.pop() - '0';
                    
                    num = currNum * 10 + num;
               }
               
            } else if (num > 1){
                for (int i = 0; i < num; i++){
                    sb.append(curr);
                }
                num = 0;
            } else {
                sb.append(curr);
            }

        }
        System.out.println(sb.reverse().toString());
    }
        
    


}








=========leetcode =Decode=======394. Decode String===
  Input: s = "3[a]2[bc]"
  Output: "aaabcbc"
  
  Input: s = "3[a2[c]]"
  Output: "accaccacc"
  
  
  class Solution {
    
    // tims is o(n * maxK)
    // n is length of String, maxK is max value of num
    // space is o(n)
    public String decodeString(String s) {
        if (s == null || s.length() == 0){
            return "";
        }
        Queue<Character> queue = new LinkedList<>();
        
        for (char ch : s.toCharArray()){
            queue.offer(ch);
        }
        return helper(queue);
        
    }
    
    public String helper(Queue<Character> queue){
        if (queue.isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int num = 0;
        
        while (!queue.isEmpty()){
            char ch = queue.poll();
            
            if (ch >= '0' && ch <= '9'){
                num = num * 10 + (ch - '0');
            } else if (ch == '['){
                String sub = helper(queue);
                for (int i = 0; i < num; i++){
                    sb.append(sub);
                }
                num = 0;
            } else if (ch == ']'){
                break;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}


===========271. Encode and Decode Strings =====
     
     Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

You are not allowed to solve the problem using any serialize methods (such as eval).

 

Example 1:

Input: dummy_input = ["Hello","World"]
Output: ["Hello","World"]
Explanation:
Machine 1:
Codec encoder = new Codec();
String msg = encoder.encode(strs);
Machine 1 ---msg---> Machine 2
     
     
     public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null || strs.size() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        for (String str : strs){
            sb.append(str.length()).append("/").append(str);
        }
        //sb: 5/Hello5/World
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        
        if (s == null || s.length() == 0){
            return result;
        }
        int index = 0;
        //indexOf(String str, int fromIndex)
        
        while (index < s.length()){
            int slashIndex = s.indexOf("/", index);
            
            int len = Integer.parseInt(s.substring(index, slashIndex));
            
            index = slashIndex + len + 1;
            result.add(s.substring(slashIndex + 1, index));
            
        }
        return result;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
