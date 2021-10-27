Given a series of game results such as xxxx beats yyyy, output the final ranking.

Example 1:

Input: ["a beats b", "b beats c", "c beats d"]
Output: ["a", "b", "c", "d"]
Example 2:

Input: ["a beats b", "a beats c"]
Output: ["a", "b", "c"] or ["a", "c", "b"]

    /*
     * Example 1:
     *
     * Input: ["a beats b", "b beats c", "c beats d"]
     * Output: ["a", "b", "c", "d"]
     * Example 2:
     *
     * Input: ["a beats b", "a beats c"]
     * Output: ["a", "b", "c"] or ["a", "c", "b"]
     */
    private void getRanking(List<String> rawRanking) {
        Map<String, List<String>> graph = buildGraph(rawRanking);
        Map<String, Integer> indegree = computeIndegree(graph);
        Queue<String> queue = new LinkedList<>();
        for(Map.Entry<String, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        List<String> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            String game = queue.poll();
            result.add(game);
            List<String> neighbors = graph.get(game);
            if (neighbors != null) {
                for(String adj : neighbors) {
                    indegree.put(adj, indegree.get(adj) - 1);
                    if (indegree.get(adj) == 0) {
                        queue.add(adj);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(result.toArray()));
    }

    private Map<String, Integer> computeIndegree(Map<String, List<String>> graph) {
        Map<String, Integer> indegree = new HashMap<>();
        for(Map.Entry<String, List<String>> entry : graph.entrySet()) {
            if (!indegree.containsKey(entry.getKey())) {
                indegree.put(entry.getKey(), 0);
            }
            for(String game : entry.getValue()) {
                if (!indegree.containsKey(game)) {
                    indegree.put(game, 0);
                }
                indegree.put(game, indegree.get(game) + 1);
            }
        }
        return indegree;
    }

    private Map<String, List<String>> buildGraph(List<String> rawRanking) {
        Map<String, List<String>> graph = new HashMap<>();
        for(String ranking : rawRanking) {
            String[] games = ranking.split(" beats ");
            if (!graph.containsKey(games[0])) {
                graph.put(games[0], new ArrayList<>());
            }
            graph.get(games[0]).add(games[1]);
        }
        return graph;
    }
