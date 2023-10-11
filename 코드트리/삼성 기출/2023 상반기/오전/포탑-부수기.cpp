// https://www.codetree.ai/training-field/frequent-problems/problems/destroy-the-turret

#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

int n, m, k;

struct Turrets {
  int status;  // 공격력
  int latest;  // 가장 최근에 참여한 턴
};

vector<vector<Turrets>> turrets;

const int INF_STATUS = 5001;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> k;

  turrets.resize(n, vector<Turrets>(m));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      int status;
      cin >> status;
      turrets[i][j] = {status, 0};
    }
  }
}

bool isAlive(int x, int y) { return turrets[x][y].status > 0; }

void printTurrets() {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (!isAlive(i, j))
        cout << 0 << ' ';
      else
        cout << turrets[i][j].status << ' ';
    }
    cout << '\n';
  }
}

bool CompareAttacker(const pair<int, int> &a, const pair<int, int> &b) {
  if (turrets[a.X][a.Y].latest == turrets[b.X][b.Y].latest) {
    if ((a.X + a.Y) == (b.X + b.Y)) {
      return a.Y > b.Y;
    }
    return (a.X + a.Y) > (b.X + b.Y);
  }

  return turrets[a.X][a.Y].latest > turrets[b.X][b.Y].latest;
}

int findMinStatus() {
  int min_status = INF_STATUS;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (isAlive(i, j)) {
        min_status = min(min_status, turrets[i][j].status);
      }
    }
  }

  return min_status;
}

// 공격자를 선정 하고, 공격자의 좌표 반환
pair<int, int> pickAttacker() {
  // 1. 공격력이 가장 낮은 포탑 찾기
  int min_status = findMinStatus();

  vector<pair<int, int>> min_turrets;

  // 2. 공격력이 가장 낮은 포탑들의 좌표를 저장
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (turrets[i][j].status == min_status) {
        min_turrets.push_back({i, j});
      }
    }
  }

  sort(min_turrets.begin(), min_turrets.end(), CompareAttacker);
  return min_turrets[0];
}

int findMaxStatus(pair<int, int> attacker) {
  int max_status = 0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      // 주의! 현재 공격자는 제외해야 한다.
      if (isAlive(i, j) && !(i == attacker.X && j == attacker.Y)) {
        max_status = max(max_status, turrets[i][j].status);
      }
    }
  }

  return max_status;
}

bool CompareTarget(const pair<int, int> &a, const pair<int, int> &b) {
  if (turrets[a.X][a.Y].latest == turrets[b.X][b.Y].latest) {
    if ((a.X + a.Y) == (b.X + b.Y)) {
      return a.Y < b.Y;
    }
    return (a.X + a.Y) < (b.X + b.Y);
  }

  return turrets[a.X][a.Y].latest < turrets[b.X][b.Y].latest;
}

// 타겟을 선정하고 타겟의 좌표를 반환
pair<int, int> pickTarget(pair<int, int> attacker) {
  // 1. 공격력이 가장 높은 포탑 찾기
  int max_target = findMaxStatus(attacker);

  vector<pair<int, int>> max_turrets;

  // 2. 공격력이 가장 높은 포탑들의 좌표를 저장
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (turrets[i][j].status == max_target &&
          !(i == attacker.X && j == attacker.Y)) {
        max_turrets.push_back({i, j});
      }
    }
  }

  sort(max_turrets.begin(), max_turrets.end(), CompareTarget);
  return max_turrets[0];
}

// 우/하/좌/상
int DX[4] = {0, 1, 0, -1};
int DY[4] = {1, 0, -1, 0};

struct BFSInfo {
  int dist;
  pair<int, int> prev;
};

// 다음 좌표를 계산한다.
pair<int, int> defineNextPoint(pair<int, int> p) {
  //  가장자리에서 막힌 방향으로 진행하고자 한다면, 반대편으로 나옵니다.
  int nx = (p.X + n) % n;
  int ny = (p.Y + m) % m;

  return {nx, ny};
}

vector<pair<int, int>> bfs(pair<int, int> attacker, pair<int, int> target) {
  vector<vector<BFSInfo>> bfs_info(n, vector<BFSInfo>(m));
  vector<vector<bool>> visited(n, vector<bool>(m));

  queue<pair<int, int>> q;
  q.push(attacker);
  visited[attacker.X][attacker.Y] = true;
  bfs_info[attacker.X][attacker.Y] = {0, {-1, -1}};

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    // 도착한 경우
    if (current.X == target.X && current.Y == target.Y) {
      break;
    }

    for (int dir = 0; dir < 4; dir++) {
      pair<int, int> next =
          defineNextPoint({current.X + DX[dir], current.Y + DY[dir]});

      if (visited[next.X][next.Y] == true) {
        continue;
      }

      if (!isAlive(next.X, next.Y)) {  // 부서진 포탑의 경우
        continue;
      }

      q.push(next);
      visited[next.X][next.Y] = true;
      bfs_info[next.X][next.Y] = {bfs_info[current.X][current.Y].dist + 1,
                                  current};
    }
  }

  // 스택에 쌓아가면서 최단 경로를 계산하자.

  if (!visited[target.X][target.Y]) {
    return {};
  }

  vector<pair<int, int>> min_dist;  // 최단 거리 경로를 저장
  stack<pair<int, int>> stk;

  stk.push(target);

  while (!stk.empty()) {
    auto current = stk.top();
    stk.pop();

    if (current.X == -1 && current.Y == -1) break;
    min_dist.push_back(current);

    stk.push(bfs_info[current.X][current.Y].prev);
  }

  return min_dist;
}

int ADJ_DX[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
int ADJ_DY[8] = {-1, 0, 1, -1, 1, -1, 0, 1};

void attack(pair<int, int> attacker, vector<vector<bool>> &isAttended) {
  // 1. 타겟 선정
  pair<int, int> target = pickTarget(attacker);

  // 2. 레이저 공격 시도
  vector<pair<int, int>> min_dist = bfs(attacker, target);
  int damage = turrets[attacker.X][attacker.Y].status;

  turrets[target.X][target.Y].status -= damage;
  isAttended[target.X][target.Y] = true;

  if (min_dist.size() != 0) {
    // 레이저 공격
    for (auto point : min_dist) {
      if (point.X == target.X && point.Y == target.Y) continue;
      if (point.X == attacker.X && point.Y == attacker.Y) continue;

      turrets[point.X][point.Y].status -= (damage / 2);
      isAttended[point.X][point.Y] = true;
    }
  } else {
    // 포탄 공격
    for (int dir = 0; dir < 8; dir++) {
      pair<int, int> next =
          defineNextPoint({target.X + ADJ_DX[dir], target.Y + ADJ_DY[dir]});

      if (next.X == target.X && next.Y == target.Y) continue;
      if (next.X == attacker.X && next.Y == attacker.Y) continue;

      turrets[next.X][next.Y].status -= (damage / 2);
      isAttended[next.X][next.Y] = true;
    }
  }
}

void inspect(vector<vector<bool>> &isAttended) {
  // 공격과 무관했던 포탑은 공격력이 1씩 올라간다.
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (!isAlive(i, j)) continue;
      if (!isAttended[i][j]) turrets[i][j].status += 1;
    }
  }
}

int findAnswer() {
  int answer = 0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (isAlive(i, j)) {
        answer = max(answer, turrets[i][j].status);
      }
    }
  }

  return answer;
}

bool isEnd() {
  int alive_count = 0;  // 부서지지 않은 포탄의 수

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (isAlive(i, j)) alive_count++;
    }
  }

  return alive_count == 1;
}

void solve() {
  for (int turn = 1; turn <= k; turn++) {
    // 만약 부서지지 않은 포탑이 1개가 된다면 즉시 종료
    if (isEnd()) break;

    // 공격에 참여한 포탑 관리
    vector<vector<bool>> isAttended(n, vector<bool>(m, false));

    // 1. 공격자 선정
    pair<int, int> attacker = pickAttacker();

    // 1-1. 해당 좌표의 공격력이 증가함
    turrets[attacker.X][attacker.Y].status += (n + m);
    turrets[attacker.X][attacker.Y].latest = turn;
    isAttended[attacker.X][attacker.Y] = true;

    // 2. 공격자의 공격
    attack(attacker, isAttended);

    // 3. 포탑 정비
    inspect(isAttended);
  }
}

int main() {
  input();
  solve();
  cout << findAnswer();

  return 0;
}