import sys

sys.setrecursionlimit(10 ** 6)

n = int(input())
a = list(map(int, input().split()))

# memo[i] := i번째에서 끝나는 LIS의 길이
memo = [-1] * n


def dp(i):
    # base case
    if i == 0:
        return 1

    # memoization
    if memo[i] != -1:
        return memo[i]

    # recursive case
    best = 0
    for j in range(0, i):
        # 가능한 경우에만 갱신
        if a[i] > a[j]:
            best = max(best, dp(j))

    memo[i] = best + 1
    return memo[i]


answer = -1
for i in range(n):
    answer = max(answer, dp(i))
print(answer)
