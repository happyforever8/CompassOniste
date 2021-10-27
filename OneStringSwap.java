1790. Check if One String Swap Can Make Strings Equal
Easy

Share
You are given two strings s1 and s2 of equal length. A string swap is an operation 
where you choose two indices in a string (not necessarily different) and swap the characters at these indices.

Return true if it is possible to make both strings equal by performing at
most one string swap on exactly one of the strings. Otherwise, return false.

 

Example 1:

Input: s1 = "bank", s2 = "kanb"
Output: true
Explanation: For example, swap the first character with the last character of s2 to make "bank".
  
  
 //     - find two mismatches, if mismatches more than 2 return false
// - mismatch check for chars. sa[0]==ta[1] && sa[1] ==ta[0]
    
    public boolean areAlmostEqual(String s1, String s2) {
        char[] char1 = new char[2];
        char[] char2 = new char[2];
        
        int count = 0;
        
        if(s1.length() != s2.length()){
            return false;
        }
        
        for(int i = 0;i<s1.length(); i++){
            char x = s1.charAt(i);
            char y = s2.charAt(i);
            
            if(x != y){
                if(count == 2){
                    return false;
                }
                char1[count] = x;
                char2[count] = y;
                count++;
            } 
        }
        
        // "bank" && "kany". (we need to validate if the swap turns out to be valid or not.)


        return char1[0] == char2[1] && char1[1] == char2[0];
    }
}
==== follow up k pair=====
      public static void main(String []args){
        System.out.println("Hello World");
        String str1 = "bankvvc";
        String str2 = "knabcvv";
        System.out.println(areAlmostEqualwithK(str1, str2, 2));
     }
     
      public static boolean areAlmostEqualwithK(String s1, String s2, int k) {
        char[] char1 = new char[2 * k + 1];
        char[] char2 = new char[2 * k + 1];
        
        int count = 0;
        
        if(s1.length() != s2.length()){
            return false;
        }
        
        for(int i = 0;i<s1.length(); i++){
            char x = s1.charAt(i);
            char y = s2.charAt(i);
            
            if(x != y){
                if(count == 2 * k + 1){
                    return false;
                }
                char1[count] = x;
                char2[count] = y;
                count++;
            } 
        }
        
        // "bank" && "kany". (we need to validate if the swap turns out to be valid or not.)
        
        Arrays.sort(char1);
        Arrays.sort(char2);
        return String.valueOf(char1).equals(String.valueOf(char2));
    }
