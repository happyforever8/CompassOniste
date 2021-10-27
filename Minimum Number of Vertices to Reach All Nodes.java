1557. Minimum Number of Vertices to Reach All Nodes

Given a directed acyclic graph, with n vertices numbered from 0 to n-1, 
and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.

Find the smallest set of vertices from which all nodes in the graph 
are reachable. It's guaranteed that a unique solution exists.

Notice that you can return the vertices in any order.

 

Example 1:



Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
Output: [0,3]
Explanation: It's not possible to reach all the nodes from a single vertex.
From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].


Explanation
Quick prove:

Necesssary condition: All nodes with no in-degree must in the final result,
because they can not be reached from
All other nodes can be reached from any other nodes.

Sufficient condition: All other nodes can be reached from some other nodes.


O(V+E) - need to check every edge and every vertex.
O(n) space - need to keep in-degree flags for every node.

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> res = new ArrayList();
        
        boolean[] inDegree = new boolean[n];
        
        for (List<Integer> edge : edges) {
			//get 'to' node from the edge and set the in-degree flag for it 
            inDegree[edge.get(1)] = true;
        }
        
        for (int i = 0; i < n; i++) {
            if (!inDegree[i])
                res.add(i);
        }
        
        return res;
    }
    
    
    
        public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
            int[] parent = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        for(int i = 0; i < edges.size(); i++) {
            List<Integer> curr = edges.get(i);
            int sv = curr.get(0);
            int ev = curr.get(1);
            if(visited[ev]) continue;
            int p1 = findParent(parent, sv);
            int p2 = findParent(parent, ev);
            parent[p2] = p1; 
            visited[ev] = true;
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) if(parent[i] == i) ans.add(i);
        return ans;
    
    }
    
      public static int findParent(int[] parent, int i) {
        if(parent[i] == i) return i;
        return parent[i] = findParent(parent, parent[i]);
    }
