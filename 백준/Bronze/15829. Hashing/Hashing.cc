#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

const long long MOD = 1234567891;

int main() {
  FASTIO;

  int L;
  string M;

  cin >> L >> M;

  long long H = 0;

  for (int i = 0; i < M.size(); i++) {
    int a = M.at(i) - 'a' + 1;  // 고유 번호
    H += (fmod(a * pow(31, i), MOD));
    H %= MOD;
  }

  cout << H;

  return 0;
}
