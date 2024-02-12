package org.example

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