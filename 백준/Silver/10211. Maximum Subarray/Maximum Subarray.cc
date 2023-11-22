#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// 크기 n인 정수형 배열 x
// x의 부분 배열 (x의 "연속"한 일부분) 중 원소의 합이 가장 큰 부분 배열 찾기

// n이 1000이므로
// 모든 부분합을 구하고 가장 큰 값 찾기

int n;
vector<int> x, pSum;

void init() {
  cin >> n;
  x.resize(n + 1);
  pSum.resize(n + 1);

  for (int i = 1; i <= n; i++) {
    cin >> x[i];
  }
}

void makePSum() {
  pSum[0] = 0;

  for (int i = 1; i <= n; i++) {
    pSum[i] = pSum[i - 1] + x[i];
  }
}

int solve() {
  int answer = INT_MIN;

  for (int i = 1; i <= n; i++) {
    for (int j = i; j <= n; j++) {
      int current = pSum[j] - pSum[i - 1];
      answer = max(current, answer);
    }
  }

  return answer;
}

int main() {
  FASTIO;
  int test_case;
  cin >> test_case;

  for (int t = 1; t <= test_case; t++) {
    init();
    makePSum();

    cout << solve() << '\n';
  }

  return 0;
}
