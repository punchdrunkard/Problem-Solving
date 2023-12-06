#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define N_MAX 51

using namespace std;

int n, s, m;
int v[N_MAX];  // i번쨰 곡이 시작하기 전에 줄 수 있는 볼륨의 차이
bool dp[N_MAX][1001];  // dp[i][j] := i번째곡에서 j번볼륨으로 가는게 가능한지?

void input() {
  FASTIO;

  cin >> n >> s >> m;
    
  for (int i = 1; i <= n; i++) {
    cin >> v[i];
  }
}

bool isValidVolume(int volume) { return 0 <= volume && volume <= m; }

int main() {
  input();

  // base-case : 1번의 경우
  if (isValidVolume(s + v[1])) {
    dp[1][s + v[1]] = true;
  }

  if (isValidVolume(s - v[1])) {
    dp[1][s - v[1]] = true;
  }

  // dp 테이블 채우기
  for (int i = 2; i <= n; i++) {    // 곡 번호
    for (int j = 0; j <= m; j++) {  // 음량에 대함
      if (dp[i - 1][j] == true) {   // 이전에 음량이 j였다면
        if (isValidVolume(j + v[i])) {
          dp[i][j + v[i]] = true;
        }

        if (isValidVolume(j - v[i])) {
          dp[i][j - v[i]] = true;
        }
      }
    }
  }

  // 최대 음량 찾기
  int answer = -1;

  for (int i = m; i >= 0; i--) {
    if (dp[n][i]) {
      answer = i;
      break;
    }
  }

  cout << answer;

  return 0;
}
