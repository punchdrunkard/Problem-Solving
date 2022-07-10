import sys

N, M = map(int, sys.stdin.readline().split())
numbers = sorted(list(map(int, sys.stdin.readline().split())))
answers = []


def find_sequence(answer, current_index):
    if len(answer) == M:
        answers.append(' '.join(map(str, answer)))
        return

    prev_element = -1

    for i in range(len(numbers)):
        if prev_element == numbers[i] or i < current_index:
            continue
        else:
            prev_element = numbers[i]
            answer.append(numbers[i])
            find_sequence(answer, i)
            answer.pop()


find_sequence([], 0)
print('\n'.join(answers))
