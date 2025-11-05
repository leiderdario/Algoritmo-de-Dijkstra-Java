import java.util.List;

/**
 * Clase principal para ejecutar el algoritmo de Dijkstra.
 */
public class Main {
    public static void main(String[] args) {
        // --- Definición del Grafo ---
        // El grafo se define con conexiones bidireccionales
        Graph grafoRed = new Graph();

        // Agregar todas las aristas (bidireccionales)
        grafoRed.addEdge(1, 2, 20);
        grafoRed.addEdge(1, 3, 15);
        grafoRed.addEdge(1, 4, 18);
        
        grafoRed.addEdge(2, 5, 10);
        grafoRed.addEdge(2, 6, 10);
        
        grafoRed.addEdge(3, 7, 11);
        
        grafoRed.addEdge(4, 8, 12);
        
        grafoRed.addEdge(5, 6, 8);
        grafoRed.addEdge(5, 9, 16);
        
        grafoRed.addEdge(6, 7, 12);
        grafoRed.addEdge(6, 9, 12);
        
        grafoRed.addEdge(7, 8, 22);
        grafoRed.addEdge(7, 10, 18);
        grafoRed.addEdge(7, 11, 18);
        
        grafoRed.addEdge(8, 12, 20);
        
        grafoRed.addEdge(9, 13, 16);
        
        grafoRed.addEdge(10, 13, 17);
        
        grafoRed.addEdge(11, 12, 15);
        grafoRed.addEdge(11, 14, 16);
        
        grafoRed.addEdge(12, 15, 15);
        
        grafoRed.addEdge(13, 14, 10);
        grafoRed.addEdge(13, 16, 18);
        
        grafoRed.addEdge(14, 16, 14);
        
        grafoRed.addEdge(15, 16, 25);

        // --- Ejemplo de Uso (Nodo 1 al 16) ---
        int nodoInicio = 1;
        int nodoDestino = 16;

        Dijkstra.Result resultado = Dijkstra.findShortestPath(grafoRed, nodoInicio, nodoDestino);

        // --- Impresion de Resultados ---
        if (resultado.hasPath()) {
            System.out.println("## Resultado: Ruta mas corta del Nodo " + nodoInicio + " al " + nodoDestino);
            System.out.println("-".repeat(50));
            
            List<Integer> ruta = resultado.getPath();
            String rutaStr = String.join(" -> ", ruta.stream().map(String::valueOf).toArray(String[]::new));
            System.out.println("Ruta: " + rutaStr);
            System.out.println("Costo Minimo Total: " + resultado.getMinCost());
        } else {
            System.out.println("No se encontro ruta del Nodo " + nodoInicio + " al " + nodoDestino + ".");
        }
    }
}
