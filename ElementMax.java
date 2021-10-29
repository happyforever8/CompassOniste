刷题网 - 散司令的变种
题目不是给string求substring，而是给List<Element>，Element class里有String category和int value，
\要求sublist中at most k category条件下的Max sum of value
例子：list: [("a", 10), ("b", 20), ("c", 30), ("b", 10)], 
k: 2 -> max sum = 60 because [("b", 20), ("c", 30), ("b", 10)] 
is the subarray containing max sum with at most k (2) categories



import java.util.*;
public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
        List<Element> list = new ArrayList<>();
        
        Element e1 = new Element("a", "10");
        Element e2 = new Element("b", "20");
        Element e3 = new Element("c", "30");
        Element e4= new Element("b", "10");
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        
        System.out.println(lengthOfLongestSubstringKDistinct(list, 2));
     }
     
      public static  int lengthOfLongestSubstringKDistinct(List<Element> list, int k) {

        
        Map<String, Integer> map = new HashMap<>();
        
        int start = 0;
        int max = 0;
        int end = 0;
        
        int sum = 0;
        
        //for (int end = 0; end < s.length(); end++){
        while (end < list.size()){
            Element curr = list.get(end);
            
            sum += Integer.parseInt(curr.value);
            map.put(curr.category, map.getOrDefault(curr.category, 0) + 1);
            
            end++;
            while (map.size() > k){  // using where here, time is faster
                Element temp = list.get(start);
                
                map.put(temp.category, map.get(temp.category)  - 1);
                
                if (map.get(temp.category) == 0){
                    map.remove(temp.category);
                    sum -= Integer.parseInt(temp.value);
                }
                start++;
            }
            max = Math.max(sum, max);
        }
        return max;
    }
    static class Element{
        String category;
        String value;
        
        public Element(String category, String value){
            this.category = category;
            this.value = value;
        }
    }
}
