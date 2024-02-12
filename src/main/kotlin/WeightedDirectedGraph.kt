package org.example

class WeightedDirectedGraph <VertexType>: Graph<VertexType>{
    /**
     * @return the vertices in the graph
     */
    private var vertices = mutableSetOf<VertexType>()
    private var edges = mutableMapOf<VertexType, MutableMap<VertexType, Double>>()

    /**
     * Add the vertex [v] to the graph
     * @param v the vertex to add
     * @return true if the vertex is successfully added, false if the vertex
     * was already in the graph
     */
    fun addVertex(v: VertexType): Boolean {
        if (vertices.contains(v)) {
            return false
        }
        vertices.add(v)
        return true
    }

    /**
     * Retrieves a set of all vertices present in the graph
     *
     * @return A [Set] containing all the vertices in the graph
     */
    override fun getVertices(): Set<VertexType> {
        return vertices.toSet()
    }

    /**
     * Adds an edge from one vertex to another with the specified cost.
     *
     * @param from The vertex from which the edge originates
     * @param to The destination vertex of the edge
     * @param cost The cost or weight associated with the edge
     */
    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        //Add new outgoing connection from from to to
      if (!edges.contains(from)) {
        edges[from] = mutableMapOf()
      }
      edges[from]?.put(to, cost)
    }

    /**
     * Retrieves a map of edges and their costs originating from a specified vertex.
     *
     * @param from The vertex from which outgoing edges are to be retrieved.
     * @return a [Map] where each key is a destination vertex reachable directly from `from`,
     * and each value is the cost of the edge to that vertex.
     */
    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        return edges[from]?.toMap() ?: emptyMap()
    }

    /**
     * Remove all edges and vertices from the graph
     */
    override fun clear() {
        vertices = mutableSetOf()
        edges = mutableMapOf()
    }

    /**
     * Implement Dijkstra's algorithm to calculate the path from a start node to an end node in a weighted
     * directed graph
     * @param graph A weighted directed graph of type WeightedDirectedGraph
     * @param startNode The node (Int) where the path should begin
     * @param endNode The node (Int) where the path should end
     * @return A [MutableList] that contains the shortest path between the start and end nodes
     */
    fun dijkstras(graph: Graph<VertexType>, startNode: VertexType, endNode: VertexType):MutableList<VertexType>? {
        val numVertices = graph.getVertices().size
        println(numVertices)
        // Initialize everything to infinity except for startNode, which is initialized to
        // 0, because thats the distance to itself
        val dist = mutableMapOf<VertexType, Double>() // T is your generic type
        val prev = mutableMapOf<VertexType, VertexType?>()
        graph.getVertices().forEach { vertex ->
            dist[vertex] = Double.MAX_VALUE // Use Double.MAX_VALUE for infinity
            prev[vertex] = null
        }
        dist[startNode] = 0.0 // Set start node distance to 0
        val priorityList = MyMinPriorityQueue<VertexType>()
        priorityList.addWithPriority(startNode, 0.0)
        while (!priorityList.isEmpty()) {
            val u = priorityList.next()
            println("u: $u")
            if (u != null) {
                println("got here")
                val curr_edges = graph.getEdges(u)
                println("graph edges in loop: $curr_edges")
                println("prev before for loop: $prev")
                for (neighbor in graph.getEdges(u)) {
                    println("neighbor: $neighbor")
                    val distance = dist[u]
                    println("dist u: $distance")
                    val alt = dist[u]?.plus(neighbor.value)
                    println("alt: $alt")
                    val neighbor_key = neighbor.key
                    println("n key: $neighbor_key")
                    val neighbor_dist = dist[neighbor.key]
                    println("n dist: $neighbor_dist ")
                    if (alt != null) {
                        if (alt < dist[neighbor.key]!!) {
                            println("got here too")
                            dist[neighbor.key] = alt
                            prev[neighbor.key] = u
                            priorityList.adjustPriority(neighbor.key, alt)
                        }
                    }
                    println("prev  at this point: $prev")
                }
            }
        }
        println("prev at end: $prev")
        val shortestPath = mutableListOf<VertexType>()
        var currentNode = endNode // for indexing
        shortestPath.add(currentNode)
        println("prev: $prev")
        println(dist)
        while (currentNode != startNode) {
            println(currentNode)
            println(prev[currentNode])
            currentNode = prev[currentNode] ?: break
            shortestPath.add(currentNode)
        }
        if (shortestPath.size == 1) {
            return null
        }
        shortestPath.reverse()
        return shortestPath
    }
}