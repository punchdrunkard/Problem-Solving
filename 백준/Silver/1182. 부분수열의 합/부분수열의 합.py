import sys

N, S = map(int, sys.stdin.readline().split())
numbers = list(map(int, sys.stdin.readline().split()))
count = 0


def find_sequence(current_sum, level):
    if level == N:
        if current_sum == S:
            global count
            count += 1
        return

    # 포함 하는 경우
    find_sequence(current_sum + numbers[level], level + 1)
    # 포함 하지 않는 경우
    find_sequence(current_sum, level + 1)


find_sequence(0, 0)
print(count if (S != 0) else count - 1)
