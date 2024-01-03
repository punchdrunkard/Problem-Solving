import sys
from collections import deque


class IslandCounter:
    EARTH = 1
    SEA = 0
    DIRS = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]

    def __init__(self, w, h, graph):
        self.w = w
        self.h = h
        self.graph = graph
        self.visited = [[False for _ in range(w)] for _ in range(h)]

    def is_valid_range(self, x, y):
        return 0 <= x < self.h and 0 <= y < self.w

    def bfs(self, x, y):
        q = deque()
        q.append((x, y))
        self.visited[x][y] = True

        while q:
            cx, cy = q.popleft()
            for DX, DY in self.DIRS:
                nx, ny = cx + DX, cy + DY
                if not self.is_valid_range(nx, ny) or self.visited[nx][ny] or self.graph[nx][ny] == self.SEA:
                    continue
                q.append((nx, ny))
                self.visited[nx][ny] = True

    def count_islands(self):
        count = 0
        for i in range(self.h):
            for j in range(self.w):
                if self.graph[i][j] == self.SEA or self.visited[i][j]:
                    continue
                self.bfs(i, j)
                count += 1
        return count


def main():
    while True:
        w, h = map(int, sys.stdin.readline().split(' '))
        if w == 0 and h == 0:
            break
        graph = [list(map(int, sys.stdin.readline().split(' '))) for _ in range(h)]
        counter = IslandCounter(w, h, graph)
        print(counter.count_islands())


if __name__ == "__main__":
    main()
