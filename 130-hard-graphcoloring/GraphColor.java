import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class GraphColor {
    public static void main(String...args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        Graph graph = new Graph();

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            int dept = Integer.parseInt(line[0]);
            for (int j = 1; j < line.length; j++) {
                graph.addTransition(dept, Integer.parseInt(line[j]));
            }
        }

        for (Map.Entry<Integer, Integer> entry : graph.getColors(2).entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}

class Graph {
    private Map<Integer, List<Integer>> adjacencyList = new LinkedHashMap<>();
    private Map<Integer, List<Integer>> predecessorList = new LinkedHashMap<>();

    public void addTransition(final int from, final int to) {
        addTransition(adjacencyList, from, to);
        addTransition(predecessorList, to, from);
    }

    private void addTransition(final Map<Integer, List<Integer>> map, final int from, final int to) {
        List<Integer> transitions = map.get(from);
        if (transitions == null) {
            transitions = new ArrayList<>();
            map.put(from, transitions);
        }
        transitions.add(to);
    }

    public Map<Integer, Integer> getColors(final int startNumColors) {
        Map<Integer, Integer> colors = new HashMap<>();

        int numColors = startNumColors;

        for (Integer vertex : adjacencyList.keySet()) {
            if (!colors.containsKey(vertex)) {
                numColors = colorVertex(vertex, numColors, colors);

                Queue<Integer> q = new LinkedList<>();
                q.add(vertex);

                while (!q.isEmpty()) {
                    int current = q.remove();
                    for (Integer next : adjacencyList.get(current)) {
                        if (!colors.containsKey(next) && adjacencyList.containsKey(next)) {
                            numColors = colorVertex(next, numColors, colors);
                            q.add(next);
                        }
                    }
                    
                }
            }
        }

        return colors;
    }

    private int colorVertex(final int vertex, final int numColors, final Map<Integer, Integer> colors) {
        int newNumColors = numColors;
        if (!colors.containsKey(vertex)) {
            int color = getNextColor(numColors, vertex, colors);
            color = (color >= 0) ? color : (++newNumColors) - 1;
            colors.put(vertex, color);
        }
        return newNumColors;
    }

    private int getNextColor(final int numColors, final int vertex, final Map<Integer, Integer> colors) {
        for (int color = 0; color < numColors; color++) {
            if (!hasPredecessorColor(color, vertex, colors)) {
                return color;
            }
        }

        return -1;
    }

    private boolean hasPredecessorColor(final int color, final int vertex, final Map<Integer, Integer> colors) {
        for (Integer predecessor : predecessorList.get(vertex)) {
            if (colors.containsKey(predecessor) && colors.get(predecessor) == color)
                return true;
        }

        return false;
    }
}