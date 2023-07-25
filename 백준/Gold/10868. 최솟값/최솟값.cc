#include <bits/stdc++.h>

#define a first
#define b second

using namespace std;

int n, m;
vector<int> numbers;
vector<pair<int, int>> queries;

void input() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("input.txt", "r", stdin);

  cin >> n >> m;
  numbers.resize(n + 1);
  queries.resize(m);

  for (int i = 1; i <= n; i++) {
    cin >> numbers[i];
  }

  for (int i = 0; i < m; i++) {
    cin >> queries[i].a >> queries[i].b;
  }
}

void solve() {
  // dp[i][j] : 시작 인덱스 i, level j를 의미
  // 즉, i에서 j 크기의 배열에서 최솟값
  int dp[n + 1][20];

  // dp 테이블을 채워보자. 2^0 부터 시작
  // initialize
  for (int i = 1; i <= n; i++) {
    dp[i][0] = numbers[i];
  }

  // dp 테이블 채우기
  for (int k = 1; k < 20; k++) {
    for (int i = 1; i + (1 << k) - 1 <= n; i++) {
      dp[i][k] = min(dp[i][k - 1], dp[i + (1 << (k - 1))][k - 1]);
    }
  }

  vector<int> log(n + 1);

  log[1] = 0;
  for (int i = 2; i <= n; i++) {
    log[i] = log[i / 2] + 1;
  }

  for (auto query : queries) {
    int k = log[query.b - query.a + 1];
    cout << min(dp[query.a][k], dp[query.b - (1 << k) + 1][k]) << '\n';
  }
}

int main() {
  input();
  solve();

  return 0;
}
