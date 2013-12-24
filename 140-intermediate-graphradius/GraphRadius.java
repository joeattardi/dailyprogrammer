import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class GraphRadius {
    public static void main(String...args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // eat the newline

        Graph graph = new Graph(n);

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                if (line[j].equals("1")) {
                    graph.addTransition(i, j);
                }
            }
        }        
        
        System.out.println(graph.getRadius());
    }
}

class Graph {
    private int numVertices;
    private List<List<Integer>> adjacencyList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new ArrayList<>(numVertices);
        for (int n = 0; n < numVertices; n++) {
            adjacencyList.add(new ArrayList<Integer>());
        }
    }

    public void addTransition(int from, int to) {        
        List<Integer> transitions = adjacencyList.get(from);
        transitions.add(to);
    }

    public int getRadius() {
        int minEccentricity = Integer.MAX_VALUE;

        for (int i = 0; i < adjacencyList.size(); i++) {
            minEccentricity = Math.min(minEccentricity, getEccentricity(i));
        }
        return minEccentricity;
    }

    public int getEccentricity(int vertex) {
        int maxDistance = -1;
        for (int i = 0; i < adjacencyList.size(); i++) {
            maxDistance = Math.max(maxDistance, getDistance(vertex, i));
        }
        return maxDistance;
    }

    public int getDistance(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[numVertices];
        int[] distance = new int[numVertices];

        q.add(start);

        while (!q.isEmpty()) {
            int node = q.remove();
            if (node == end) {
                return distance[end];
            }
            for (Integer n : adjacencyList.get(node)) {
                if (!visited[n]) {
                    distance[n] = distance[node] + 1;
                    visited[n] = true;
                    q.add(n);
                }
            }
        }

        return -1;
    }    
}