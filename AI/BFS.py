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