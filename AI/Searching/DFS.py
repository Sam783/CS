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

visited=[]

def dfs(visited,graph,node):
    if node not in visited:
        print(node, end=' ')
        visited.append(node)
        for neighbour in graph[node]:
            dfs(visited,graph,neighbour)

dfs(visited,graph,'S')