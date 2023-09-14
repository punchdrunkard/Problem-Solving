#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define X first
#define Y second

using namespace std;

int n, m;
vector<vector<int>> board;
vector<vector<bool>> clouds;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  board.resize(n + 1, vector<int>(n + 1));  // 1-indexed

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cin >> board[i][j];
    }
  }

  // 최초의 비구름을 만든다.
  clouds.resize(n + 1, vector<bool>(n + 1, false));
  clouds[n][1] = true;
  clouds[n][2] = true;
  clouds[n - 1][1] = true;
  clouds[n - 1][2] = true;
}

vector<pair<int, int>> DIRS = {{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1},
                               {0, 1}, {1, 1},  {1, 0},   {1, -1}};

int computeRange(int d) {
  if (d > n) {
    d %= n;
  } else if (d < 0) {
    d = (d % n) + n;
  }

  if (d == 0) {
    return n;
  }

  return d;
}

void move(int d, int s) {
  vector<vector<bool>> temp(n + 1, vector<bool>(n + 1, false));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (clouds[i][j]) {
        pair<int, int> next = {computeRange(i + DIRS[d].X * s),
                               computeRange(j + DIRS[d].Y * s)};

        temp[next.X][next.Y] = true;
      }
    }
  }

  clouds = temp;
}

int findAnswer() {
  int answer = 0;

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      answer += board[i][j];
    }
  }

  return answer;
}

// 디버깅용 출력함수
template <typename T>
void printBoard(vector<vector<T>> &arr) {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cout << arr[i][j] << " ";
    }
    cout << "\n";
  }

  cout << "\n";
}

void rain() {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (clouds[i][j]) {
        board[i][j] += 1;
      }
    }
  }
}

bool isValidRange(int x, int y) { return 1 <= x && x <= n && 1 <= y && y <= n; }

void copyRain() {
  vector<pair<int, int>> CROSS_DIRS = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (clouds[i][j]) {
        int count = 0;

        for (auto DIR : CROSS_DIRS) {
          pair<int, int> next = {i + DIR.X, j + DIR.Y};

          if (isValidRange(next.X, next.Y)) {
            if (board[next.X][next.Y] > 0) {
              count++;
            }
          }
        }

        board[i][j] += count;
      }
    }
  }
}

void makeCloud() {
  vector<vector<bool>> temp(n + 1, vector<bool>(n + 1, false));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (board[i][j] >= 2 && !clouds[i][j]) {
        temp[i][j] = true;
        board[i][j] -= 2;
      }
    }
  }

  clouds = temp;
}

int main() {
  input();

  for (int i = 1; i <= m; i++) {
    int d, s;  // 방향, 거리
    cin >> d >> s;

    move(d, s);
    rain();
    copyRain();
    makeCloud();
  }

  cout << findAnswer();

  return 0;
}
