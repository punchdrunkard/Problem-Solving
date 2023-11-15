#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

typedef long long ll;

int n;
vector<ll> liquids;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  liquids.resize(n);

  // 산성 용액과 알칼리성 용액의 특성값이 정렬된 순서로 주어진다.
  for (int i = 0; i < n; i++) {
    cin >> liquids[i];
  }
}

// 특성값이 0에 가장 가까운 용액의 특성 값을
// 오름차순으로 리턴
pair<int, int> solve() {
  int lo = 0, hi = n - 1;

  // 현재 답을 저장
  pair<int, int> answer = {lo, hi};
  ll diff = abs(liquids[lo] + liquids[hi]);

  while (lo < hi) {
    ll current = liquids[lo] + liquids[hi];

    if (abs(current) < diff) {
      diff = abs(current);
      answer = {lo, hi};
    }

    // 합이 양수이면, 합이 작아져야 한다.
    if (current > 0) {
      hi--;
    } else if (current < 0) {  // 합이 양수이면, 합이 커져야 한다.
      lo++;
    } else {
      diff = 0;
      answer = {lo, hi};
      break;
    }
  }

  return {liquids[answer.first], liquids[answer.second]};
}

int main() {
  input();
  auto answer = solve();
  cout << answer.first << ' ' << answer.second;

  return 0;
}
