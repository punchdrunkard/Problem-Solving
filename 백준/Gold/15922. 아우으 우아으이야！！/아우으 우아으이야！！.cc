#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define X first
#define Y second

#define INF 1'000'000'001

using namespace std;

int n;  // 수직선 위에 그릴 선분의 갯수
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

  // 시작점의 좌표 : 가장 왼쪽
  int current_x = -INF;
  int current_y = -INF;

  long long answer = 0;

  for (auto line : lines) {
    // 새롭게 선을 긋는 경우
    if (current_y < line.X) {
      answer += (line.Y - line.X);

      current_x = line.X;
      current_y = line.Y;
    } else if (current_x <= line.X && current_y < line.Y) {
      // 선을 연장하는 경우
      answer += (line.Y - current_y);
      current_y = line.Y;
    }
  }

  cout << answer;

  return 0;
}
