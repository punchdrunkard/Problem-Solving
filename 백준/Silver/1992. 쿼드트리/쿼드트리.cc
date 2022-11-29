
#include <bits/stdc++.h>
using namespace std;

#define MAX 65

int n;
char video[MAX][MAX];
string answer = "";

void input() {
  cin >> n;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> video[i][j];
    }
  }
}

bool isSameColor(int n, int r, int c) {
  for (int i = r; i < r + n; i++) {
    for (int j = c; j < c + n; j++) {
      if (video[r][c] != video[i][j]) return false;
    }
  }
  return true;
}

void func(int n, int r, int c) {
  if (n == 1) {
    answer.push_back(video[r][c]);
    return;
  }

  if (isSameColor(n, r, c)) {
    answer.push_back(video[r][c]);
    return;
  } else {
    int section = n / 2;

    answer.push_back('(');
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        func(section, r + i * section, c + j * section);
      }
    }
    answer.push_back(')');
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  input();
  func(n, 0, 0);
  cout << answer;
}
