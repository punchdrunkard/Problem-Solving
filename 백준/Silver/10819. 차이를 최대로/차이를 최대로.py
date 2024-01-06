import sys
import itertools


def calculate(num_list):
    return sum(abs(num_list[i - 1] - num_list[i]) for i in range(1, len(num_list)))


if __name__ == "__main__":
    n = int(sys.stdin.readline())
    numbers = list(map(int, sys.stdin.readline().split()))

    answer = max(calculate(perm) for perm in itertools.permutations(numbers))
    print(answer)
    