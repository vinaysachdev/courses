"""
Implementation of graph traversal of Breath first search(BFS) algorithm
"""
import random
from collections import deque

def bfs_visited(ugraph, start_node):
    """
    Function that implements the BFS algorithm for graph traversal
    """
    queue = deque()
    queue.append(start_node)
    visited = set([start_node])
    while queue:
        node = queue.popleft()
        for neighbor in  ugraph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)
    
    return visited

def cc_visited(ugraph):
    """
    Takes a undirected graph and returns a list of sets, where each set
    contains all the node in connected component
    """
    remaining_nodes = set(ugraph.keys())
    connected_comps = []
    while remaining_nodes:
        start_node = remaining_nodes.pop()
        visited = bfs_visited(ugraph, start_node)
        connected_comps.append(visited)
        remaining_nodes.difference_update(visited) 
    
    return connected_comps

def largest_cc_size(ugraph):
    """
    Takes a undirected graph ugraph and return a size (an interger) of largest
    connected component in ugraph
    """
    largest_component = 0
    connected_components = cc_visited(ugraph)
    for component in connected_components:
        if len(component) > largest_component:
            largest_component = len(component)

    return largest_component

def compute_resilience(ugraph, attack_order):
    """
    Takes the undirected graph and list of nodes attack_order and iterate through the nodes in attack_order.
        
    """
    largest_conn_comp = [largest_cc_size(ugraph)]
    for node_to_remove in attack_order:
        neighbors = ugraph.pop(node_to_remove)
        for node in neighbors:
            set_of_neighbor = ugraph[node]
            set_of_neighbor.remove(node_to_remove)
        largest_conn_comp.append(largest_cc_size(ugraph))    

    return largest_conn_comp
    
ugraph = {1 : set([2,3,5]),
          2 : set([1]),
          3 : set([1]),
          4 : set([]),
          5 : set([1,6]),
          6 : set([5])}
                  
#print bfs_visited(ugraph, 1)
#print cc_visited(ugraph)
#print largest_cc_size(ugraph)
#print compute_resilience(ugraph,[1])
