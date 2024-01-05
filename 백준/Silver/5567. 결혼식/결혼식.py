import sys
from collections import deque


def bfs():
    visited = [False] * (n + 1)
    dist = [0] * (n + 1)

    queue = deque([1])
    visited[1] = True

    while queue:
        current = queue.popleft()

        for friend in edges[current]:
            if visited[friend]:
                continue

            visited[friend] = True
            dist[friend] = dist[current] + 1
            queue.append(friend)

    count = sum(1 for d in dist if 0 < d <= 2)
    return count


if __name__ == "__main__":
    n = int(sys.stdin.readline())
    m = int(sys.stdin.readline())
    edges = [[] for _ in range(n + 1)]

    for _ in range(m):
        a, b = map(int, sys.stdin.readline().split())
        edges[a].append(b)
        edges[b].append(a)

    print(bfs())
