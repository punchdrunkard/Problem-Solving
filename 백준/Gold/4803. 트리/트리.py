import sys


# 탐색하고 사이클이면 False 리턴
def dfs(current, parent, adj, visited):
    visited[current] = True

    for node in adj[current]:
        if visited[node]:
            if node != parent:  # 다른 경로를 통해 방문한 노드를 만남
                return True
        else:
            if dfs(node, current, adj, visited):  # 재귀적으로 탐색
                return True

    return False


def solve(n, adj):
    answer = 0
    visited = [False] * (n + 1)

    for i in range(1, n + 1):
        if visited[i]:
            continue

        is_tree = not dfs(i, i, adj, visited)
        answer += is_tree

    return answer


def print_answer(tc, answer):
    index_string = f"Case {tc}: "
    answer_string = ""

    if answer == 0:
        answer_string = "No trees."
    elif answer == 1:
        answer_string = "There is one tree."
    else:
        answer_string = f"A forest of {answer} trees."

    print(index_string + answer_string)


def main():
    # n := 정점의 갯수, m := 간선의 갯수
    n, m = map(int, sys.stdin.readline().split())
    tc = 1

    while not (n == 0 and m == 0):
        adj = [[] for _ in range(n + 1)]

        for _ in range(m):
            x, y = map(int, sys.stdin.readline().split())
            adj[x].append(y)
            adj[y].append(x)

        print_answer(tc, solve(n, adj))

        tc += 1
        n, m = map(int, sys.stdin.readline().split())


main()
