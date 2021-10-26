Given a list of accounts where each element accounts[i] is a list of strings, 
where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the
same person if there is some common email to both accounts. Note that even if
  two accounts have the same name, they may belong to different people as people 
  could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the 
first element of each account is the name, and the rest of the elements are emails 
in sorted order. The accounts themselves can be returned in any order.

 

Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
  
  
  class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
//         总结了一下，一共km个数据（二维存储，k为用户个数，m为平均每个用户email个数）
// 时间复杂度为O(k2m+k+km),可以算是O(n)的复杂度。
// 空间复杂度为O(km+k),故也是O(n)。


        List<List<String>> result = new ArrayList<>();
        
        if (accounts == null || accounts.size() == 0){
            return result;
        }
        Map<String, Set<String>> graph = new HashMap<>(); //email to email
        Map<String, String> map = new HashMap<>(); //  email to userName
        
        for (int i = 0; i < accounts.size(); i++){
           String userName = accounts.get(i).get(0);
            
            for (int j = 1; j < accounts.get(i).size(); j++){
                String curr = accounts.get(i).get(j);
                
                graph.putIfAbsent(curr, new HashSet<>());
                map.putIfAbsent(curr, userName);
                
                if (j == 1){
                    continue;
                }
                graph.get(curr).add(accounts.get(i).get(j - 1));
                graph.get(accounts.get(i).get(j - 1)).add(curr);
            }
        }
        Set<String> visited = new HashSet<>();
        
        for (String email : map.keySet()){
            List<String> list = new ArrayList<>();
            
            if (visited.add(email)){
                helper(graph, visited, list, email);
                Collections.sort(list);
                list.add(0, map.get(email));
                result.add(list);
            }
        }
        return result;
    }
    public void helper(Map<String, Set<String>> graph, Set<String> visited, List<String> list, String email){
        list.add(email);
        
        for (String next : graph.get(email)){
            if (visited.add(next)){
                helper(graph, visited, list, next);
            }
        }
    }
}
