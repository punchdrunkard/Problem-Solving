import sys
sys.setrecursionlimit(10**6)


# pre_order 은 항상 가장 앞에 있는 원소가 root 인 성질이 있음
# Root[l .... r] 형태의 트리를 postorder로 순회하는 함수
def post_order(tree, l, r):
    # 왼쪽 subtree와 오른쪽 subtree를 나눈다.
    if l > r:
        return ""

    root = tree[l]
    right_subtree_start = next((i for i in range(l + 1, r + 1) if tree[i] > root), r + 1)

    left = post_order(tree, l + 1, right_subtree_start - 1)
    right = post_order(tree, right_subtree_start, r)

    return left + right + f"{root}\n"


def main():
    pre_order = [int(line.strip()) for line in sys.stdin]
    answer = post_order(pre_order, 0, len(pre_order) - 1)
    print(answer)


main()
