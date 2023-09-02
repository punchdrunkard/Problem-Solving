#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;  // 수열의 크기, 질문의 갯수
vector<int> numbers;
vector<pair<int, int>> questions;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  numbers.resize(n + 1);

  for (int i = 1; i <= n; i++) {
    cin >> numbers[i];
  }

  cin >> m;
  questions.resize(m);

  for (int i = 0; i < m; i++) {
    cin >> questions[i].first >> questions[i].second;
  }
}

int main() {
  input();

  // dp[i][j] : 수열의 i부터 j까지가 palindrome인가?
  vector<vector<bool>> dp(n + 1, vector<bool>(n + 1, false));

  dp[1][1] = true;

  for (int i = 2; i <= n; i++) {
    dp[i][i] = true;

    if (numbers[i - 1] == numbers[i]) {
      dp[i - 1][i] = true;
    }
  }

  // 시작 인덱스 s와 끝 인덱스 e의 간격
  // 길이가 3 이상인 부분 문자열에 대해 펠린드롬 여부를 확인한다.
  for (int gap = 2; gap < n; gap++) {
    for (int s = 1; s + gap <= n; s++) {
      // 현재 검사하는 부분 수열의 끝 두 수가 같아야 하며,
      // 그 사이에 있는 수열이 palindrome 이어야 한다.
      int e = s + gap;
      dp[s][e] = dp[s + 1][e - 1] && (numbers[s] == numbers[e]);
    }
  }

  for (auto q : questions) {
    cout << dp[q.first][q.second] << "\n";
  }

  return 0;
}
