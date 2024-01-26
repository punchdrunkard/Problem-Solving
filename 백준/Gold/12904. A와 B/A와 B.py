def solve(src, dest):
    while len(src) < len(dest):
        if dest[-1] == 'A':
            dest = dest[:-1]
        elif dest[-1] == 'B':
            dest = dest[:-1][::-1]

    return src == dest


def main():
    a, b = input(), input()
    result = solve(a, b)
    print(int(result))


main()
