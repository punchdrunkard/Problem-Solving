import sys

sys.setrecursionlimit(2 * (10 ** 6))


def dfs(x, par, cost):
    dp[x] = cost

    for child in children[x]:
        if child == par:
            continue

        dfs(child, x, cost + weight[child])


if __name__ == "__main__":
    n, m = map(int, sys.stdin.readline().split())
    parent = [None] + list(map(int, sys.stdin.readline().split()))

    # children[i] := i번 노드의 자식들
    children = [[] for _ in range(n + 1)]

    for i in range(2, n + 1):
        children[parent[i]].append(i)

    weight = [0] * (n + 1)
    # dp[i] := i번째 직원이 칭찬을 받은 정도
    dp = [0] * (n + 1)

    for _ in range(m):
        i, w = map(int, sys.stdin.readline().split())
        weight[i] += w  # 한 사람이 여러 번 칭찬받을 수도 있다

    dfs(1, -1, 0)

    print(' '.join(map(str, dp[1:])))
