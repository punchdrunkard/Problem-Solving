import sys
import heapq

si = sys.stdin.readline

n, m = map(int, si().split())
adj = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b, c = map(int, si().split())
    adj[a].append([b, c])
    adj[b].append([a, c])


start, destination = map(int, si().split())

dist = [(10**5) * 5000 + 1] * (n + 1)
dist[start] = 0

pq = []
heapq.heappush(pq, (0, start))

while pq:
    cost, node = heapq.heappop(pq)

    if dist[node] < cost:
        continue

    for next_node, weight in adj[node]:
        next_cost = cost + weight

        if dist[next_node] > next_cost:
            dist[next_node] = next_cost
            heapq.heappush(pq, (next_cost, next_node))

print(dist[destination])
