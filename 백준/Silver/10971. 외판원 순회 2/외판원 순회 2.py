import sys


def dfs(start, current, cost, visited, count):
    global answer, n, w

    # 모든 노드를 방문하고 시작 노드로 돌아온 경우
    if count == n and w[current][start] != 0:
        answer = min(answer, cost + w[current][start])
        return

    # 탐색
    for node_index, current_cost in enumerate(w[current]):
        if current_cost == 0 or visited[node_index]:
            continue  # 경로가 존재하지 않거나 이미 방문한 노드인 경우

        visited[node_index] = True
        dfs(start, node_index, cost + current_cost, visited, count + 1)
        visited[node_index] = False


if __name__ == "__main__":
    answer = float('inf')

    # 그래프 입력받기
    n = int(sys.stdin.readline())
    w = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

    for node in range(n):  # node로 시작하는 cycle이 있나?
        visited = [False] * n
        visited[node] = True
        dfs(node, node, 0, visited, 1)

    print(answer)
