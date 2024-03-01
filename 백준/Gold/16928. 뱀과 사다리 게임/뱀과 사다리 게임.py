import sys
from collections import deque


def init():
    global ladders, snakes
    n, m = map(int, sys.stdin.readline().split())
    ladders = [None] * 101
    snakes = [None] * 101

    for _ in range(n):
        x, y = map(int, input().split())
        ladders[x] = y

    for _ in range(m):
        u, v = map(int, input().split())
        snakes[u] = v


def bfs():
    q = deque([(1, 0)])  # 시작 지점과 초기 카운트
    visited = [False] * 101
    visited[1] = True

    while q:
        locate, count = q.popleft()

        if locate == 100:
            return count

        for dice in range(1, 7):
            next_loc = locate + dice
            if next_loc > 100:  # 유효한 범위 체크
                continue

            # 사다리나 뱀이 있는 경우
            if ladders[next_loc]:
                next_loc = ladders[next_loc]
            elif snakes[next_loc]:
                next_loc = snakes[next_loc]

            if not visited[next_loc]:
                visited[next_loc] = True
                q.append((next_loc, count + 1))

    return -1  # 목적지에 도달할 수 없는 경우


def main():
    init()
    print(bfs())


if __name__ == "__main__":
    main()
