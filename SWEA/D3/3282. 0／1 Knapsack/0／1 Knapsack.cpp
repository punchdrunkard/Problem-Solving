#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int tc = 1; tc <= test_case; tc++) {
    int n, k;  // 물건의 개수, 가방의 부피
    cin >> n >> k;

    vector<int> v(n), c(n);  // 부피, 가치

    for (int i = 0; i < n; i++) {
      cin >> v[i] >> c[i];
    }

    // dp[i][j] : 가방의 무게가 j일 때, i번째 물건까지를 선택했을 때의 최대 가치
    int dp[n + 1][k + 1];
    memset(dp, 0, sizeof(dp));

    // 가방을 채우는 방법은 다음과 같다.
    // i 번째 item을 넣는 경우 : c_i + dp[i - 1][j - v_i]
    // i 번째 item을 넣지 않는 경우 :  dp[i - 1][j]
    for (int item = 1; item <= n; item++) {
      for (int j = 1; j <= k; j++) {
        // 현재 아이템이 들어갈 수 있는지 없는지를 먼저 검사
        if (j - v[item - 1] < 0) {
          dp[item][j] = dp[item - 1][j];
        } else {
          dp[item][j] =
              max(c[item - 1] + dp[item - 1][j - v[item - 1]], dp[item - 1][j]);
        }
      }
    }

    cout << "#" << tc << " " << dp[n][k] << "\n";
  }

  return 0;
}
