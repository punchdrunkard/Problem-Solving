#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

typedef long long ll;
using namespace std;

int n;
vector<int> arr;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  arr.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> arr[i];
  }
}

int main() {
  input();

  sort(arr.begin(), arr.end());

  int lo = 0, hi = n - 1;

  pair<int, int> answer = {lo, hi};
  ll diff = abs(arr[lo] + arr[hi]);

  while (lo < hi) {  // lo와 hi가 한 원소를 가리킬 수 없음
    ll sum = arr[lo] + arr[hi];

    // 답 갱신 로직
    if (abs(sum) < diff) {
      diff = abs(sum);
      answer = {lo, hi};
    }

    if (sum < 0) {  // 0이 되려면 값이 커져야 한다.
      lo++;
    } else if (sum > 0) {  // 0이 되려면 값이 작아져야 한다.
      hi--;
    } else {  // sum == 0
      diff = 0;
      answer = {lo, hi};
      break;
    }
  }

  cout << arr[answer.first] << " " << arr[answer.second];

  return 0;
}
