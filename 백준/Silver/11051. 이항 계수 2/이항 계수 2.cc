#include <bits/stdc++.h>
using namespace std;

#define MAX 1001
#define DIV 10007

int n, k;
int combination[MAX][MAX];

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> k;

  for (int i = 1; i <= n; i++) {
    combination[i][0] = combination[i][i] = 1;

    for (int j = 1; j < i; j++) {
      combination[i][j] =
          (combination[i - 1][j] + combination[i - 1][j - 1]) % DIV;
    }
  }

  cout << combination[n][k];
}
