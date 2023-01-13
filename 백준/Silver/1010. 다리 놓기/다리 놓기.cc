#include <bits/stdc++.h>
using namespace std;

#define MAX 31

// 오른쪽 사이트(m 개 중)에서 n 개를 선택하면
// 왼쪽 사이트가 어떻게 연결되어야 될 지는 자동으로 정해진다.

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int t;
  cin >> t;

  while (t--) {
    int n, m;
    cin >> n >> m;

    int combination[MAX][MAX];
    memset(combination, 0, sizeof(combination));

    for (int i = 1; i <= m; i++) {
      combination[i][0] = combination[i][i] = 1;

      for (int j = 1; j < i; j++) {
        combination[i][j] = combination[i - 1][j] + combination[i - 1][j - 1];
      }
    }

    cout << combination[m][n] << '\n';
  }

  return 0;
}
