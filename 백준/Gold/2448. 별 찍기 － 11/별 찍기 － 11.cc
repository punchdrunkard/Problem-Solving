#include <bits/stdc++.h>
using namespace std;

char answer[3200][6300];
int n;

void fillBaseCase(int r, int c) {
  answer[r][c] = '*';
  answer[r + 1][c - 1] = '*';
  answer[r + 1][c + 1] = '*';

  for (int i = -2; i < 3; i++) {
    answer[r + 2][c + i] = '*';
  }
}

void fill(int r, int c, int n) {
  if (n == 3) {
    fillBaseCase(r, c);
    return;
  }

  int nextSection = n / 2;
  fill(r, c, nextSection);
  fill(r + nextSection, c - nextSection, nextSection);  // 왼쪽
  fill(r + nextSection, c + nextSection, nextSection);  // 오른쪽
}

void print(int n) {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n * 2 - 1; j++) {
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
  fill(0, n - 1, n);
  print(n);
}