#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

typedef long long ll;

int n;  // 지방의 수
ll m;   // 총 예산
vector<int> requests;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  //  freopen("sample_input.txt", "r", stdin);

  cin >> n;
  requests.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> requests[i];
  }

  cin >> m;
}

// 상한액이 x원일 때, 예산을 배정할 수 있는가?
bool check(ll x) {
  ll sum = 0;

  for (auto &request : requests) {
    if (request >= x) {
      sum += x;
    } else {
      sum += request;
    }
  }

  return sum <= m;
}

int main() {
  input();

  // 이분 탐색
  // 주의점! check(lo) !== check(hi)를 만족해야 한다!
  ll lo = 1, hi = *max_element(requests.begin(), requests.end()) + 1;

  while (lo + 1 < hi) {
    ll mid = lo + (hi - lo) / 2;

    if (check(mid)) {
      lo = mid;
    } else {
      hi = mid;
    }
  }

  cout << lo;

  return 0;
}
