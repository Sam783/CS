graph = {
    'S': ['A','B','C'],
    'A': ['D','E','B'],
    'B': ['G'],
    'C': ['F'],
    'D': ['H'],
    'E': ['G'],
    'F': ['G'],
    'G': [],
    'H': []
}

visited = []
queue = []

def bfs(visited, graph, node):
  visited.append(node)
  queue.append(node)
  while queue:
    m = queue.pop(0)
    print (m, end = " ")
    for neighbour in graph[m]:
      if neighbour not in visited:
        visited.append(neighbour)
        queue.append(neighbour)

bfs(visited, graph, 'S')

# visited=[]
# def dfs(visited,graph,node):
#     if node not in visited:
#         print(node)
#         visited.append(node)
#         for neighbour in graph[node]:
#             dfs(visited,graph,neighbour)
# dfs(visited,graph,'9')