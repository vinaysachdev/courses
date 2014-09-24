"""
Implement the code to generate complete undirected random graph
"""
import random

def make_undirected_random_graph(num_nodes, probability):
    """
    This function takes a num of nodes as input and return complete graph
    """
    ugraph = {}
    no_of_edges = 0
    for node_1 in range(0, num_nodes):
        for node_2 in range(0, num_nodes):
            rand = random.random()
            if rand < probability and node_1 != node_2:
                neighbour = ugraph.get(node_1, set())
                neighbour.add(node_2)
                ugraph[node_1] = neighbour
                neighbour = ugraph.get(node_2, set());
                neighbour.add(node_1)
                ugraph[node_2] = neighbour
                no_of_edges += 1
    print "make_undirected_random_graph: " , no_of_edges
    return ugraph

#print make_undirected_random_graph(1347, 0.00175)
