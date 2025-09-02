import sys

sys.setrecursionlimit(10 ** 6)

n = int(input())
cost = [list(map(int, input().split())) for _ in range(n)]

available_color = [[1, 2], [0, 2], [0, 1]]
# memo[i][0] := i번쨰 집을 0번째 색으로 칠하는데 드는 비용
memo = [[-1] * 3 for _ in range(n)]


def dp(i, color):
    # base case
    if i == 0:
        return cost[i][color]

    # memoization
    if memo[i][color] != -1:
        return memo[i][color]

    memo[i][color] = cost[i][color]
    memo[i][color] += min(dp(i - 1, available_color[color][0]), dp(i - 1, available_color[color][1]))
    return memo[i][color]


print(min(dp(n - 1, 0), dp(n - 1, 1), dp(n - 1, 2)))
