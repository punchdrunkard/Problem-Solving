import sys
from collections import deque


# 정점 i에서 부터 가능한 경로를 탐색
def bfs(graph, dist, start):
    queue = deque([start])
    visited = [False for _ in range(n)]

    while queue:
        current_vertex = queue.popleft()

        for next_vertex in range(n):
            if graph[current_vertex][next_vertex] and not visited[next_vertex]:
                visited[next_vertex] = True
                dist[start][next_vertex] = 1
                queue.append(next_vertex)


def solve(n, graph):
    dist = [[0 for _ in range(n)] for _ in range(n)]

    for v in range(n):  # v번 정점 부터 방문
        bfs(graph, dist, v)

    print('\n'.join(" ".join(map(str, row)) for row in dist))


if __name__ == "__main__":
    n = int(input())
    graph = [list(map(int, sys.stdin.readline().strip().split(' '))) for _ in range(n)]
    solve(n, graph)
