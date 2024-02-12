import org.example.WeightedDirectedGraph
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 *
 */
class WeightedDirectedGraphTest {
    /**
     * Ensure that vertices cannot be added twice to the vertices
     * in the graph
     */
    @Test
    fun testAddVertex() {
        val graph = WeightedDirectedGraph<Int>()
        assertTrue(graph.addVertex(1), "Adding the vertex should work because its not already in the graph")
        assertFalse(graph.addVertex(1), "Adding the vertex again should fail")
    }

    /**
     * Ensure that getVertices returns the vertices
     * in the graph
     */
    @Test
    fun testGetVertices() {
        val graph = WeightedDirectedGraph<Int>()
        graph.addVertex(1)
        graph.addVertex(2)
        val vertices = graph.getVertices()
        assertEquals(setOf(1, 2), vertices, "Make sure all of the vertices show up")
    }

    /**
     * Test that adding updates the graph's edges map appropriately
     */
    @Test
    fun testAddEdge() {
        val graph = WeightedDirectedGraph<Int>()
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addEdge(1, 2, 5.0) // Add an edge from vertex 1 to vertex 2 with weight 5.0
        val edgesFrom1 = graph.getEdges(1)
        assertTrue(edgesFrom1.containsKey(2) && edgesFrom1[2] == 5.0, "The map at the from vertex should include the to vertex")
    }

    /**
     * Ensure that getEdges has a mapping from the from vertex
     * to all of the to vertices its connected to. Ensure that
     * they show up in the map.
     */
    @Test
    fun testGetEdges() {
        val graph = WeightedDirectedGraph<Int>()
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addEdge(1, 2, 1.0)
        graph.addEdge(1, 3, 2.0)

        val edges = graph.getEdges(1)
        assertEquals(2, edges.size, "The first vertex should have 2 edges mapped to it")
        assertEquals(1.0, edges[2], "Edge 1-2 should have a weight of 1.0")
        assertEquals(2.0, edges[3], "Edge 1-3 should have weight of 2.0")
    }

    /**
     * Ensure that clear actually clears the graph's nodes
     * and edges
     */
    @Test
    fun testClear() {
        val graph = WeightedDirectedGraph<Int>()
        graph.addVertex(1)
        graph.addVertex(2)
        graph.addVertex(3)
        graph.addEdge(1, 2, 1.0)
        graph.addEdge(1, 3, 2.0)
        graph.clear()
        assertEquals(emptySet(), graph.getVertices(), "The vertices set should be empty")
        assertEquals(emptyMap(), graph.getEdges(1), "The edges map should be empty")
    }
}