import sys
sys.setrecursionlimit(10**6)

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

# memo[i][j] := (i, j)로 이동할 때 가져올 수 있는 사탕의 최대 개수
memo = [[-1] * m for _ in range(n)]


def dp(x, y):
    # base case
    if x < 0 or y < 0:
        return 0

    # memoization
    if memo[x][y] != -1:
        return memo[x][y]

    # recursive case
    memo[x][y] = board[x][y]
    memo[x][y] += max(dp(x - 1, y), dp(x, y - 1), dp(x - 1, y - 1))
    return memo[x][y]


print(dp(n - 1, m - 1))
