import java.util.*;

/**
 * Implementación del Algoritmo de Dijkstra para encontrar la ruta más corta.
 */
public class Dijkstra {
    
    /**
     * Clase interna para representar un nodo con su distancia en la cola de prioridad.
     */
    private static class NodeDistance implements Comparable<NodeDistance> {
        int node;
        int distance;

        public NodeDistance(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    /**
     * Clase para almacenar el resultado del algoritmo.
     */
    public static class Result {
        private int minCost;
        private List<Integer> path;

        public Result(int minCost, List<Integer> path) {
            this.minCost = minCost;
            this.path = path;
        }

        public int getMinCost() {
            return minCost;
        }

        public List<Integer> getPath() {
            return path;
        }

        public boolean hasPath() {
            return minCost != Integer.MAX_VALUE && !path.isEmpty();
        }
    }

    /**
     * Calcula la ruta más corta entre dos nodos usando el Algoritmo de Dijkstra.
     *
     * @param graph El grafo a explorar
     * @param start El nodo de inicio
     * @param end El nodo de destino
     * @return Resultado con el costo mínimo y la ruta más corta
     */
    public static Result findShortestPath(Graph graph, int start, int end) {
        // 1. Inicialización de distancias
        Map<Integer, Integer> distances = new HashMap<>();
        for (int node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        // 2. Inicialización de predecesores para reconstruir la ruta
        Map<Integer, Integer> predecessors = new HashMap<>();
        for (int node : graph.getNodes()) {
            predecessors.put(node, null);
        }

        // 3. Cola de prioridad (min-heap) para explorar el nodo con menor distancia
        PriorityQueue<NodeDistance> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new NodeDistance(start, 0));

        while (!priorityQueue.isEmpty()) {
            NodeDistance current = priorityQueue.poll();
            int currentNode = current.node;
            int currentDistance = current.distance;

            // Si ya hemos encontrado un camino más corto a este nodo, saltamos
            if (currentDistance > distances.get(currentNode)) {
                continue;
            }

            // Condición de parada: si llegamos al destino
            if (currentNode == end) {
                break;
            }

            // 4. Explorar vecinos
            for (Map.Entry<Integer, Integer> neighbor : graph.getNeighbors(currentNode).entrySet()) {
                int neighborNode = neighbor.getKey();
                int weight = neighbor.getValue();
                int newDistance = currentDistance + weight;

                // Si encontramos un camino más corto
                if (newDistance < distances.get(neighborNode)) {
                    distances.put(neighborNode, newDistance);
                    predecessors.put(neighborNode, currentNode);
                    priorityQueue.offer(new NodeDistance(neighborNode, newDistance));
                }
            }
        }

        // 5. Reconstruir la ruta
        List<Integer> path = new ArrayList<>();
        Integer node = end;
        while (node != null) {
            path.add(node);
            node = predecessors.get(node);
        }
        Collections.reverse(path);

        // Retorna la ruta solo si el destino es alcanzable
        int finalDistance = distances.get(end);
        if (finalDistance == Integer.MAX_VALUE) {
            return new Result(Integer.MAX_VALUE, new ArrayList<>());
        }

        return new Result(finalDistance, path);
    }
}
