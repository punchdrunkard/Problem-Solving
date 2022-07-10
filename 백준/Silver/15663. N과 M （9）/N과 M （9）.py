import sys

N, M = map(int, sys.stdin.readline().split())
numbers = sorted(list(map(int, sys.stdin.readline().split())))
visited = [False] * 9
answers = []


def find_sequence(answer):
    if len(answer) == M:
        answers.append(' '.join(map(str, answer)))
        return

    prev_element = -1
    for i in range(len(numbers)):
        if visited[i] or prev_element == numbers[i]:
            continue
        else:
            prev_element = numbers[i]
            visited[i] = True
            answer.append(numbers[i])
            find_sequence(answer)
            visited[i] = False
            answer.pop()


find_sequence([])
print('\n'.join(answers))