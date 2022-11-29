
#include <bits/stdc++.h>
using namespace std;

#define MAX 129

int n;
int paper[MAX][MAX];
int cnt[2];  // 하양 0, 파랑 1

void input() {
  cin >> n;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> paper[i][j];
    }
  }
}

bool isSameColor(int n, int r, int c) {
  for (int i = r; i < r + n; i++) {
    for (int j = c; j < c + n; j++) {
      if (paper[r][c] != paper[i][j]) return false;
    }
  }
  return true;
}

void func(int n, int r, int c) {
  if (n == 1) {
    cnt[paper[r][c]] += 1;
    return;
  }

  if (isSameColor(n, r, c)) {
    cnt[paper[r][c]] += 1;
  } else {
    int section = n / 2;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        func(section, r + i * section, c + j * section);
      }
    }
  }
}

void printAnswer() {
  for (int i = 0; i < 2; i++) cout << cnt[i] << '\n';
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  input();
  func(n, 0, 0);
  printAnswer();
}
