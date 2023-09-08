#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;  // 수의 갯수
vector<int> a;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  a.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> a[i];
  }
}

int main() {
  input();
  sort(a.begin(), a.end());

  int count = 0;

  // a[i] = a[j] + a[k] 인 i가 존재하는가?
  for (int i = 0; i < n; i++) {
    int target = a[i];
    int lo = 0, hi = n - 1;

    while (lo < hi) {
      if (lo == i) {
        lo++;
      }

      if (hi == i) {
        hi--;
      }

      if (lo == hi) {
        break;
      }

      long long sum = a[lo] + a[hi];

      if (sum > target) {
        hi--;
      } else if (sum < target) {
        lo++;
      } else {  // sum == target, 정답을 찾은 경우
        count++;
        break;
      }
    }
  }

  cout << count;

  return 0;
}
