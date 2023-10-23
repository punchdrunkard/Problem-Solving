#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

const long long MOD = 1234567891;

// 31의 i 승
long long power_31(int i) {
  long long ans = 1;

  for (int j = 0; j < i; j++) {
    ans *= 31;
    ans %= MOD;
  }

  return ans % MOD;
}

int main() {
  FASTIO;

  int L;
  string M;

  cin >> L >> M;

  long long H = 0;

  for (int i = 0; i < M.size(); i++) {
    int a = M.at(i) - 'a' + 1;  // 고유 번호
    long long power = power_31(i);

    H += (a * power % MOD) % MOD;
  }

  cout << H % MOD;

  return 0;
}
