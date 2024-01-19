#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

vector<vector<int>> getPrefixSum(vector<int> &costs) {
  int size = costs.size();
  vector<vector<int>> sum(size, vector<int>(size));

  for (int i = 0; i < size; i++) {
    sum[i][i] = costs[i];

    for (int j = i + 1; j < size; j++) {
      sum[i][j] = sum[i][j - 1] + costs[j];
    }
  }

  return sum;
}

int solve(int k, vector<int> &costs) {
  // prefix sum으로 각 구간의 누적합을 구한다.
  vector<vector<int>> prefix_sum = getPrefixSum(costs);

  // dp[i][j] := 구간 i ~ j까지에서 최솟값
  vector<vector<int>> dp(k, vector<int>(k));

  // fill dp table
  for (int len = 2; len <= k; len++) {       // 구간의 길이
    for (int st = 0; st <= k - len; st++) {  // 시작점
      // 구간의 길이와 시작점이 있다면 끝 점은 정해짐!
      int en = st + len - 1;
      dp[st][en] = 0x7fffffff;

      for (int mid = st; mid < en; mid++) {
        dp[st][en] =
            min(dp[st][en], dp[st][mid] + dp[mid + 1][en] + prefix_sum[st][en]);
      }
    }
  }

  return dp[0][k - 1];
}

int main() {
  FASTIO;

  int t;
  cin >> t;

  for (int test_case = 0; test_case < t; test_case++) {
    int k;
    cin >> k;

    vector<int> costs(k);
    for (int i = 0; i < k; i++) {
      cin >> costs[i];
    }

    cout << solve(k, costs) << "\n";
  }

  return 0;
}
