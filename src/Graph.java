import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private int[][] matrix = null;
    private List<Integer>[] list = null;
    private GraphType type;
    private int V;

    @SuppressWarnings("unchecked")
    public Graph(int V, GraphType type) {
        this.type = type;
        this.V = V;
        switch (this.type) {
            case List: {
                this.list = new LinkedList[V + 1];
                for (int i = 0; i < V + 1; i++) {
                    this.list[i] = new LinkedList<Integer>();
                }
                break;
            }
            case Matrix: {
                this.matrix = new int[V + 1][V + 1];
                break;
            }
        }
    }

    public void addEdge(int u, int v) {
        if (u <= 0 || u > this.V || v <= 0 || v > this.V) {
            throw new Error("u and v should be valid vertices");
        }
        switch (this.type) {
            case List: {
                this.list[u].add(v);
                break;
            }
            case Matrix: {
                matrix[u][v] = 1;
                matrix[v][u] = 1;
                break;
            }
        }
    }

    public void BFS(int startVertex) {
         if(startVertex <= 0 || startVertex > this.V){
            throw new Error("Start vertex should be a valid vertex");
        }
        Queue<Integer> check = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        check.add(startVertex);
        visited.add(startVertex);
        while (!check.isEmpty()) {
            Integer currentVertex = check.poll();
            List<Integer> neighbors = this.getNeighbours(currentVertex);
            for (int i = 0; i < neighbors.size(); i++) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor); 
                    check.add(neighbor);
                }
            }
            System.out.println("Processed : " + currentVertex);
        }
    }

    private List<Integer> getNeighbours(int v) {
        List<Integer> n = new ArrayList<>();
        switch (this.type) {
            case List: {
                n.addAll(this.list[v]);
                break;
            }
            case Matrix: {
                for (int i = 0; i <= this.V; i++) {
                    if (matrix[v][i] > 0) {
                        n.add(i);
                    }
                }
                break;
            }
        }
        return n;
    }

    public void DFS(int startVertex){
        if(startVertex <= 0 || startVertex > this.V){
            throw new Error("Start vertex should be a valid vertex");
        }
        Stack<Integer> s = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        s.push(startVertex);
        visited.add(startVertex);
        while(!s.isEmpty()){
            int currentVertex = s.pop();
            List<Integer> neighbors = this.getNeighbours(currentVertex);
            for (int i = 0; i < neighbors.size(); i++) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    s.push(neighbor);
                }
            }
            System.out.println("Processed : " + currentVertex);
        }
    }
}
