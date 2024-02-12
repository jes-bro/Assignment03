import org.example.MyMinPriorityQueue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * A class to test all of the functions in the class
 * MyMinPriorityQueue. The class consists of the following methods:
 * - isEmpty: Which checks whether or not the queue is empty
 * - addWithPriority: Which inserts elements into their correct
 * position in the queue
 * - next: Which dequeues the top element, which has the lowest
 * priority value
 * - adjustPriority: Which updates the priority of a particular
 * element and rearranges the queue to maintain the correct
 * hierarchy
 */
class MyMinPriorityQueueTest {

    /**
     * Test whether isEmpty returns true when the queue is empty
     * and false when the queue has elements
     */
    @Test
    fun testIsEmpty() {
        val queue = MyMinPriorityQueue<Int>()

        // Initially, the queue should be empty
        assertTrue(queue.isEmpty(), "The queue should be empty before elements are added to it")

        // After adding an element, the queue should not be empty
        queue.addWithPriority(1, 1.0)
        assertFalse(queue.isEmpty(), "The queue should not be empty once an element has been added")
    }

    /**
     * Test whether addWithPriority queues elements in the correct order
     */
    @Test
    fun testAddWithPriority() {
        val queue = MyMinPriorityQueue<Int>()
        queue.addWithPriority(1, 5.0)
        queue.addWithPriority(2, 1.0)
        val next = queue.next()
        assertEquals(2, next, "The element with the lowest priority value / highest position should be returned first")

    }

    /**
     * Test whether next queues the element with the lowest priority value
     */
    @Test
    fun testNext() {
        val queue = MyMinPriorityQueue<Int>()
        queue.addWithPriority(1, 1.0)
        queue.addWithPriority(2, 0.5)

        val firstNext = queue.next()
        assertEquals(2, firstNext, "The lower priority number in the queue should be returned first")

        val secondNext = queue.next()
        assertEquals(1, secondNext, "The lowest priority number in the queue should be returned first")
    }

    /**
     * Test whether adjust priority reorders elements correctly
     */
    @Test
    fun testAdjustPriority() {
        val queue = MyMinPriorityQueue<Int>()
        queue.addWithPriority(1, 1.0)
        queue.addWithPriority(2, 2.0)
        queue.addWithPriority(3, 3.0)

        // Make the priority of 3 higher
        queue.adjustPriority(3, 0.5)

        val next = queue.next()
        assertEquals(3, next, "Element 3 should have the highest priority and be queued next")
    }
}