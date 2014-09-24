"""
Implementation of FastTargetedOrder
"""

from load_graph import copy_graph, delete_node

def fast_targeted_order(ugraph):
    """
    This function takes an undirected graph and do the following:
    This method creates a list degree_sets whose kth element is the set of nodes of degree k.
    The method then iterates through the list degree_sets in order of decreasing degree.
    The method then repeatedly chooses a node from this set, deletes that node from the graph,
    and updates degree_sets appropriately.
    """
    # copy the graph
    graph = copy_graph(ugraph)
    degree_sets = dict((indx, set([])) for indx in range(0, len(graph)));

    for node, neighbours in graph.iteritems():
        node_set = degree_sets.get(len(neighbours), set())
        node_set.add(node)
        degree_sets[len(neighbours)] = node_set

    node_list = []
    i = 0
    for k in range(len(graph)-1, 0, -1):
        while len(degree_sets[k]) != 0:
            node = degree_sets[k].pop()
            for neighbour in graph[node]:
                node_degree = len(graph[neighbour])
                degree_sets[node_degree].discard(neighbour)
                nodes = degree_sets[node_degree-1]
                nodes.add(neighbour)
                degree_sets[node_degree-1] = nodes
        
            node_list.append(node)
            i += 1
            delete_node(graph, node)
    return node_list
                       
