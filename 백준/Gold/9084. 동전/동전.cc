#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// knapsack과 흐름이 유사하다!

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int tc = 1; tc <= test_case; tc++) {
    int n;  // 동전의 가짓수
    cin >> n;
    int coins[n];

    // n가지 동전의 금액 (왜 오름차순으로 정렬되어 있을까?)
    for (int i = 0; i < n; i++) {
      cin >> coins[i];
    }

    int m;  // 만들어야 할 금액
    cin >> m;

    // dp[i][j] :index j번째 까지의 동전을 사용했을 떄 i원을 만드는 경우의 수
    int dp[m + 1][n];
    memset(dp, 0, sizeof(dp));

    // initialize: 첫 번째 동전으로 만들 수 있는 금액
    for (int i = 0; i <= m; i += coins[0]) {
      dp[i][0] = 1;
    }

    for (int i = 0; i <= m; i++) {
      for (int j = 1; j < n; j++) {
        if (i - coins[j] >= 0) {
          // j번째 동전을 포함하지 않고 i원을 만드는 경우 +  j번째 동전을
          // 포함하여 i원을 만드는 경우
          dp[i][j] = dp[i][j - 1] + dp[i - coins[j]][j];
        } else {
          dp[i][j] = dp[i][j - 1];
        }
      }
    }

    cout << dp[m][n - 1] << "\n";
  }

  return 0;
}
