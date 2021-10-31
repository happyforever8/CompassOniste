算是个hard或者medium中上，本质是：给一个list的edge，directional graph，问是不是valid，

可以有两个点重合，类似于，a -> b, b = c, 如果 c -> a就是invalid。这道题去掉了包装不太难，在提示了一下才写出来了

class Solution {
    
//     Time Complexity:O(∣E∣+∣V∣) 
//         where |V is the number of courses, and |E| is the number of dependencies.
        
      // space is Same
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // BFS
        
         if (prerequisites == null || prerequisites.length == 0 || numCourses < 0){
            return true;
        }
        //int n = prerequisites.length;
        
        List[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        
        for (int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        
        for (int[] pre : prerequisites){
            degree[pre[0]]++;
            graph[pre[1]].add(pre[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < degree.length; i++){
            if (degree[i] == 0){
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()){
            int course = queue.poll();
            numCourses--;
            for (int i = 0; i < graph[course].size(); i++){
                int next = (int)graph[course].get(i);
                
                degree[next]--;
                
                if (degree[next] == 0){
                    queue.offer(next);
                }
            }
        }
        
        return numCourses == 0 ? true : false;
    }     
        
        
        // DFS
//         if (prerequisites == null || prerequisites.length < 0 || numCourses <0){
//             return true;
//         }
//         int[] visited = new int[numCourses];
        
//         Map<Integer, Set<Integer>> graph = new HashMap<>();
        
//         for (int i = 0; i < numCourses; i++){
//             graph.put(i, new HashSet<>());
//         }
        
//         for (int[] pre : prerequisites){
//             graph.get(pre[1]).add(pre[0]);
//         }
        
//         for (int i = 0; i < numCourses; i++){
//             if (hasCycle(graph, i, visited)){
//                 return false;
//             }
//         }
//         return true;
//     }
    
//     public boolean hasCycle(Map<Integer, Set<Integer>> graph, int course, int[] visited){
//         if (visited[course] == 1){
//             return true;
//         }
//         if (visited[course] == 2){
//             return false;
//         }
//         visited[course] = 1;
        
//         for (int next : graph.get(course)){
//             if (hasCycle(graph, next, visited)){
//                 return true;
//             }
//         }
//         visited[course] = 2;
//         return false;
//     }
}
