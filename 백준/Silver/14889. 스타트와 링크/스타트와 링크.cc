#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;                  // 사람 수
vector<vector<int>> s;  // 능력치

void input() {
  FASTIO;
  cin >> n;
  s.resize(n, vector<int>(n));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> s[i][j];
    }
  }
}

int getStatusDiff(vector<int> &start) {
  int startStatusSum = 0;
  int linkStatusSum = 0;

  vector<bool> startSelected(n, false);
  for (int member : start) {
    startSelected[member] = true;
  }

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (i == j) continue;

      // i, j가 start 에 속한다.
      if (startSelected[i] && startSelected[j]) {
        startStatusSum += s[i][j];
      }

      // i, j가 link 에 속한다.
      if (!startSelected[i] && !startSelected[j]) {
        linkStatusSum += s[i][j];
      }
    }
  }

  return abs(startStatusSum - linkStatusSum);
}

int answer = INT_MAX;

// count := 현재 배정된 사람 수, index := 현재 인덱스
void solve(int index, vector<int> start) {
  if (start.size() == n / 2) {
    int diff = getStatusDiff(start);

    if (diff == 0) {
      cout << 0;
      exit(0);
    }

    answer = min(answer, diff);
    return;
  }

  for (int i = index; i < n; i++) {
    start.push_back(i);
    solve(i + 1, start);
    start.pop_back();
  }
}

int main() {
  input();
  solve(0, {});
  cout << answer;

  return 0;
}
