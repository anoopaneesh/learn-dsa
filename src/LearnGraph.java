public class LearnGraph {
    public static void main(String[] args){
        Graph G = new Graph(4, GraphType.List);
        G.addEdge(1, 2);
        G.addEdge(1, 3);
        G.addEdge(2, 3);
        G.addEdge(3, 1);
        G.addEdge(3, 4);

        // G.BFS();
        G.DFS(2);
    }
}
