import sys

numbers = set()

for i in range(10):
    number = int(sys.stdin.readline())
    numbers.add(number % 42)

print(len(numbers))