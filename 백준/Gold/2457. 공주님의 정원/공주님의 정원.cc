#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define X first
#define Y second

using namespace std;

int n;  // 꽃들의 총 갯수
vector<pair<int, int>> flowers;

// 그리디 알고리즘 : 현재 상태에서 가장 "좋은" 선택을 하고,
// 이를 반복함으로써 문제를 해결한다.

// 즉, 매번 현지 시점 (current)에서 피어있는 꽃 중에서
// 가장 마지막에 지는 꽃을 선택한다.

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  flowers.resize(n);

  for (int i = 0; i < n; i++) {
    int s_month, s_day, e_month, e_day;
    cin >> s_month >> s_day >> e_month >> e_day;

    int st = s_month * 100 + s_day;
    int en = e_month * 100 + e_day;

    flowers[i] = {st, en};
  }
}

int main() {
  input();

  sort(flowers.begin(), flowers.end());

  int current = 301;  // 현재 시간
  int count = 0;      // 꽃의 갯수

  while (current <= 1130) {
    // 다음에 피울 꽃의 지는 시간
    int next = current;

    for (auto flower : flowers) {
      if (flower.X <= current && next < flower.Y) {
        next = flower.Y;
      }
    }

    // 업데이트 되었다면 증가시킨다.
    if (next == current) {
      count = 0;
      break;
    } else {
      count += 1;
      current = next;
    }
  }

  cout << count;

  return 0;
}
