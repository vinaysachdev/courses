"""
This file contains plotting fucntion for application in Module-1
"""

import SimpleGUICS2Pygame.simpleplot as simpleplot
import math
import random
from alg_load_graph import load_graph
from makegraphs import make_complete_graph,compute_in_degrees, in_degree_distribution
from dpatrail import DPATrial

# Plot options
STANDARD = False
LOGLOG = True

CITATION_URL = "http://storage.googleapis.com/codeskulptor-alg/alg_phys-cite.txt"

def get_normalize_deg_dist(digraph):
    """
    this function load the graph and return in degree distribution of graph    
    """
    noOfNodes = len(digraph)*1.0
    print noOfNodes
    in_degree_dist = in_degree_distribution(digraph)
    #print in_degree_dist
    # normalize in degree distribution
    for (key, val) in in_degree_dist.iteritems():
        normal_val = val/noOfNodes
        in_degree_dist[key] = normal_val

    return in_degree_dist

def build_plot(plot_size, digraph, plot_type):
    """
    Build plot
    """
    plot = []
    for in_degree, val in digraph.iteritems():
        if plot_type == LOGLOG:
            if in_degree != 0:
                plot.append([math.log(in_degree), math.log(val)])
#else:
#           plot.append([input_val, val])
    return plot


def randomgraph(noOfNodes, probability):
    """
    generate a random graph with probability p
    """    
    graph = {}
    for node_1 in range(0, noOfNodes):
        for node_2 in range(0, noOfNodes):
            rand = random.random()
            if rand < probability and node_1 != node_2:
                neighbour = graph.get(node_1, set())
                neighbour.add(node_2)
                graph[node_1] = neighbour
                neighbour = graph.get(node_2, set());
                neighbour.add(node_1)
                graph[node_2] = neighbour
    return graph           

def dpa(noOfNodes, startNoOfOnodes):
    """
    implement DPA algorithm to generate a directed graph
    """
    graph = make_complete_graph(startNoOfOnodes)
    objDPATrail = DPATrial(startNoOfOnodes);
    for node in range(startNoOfOnodes,noOfNodes):
        graph[node] = objDPATrail.run_trial(startNoOfOnodes)
    return graph


###############################################
#citation_digraph = load_graph(CITATION_URL)
#random_digraph = randomgraph(3000, 0.6)
dpa_graph = dpa(27770, 12)
#in_degree_dist = get_normalize_deg_dist(citation_digraph)
#print in_degree_dist
# plottting code
plot_type = LOGLOG
plot_size = 100

#citation_plot = build_plot(plot_size, in_degree_dist, plot_type)
#in_degree_dist = get_normalize_deg_dist(random_digraph)
#random_plot = build_plot(plot_size, in_degree_dist, plot_type)

# dpa graph in degree distribution
in_degree_dist = get_normalize_deg_dist(dpa_graph)
dpa_plot = build_plot(plot_size, in_degree_dist, plot_type)
#print plot
simpleplot.plot_scatter("Log/log plot of In degree distribution of dpa graph, n=27770", 600, 600, "log(in_degree)", "log(fraction of nodes)", [dpa_plot])
simpleplot._block()

