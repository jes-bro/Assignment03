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

}