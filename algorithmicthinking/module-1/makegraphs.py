"""
This file implements project for Module-1.
"""

#These are example graphs.
EX_GRAPH0 = {0: set([1,2]),
             1: set([]),
             2: set([])}

EX_GRAPH1 = {0: set([1,4,5]),
             1: set([2,6]),
             2: set([3]),
             3: set([0]),
             4: set([1]),
             5: set([2]),
             6: set([])}
                
EX_GRAPH2 = {0: set([1,4,5]),
             1: set([2,6]),
             2: set([3,7]),
             3: set([7]),
             4: set([1]),
             5: set([2]),
             6: set([]),
             7: set([3]),
             8: set([1,2]),
             9: set([0,3,4,5,6,7])}

def make_complete_graph(num_nodes):
    """
    This function returns complete graph i.e. every node is connected to every node.
    """
    graph = {}
    if num_nodes <= 0:
        return graph;
    for node_1 in range(0, num_nodes):
        graph[node_1] = {node_2 for node_2 in range(0, num_nodes) if node_1!=node_2}
    return graph                


def compute_in_degrees(digraph):
    """
    This function returns dictionary with node name as key and no of in degree as value.
    """
    in_degrees = dict((key, 0) for key in digraph.iterkeys());
    for neighbours in digraph.values():
        for neighbour in neighbours:
            in_degrees[neighbour] += 1

    return in_degrees
    
def in_degree_distribution(digraph):
    """
    takes a directed graph digraph (represented as a dictionary)
    and computes the unnormalized distribution of the in-degrees of the graph.
    """
    in_deg_dist = {}
    in_degrees = compute_in_degrees(digraph)
    for in_degree in in_degrees.itervalues():
        val = in_deg_dist.get(in_degree, 0)
        in_deg_dist[in_degree] = val + 1
    return in_deg_dist

