import sys
from collections import deque


def is_valid_range(x):
    return 0 <= x <= 200000


# 현재 좌표 n에서 k로 가기 위한 거리를 계산 한다.
def bfs():
    dist = [[-1, -1]] * (200000 + 1)  # dist[i] := i 위치로 가기 위한 가장 짧은 시간, 이전의 위치
    q = deque([(n, 0)])
    dist[n] = [0, n]

    while q:
        current, time = q.popleft()
        next_locations = [current + 1, current - 1, 2 * current]

        for location in next_locations:
            if not is_valid_range(location):
                continue

            # 현재 점을 이미 방문한 경우
            if dist[location][0] != -1:
                if dist[location][0] > time + 1:
                    dist[location] = [time + 1, current]
                    q.append([location, time + 1])
            # 아직 방문 하지 않은 경우
            else:
                q.append([location, time + 1])
                dist[location] = [time + 1, current]

    return dist


def find_path():
    stack = []
    current = k

    while current != n:
        stack.append(current)
        current = dist[current][1]

    stack.append(n)
    stack.reverse()

    return ' '.join([str(item) for item in stack])


if __name__ == "__main__":
    n, k = map(int, sys.stdin.readline().split())
    dist = bfs()
    path = find_path()

    print(dist[k][0])
    print(path)
