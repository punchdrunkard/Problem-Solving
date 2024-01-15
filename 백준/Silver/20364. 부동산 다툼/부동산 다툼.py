import sys
from collections import defaultdict

n, q = map(int, sys.stdin.readline().split())


def solve():
    # 이전에 해당 노드들에 대한 부모가 방문되었다면 살 수 없음
    visited = defaultdict(bool)

    for _ in range(q):
        current = int(sys.stdin.readline())
        parent = current
        result = 0
        can_get = True

        # 루트로 올라올 때 까지 하나라도 방문된 부모가 있다면? (끝까지 올라가야 함)
        while not parent == 1:
            if visited[parent]:  # 점유하지 못하는 경우
                can_get = False
                result = parent

            parent //= 2

        if can_get:
            visited[current] = True

        print(result)


if __name__ == "__main__":
    solve()
