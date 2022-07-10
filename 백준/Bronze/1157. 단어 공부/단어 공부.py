import sys
from collections import Counter

word = sys.stdin.readline().strip().upper()
word_counter = Counter(word).most_common()
count = 0
for i in range(1, len(word_counter)):
    if word_counter[i][1] == word_counter[0][1]:
        count += 1

print(word_counter[0][0] if count == 0 else '?')
