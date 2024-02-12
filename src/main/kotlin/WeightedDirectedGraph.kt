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
         *   was already in the graph
         */
        fun addVertex(v: VertexType): Boolean {
            if (vertices.contains(v)) {
                return false
            }
            vertices.add(v)
            return true
        }

        override fun getVertices(): Set<VertexType> {
            return vertices.toSet()
        }

        /**
         * Add an
         */
        override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
            //Add new outgoing connection from from to to
          if (!edges.contains(from)) {
            edges[from] = mutableMapOf()
          }
          edges[from]?.put(to, cost)
        }

        /**
         *
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