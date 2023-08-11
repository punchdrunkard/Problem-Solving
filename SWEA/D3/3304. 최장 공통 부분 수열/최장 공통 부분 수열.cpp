#include <bits/stdc++.h>

using namespace std;

int solve(string x, string y) {
  vector<vector<int>> dp(x.size() + 1, vector<int>(y.size() + 1, 0));

  // init dp table
  for (int i = 0; i <= x.size(); i++) {
    dp[i][0] = 0;
  }

  for (int i = 0; i <= y.size(); i++) {
    dp[0][i] = 0;
  }

  // fill dp table
  for (int i = 1; i <= x.size(); i++) {
    for (int j = 1; j <= y.size(); j++) {
      if (x[i - 1] == y[j - 1]) {
        dp[i][j] = dp[i - 1][j - 1] + 1;
      } else {
        dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
      }
    }
  }

  return dp[x.size()][y.size()];
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int i = 1; i <= test_case; i++) {
    string x, y;
    cin >> x >> y;

    cout << "#" << i << " " << solve(x, y) << "\n";
  }

  return 0;
}
