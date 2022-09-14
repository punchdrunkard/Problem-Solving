import sys
from collections import defaultdict, deque


def input_data():
    n = int(sys.stdin.readline())
    tree = defaultdict(list)

    for _ in range(n - 1):
        node1, node2 = map(int, sys.stdin.readline().split(' '))
        tree[node1].append(node2)
        tree[node2].append(node1)

    return n, tree


def level_order(n, tree):
    queue = deque([1])
    parents = [-1 for _ in range(n + 1)]

    while len(queue):
        parent = queue.popleft()
        for node in tree[parent]:
            if parents[parent] != node:
                queue.append(node);
                parents[node] = parent

    return parents


def print_answer(n, answer):
    for i in range(2, n + 1):
        print(answer[i])


def main():
    n, tree = input_data()
    parents = level_order(n, tree)
    print_answer(n, parents)


main()
