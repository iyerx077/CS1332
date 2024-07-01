import java.util.*;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<Vertex<T>> path = new ArrayList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Queue<Vertex<T>> vertices = new LinkedList<>();
        vertices.add(start);
        visitedSet.add(start);
        path.add(start);
        while (!vertices.isEmpty()){
            Vertex<T> v = vertices.remove();
            for (VertexDistance<T> w: graph.getAdjList().get(v)){
                if (!visitedSet.contains(w.getVertex())){
                    vertices.add(w.getVertex());
                    visitedSet.add(w.getVertex());
                    path.add(w.getVertex());
                }
            }
        }
        return path;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<Vertex<T>> path = new ArrayList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        dfshelp(start, graph, path, visitedSet);
        return path;
    }
    private static <T> void dfshelp(Vertex<T> start, Graph<T> graph, List<Vertex<T>> path, Set<Vertex<T>> visitedSet){
        if (start == null){
            return;
        }
        visitedSet.add(start);
        path.add(start);
        for (VertexDistance<T> w : graph.getAdjList().get(start)){
            if (!visitedSet.contains(w.getVertex())){
                dfshelp(w.getVertex(), graph, path, visitedSet);
            }
        }
    }
}