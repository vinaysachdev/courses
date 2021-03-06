"""
plot running time of targeted_order and fast_targeted_order on graph
generated by UPA algorithm
"""

import SimpleGUICS2Pygame.simpleplot as simpleplot
from load_graph import targeted_order
from fast_targeted_order import fast_targeted_order
from upa import upa
import timeit
import gc

def prepare_plot():
    """
    prepare the plot of running time of targeted_order and fast_targeted_order
    """
    initial_num = 5
    targeted_plot=[]
    fast_targeted_plot = []
    for num_nodes in range(10, 1000,10):
        graph = upa(num_nodes, initial_num)
        gc.disable()
        start_time = timeit.default_timer()
#start_time = int(round(timeit.default_timer()*1000))
        targeted_order(graph)
        end_time = timeit.default_timer()
#end_time = int(round(timeit.default_timer()*1000))
        targeted_plot.append([num_nodes, (end_time - start_time)])
        # fast_taregeted running time
        start_time = timeit.default_timer()
#start_time = int(round(timeit.default_timer()*1000))
        fast_targeted_order(graph)
        end_time = timeit.default_timer()
#end_time = int(round(timeit.default_timer()*1000)) 
        fast_targeted_plot.append([num_nodes, (end_time - start_time)])
        gc.enable()
        #print end_time - start_time

    return [targeted_plot, fast_targeted_plot]

plot = prepare_plot()

simpleplot.plot_lines("Plot of running time of targeted_order vs fast_targeted_order\n(Desktop Python, time in seconds)",
                        700, 700, "number of nodes", "running time(seonds)", plot, False, 
                        ["targeted_order", "fast_targeted_order"])
simpleplot._block()


