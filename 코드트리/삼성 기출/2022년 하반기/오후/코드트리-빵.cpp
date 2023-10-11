#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

int n, m;

// TODO: 모든 사람이 편의점에 도달하는 시간
struct BoardInfo {
  bool disable;        // 해당 칸을 지나갈 수 있는가?
  bool is_basecamp;    // 현재 칸에 베이스 캠프가 위치하는가?
  vector<int> people;  // 현재 칸에 존재하는 사람들
};

// 편의점의 좌표 저장
// stores[i] := i번 사람이 가고 싶어하는 편의점의 좌표 (1-indexed)
vector<pair<int, int>> stores;
// isArrived[i] := i번 사람이 편의점에 도착했는지 여부
vector<bool> is_arrived;

vector<vector<BoardInfo>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);
  cin >> n >> m;

  stores.resize(m + 1);
  is_arrived.resize(m + 1);
  board.resize(n + 1, vector<BoardInfo>(n + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      bool is_basecamp;
      cin >> is_basecamp;
      board[i][j].is_basecamp = is_basecamp;
    }
  }

  for (int i = 1; i <= m; i++) {
    cin >> stores[i].X >> stores[i].Y;
  }
}

bool isAllArrived() {
  for (int i = 1; i <= m; i++) {
    if (is_arrived[i] == false) return false;
  }

  return true;
}

bool isValidRange(pair<int, int> p) {
  return 1 <= p.X && p.X <= n && 1 <= p.Y && p.Y <= n;
}

int DX[4] = {-1, 0, 0, 1};
int DY[4] = {0, -1, 1, 0};

struct BFSInfo {
  int dist;             // 거리
  pair<int, int> prev;  // 이전 포인트의 좌표
};

// 해당 거리에서 최단 거리 경로를 찾아야 한다.
pair<int, int> findNextPoint(int x, int y, pair<int, int> target) {
  // TODO: (x, y) ~ target까지 가는 다음 위치 구하기
  queue<pair<int, int>> q;

  vector<vector<bool>> visited(n + 1, vector<bool>(n + 1, false));
  vector<vector<BFSInfo>> bfs_info(n + 1, vector<BFSInfo>(n + 1));

  q.push({x, y});
  visited[x][y] = true;
  bfs_info[x][y] = {0, {-1, -1}};

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();
    // stk.push(current);

    if (current.X == target.X && current.Y == target.Y) break;

    for (int dir = 0; dir < 4; dir++) {
      pair<int, int> next = {current.X + DX[dir], current.Y + DY[dir]};

      // 격자를 벗어나면 안됨
      if (!isValidRange(next)) continue;
      // 격자에서 이동이 불가능 하면 안됨
      if (board[next.X][next.Y].disable) continue;
      // 이미 방문한 경우 안됨
      if (visited[next.X][next.Y]) continue;

      q.push(next);
      visited[next.X][next.Y] = true;
      bfs_info[next.X][next.Y] = {bfs_info[current.X][current.Y].dist + 1,
                                  current};
    }
  }

  // 경로 추적
  stack<pair<int, int>> stk;
  stk.push(target);

  vector<pair<int, int>> route;

  // 도착지 부터 스택에 넣기
  while (true) {
    auto current = stk.top();
    if (current.X == x && current.Y == y) break;
    stk.push(bfs_info[current.X][current.Y].prev);
  }

  while (!stk.empty()) {
    route.push_back(stk.top());
    stk.pop();
  }

  // TODO: 경로에서 가장 앞의 포인트를 반환하기 (여기 나중에 디버깅 필요)
  return route[1];
}

void movePeople() {
  vector<vector<vector<int>>> temp(n + 1, vector<vector<int>>(n + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (board[i][j].people.empty()) continue;

      // 해당 지점의 사람들에 대해서
      for (auto person : board[i][j].people) {
        // 이미 편의점에 도착해있는 경우
        if (i == stores[person].X && j == stores[person].Y) {
          temp[i][j].push_back(person);
          continue;
        }

        // 해당 지점부터 그 사람이 가야하는 경로를 찾는다.
        pair<int, int> next = findNextPoint(i, j, stores[person]);
        // 그 사람을 이동 시킨다.
        temp[next.X][next.Y].push_back(person);
      }
    }
  }

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      board[i][j].people = temp[i][j];
    }
  }
}

void checkArrived() {
  // 편의점에 도달했는지 확인한다.
  for (int i = 1; i <= m; i++) {
    pair<int, int> store = stores[i];

    for (auto person : board[store.X][store.Y].people) {
      if (person == i) {  // 편의점에 도달한 사람이 있음
        board[store.X][store.Y].disable = true;
        is_arrived[i] = true;
      }
    }
  }
}

const int INF = 1e6;

// BFS - 편의점과 각 베이스 캠프들의 최단거리 찾기
pair<int, int> findNearestBaseCamp(pair<int, int> target) {
  queue<pair<int, int>> q;
  vector<vector<int>> dist(n + 1, vector<int>(n + 1, INF));
  vector<vector<bool>> visited(n + 1, vector<bool>(n + 1, false));

  q.push(target);
  dist[target.X][target.Y] = 0;
  visited[target.X][target.Y] = true;

  // 모든 격자까지의 이동 거리를 구한다.
  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      pair<int, int> next = {current.X + DX[dir], current.Y + DY[dir]};

      if (!isValidRange(next)) continue;
      if (board[next.X][next.Y].disable) continue;
      if (visited[next.X][next.Y]) continue;

      q.push(next);
      visited[next.X][next.Y] = true;
      dist[next.X][next.Y] = dist[current.X][current.Y] + 1;
    }
  }

  // 답을 찾기
  pair<int, int> answer = {INF, INF};
  int min_dist = INF;

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (board[i][j].is_basecamp) {
        if (min_dist > dist[i][j]) {
          min_dist = dist[i][j];
          answer = {i, j};
        }
      }
    }
  }

  return answer;
}

void checkBaseCamp(int i) {
  pair<int, int> target = stores[i];
  pair<int, int> base_camp = findNearestBaseCamp(target);

  board[base_camp.X][base_camp.Y].disable = true;
  board[base_camp.X][base_camp.Y].people.push_back(i);
}

void printPeople() {
  cout << "-- 현재 사람 근황 --\n";

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cout << "( ";

      for (auto p : board[i][j].people) {
        cout << p << ' ';
      }
      cout << ")";
    }

    cout << '\n';
  }
}

int solve() {
  int t = 0;

  while (!isAllArrived()) {
    t++;

    // t번의 사람이 출발한다.
    // 1. 격자에 있는 사람 모두가 1칸 움직인다.
    movePeople();

    // 2. 편의점에 도달한 사람이 있다면, 해당 편의점에서 멈춘다.
    checkArrived();

    // 3. 베이스 캠프
    if (t <= m) {
      checkBaseCamp(t);
    }
  }

  return t;
}

int main() {
  input();
  cout << solve();

  return 0;
}
