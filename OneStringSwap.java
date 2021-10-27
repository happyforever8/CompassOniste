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
  
  
  class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()){
            return false;
        }
        int start = 0;
        int end = s1.length() - 1;
        
        if (s1.equals(s2)){
            return true;
        }
        while (start < end){
            char[] ch = s1.toCharArray();
            
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            
            if (String.valueOf(ch).equals(s2)){
                return true;
            }
            start++;
            end--;
        }
        if (){
            
        }
        return false;
    }
}
