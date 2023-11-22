#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// 문자열 s, 특정 알파벳 a, 구간 [l ,r ]
// s의 l번째 문자부터 r번째 문자 사이에 a가 몇 번 나타나냐?
// 문자는 0번째 부터 세며, l r은 닫힌 구간

// 시간 복잡도
// 구간합 O(N) => 20aks
// 쿼리: 20만 * O(1)

string s;
int q;

vector<vector<int>> pSum;

void makePSum() {
  // pSum[i] : [0 ~ i]까지 알파벳 소문자 ~의 출현 횟수 (a- z까지)
  int n = s.size();
  pSum.resize(n + 1, vector<int>('z' - 'a' + 1, 0));

  // a - z까지
  for (int i = 1; i <= n; i++) {
    pSum[i][s[i - 1] - 'a'] += 1;

    for (int j = 'a' - 'a'; j <= 'z' - 'a'; j++) {
      pSum[i][j] += pSum[i - 1][j];  // 이전의 출현 횟수를 더한다.
    }
  }
}

int solve(char a, int l, int r) {
  return pSum[r + 1][a - 'a'] - pSum[l][a - 'a'];
}

int main() {
  FASTIO;
  cin >> s >> q;
    
  makePSum();

  for (int i = 0; i < q; i++) {
    char a;
    int l, r;
    cin >> a >> l >> r;
    cout << solve(a, l, r) << '\n';
  }
  return 0;
}
