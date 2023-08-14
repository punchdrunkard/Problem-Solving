#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

typedef long long ll;

ll s, c, sum;
vector<ll> onions;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> s >> c;
  onions.resize(s);

  for (int i = 0; i < s; i++) {
    cin >> onions[i];
    sum += onions[i];
  }
}

// 길이 k의 파의 양을 파닭에 넣어서 c개의 파닭을 만들 수 있는가?
bool check(ll k) {
  if (k == 0) return true;

  ll count = 0;  // 사용한 파의 갯수

  for (auto &onion : onions) {
    count += (onion / k);
  }

  return count >= c;
}

int main() {
  input();

  // 이분 탐색
  ll lo = -1, hi = *max_element(onions.begin(), onions.end()) + 1;

  while (lo + 1 < hi) {
    ll mid = (lo + hi) / 2;

    if (check(mid)) {
      lo = mid;
    } else {
      hi = mid;
    }
  }

  ll answer = sum - (lo * c);
  cout << answer;

  return 0;
}
