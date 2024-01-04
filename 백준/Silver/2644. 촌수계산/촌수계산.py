import sys
from collections import deque


def bfs(n, start, edges, dist):
    queue = deque([start])
    visited = [False for _ in range(n + 1)]

    while queue:
        current = queue.popleft()

        for next_node in edges[current]:
            if not visited[next_node]:
                visited[next_node] = True
                dist[next_node] = dist[current] + 1
                queue.append(next_node)


# a -> b 까지의 거리를 구하면됨 즉 a에서 bfs돌리고
def solve(n, a, b, edges):
    dist = [0 for _ in range(n + 1)]
    bfs(n, a, edges, dist)
    print(dist[b] if not dist[b] == 0 and not a == b else -1)


if __name__ == "__main__":
    n = int(input())
    a, b = map(int, sys.stdin.readline().split(' '))
    m = int(input())

    edges = [[] for _ in range(n + 1)]

    for _ in range(m):
        x, y = map(int, sys.stdin.readline().split(' '))
        edges[x].append(y)
        edges[y].append(x)

    solve(n, a, b, edges)
