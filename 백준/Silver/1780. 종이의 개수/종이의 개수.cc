
#include <bits/stdc++.h>
using namespace std;

int n;
vector<vector<int>> paper;
int cnt[3];

void input() {
  cin >> n;

  for (int i = 0; i < n; i++) {
    vector<int> row;
    for (int j = 0; j < n; j++) {
      int col;
      cin >> col;
      row.push_back(col);
    }
    paper.push_back(row);
  }
}  // end of input()

bool isCut(int n, int r, int c) {
  for (int i = r; i < r + n; i++) {
    for (int j = c; j < c + n; j++) {
      if (paper[r][c] != paper[i][j]) {
        return false;
      }
    }
  }
  return true;
}  // end of isCut()

void func(int n, int r, int c) {
  if (isCut(n, r, c)) {
    cnt[paper[r][c] + 1] += 1;
    return;
  }

  int section = n / 3;

  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      func(section, r + i * section, c + j * section);
    }
  }
} // end of func

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  input();
  func(n, 0, 0);
  for (int i = 0; i < 3; i++) cout << cnt[i] << "\n";
} // end of main
