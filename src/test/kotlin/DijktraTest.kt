import org.example.WeightedDirectedGraph
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * A collection of tests to ensure the dijkstra method (of the weighted directed graph
 * class) works correctly
 */
class DijkstraTest {

    /**
     * Test whether a disconnected graph, with no connection between the start
     * and end nodes, returns null
     */
    @Test
    fun testDisconnectedGraph() {
        val graph = WeightedDirectedGraph<Int>()
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addEdge(0, 1, 1.0)

        graph.addVertex(2)
        graph.addVertex(3)

        val startNode = 0
        val endNode = 3
        val shortestPath = graph.dijkstras(graph, startNode, endNode)

        assertEquals(null, shortestPath, "The path should be null for a disconnected graph")
    }

    /**
     * Ensure that Dijkstra works on a graph with one edge
     */
    @Test
    fun testGraphWithOneEdge() {
        val graph = WeightedDirectedGraph<Int>()
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addEdge(0, 1, 1.0)
        val startNode = 0
        val endNode = 1
        val shortestPath = graph.dijkstras(graph, startNode, endNode)
        assertEquals(mutableListOf(0, 1), shortestPath, "The shortest path should be between the only two vertices in the graph")
    }

    /**
     * Test that Dijkstra selects the shortest path when there are
     * several options
     */
    @Test
    fun testLongCompetingPaths() {
        val graph = WeightedDirectedGraph<Int>()
        graph.addVertex(0)
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addVertex(4)

        graph.addEdge(0, 1, 1.0)
        graph.addEdge(1, 4, 1.0)

        graph.addEdge(0, 2, 5.0)
        graph.addEdge(2, 3, 5.0)
        graph.addEdge(3, 4, 1.0)

        val startNode = 0
        val endNode = 4
        val shortestPath = graph.dijkstras(graph, startNode, endNode)

        val expectedPath = mutableListOf(0, 1, 4)
        assertEquals(expectedPath, shortestPath, "The short path should be selected by the algorithm")
    }
}