import java.util.List;

/**
 * Clase para realizar pruebas rigurosas del algoritmo de Dijkstra
 */
public class TestDijkstra {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("PRUEBAS RIGUROSAS DEL ALGORITMO DE DIJKSTRA");
        System.out.println("=".repeat(70));
        System.out.println();
        
        // Crear el grafo original
        Graph grafoRed = crearGrafoOriginal();
        
        // Ejecutar todas las pruebas
        test1_RutaOriginal(grafoRed);
        test2_RutaInversa(grafoRed);
        test3_RutasCortas(grafoRed);
        test4_RutasLargas(grafoRed);
        test5_MismoNodo(grafoRed);
        test6_NodosAdyacentes(grafoRed);
        test7_RutasAlternativas(grafoRed);
        test8_TodosLosNodos(grafoRed);
        test9_GrafoSimple();
        test10_GrafoDesconectado();
        
        // Resumen final
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("RESUMEN DE PRUEBAS");
        System.out.println("=".repeat(70));
        System.out.println("Pruebas exitosas: " + testsPassed);
        System.out.println("Pruebas fallidas: " + testsFailed);
        System.out.println("Total: " + (testsPassed + testsFailed));
        
        if (testsFailed == 0) {
            System.out.println();
            System.out.println("*** TODAS LAS PRUEBAS PASARON EXITOSAMENTE ***");
        } else {
            System.out.println();
            System.out.println("!!! ALGUNAS PRUEBAS FALLARON !!!");
        }
        System.out.println("=".repeat(70));
    }
    
    private static Graph crearGrafoOriginal() {
        Graph grafo = new Graph();
        
        grafo.addEdge(1, 2, 20);
        grafo.addEdge(1, 3, 15);
        grafo.addEdge(1, 4, 18);
        grafo.addEdge(2, 5, 10);
        grafo.addEdge(2, 6, 10);
        grafo.addEdge(3, 7, 11);
        grafo.addEdge(4, 8, 12);
        grafo.addEdge(5, 6, 8);
        grafo.addEdge(5, 9, 16);
        grafo.addEdge(6, 7, 12);
        grafo.addEdge(6, 9, 12);
        grafo.addEdge(7, 8, 22);
        grafo.addEdge(7, 10, 18);
        grafo.addEdge(7, 11, 18);
        grafo.addEdge(8, 12, 20);
        grafo.addEdge(9, 13, 16);
        grafo.addEdge(10, 13, 17);
        grafo.addEdge(11, 12, 15);
        grafo.addEdge(11, 14, 16);
        grafo.addEdge(12, 15, 15);
        grafo.addEdge(13, 14, 10);
        grafo.addEdge(13, 16, 18);
        grafo.addEdge(14, 16, 14);
        grafo.addEdge(15, 16, 25);
        
        return grafo;
    }
    
    private static void test1_RutaOriginal(Graph grafo) {
        System.out.println("TEST 1: Ruta del Nodo 1 al 16 (Caso Original)");
        System.out.println("-".repeat(70));
        
        Dijkstra.Result resultado = Dijkstra.findShortestPath(grafo, 1, 16);
        
        boolean correcto = resultado.getMinCost() == 74 && 
                          resultado.getPath().toString().equals("[1, 3, 7, 11, 14, 16]");
        
        imprimirResultado(resultado, 1, 16);
        verificarTest(correcto, "Ruta esperada: 1 -> 3 -> 7 -> 11 -> 14 -> 16, Costo: 74");
        System.out.println();
    }
    
    private static void test2_RutaInversa(Graph grafo) {
        System.out.println("TEST 2: Ruta del Nodo 16 al 1 (Inversa)");
        System.out.println("-".repeat(70));
        
        Dijkstra.Result resultado = Dijkstra.findShortestPath(grafo, 16, 1);
        
        boolean correcto = resultado.getMinCost() == 74 && 
                          resultado.getPath().toString().equals("[16, 14, 11, 7, 3, 1]");
        
        imprimirResultado(resultado, 16, 1);
        verificarTest(correcto, "Ruta esperada: 16 -> 14 -> 11 -> 7 -> 3 -> 1, Costo: 74");
        System.out.println();
    }
    
    private static void test3_RutasCortas(Graph grafo) {
        System.out.println("TEST 3: Rutas Cortas (Nodos Cercanos)");
        System.out.println("-".repeat(70));
        
        // Nodo 1 al 3
        Dijkstra.Result r1 = Dijkstra.findShortestPath(grafo, 1, 3);
        boolean t1 = r1.getMinCost() == 15 && r1.getPath().toString().equals("[1, 3]");
        imprimirResultado(r1, 1, 3);
        verificarTest(t1, "Ruta esperada: 1 -> 3, Costo: 15");
        
        // Nodo 5 al 6
        Dijkstra.Result r2 = Dijkstra.findShortestPath(grafo, 5, 6);
        boolean t2 = r2.getMinCost() == 8 && r2.getPath().toString().equals("[5, 6]");
        imprimirResultado(r2, 5, 6);
        verificarTest(t2, "Ruta esperada: 5 -> 6, Costo: 8");
        System.out.println();
    }
    
    private static void test4_RutasLargas(Graph grafo) {
        System.out.println("TEST 4: Rutas Largas (Extremos del Grafo)");
        System.out.println("-".repeat(70));
        
        // Nodo 1 al 15
        Dijkstra.Result r1 = Dijkstra.findShortestPath(grafo, 1, 15);
        imprimirResultado(r1, 1, 15);
        boolean t1 = r1.hasPath();
        verificarTest(t1, "Debe existir una ruta del 1 al 15");
        
        // Nodo 4 al 16
        Dijkstra.Result r2 = Dijkstra.findShortestPath(grafo, 4, 16);
        imprimirResultado(r2, 4, 16);
        boolean t2 = r2.hasPath();
        verificarTest(t2, "Debe existir una ruta del 4 al 16");
        System.out.println();
    }
    
    private static void test5_MismoNodo(Graph grafo) {
        System.out.println("TEST 5: Mismo Nodo de Inicio y Destino");
        System.out.println("-".repeat(70));
        
        Dijkstra.Result resultado = Dijkstra.findShortestPath(grafo, 8, 8);
        
        boolean correcto = resultado.getMinCost() == 0 && 
                          resultado.getPath().size() == 1 &&
                          resultado.getPath().get(0) == 8;
        
        imprimirResultado(resultado, 8, 8);
        verificarTest(correcto, "Ruta esperada: 8, Costo: 0");
        System.out.println();
    }
    
    private static void test6_NodosAdyacentes(Graph grafo) {
        System.out.println("TEST 6: Nodos Directamente Conectados");
        System.out.println("-".repeat(70));
        
        // 1 -> 2 (costo 20)
        Dijkstra.Result r1 = Dijkstra.findShortestPath(grafo, 1, 2);
        boolean t1 = r1.getMinCost() == 20 && r1.getPath().toString().equals("[1, 2]");
        imprimirResultado(r1, 1, 2);
        verificarTest(t1, "Ruta esperada: 1 -> 2, Costo: 20");
        
        // 13 -> 14 (costo 10)
        Dijkstra.Result r2 = Dijkstra.findShortestPath(grafo, 13, 14);
        boolean t2 = r2.getMinCost() == 10 && r2.getPath().toString().equals("[13, 14]");
        imprimirResultado(r2, 13, 14);
        verificarTest(t2, "Ruta esperada: 13 -> 14, Costo: 10");
        System.out.println();
    }
    
    private static void test7_RutasAlternativas(Graph grafo) {
        System.out.println("TEST 7: Verificar Rutas Optimas (No Directas)");
        System.out.println("-".repeat(70));
        
        // Del 1 al 7: puede ir directo (1->3->7=26) o por otro camino
        Dijkstra.Result r1 = Dijkstra.findShortestPath(grafo, 1, 7);
        boolean t1 = r1.getMinCost() == 26;
        imprimirResultado(r1, 1, 7);
        verificarTest(t1, "Costo esperado: 26 (ruta mas corta)");
        
        // Del 2 al 13
        Dijkstra.Result r2 = Dijkstra.findShortestPath(grafo, 2, 13);
        imprimirResultado(r2, 2, 13);
        boolean t2 = r2.hasPath();
        verificarTest(t2, "Debe existir una ruta del 2 al 13");
        System.out.println();
    }
    
    private static void test8_TodosLosNodos(Graph grafo) {
        System.out.println("TEST 8: Conectividad - Todos los nodos alcanzables desde el 1");
        System.out.println("-".repeat(70));
        
        boolean todosConectados = true;
        for (int i = 1; i <= 16; i++) {
            Dijkstra.Result r = Dijkstra.findShortestPath(grafo, 1, i);
            if (!r.hasPath()) {
                System.out.println("ERROR: Nodo " + i + " no alcanzable desde 1");
                todosConectados = false;
            }
        }
        
        verificarTest(todosConectados, "Todos los 16 nodos deben ser alcanzables desde el nodo 1");
        System.out.println();
    }
    
    private static void test9_GrafoSimple() {
        System.out.println("TEST 9: Grafo Simple (3 Nodos)");
        System.out.println("-".repeat(70));
        
        Graph grafoSimple = new Graph();
        grafoSimple.addEdge(1, 2, 5);
        grafoSimple.addEdge(2, 3, 10);
        grafoSimple.addEdge(1, 3, 20);
        
        Dijkstra.Result resultado = Dijkstra.findShortestPath(grafoSimple, 1, 3);
        
        // La ruta mas corta debe ser 1->2->3 (15) no 1->3 (20)
        boolean correcto = resultado.getMinCost() == 15 && 
                          resultado.getPath().toString().equals("[1, 2, 3]");
        
        imprimirResultado(resultado, 1, 3);
        verificarTest(correcto, "Ruta esperada: 1 -> 2 -> 3, Costo: 15 (no la directa de 20)");
        System.out.println();
    }
    
    private static void test10_GrafoDesconectado() {
        System.out.println("TEST 10: Grafo Desconectado (Sin Ruta)");
        System.out.println("-".repeat(70));
        
        Graph grafoDesconectado = new Graph();
        grafoDesconectado.addNode(1);
        grafoDesconectado.addNode(2);
        grafoDesconectado.addNode(3);
        grafoDesconectado.addNode(4);
        
        // Solo conectar 1-2 y 3-4, dejando dos componentes separados
        grafoDesconectado.addEdge(1, 2, 5);
        grafoDesconectado.addEdge(3, 4, 10);
        
        Dijkstra.Result resultado = Dijkstra.findShortestPath(grafoDesconectado, 1, 4);
        
        boolean correcto = !resultado.hasPath() && 
                          resultado.getMinCost() == Integer.MAX_VALUE;
        
        imprimirResultado(resultado, 1, 4);
        verificarTest(correcto, "No debe existir ruta (grafo desconectado)");
        System.out.println();
    }
    
    private static void imprimirResultado(Dijkstra.Result resultado, int inicio, int fin) {
        System.out.println("  Origen: " + inicio + " -> Destino: " + fin);
        if (resultado.hasPath()) {
            String rutaStr = String.join(" -> ", 
                resultado.getPath().stream().map(String::valueOf).toArray(String[]::new));
            System.out.println("  Ruta: " + rutaStr);
            System.out.println("  Costo: " + resultado.getMinCost());
        } else {
            System.out.println("  NO HAY RUTA DISPONIBLE");
        }
    }
    
    private static void verificarTest(boolean pasado, String mensaje) {
        if (pasado) {
            System.out.println("  [PASADO] " + mensaje);
            testsPassed++;
        } else {
            System.out.println("  [FALLIDO] " + mensaje);
            testsFailed++;
        }
    }
}
