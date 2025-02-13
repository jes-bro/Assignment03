package org.example

/**
 * An implementation of a minimum priority queue
 * @param queue a minheap that stores the vertices and their
 * priorities in order from minimum priority to maximum priority
 * as depth increases
 */
class MyMinPriorityQueue<T>: MinPriorityQueue<T> {
        private var queue = MinHeap<T>()
        /**
         * @return true if the queue is empty, false otherwise
         */
        override fun isEmpty(): Boolean {
            return queue.isEmpty()
        }

        /**
         * Add [elem] with at level [priority]
         * @param elem the element to be added to the queue
         * @param priority the priority of the element, with lower values indicating higher priority
         */
        override fun addWithPriority(elem: T, priority: Double) {
            queue.insert(elem, priority)
        }

        /**
         * Get the next (lowest priority) element.
         * @return the next element in terms of priority.  If empty, return null.
         */
        override fun next(): T? {
            if (queue.isEmpty()) {
                return null
            } else {
                return queue.getMin()
            }
        }

        /**
         * Adjust the priority of the given element
         * @param elem whose priority should change
         * @param newPriority the priority to use for the element
         *   the lower the priority the earlier the element int
         *   the order.
         */
        override fun adjustPriority(elem: T, newPriority: Double) {
            if (!queue.contains(elem)) {
              queue.insert(elem,newPriority)
            } else {
              queue.adjustHeapNumber(elem, newPriority)
            }
        }

}