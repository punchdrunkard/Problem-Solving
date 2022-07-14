n = int(input())
data =[]
for i in range(n):
    data.append(int(input()))

sorted_data = sorted(data)
print('\n'.join(map(str, sorted_data)))
