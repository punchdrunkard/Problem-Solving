import itertools

n = int(input())
sequences = list(itertools.permutations(range(1, n + 1)))
print('\n'.join(' '.join(map(str, sequence)) for sequence in sequences))
