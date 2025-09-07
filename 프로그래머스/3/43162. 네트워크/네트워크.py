from collections import deque

answer = 0

# connected component의 갯수 찾기 
def solution(n, computers):
    # 1. 그래프 만들기 -> adj, 양방향 
    
    adj = makeGraph(n, computers)
      
    global answer
    visited = [False] * n
    
    # n번 컴퓨터부터 bfs (n번 컴퓨터 부터 연결되어있는 네트워크 탐색 )
    for i in range(n):
        if visited[i]:
            continue
        bfs(adj, visited, i)
        answer += 1
    
    return answer

def bfs(adj, visited, start):
    q = deque()
    q.append(start)
    
    while len(q) != 0:
        sz = len(q)
        
        for _ in range(sz):
            current = q.pop()
            
            for i in adj[current]:
                if visited[i]:
                    continue
                
                visited[i] = True
                q.append(i)


def makeGraph(n, computers):
    adj = [[] for _ in range(n)]
    for i in range(len(computers)):
        for j in range(len(computers[i])):
            if i == j:
                continue
            if computers[i][j] == 1:
                adj[i].append(j)
    return adj