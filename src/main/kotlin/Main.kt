package org.example

/**
 * Use Dijkstra's algorithm to calculate the shortest
 * route between beaches on Cape Cod (my home turf)
 */
fun main() {
    val graph = WeightedDirectedGraph<String>()

    /*
    Race Point Beach, Provincetown = RPB
    Nauset Beach, Orleans = NB
    Coast Guard Beach, Eastham = CGB
    Bank Street Beach, Harwich = BSB
    Red River Beach, Harwich = RRB
     */

    // Add beaches as vertices
    val beaches = listOf("NB", "CGB", "RPB", "OSB", "SNB")
    beaches.forEach { beach ->
        graph.addVertex(beach)
    }

    // Add distances in miles as edges
    // Add vertices
    graph.addVertex("RPB")
    graph.addVertex("NB")
    graph.addVertex("CGB")
    graph.addVertex("BSB")
    graph.addVertex("RRB")

    // Add edges with distances
    graph.addEdge("RPB", "NB", 27.0)
    graph.addEdge("NB", "CGB", 16.0)
    graph.addEdge("CGB", "BSB", 23.0)
    graph.addEdge("BSB", "RRB", 4.0)
    graph.addEdge("RRB", "RPB", 50.0)
    graph.addEdge("NB", "BSB", 20.0)
    val startBeach = "NB"
    val endBeach = "RPB"
    val shortestPath = graph.dijkstras(graph, startBeach, endBeach)
    println("Shortest path from $startBeach to $endBeach: $shortestPath")
}