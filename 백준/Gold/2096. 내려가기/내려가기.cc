#include <bits/stdc++.h>
#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
const int INF = 1e9;
const int ROW_SIZE = 3;
int main() {
  FASTIO;
  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);
  int n;
  cin >> n;
  // 슬라이딩 윈도우 - 값이 들어오는대로 바로 처리해야함
  // 이전 값을 재활용하여 현재 값을 갱신하지만, 답을 갱신하는 과정에서 봐야하는
  // 건 현재 값(새로 추가되는 수)와 직전의 값 밖에 없다. 따라서 window size 2
  // (이전값과 현재값) 를 이용하여 값을 갱신하자.
  int prev_min[ROW_SIZE] =
      {
          0,
      },
      prev_max[ROW_SIZE] = {
          0,
      };
  for (int i = 0; i < n; i++) {
    int x, y, z;
    cin >> x >> y >> z;  // 현재 값
    // 왼쪽, 중간, 오른쪽
    int left = max(prev_max[0], prev_max[1]) + x;
    int middle = max({prev_max[0], prev_max[1], prev_max[2]}) + y;
    int right = max(prev_max[1], prev_max[2]) + z;
    prev_max[0] = left;
    prev_max[1] = middle;
    prev_max[2] = right;
    left = min(prev_min[0], prev_min[1]) + x;
    middle = min({prev_min[0], prev_min[1], prev_min[2]}) + y;
    right = min(prev_min[1], prev_min[2]) + z;
    prev_min[0] = left;
    prev_min[1] = middle;
    prev_min[2] = right;
  }
    
  cout << *max_element(prev_max, prev_max + ROW_SIZE) << " "
       << *min_element(prev_min, prev_min + ROW_SIZE);
  return 0;
}
