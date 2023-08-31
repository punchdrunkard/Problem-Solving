#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define X first
#define Y second

#define INF 1'000'000'000;

using namespace std;

int n;
vector<pair<int, int>> lines;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  lines.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> lines[i].X >> lines[i].Y;
  }
}

int main() {
  input();

  sort(lines.begin(), lines.end());

  int current_x = -INF;
  int current_y = -INF;

  long long answer = 0;

  for (auto line : lines) {
    // 1. 선문이 새로 시작되는 경우
    if (current_y < line.X) {
      answer += (line.Y - line.X);

      current_x = line.X;
      current_y = line.Y;
    }
    // 2. 선분의 일부가 겹치는 경우 (선분을 그대로 연장하자)
    else if (current_x <= line.X && current_y <= line.Y) {
      answer += (line.Y - current_y);
      current_y = line.Y;
    }
  }

  cout << answer;

  return 0;
}
