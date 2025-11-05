# Algoritmo de Dijkstra en Java

Este proyecto implementa el **Algoritmo de Dijkstra** para encontrar la ruta más corta entre dos nodos en un grafo ponderado y bidireccional.

## 📋 Descripción

El programa calcula la ruta de menor costo entre dos nodos en una red de 16 nodos interconectados. Utiliza una cola de prioridad (min-heap) para explorar eficientemente los caminos y encuentra la ruta óptima.

## 🗂️ Estructura del Proyecto

```
dijkstra-algorithm/
├── src/
│   ├── Main.java       # Clase principal con el grafo y ejecución
│   ├── Dijkstra.java   # Implementación del algoritmo de Dijkstra
│   └── Graph.java      # Clase que representa el grafo
└── README.md           # Este archivo
```

## 📦 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **JDK (Java Development Kit)** versión 8 o superior
  - Descarga desde: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) o [OpenJDK](https://openjdk.org/)

### Verificar Instalación de Java

Abre una terminal/consola y ejecuta:

```bash
java -version
```

Deberías ver algo similar a:
```
java version "17.0.1" 2021-10-19 LTS
```

Si también necesitas compilar, verifica:
```bash
javac -version
```

## 🚀 Cómo Ejecutar el Proyecto

### Opción 1: Desde la Terminal (Windows)

1. **Abre PowerShell o CMD**

2. **Navega a la carpeta `src`:**
   ```powershell
   cd "ruta\completa\al\proyecto\dijkstra-algorithm\src"
   ```
   
   Ejemplo:
   ```powershell
   cd "C:\Users\TuUsuario\Desktop\dijkstra-algorithm\src"
   ```

3. **Compila todos los archivos Java:**
   ```powershell
   javac *.java
   ```
   
   Esto generará archivos `.class` en la misma carpeta.

4. **Ejecuta el programa:**
   ```powershell
   java Main
   ```

### Opción 2: Desde la Terminal (Linux/Mac)

1. **Abre la Terminal**

2. **Navega a la carpeta `src`:**
   ```bash
   cd /ruta/completa/al/proyecto/dijkstra-algorithm/src
   ```

3. **Compila todos los archivos Java:**
   ```bash
   javac *.java
   ```

4. **Ejecuta el programa:**
   ```bash
   java Main
   ```

### Opción 3: Usando un IDE (IntelliJ IDEA, Eclipse, VS Code)

#### IntelliJ IDEA:
1. Abre IntelliJ IDEA
2. File → Open → Selecciona la carpeta `dijkstra-algorithm`
3. Haz clic derecho en `Main.java` → Run 'Main.main()'

#### Eclipse:
1. Abre Eclipse
2. File → Open Projects from File System → Selecciona la carpeta `dijkstra-algorithm`
3. Haz clic derecho en `Main.java` → Run As → Java Application

#### VS Code:
1. Abre VS Code
2. Instala la extensión "Extension Pack for Java"
3. Abre la carpeta `dijkstra-algorithm`
4. Haz clic derecho en `Main.java` → Run Java

## 📊 Salida Esperada

Al ejecutar el programa, deberías ver:

```
## Resultado: Ruta mas corta del Nodo 1 al 16
--------------------------------------------------
Ruta: 1 -> 3 -> 7 -> 11 -> 14 -> 16
Costo Minimo Total: 74
```

## 🔧 Configuración del Grafo

El grafo actual tiene **16 nodos** con las siguientes conexiones (bidireccionales):

| Nodo | Conexiones (Nodo: Peso) |
|------|-------------------------|
| 1    | 2:20, 3:15, 4:18       |
| 2    | 1:20, 5:10, 6:10       |
| 3    | 1:15, 7:11             |
| 4    | 1:18, 8:12             |
| 5    | 2:10, 6:8, 9:16        |
| 6    | 2:10, 5:8, 7:12, 9:12  |
| 7    | 3:11, 6:12, 8:22, 10:18, 11:18 |
| 8    | 4:12, 7:22, 12:20      |
| 9    | 5:16, 6:12, 13:16      |
| 10   | 7:18, 13:17            |
| 11   | 7:18, 12:15, 14:16     |
| 12   | 8:20, 11:15, 15:15     |
| 13   | 9:16, 10:17, 14:10, 16:18 |
| 14   | 11:16, 13:10, 16:14    |
| 15   | 12:15, 16:25           |
| 16   | 13:18, 14:14, 15:25    |

### Modificar el Grafo

Para cambiar los nodos de inicio/destino, edita estas líneas en `Main.java`:

```java
int nodoInicio = 1;    // Cambia el nodo de inicio
int nodoDestino = 16;  // Cambia el nodo de destino
```

Para agregar/modificar conexiones, edita las líneas `addEdge()` en `Main.java`:

```java
grafoRed.addEdge(nodoOrigen, nodoDestino, peso);
```

## 🧪 Ejemplo de Uso Personalizado

```java
// Crear un nuevo grafo simple
Graph miGrafo = new Graph();

// Agregar conexiones
miGrafo.addEdge(1, 2, 5);
miGrafo.addEdge(2, 3, 10);
miGrafo.addEdge(1, 3, 20);

// Calcular ruta más corta
Dijkstra.Result resultado = Dijkstra.findShortestPath(miGrafo, 1, 3);

// Mostrar resultado
if (resultado.hasPath()) {
    System.out.println("Ruta: " + resultado.getPath());
    System.out.println("Costo: " + resultado.getMinCost());
}
```

## 🛠️ Solución de Problemas

### Error: "javac no se reconoce como comando"
- **Solución**: Java no está instalado o no está en el PATH del sistema.
  - Instala JDK
  - Agrega Java al PATH del sistema

### Error: "Could not find or load main class Main"
- **Solución**: Asegúrate de estar en la carpeta `src` cuando ejecutas `java Main`

### Error de compilación con caracteres especiales
- **Solución**: El código ya está optimizado para Windows-1252. Si tienes problemas, compila con:
  ```bash
  javac -encoding UTF-8 *.java
  ```

## 📝 Algoritmo

El programa usa el **Algoritmo de Dijkstra** que:
1. Inicializa todas las distancias como infinito excepto el nodo inicial (0)
2. Usa una cola de prioridad para explorar el nodo con menor distancia
3. Actualiza las distancias a los vecinos si se encuentra un camino más corto
4. Reconstruye la ruta usando un mapa de predecesores

**Complejidad Temporal**: O((V + E) log V)  
- V = número de vértices
- E = número de aristas

## 👨‍💻 Autor

Proyecto creado como implementación educativa del Algoritmo de Dijkstra.

## 📄 Licencia

Este proyecto es de código abierto y está disponible para uso educativo.
