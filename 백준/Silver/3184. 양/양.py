import sys
from collections import deque


def is_valid_range(r, c, x, y):
    return 0 <= x < r and 0 <= y < c


def bfs(r, c, x, y, visited, graph): 
    s_count, w_count = 0, 0
    DELTAS = ((-1, 0), (1, 0), (0, -1), (0, 1))

    q = deque([(x, y)])
    visited[x][y] = True

    while q:
        cx, cy = q.popleft()

        for DX, DY in DELTAS:
            nx, ny = cx + DX, cy + DY

            if not is_valid_range(r, c, nx, ny) or visited[nx][ny] or graph[nx][ny] == '#':
                continue

            visited[nx][ny] = True
            q.append((nx, ny))
            s_count += graph[nx][ny] == 'o'
            w_count += graph[nx][ny] == 'v'

    return s_count, w_count


def solve(r, c, graph):
    sheep_count, wolf_count = 0, 0;
    visited = [[False] * c for _ in range(r)]

    for i in range(r):
        for j in range(c):
            if not visited[i][j]:
                s, w = bfs(r, c, i, j, visited, graph)
                sheep_count, wolf_count = (sheep_count + s, wolf_count) if s > w else (sheep_count, wolf_count + w)

    return sheep_count, wolf_count;


if __name__ == "__main__":
    r, c = map(int, sys.stdin.readline().split(" "))
    graph = [sys.stdin.readline().strip() for _ in range(r)]

    sheep_count, wolf_count = solve(r, c, graph)
    print(sheep_count, wolf_count)
