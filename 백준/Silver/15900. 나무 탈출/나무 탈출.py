import sys

sys.setrecursionlimit(600000)

answer = 0


def dfs(node, par, adj, current, dist):
    dist[node] = current

    for child in adj[node]:
        # 리프 노드인 경우
        if child == par and len(adj[node]) == 1:
            global answer
            answer += dist[node]

        if child == par:
            continue

        dfs(child, node, adj, current + 1, dist)


def main():
    n = int(sys.stdin.readline())
    adj = [[] for _ in range(n + 1)]

    for _ in range(n - 1):
        a, b = map(int, sys.stdin.readline().split())
        adj[a].append(b)
        adj[b].append(a)

    dist = [[] for _ in range(n + 1)]
    dist[1] = 0

    dfs(1, -1, adj, 0, dist)
    print("Yes" if answer % 2 == 1 else "No")


main()
