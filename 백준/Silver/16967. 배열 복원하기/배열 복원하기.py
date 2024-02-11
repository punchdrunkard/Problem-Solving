import sys


def input():
    return sys.stdin.readline().strip()


def solve():
    # 1. 배열 a (겹쳐지는 것 포함) 을 구함
    a = []

    for i in range(h):
        row = []
        for j in range(w):
            row.append(b[i][j])
        a.append(row)

    # 2. 겹쳐지는 부분 처리
    for i in range(x, h):
        for j in range(y, w):
            a[i][j] = b[i][j] - a[i - x][j - y]

    for row in a:
        for elem in row:
            print(elem, end=' ')
        print()


if __name__ == "__main__":
    h, w, x, y = map(int, input().split())
    b = [list(map(int, input().split())) for _ in range(h + x)]
    solve()
