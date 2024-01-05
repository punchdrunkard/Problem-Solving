import sys
from collections import deque


def make_graph():
    graph = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

    # 그래프 입력
    for _ in range(m):
        a, b = map(int, sys.stdin.readline().split())
        graph[a][b] = 1
        graph[b][a] = 1

    return graph


def bfs(start):
    dist = [0] * (n + 1)
    visited = [False] * (n + 1)

    queue = deque([start])
    visited[start] = True

    while queue:
        current = queue.popleft()

        for node, isFriend in enumerate(graph[current]):
            if not isFriend or visited[node]:
                continue

            dist[node] = dist[current] + 1
            visited[node] = True
            queue.append(node)

    return sum(dist)


def solve():
    bacons = [0] * (n + 1)

    min = 10 ** 6
    answer = -1

    # 각 노드로 부터 케빈 베이컨 지수를 계산한다.
    # 케빈 베이컨 지수 : 어떤 사람 n에 대하여, 그 사람과 모든 사람의 최단 거리의 합
    for node in range(1, n + 1):
        bacons[node] = bfs(node)
        if bacons[node] < min:
            min = bacons[node]
            answer = node

    return answer


if __name__ == "__main__":
    n, m = map(int, sys.stdin.readline().split())
    graph = make_graph()
    print(solve())
