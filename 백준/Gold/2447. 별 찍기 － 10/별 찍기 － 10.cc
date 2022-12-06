
#include <bits/stdc++.h>
using namespace std;

#define MAX 6562

char answer[MAX][MAX];
int n;

void fill(int r, int c, int n) {
  if (n == 1) {
    answer[r][c] = '*';
    return;
  }

  // recursive case
  int nextN = n / 3;

  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      if (i == 1 && j == 1) continue;
      fill(r + i * nextN, c + j * nextN, n / 3);
    }
  }
}

void print(int n) {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cout << answer[i][j];
    }
    cout << '\n';
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> n;
  memset(answer, ' ', sizeof(answer));
  fill(0, 0, n);
  print(n);
}
