#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

typedef long long ll;

using namespace std;

int n, q;
vector<ll> a, pSum;

void input() {
  FASTIO;
  cin >> n >> q;
  a.resize(n + 1);

  for (int i = 1; i <= n; i++) {
    cin >> a[i];
  }
}

void makePSum() {
  pSum.resize(n + 1, 0);

  for (int i = 1; i <= n; i++) {
    pSum[i] ^= (pSum[i - 1] ^ a[i]);
  }
}

// XOR은 교환법칙 성립하므로
int solve(int s, int e) { return pSum[e] ^ pSum[s - 1]; }

int main() {
  input();
  makePSum();

  // 어떤 수와 0을 XOR하면 그 수가 그대로 나온다.
  int answer = 0;

  for (int i = 0; i < q; i++) {
    int s, e;
    cin >> s >> e;

    answer ^= (solve(s, e));
  }

  cout << answer;

  return 0;
}
