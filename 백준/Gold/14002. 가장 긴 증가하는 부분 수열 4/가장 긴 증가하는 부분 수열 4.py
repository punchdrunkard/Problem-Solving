import sys


def input():
    return sys.stdin.readline().strip()


def solve():
    n = int(input())
    a = list(map(int, input().split()))

    dp = [1 for _ in range(n)]
    prev = [-1 for _ in range(n)]

    for i in range(1, n):
        for j in range(i):
            if a[i] > a[j] and dp[i] < dp[j] + 1:
                dp[i] = dp[j] + 1
                prev[i] = j

    lis_length = max(dp)
    idx = dp.index(lis_length)

    seq = []
    while idx != -1:
        seq.append(a[idx])
        idx = prev[idx]

    print(lis_length)
    print(' '.join(map(str, reversed(seq))))


if __name__ == "__main__":
    solve()
