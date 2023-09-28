#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

int n, q;
int side;  // 한 변의 길이
vector<vector<int>> board;

const array<int, 4> DX = {-1, 1, 0, 0};
const array<int, 4> DY = {0, 0, -1, 1};

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> q;
  side = pow(2, n);
  board.resize(side, vector<int>(side));

  for (int i = 0; i < side; i++) {
    for (int j = 0; j < side; j++) {
      cin >> board[i][j];
    }
  }
}

// (x, y)에서 부터 length 만큼의 정사각형 영역을 회전
void rotate(int x, int y, int length) {
  // 1. 해당 부분 만큼을 temp에 복사한다.
  vector<vector<int>> before_rotate(length, vector<int>(length));

  for (int i = 0; i < length; i++) {
    for (int j = 0; j < length; j++) {
      before_rotate[i][j] = board[x + i][y + j];
    }
  }

  // 2. temp를 회전한다
  vector<vector<int>> after_rotate(length, vector<int>(length));

  for (int i = 0; i < length; i++) {
    for (int j = 0; j < length; j++) {
      after_rotate[j][length - i - 1] = before_rotate[i][j];
    }
  }

  // 3. 그대로 board에 붙인다.
  for (int i = 0; i < length; i++) {
    for (int j = 0; j < length; j++) {
      board[x + i][y + j] = after_rotate[i][j];
    }
  }
}

void printBoard(vector<vector<int>> &board) {
  for (int i = 0; i < board.size(); i++) {
    for (int j = 0; j < board.size(); j++) {
      cout << board[i][j] << ' ';
    }
    cout << '\n';
  }
}

vector<pair<int, int>> getStartPoints(int len) {
  vector<pair<int, int>> points;

  for (int i = 0; i < side; i += len) {
    for (int j = 0; j < side; j += len) {
      points.push_back({i, j});
    }
  }

  return points;
}

bool isValidRange(int x, int y) {
  return 0 <= x && x < side && 0 <= y && y < side;
}

bool isMelt(int x, int y) {
  int count = 0;

  for (int dir = 0; dir < 4; dir++) {
    int nx = x + DX[dir], ny = y + DY[dir];
    if (!isValidRange(nx, ny)) continue;
    if (board[nx][ny] > 0) count++;
  }

  return count <= 2;
}

void meltIce() {
  vector<vector<int>> temp(side,
                           vector<int>(side));  // 결과를 저장할 임시 배열

  for (int i = 0; i < side; i++) {
    for (int j = 0; j < side; j++) {
      if (isMelt(i, j)) {
        if (board[i][j] > 0) {
          temp[i][j] = board[i][j] - 1;
        }
      } else {
        temp[i][j] = board[i][j];
      }
    }
  }

  board = temp;
}

int getIceSum() {
  int count = 0;

  for (int i = 0; i < side; i++) {
    for (int j = 0; j < side; j++) {
      count += board[i][j];
    }
  }

  return count;
}

int bfs(int x, int y, vector<vector<bool>> &visited) {
  queue<pair<int, int>> q;
  q.push({x, y});
  visited[x][y] = true;
  int graph_size = 1;

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      int nx = current.X + DX[dir];
      int ny = current.Y + DY[dir];

      if (!isValidRange(nx, ny)) continue;
      if (visited[nx][ny]) continue;
      if (board[nx][ny] == 0) continue;

      q.push({nx, ny});
      visited[nx][ny] = true;
      graph_size++;
    }
  }

  return graph_size;
}

int getMaxIceSize() {
  int max_count = 0;

  // 첫번째 점부터 bfs를 돌리면서, connected component 찾기
  vector<vector<bool>> visited(side, vector<bool>(side, false));

  for (int i = 0; i < side; i++) {
    for (int j = 0; j < side; j++) {
      if (visited[i][j]) continue;
      if (board[i][j] == 0) continue;

      max_count = max(max_count, bfs(i, j, visited));
    }
  }

  return max_count;
}

void solve() {
  for (int cmd = 0; cmd < q; cmd++) {
    int l;
    cin >> l;

    int len = pow(2, l);

    // 1. 격자를 부분 격자로 나눈다.
    vector<pair<int, int>> start_points = getStartPoints(len);

    // 2. 모든 부분 격자를 시계 방향으로 90도 회전시킨다.
    for (auto point : start_points) {
      rotate(point.X, point.Y, len);
    }

    // 3. 얼음의 양이 1 줄어든다.
    meltIce();
  }

  // 남아있는 얼음의 합 구하기
  int ice_sum = getIceSum();

  // 가장 큰 덩어리 찾기
  int max_ice_size = getMaxIceSize();

  cout << ice_sum << "\n" << max_ice_size;
}

int main() {
  input();
  solve();

  return 0;
}
