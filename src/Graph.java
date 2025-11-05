import java.util.*;

/**
 * Clase que representa un grafo con nodos y aristas ponderadas.
 */
public class Graph {
    private Map<Integer, Map<Integer, Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Agrega un nodo al grafo.
     */
    public void addNode(int node) {
        adjacencyList.putIfAbsent(node, new HashMap<>());
    }

    /**
     * Agrega una arista bidireccional entre dos nodos con un peso específico.
     */
    public void addEdge(int from, int to, int weight) {
        adjacencyList.putIfAbsent(from, new HashMap<>());
        adjacencyList.putIfAbsent(to, new HashMap<>());
        adjacencyList.get(from).put(to, weight);
        adjacencyList.get(to).put(from, weight); // Bidireccional
    }

    /**
     * Obtiene los vecinos de un nodo.
     */
    public Map<Integer, Integer> getNeighbors(int node) {
        return adjacencyList.getOrDefault(node, new HashMap<>());
    }

    /**
     * Obtiene todos los nodos del grafo.
     */
    public Set<Integer> getNodes() {
        return adjacencyList.keySet();
    }
}
