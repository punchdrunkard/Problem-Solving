import sys
sys.setrecursionlimit(10**6)

if __name__ == "__main__":
    n, m = map(int, sys.stdin.readline().split())
    parent = [i for i in range(n)]

    def find(a):
        if parent[a] == a:
            return a

        # 경로 압축
        result = find(parent[a])
        parent[a] = result
        return result


    def union(a, b):
        a = find(a)
        b = find(b)

        parent[a] = b

    def solve():
        for turn in range(1, m + 1):
            node1, node2 = map(int, sys.stdin.readline().split())

            if find(node1) == find(node2):
                return turn
            else:
                union(node1, node2)

        return 0

    print(solve())
