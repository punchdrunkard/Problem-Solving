import sys

a, b, v = map(int, sys.stdin.readline().split())
day = (v - a) // (a - b)
answer = day + 1

if (v - a) % (a - b) != 0:
    answer += 1

if (v - a) <= 0:
    answer = 1

print(answer)
