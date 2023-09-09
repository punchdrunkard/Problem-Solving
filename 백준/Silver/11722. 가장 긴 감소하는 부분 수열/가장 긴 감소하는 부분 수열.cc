#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;
vector<int> a, dp;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  a.resize(n + 1);
  dp.resize(n + 1, 0);

  for (int i = 1; i <= n; i++) {  // 1-indexed
    cin >> a[i];
  }
}

int main() {
  input();

  // dp[i] : a[i]를 포함하는 가장 긴 감소하는 부분 수열의 길이
  for (int i = 1; i <= n; i++) {  // i : 현재 인덱스
    dp[i] = 1;
    for (int j = 1; j < i; j++) {  // j : 이전 값의 인덱스
      // 이전 수열에 현재 수를 추가할 수 있다면?
      if (a[j] > a[i]) {
        dp[i] = max(dp[i], dp[j] + 1);
      }
    }
  }

  cout << *max_element(dp.begin(), dp.end());

  return 0;
}
