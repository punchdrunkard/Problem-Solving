#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define X first
#define Y second

using namespace std;

int n;  // 꽃들의 총 갯수
vector<pair<int, int>> flowers;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  flowers.resize(n);

  for (int i = 0; i < n; i++) {
    int s_month, s_day, e_month, e_day;
    cin >> s_month >> s_day >> e_month >> e_day;

    int start = s_month * 100 + s_day;
    int end = e_month * 100 + e_day;

    flowers[i] = {start, end};
  }
}

int main() {
  input();

  sort(flowers.begin(), flowers.end());

  int answer = 0;
  int current = 301;  // 3월 1일

  while (current <= 1130) {
    int next = current;  // 꽃이 지는 날짜

    for (auto flower : flowers) {
      // 현재 시점에서 피어있는 꽃 중 가장 오랫동안 피어있는 꽃을 선택
      if (flower.X <= current && flower.Y > next) {
        next = flower.Y;
      }
    }

    // 꽃이 갱신되지 않음 = 현재 시점에서 선택할 수 있는 꽃이 없다.
    if (next == current) {
      answer = 0;
      break;
    }

    // 꽃이 갱신되었으면 날짜를 갱신
    answer++;
    current = next;
  }

  cout << answer;

  return 0;
}
