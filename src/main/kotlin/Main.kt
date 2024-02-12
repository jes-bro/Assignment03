package org.example

/**
 * Implement Dijkstra's algorithm to calculate the path from a start node to an end node in a weighted
 * directed graph
 * @param graph A weighted directed graph of type WeightedDirectedGraph
 * @param startNode The node (Int) where the path should begin
 * @param endNode The node (Int) where the path should end
 * @return A [MutableList] that contains the shortest path between the start and end nodes
 */
fun dijkstras(graph: Graph<Int>, startNode: Int, endNode: Int):MutableList<Int>? {
    val numVertices = graph.getVertices().size
    println(numVertices)
    // Initialize everything to infinity except for startNode, which is initialized to
    // 0, because thats the distance to itself
    val dist = MutableList(numVertices) { Double.MAX_VALUE }.apply {
        this[startNode] = 0.0 // Distance to itself is 0
    }
    val prev = MutableList<Int?>(numVertices) { null }
    val priorityList = MyMinPriorityQueue<Int>()
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
                val alt = dist[u] + neighbor.value
                println("alt: $alt")
                val neighbor_key = neighbor.key
                println("n key: $neighbor_key")
                val neighbor_dist = dist[neighbor.key]
                println("n dist: $neighbor_dist ")
                if (alt < dist[neighbor.key]) {
                    println("got here too")
                    dist[neighbor.key] = alt
                    prev[neighbor.key] = u
                    priorityList.adjustPriority(neighbor.key, alt)
                    }
                    println("prev  at this point: $prev")
                }
            }
        }
    println("prev at end: $prev")
    val shortestPath = mutableListOf<Int>()
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
fun main() {
    val graph = WeightedDirectedGraph<Int>()
    // Assuming your Graph class has methods to add vertices and edges
    graph.addVertex(0)
    graph.addVertex(1)
    graph.addEdge(0, 1, 1.0) // Connected component 1

    graph.addVertex(2)
    graph.addVertex(3) // Connected component 2, no edges connecting to component 1

    val startNode = 0
    val endNode = 3
    val shortestPath = dijkstras(graph, startNode, endNode)

    println("graph edges: ${graph.getEdges(startNode)}")
    println("vertices: ${graph.getVertices()}")
    println("graph edges: ${graph.getEdges(startNode)}")
    println("Shortest path from $startNode to $endNode: $shortestPath")
}