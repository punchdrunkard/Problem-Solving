#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

typedef long long ll;

int n, m;
vector<int> cashes;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  cashes.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> cashes[i];
  }
}

// k원을 인출했을 때,
// m 번 돈을 빼서 n일 동안 사용할 수 있는가?
bool check(ll k) {
  ll count = 0;  // 몇 번 돈을 뺐는가?
  ll sum = 0;    // 현재 사용한 돈
  for (auto cash : cashes) {
    if (cash + sum > k) {
      count++;
      sum = 0;  // 돈을 뽑는다.
    }

    // 예외: 애초에 사용할 수 없는 경우
    if (cash + sum > k) {
      return false;
    }

    sum += cash;
  }

  return count < m;
}

int main() {
  input();

  // 이분 탐색
  ll lo = 0, hi = 1e9;

  while (lo + 1 < hi) {
    ll mid = (lo + hi) / 2;

    if (!check(mid)) {
      lo = mid;
    } else {
      hi = mid;
    }
  }

  cout << hi;

  return 0;
}
