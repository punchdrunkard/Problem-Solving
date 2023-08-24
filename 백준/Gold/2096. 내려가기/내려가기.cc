#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int n;
  cin >> n;

  // 맨 위에 있는 값은 0, 0, 0이라고 가정
  vector<int> prev_max(3, 0), prev_min(3, 0);

  for (int i = 0; i < n; i++) {
    int x, y, z;  // 현재 x, y, z
    cin >> x >> y >> z;

    int left_max = max(prev_max[0], prev_max[1]) + x;
    int middle_max = max({prev_max[0], prev_max[1], prev_max[2]}) + y;
    int right_max = max(prev_max[1], prev_max[2]) + z;

    int left_min = min(prev_min[0], prev_min[1]) + x;
    int middle_min = min({prev_min[0], prev_min[1], prev_min[2]}) + y;
    int right_min = min(prev_min[1], prev_min[2]) + z;

    prev_max = {left_max, middle_max, right_max};
    prev_min = {left_min, middle_min, right_min};
  }

  cout << *max_element(prev_max.begin(), prev_max.end()) << ' '
       << *min_element(prev_min.begin(), prev_min.end());

  return 0;
}
