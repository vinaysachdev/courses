"""
implementaion of UPA algorithm to generate undirected graph
"""
from upa_trail import UPATrial

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

def upa(noOfNodes, initial_num):
    """
    implement UPA algorithm to generate a undirected graph
    """
    ugraph = make_complete_graph(initial_num)
    no_of_edges = initial_num*(initial_num - 1)/2
    objUPATrail = UPATrial(initial_num)
    for node_1 in range(initial_num,noOfNodes):
        for node_2 in objUPATrail.run_trial(initial_num):
            neighbours = ugraph.get(node_1, set())
            neighbours.add(node_2)
            ugraph[node_1] = neighbours
            neighbours = ugraph.get(node_2);
            neighbours.add(node_1)
            ugraph[node_2] = neighbours
            no_of_edges += 1
#print "upa: no_of_edges: ",no_of_edges
    return ugraph

#print make_complete_graph(5)
#print upa(1347, 2)
