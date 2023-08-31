#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;
typedef long long ll;

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int tc = 1; tc <= test_case; tc++) {
    int n;  // 날의 수
    cin >> n;

    vector<int> days(n);

    for (int day = 0; day < n; day++) {
      cin >> days[day];
    }

    // 뒤에서 부터
    ll answer = 0;

    int current_max = 0;  // 현재 최댓값

    for (int i = n - 1; i >= 0; i--) {
      if (current_max < days[i]) {
        current_max = days[i];
      } else {
        answer -= days[i];
        answer += current_max;
      }
    }

    cout << answer << '\n';
  }

  return 0;
}
