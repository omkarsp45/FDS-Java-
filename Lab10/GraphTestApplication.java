
// import java.util package for using collections
import java.util.*;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

// define the Vertex interface
interface Vertex {
    // get the label of the vertex
    String getLabel();
}

// define the Edge interface
interface Edge {
    // get the source vertex of the edge
    Vertex getSource();

    // get the destination vertex of the edge
    Vertex getDestination();

    // get the weight of the edge
    int getWeight();
}

// define the Graph interface
interface Graph {
    // get the number of vertices in the graph
    int numVertices();

    // get the number of edges in the graph
    int numEdges();

    // get an iterator of all vertices in the graph
    Iterator<Vertex> vertices();

    // get an iterator of all edges in the graph
    Iterator<Edge> edges();

    // get the edge from vertex u to vertex v, or null if none
    Edge getEdge(Vertex u, Vertex v);

    // get an array of the two endpoint vertices of edge e
    Vertex[] endVertices(Edge e);

    // get the vertex opposite to v on edge e
    Vertex opposite(Vertex v, Edge e);

    // get the out-degree of vertex v
    int outDegree(Vertex v);

    // get the in-degree of vertex v
    int inDegree(Vertex v);

    // get an iterator of all outgoing edges of vertex v
    Iterator<Edge> outGoingEdges(Vertex v);

    // get an iterator of all incoming edges of vertex v
    Iterator<Edge> inComingEdges(Vertex v);

    // insert and return a new vertex with label x
    Vertex insertVertex(String x);

    // insert and return a new edge from u to v with weight x
    Edge insertEdge(Vertex u, Vertex v, int x);
}

// define the InnerVertex class that implements the Vertex interface
class InnerVertex implements Vertex {
    // the label of the vertex
    private String label;
    // the position of the vertex in the vertex array
    private int position;

    // the constructor of the InnerVertex class
    public InnerVertex(String label, int position) {
        this.label = label;
        this.position = position;
    }

    // get the label of the vertex
    public String getLabel() {
        return label;
    }

    // get the position of the vertex
    public int getPosition() {
        return position;
    }

    // override the equals method to compare two vertices by their labels
    public boolean equals(Object o) {
        if (o instanceof InnerVertex) {
            InnerVertex v = (InnerVertex) o;
            return label.equals(v.label);
        }
        return false;
    }

    // override the hashCode method to generate a hash code based on the label
    public int hashCode() {
        return label.hashCode();
    }

    // override the toString method to return the label of the vertex
    public String toString() {
        return label;
    }
}

// define the InnerEdge class that implements the Edge interface
class InnerEdge implements Edge {
    // the source vertex of the edge
    private Vertex source;
    // the destination vertex of the edge
    private Vertex destination;
    // the weight of the edge
    private int weight;

    // the constructor of the InnerEdge class
    public InnerEdge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // get the source vertex of the edge
    public Vertex getSource() {
        return source;
    }

    // get the destination vertex of the edge
    public Vertex getDestination() {
        return destination;
    }

    // get the weight of the edge
    public int getWeight() {
        return weight;
    }

    // override the equals method to compare two edges by their source and
    // destination vertices
    public boolean equals(Object o) {
        if (o instanceof InnerEdge) {
            InnerEdge e = (InnerEdge) o;
            return source.equals(e.source) && destination.equals(e.destination);
        }
        return false;
    }

    // override the hashCode method to generate a hash code based on the source and
    // destination vertices
    public int hashCode() {
        return source.hashCode() + destination.hashCode();
    }

    // override the toString method to return the source, destination and weight of
    // the edge
    public String toString() {
        return "(" + source + ", " + destination + ", " + weight + ")";
    }
}

// define the AdjacencyMapGraph class that implements the Graph interface
class AdjacencyMapGraph implements Graph {
    // the array of vertices in the graph
    private Vertex[] vertices;
    // the array of adjacency maps for each vertex
    private Map<Vertex, Edge>[] adjMaps;
    // the number of vertices in the graph
    private int numVertices;
    // the number of edges in the graph
    private int numEdges;

    // the constructor of the AdjacencyMapGraph class
    public AdjacencyMapGraph(int capacity) {
        // initialize the vertex array with the given capacity
        vertices = new InnerVertex[capacity];
        // initialize the adjacency map array with the same capacity
        adjMaps = (Map<Vertex, Edge>[]) new Map[capacity];
        // initialize the number of vertices and edges to zero
        numVertices = 0;
        numEdges = 0;
    }

    // get the number of vertices in the graph
    public int numVertices() {
        return numVertices;
    }

    // get the number of edges in the graph
    public int numEdges() {
        return numEdges;
    }

    // get an iterator of all vertices in the graph
    public Iterator<Vertex> vertices() {
        // create a list to store the vertices
        List<Vertex> list = new ArrayList<>();
        // loop through the vertex array and add the non-null vertices to the list
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i] != null) {
                list.add(vertices[i]);
            }
        }
        // return an iterator of the list
        return list.iterator();
    }

    // get an iterator of all edges in the graph
    public Iterator<Edge> edges() {
        // create a list to store the edges
        List<Edge> list = new ArrayList<>();
        // loop through the adjacency map array and add the edges to the list
        for (int i = 0; i < numVertices; i++) {
            if (adjMaps[i] != null) {
                list.addAll(adjMaps[i].values());
            }
        }
        // return an iterator of the list
        return list.iterator();
    }

    // get the edge from vertex u to vertex v, or null if none
    public Edge getEdge(Vertex u, Vertex v) {
        // check if u and v are valid vertices
        validate(u);
        validate(v);
        // get the position of u in the vertex array
        int uPos = ((InnerVertex) u).getPosition();
        // get the adjacency map of u
        Map<Vertex, Edge> map = adjMaps[uPos];
        // if the map is not null, return the edge associated with v, or null if none
        if (map != null) {
            return map.get(v);
        }
        return null;
    }

    // get an array of the two endpoint vertices of edge e
    public Vertex[] endVertices(Edge e) {
        // check if e is a valid edge
        validate(e);
        // create an array of size 2
        Vertex[] endpoints = new Vertex[2];
        // assign the source and destination vertices of e to the array
        endpoints[0] = e.getSource();
        endpoints[1] = e.getDestination();
        // return the array
        return endpoints;
    }

    // get the vertex opposite to v on edge e
    public Vertex opposite(Vertex v, Edge e) {
        // check if v and e are valid vertex and edge
        validate(v);
        validate(e);
        // get the source and destination vertices of e
        Vertex u = e.getSource();
        Vertex w = e.getDestination();
        // if v is equal to u, return w, or if v is equal to w, return u
        if (v.equals(u)) {
            return w;
        } else if (v.equals(w)) {
            return u;
        } else {
            // otherwise, throw an IllegalArgumentException
            throw new IllegalArgumentException("v is not incident to e");
        }
    }

    // get the out-degree of vertex v
    public int outDegree(Vertex v) {
        // check if v is a valid vertex
        validate(v);
        // get the position of v in the vertex array
        int vPos = ((InnerVertex) v).getPosition();
        // get the adjacency map of v
        Map<Vertex, Edge> map = adjMaps[vPos];
        // if the map is not null, return its size, or zero otherwise
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    // get the in-degree of vertex v
    public int inDegree(Vertex v) {
        // check if v is a valid vertex
        validate(v);
        // initialize a counter for the in-degree
        int inDegree = 0;
        // loop through the adjacency map array
        for (int i = 0; i < numVertices; i++) {
            // get the current map
            Map<Vertex, Edge> map = adjMaps[i];
            // if the map is not null and contains v as a key, increment the counter
            if (map != null && map.containsKey(v)) {
                inDegree++;
            }
        }
        // return the counter
        return inDegree;
    }

    // get an iterator of all outgoing edges of vertex v
    public Iterator<Edge> outGoingEdges(Vertex v) {
        // check if v is a valid vertex
        validate(v);
        // get the position of v in the vertex array
        int vPos = ((InnerVertex) v).getPosition();
        // get the adjacency map of v
        Map<Vertex, Edge> map = adjMaps[vPos];
        // if the map is not null, return an iterator of its values, or an empty
        // iterator otherwise
        if (map != null) {
            return map.values().iterator();
        }
        return Collections.emptyIterator();
    }

    // get an iterator of all incoming edges of vertex v
    public Iterator<Edge> inComingEdges(Vertex v) {
        // check if v is a valid vertex
        validate(v);
        // create a list to store the incoming edges
        List<Edge> list = new ArrayList<>();
        // loop through the adjacency map array
        for (int i = 0; i < numVertices; i++) {
            // get the current map
            Map<Vertex, Edge> map = adjMaps[i];
            // if the map is not null and contains v as a key, add the corresponding edge to
            // the list
            if (map != null && map.containsKey(v)) {
                list.add(map.get(v));
            }
        }
        // return an iterator of the list
        return list.iterator();
    }

    // insert and return a new vertex with label x
    public Vertex insertVertex(String x) {
        // check if the vertex array is full
        if (numVertices == vertices.length) {
            // throw an IllegalStateException
            throw new IllegalStateException("Vertex array is full");
        }
        // create a new vertex with label x and the next available position
        Vertex v = new InnerVertex(x, numVertices);
        // assign the vertex to the vertex array
        vertices[numVertices] = v;
        // increment the number of vertices
        numVertices++;
        // return the vertex
        return v;
    }

    // insert and return a new edge from u to v with weight x
    public Edge insertEdge(Vertex u, Vertex v, int x) {
        // check if u and v are valid vertices
        validate(u);
        validate(v);
        // check if there is already an edge from u to v
        if (getEdge(u, v) != null) {
            // throw an IllegalArgumentException
            throw new IllegalArgumentException("Edge from u to v already exists");
        }
        // create a new edge from u to v with weight x
        Edge e = new InnerEdge(u, v, x);
        // get the position of u in the vertex array
        int uPos = ((InnerVertex) u).getPosition();
        // get the adjacency map of u
        Map<Vertex, Edge> map = adjMaps[uPos];
        // if the map is null, create a new hash map and assign it to the adjacency map
        // array
        if (map == null) {
            map = new HashMap<>();
            adjMaps[uPos] = map;
        }
        // put the edge with v as the key in the map
        map.put(v, e);
        // increment the number of edges
        numEdges++;
        // return the edge
        return e;
    }

    // validate that the vertex is an instance of InnerVertex and belongs to this
    // graph
    private void validate(Vertex v) {
        // if v is not an instance of InnerVertex, throw a IllegalArgumentException
        if (!(v instanceof InnerVertex)) {
            throw new IllegalArgumentException("Vertex is not valid");
        }
        // get the position of v in the vertex array
        int vPos = ((InnerVertex) v).getPosition();
        // if the position is out of bounds or the vertex array does not contain v,
        // throw a IllegalArgumentException
        if (vPos < 0 || vPos >= numVertices || vertices[vPos] != v) {
            throw new IllegalArgumentException("Vertex does not belong to this graph");
        }
    }

    // validate that the edge is an instance of InnerEdge and belongs to this graph
    private void validate(Edge e) {
        // if e is not an instance of InnerEdge, throw a IllegalArgumentException
        if (!(e instanceof InnerEdge)) {
            throw new IllegalArgumentException("Edge is not valid");
        }
        // get the source and destination vertices of e
        Vertex u = e.getSource();
        Vertex v = e.getDestination();
        // validate both vertices
        validate(u);
        validate(v);
        // get the position of u in the vertex array
        int uPos = ((InnerVertex) u).getPosition();
        // get the adjacency map of u
        Map<Vertex, Edge> map = adjMaps[uPos];
        // if the map is null or does not contain e with v as the key, throw a
        // IllegalArgumentException
        if (map == null || map.get(v) != e) {
            throw new IllegalArgumentException("Edge does not belong to this graph");
        }
    }
}

public class GraphTestApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new AdjacencyMapGraph(10); // You can adjust the capacity as needed
        int choice = 0;
        System.out.println("\nMenu:");
        System.out.println("1. Insert Vertex");
        System.out.println("2. Insert Edge");
        System.out.println("3. Display Vertices");
        System.out.println("4. Display Edges");
        System.out.println("5. Display Outgoing Edges");
        System.out.println("6. Display Incoming Edges");
        System.out.println("7. Exit");
        do {
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter the label for the new vertex: ");
                        String label = scanner.nextLine();
                        Vertex v = graph.insertVertex(label);
                        System.out.println("Vertex " + v.getLabel() + " inserted successfully.");
                        break;

                    case 2:
                        System.out.print("Enter the label of the source vertex: ");
                        String sourceLabel = scanner.nextLine();
                        Vertex sourceVertex = findVertex(graph, sourceLabel);

                        System.out.print("Enter the label of the destination vertex: ");
                        String destLabel = scanner.nextLine();
                        Vertex destVertex = findVertex(graph, destLabel);

                        System.out.print("Enter the weight of the edge: ");
                        int weight = scanner.nextInt();
                        Edge e = graph.insertEdge(sourceVertex, destVertex, weight);
                        System.out.println("Edge " + e + " inserted successfully.");
                        break;

                    case 3:
                        System.out.println("Vertices:");
                        displayVertices(graph);
                        break;

                    case 4:
                        System.out.println("Edges:");
                        displayEdges(graph);
                        break;

                    case 5:
                        System.out.print("Enter the label of the vertex: ");
                        String outVertexLabel = scanner.nextLine();
                        Vertex outVertex = findVertex(graph, outVertexLabel);
                        displayOutgoingEdges(graph, outVertex);
                        break;

                    case 6:
                        System.out.print("Enter the label of the vertex: ");
                        String inVertexLabel = scanner.nextLine();
                        Vertex inVertex = findVertex(graph, inVertexLabel);
                        displayIncomingEdges(graph, inVertex);
                        break;

                    case 7:
                        System.out.println("Exiting the program. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                choice = 0;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void displayVertices(Graph graph) {
        Iterator<Vertex> iterator = graph.vertices();
        while (iterator.hasNext()) {
            Vertex v = iterator.next();
            System.out.println(v.getLabel());
        }
    }

    private static void displayEdges(Graph graph) {
        Iterator<Edge> iterator = graph.edges();
        while (iterator.hasNext()) {
            Edge e = iterator.next();
            System.out.println(e);
        }
    }

    private static void displayOutgoingEdges(Graph graph, Vertex v) {
        Iterator<Edge> iterator = graph.outGoingEdges(v);
        while (iterator.hasNext()) {
            Edge e = iterator.next();
            System.out.println(e);
        }
    }

    private static void displayIncomingEdges(Graph graph, Vertex v) {
        Iterator<Edge> iterator = graph.inComingEdges(v);
        while (iterator.hasNext()) {
            Edge e = iterator.next();
            System.out.println(e);
        }
    }

    private static Vertex findVertex(Graph graph, String label) {
        Iterator<Vertex> iterator = graph.vertices();
        while (iterator.hasNext()) {
            Vertex v = iterator.next();
            if (v.getLabel().equals(label)) {
                return v;
            }
        }
        throw new IllegalArgumentException("Vertex with label " + label + " not found.");
    }
}
