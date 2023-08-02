#include <bits/stdc++.h>

using namespace std;

#define X first
#define Y second

// bfs + connected component

#define MAX 301

const int dx[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
const int dy[8] = {-1, 0, 1, -1, 1, -1, 0, 1};

using pii = std::pair<int, int>;

int n, answer, remain_count;
vector<vector<char>> board;
vector<vector<int>> numbers;  // 넘버링 된 보드
vector<vector<bool>> visited;

void init() {
  board = vector<vector<char>>(n, vector<char>(n, ' '));
  visited = vector<vector<bool>>(n, vector<bool>(n, false));
  numbers = vector<vector<int>>(n, vector<int>(n, -1));
  answer = INT_MAX;
  remain_count = 0;
}

bool isValid(pii p) { return p.X >= 0 && p.X < n && p.Y >= 0 && p.Y < n; }

template <typename T>
void printBoard(vector<vector<T>> b) {
  cout << "print board\n";

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cout << b[i][j] << ' ';
    }
    cout << '\n';
  }
}

// start 에서 부터 bfs를 돌려서 클릭 카운트 세기
void bfs(pii start) {
  queue<pii> q;
  // int count = 0;

  // 첫 점 방문
  q.push(start);
  visited[start.X][start.Y] = true;

  while (!q.empty()) {
    pii current = q.front();
    q.pop();

    // 현재 점이 0이면 기준 8방향을 모두 큐에 넣음
    if (numbers[current.X][current.Y] == 0) {
      for (int i = 0; i < 8; i++) {
        pii next = {current.X + dx[i], current.Y + dy[i]};

        if (!isValid(next)) continue;
        if (visited[next.X][next.Y] || numbers[next.X][next.Y] == -1) continue;

        visited[next.X][next.Y] = true;
        q.push(next);
      }
    }
  }
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int i = 1; i <= test_case; i++) {
    cin >> n;
    init();

    vector<pii> minesweepers;  // 지뢰의 좌표 저장

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        cin >> board[i][j];

        if (board[i][j] == '*') {
          minesweepers.push_back({i, j});
        } else {
          numbers[i][j] = 0;
        }
      }
    }

    // 먼저 보드에 넘버링하기
    for (auto ms : minesweepers) {
      for (int dir = 0; dir < 8; dir++) {
        pii next = {ms.X + dx[dir], ms.Y + dy[dir]};

        if (isValid(next) && board[next.X][next.Y] == '.') {
          numbers[next.X][next.Y]++;
        }
      }
    }

    // 값이 0인 칸 우선 클릭
    vector<pii> zeroes;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (numbers[i][j] == 0) {
          zeroes.push_back({i, j});
        }
      }
    }

    int click_count = 0;

    // 0인 것 부터 먼저 bfs를 돌린다.
    for (auto zero : zeroes) {
      if (!visited[zero.X][zero.Y]) {
        click_count++;
        bfs(zero);
      }
    }

    // 아직 방문안한 점을 따로 방문한다.
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (visited[i][j] || numbers[i][j] == -1) continue;
        visited[i][j] = true;
        click_count++;
      }
    }

    cout << "#" << i << " " << click_count << "\n";
  }

  return 0;
}
