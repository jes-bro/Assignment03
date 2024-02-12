package org.example

fun dijkstras(graph: Graph<Int>, startNode: Int, endNode: Int):MutableList<Int> {
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
    shortestPath.reverse()
    return shortestPath
}
fun main() {
    val graph = WeightedDirectedGraph<Int>()
    graph.addVertex(0)
    graph.addVertex(1)
    graph.addVertex(2)
    graph.addVertex(3)
    graph.addVertex(4)
    graph.addEdge(0, 1, 4.0)
    graph.addEdge(0, 2, 1.0)
    graph.addEdge(2, 1, 2.0)
    graph.addEdge(1, 3, 1.0)
    graph.addEdge(2, 3, 5.0)
    graph.addEdge(3, 4, 3.0)

    val startNode = 0
    val endNode = 4
    println("graph edges: ${graph.getEdges(startNode)}")
    println("vertices: ${graph.getVertices()}")
    val shortestPath = dijkstras(graph, startNode, endNode)
    println("graph edges: ${graph.getEdges(startNode)}")
    println("Shortest path from $startNode to $endNode: $shortestPath")
}