#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX 21

typedef long long ll;
using namespace std;

int n;
int memo[MAX][MAX][MAX];

int w(int a, int b, int c) {
  if (a <= 0 || b <= 0 || c <= 0) {
    return 1;
  }

  if (a > 20 || b > 20 || c > 20) {
    return w(20, 20, 20);
  }

  if (memo[a][b][c]) {
    return memo[a][b][c];
  }

  if (a < b && b < c) {
    memo[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
    return memo[a][b][c];
  } else {
    memo[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) -
                    w(a - 1, b - 1, c - 1);
  }

  return memo[a][b][c];
}

int main() {
  FASTIO;

  // freopen("sample_input.txt", "r", stdin);

  while (true) {
    int a, b, c;
    cin >> a >> b >> c;

    if (a == -1 && b == -1 && c == -1) break;

    cout << "w(" << a << ", " << b << ", " << c << ") = " << w(a, b, c) << '\n';
  }

  return 0;
}
