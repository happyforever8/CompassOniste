Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the 
letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]

class Solution {
    // time is O(nklogk) n is strs.length;
    // k is the max length of the str in strs
    
    // space is O(nk)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        
        if (strs == null || strs.length == 0){
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs){
            char[] ch = str.toCharArray();
            
            Arrays.sort(ch);
            String curr = String.valueOf(ch);
            map.putIfAbsent(curr, new ArrayList<>());
            
            map.get(curr).add(str);
        }
        
        for (Map.Entry<String, List<String>> entry : map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }
}
