#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

// 설계
// 싸움땅!

// n * n 격자에서 진행
// 초기에는 무기들이 없는 빈 격자에 플레이어들이 위치, 각 플레이어들은 초기
// 위치를 가짐 각 플레이어의 초기 능력치는 모두 다르다.

// 설계
// 플레이어가 가진 정보 : 초기 능력치, 본인이 향하고 있는 방향

// 격자의 한 칸이 가지는 정보
// - 격자는 총을 여러 개 가질 수 있다.

struct Player {
  int id;
  int inital_status;  // 초기 능력치
  int gun;  // 가지고 있는 총의 능력치 (0인 경우 총이 없음)
  int dir;
  int x;
  int y;
  int score;
};

// 격자는 총만 관리하자
vector<vector<vector<int>>> board;

// players[i] := i번 플레이어의 정보
vector<Player> players;

// 북, 동, 남, 서
int DX[4] = {-1, 0, 1, 0};
int DY[4] = {0, 1, 0, -1};

int n, m, k;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> k;

  board.resize(n, vector<vector<int>>(n));
  players.resize(m);

  // 격자에 있는 총의 정보
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      int gun;
      cin >> gun;
      if (gun != 0) board[i][j].push_back(gun);
    }
  }

  // 위치 입력은 1-indexed로 들어옴
  for (int i = 0; i < m; i++) {
    int x, y, d, s;
    cin >> x >> y >> d >> s;
    Player p = {i, s, 0, d, x - 1, y - 1, 0};
    players[i] = p;
  }
}

void printPlayer() {
  cout << "-------- 플레이어의 상태를 출력 --------\n";

  for (int i = 0; i < m; i++) {
    cout << i << "번 플레이어\n";
    cout << "위치 : " << players[i].x << ' ' << players[i].y << '\n';
    cout << "초기 능력: " << players[i].inital_status << '\n';
    cout << "가지고 있는 총 : " << players[i].gun << '\n';
    cout << "방향 : " << players[i].dir << '\n';

    cout << "========\n";
  }
}

void printGuns() {
  cout << "-- 총의 상태를 출력 --\n";

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cout << "( ";
      for (auto g : board[i][j]) {
        cout << g << ' ';
      }
      cout << ")";
    }
    cout << '\n';
  }
}

bool isValidRange(int x, int y) { return 0 <= x && x < n && 0 <= y && y < n; }

// 반대로
int changeDirection180(int dir) {
  if (dir == 0) return 2;  // 북(0) -> 남(2)
  if (dir == 1) return 3;  // 동(1) -> 서(3)
  if (dir == 2) return 0;  // 남(2) -> 복(0)
  if (dir == 3) return 1;  // 서(3) -> 동(1)

  return -1;
}

// 시계방향으로 90도 회전
int changeDirection90(int dir) {
  if (dir < 3) {
    return dir + 1;
  } else {
    return 0;
  }
}

void printPlayerBoard() {
  vector<vector<vector<int>>> temp(n, vector<vector<int>>(n));

  for (auto player : players) {
    temp[player.x][player.y].push_back(player.id);
  }

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cout << "( ";

      for (auto p : temp[i][j]) {
        cout << p << ' ';
      }
      cout << ")";
    }
    cout << '\n';
  }
}

// (x, y)에 다른 플레이어가 존재하는가?
bool isExistPlayer(int x, int y) {
  int count = 0;

  for (auto player : players) {
    if (player.x == x && player.y == y) {
      count++;
    }

    // 자기 자신이 포함되므로 (하나씩 움직여서 두명이 최대임)
    if (count >= 2) return true;
  }

  return false;
}

// 본인이 향하고 있는 방향대로 한 칸 이동한다.
void movePlayer(Player& player) {
  pair<int, int> next = {player.x + DX[player.dir], player.y + DY[player.dir]};

  // 만약 격자를 벗어나는 경우에는 반대방향으로 바꾸어 이동한다.
  if (!isValidRange(next.X, next.Y)) {
    player.dir = changeDirection180(player.dir);
    next = {player.x + DX[player.dir], player.y + DY[player.dir]};
  }

  player.x = next.X;
  player.y = next.Y;
}

void getGun(Player& player) {
  // 총이 있는 경우, 해당 플레이어는 총을 획득

  // 해당 격자에 있는 총에 대하여
  for (auto gun : board[player.x][player.y]) {
    // 공격력이 가장 강한 총이 뒤로 오도록 정렬
    sort(board[player.x][player.y].begin(), board[player.x][player.y].end());

    int max_gun =
        board[player.x][player.y][board[player.x][player.y].size() - 1];

    // 플레이어가 총을 가지고 있지 않다면
    if (player.gun == 0) {
      // 총을 획득하기
      player.gun = max_gun;
      board[player.x][player.y].pop_back();

    } else {
      // 이미 총을 가지고 있는 경우에는 놓여 있는 총들과 플레이어가 가지고 있는
      // 총 중 공격력이 쎈 총을 획득하고 나머지 총은 해당 격자에 둔다.
      if (player.gun < max_gun) {
        board[player.x][player.y][board[player.x][player.y].size() - 1] =
            player.gun;
        player.gun = max_gun;
      }
    }
  }
}

vector<int> findTwoPlayer(int x, int y) {
  vector<int> res;

  for (auto player : players) {
    if (player.x == x && player.y == y) {
      res.push_back(player.id);
    }
  }

  return res;
}

struct GameResult {
  int winner;
  int w_score;
  int loser;
  int l_score;
};

int getScore(int id) { return players[id].inital_status + players[id].gun; }

GameResult getGameResult(int id1, int id2) {
  int score1 = getScore(id1);
  int score2 = getScore(id2);

  if (score1 != score2) {
    if (score1 > score2) {
      return {id1, score1, id2, score2};
    } else {
      return {id2, score2, id1, score1};
    }
  } else {
    if (players[id1].inital_status > players[id2].inital_status) {
      return {id1, score1, id2, score2};
    } else {
      return {id2, score2, id1, score1};
    }
  }

  return {-1, -1, -1, -1};
}

bool isExistPlayerOne(int x, int y) {
  for (auto player : players) {
    if (player.x == x && player.y == y) {
      return true;
    }
  }

  return false;
}

// (x, y)에 있는 두 플레이어가 싸운다.
void fight(int x, int y) {
  // 1. 해당 칸에 있는 두 플레이어를 찾는다.
  vector<int> two_players = findTwoPlayer(x, y);

  int p1_id = two_players[0];
  int p2_id = two_players[1];

  // 2. 위너 결정하기
  GameResult game_result = getGameResult(p1_id, p2_id);

  // 2-1. 진 경우
  // 본인이 가지고 있는 총을 해당 격자에 내려 놓는다.
  if (players[game_result.loser].gun != 0) {
    board[x][y].push_back(players[game_result.loser].gun);
    players[game_result.loser].gun = 0;
  }

  Player loser = players[game_result.loser];

  // 해당 플레이어가 원래 가지고 있던 방향대로 한 칸 이동한다.
  for (int dir = loser.dir; dir < loser.dir + 4; dir++) {
    int d = dir >= 4 ? dir % 4 : dir;
    pair<int, int> next = {x + DX[d], y + DY[d]};

    // 만약 이동하려는 칸에 다른 플레이어가 있거나 격자 범위 밖인 경우에는
    // 오른쪽으로 90도씩 회전하여 빈 칸이 보이는 순간 이동합니다.
    if (!isValidRange(next.X, next.Y)) continue;
    if (isExistPlayerOne(next.X, next.Y)) continue;

    // 이동
    players[game_result.loser].x = next.X;
    players[game_result.loser].y = next.Y;
    players[game_result.loser].dir = d;
    break;
  }

  //  만약 해당 칸에 총이 있다면, 해당 플레이어는 가장 공격력이 높은 총을
  //  획득하고 나머지 총들은 해당 격자에 내려 놓습니다.
  getGun(players[game_result.loser]);

  // 2-2. 이긴 경우
  // 이긴 플레이어는 각 플레이어의 초기 능력치와 가지고 있는 총의 공격력의 합의
  // 차이만큼을 포인트로 획득하게 됩니다.

  players[game_result.winner].score +=
      (game_result.w_score - game_result.l_score);
  getGun(players[game_result.winner]);
}

void play(Player& player) {
  // 이동한 방향에 플레이어가 있다면
  if (isExistPlayer(player.x, player.y)) {
    // 두 플레이어가 싸우게 됩니다.

    fight(player.x, player.y);
  } else {  // 이동한 방향에 플레이어가 없다면
            // 해당 칸에 총이 있는지 확인
    if (!board[player.x][player.y].empty()) {
      getGun(player);
    }
  }
}

void solve() {
  // 게임의 진행 (하나의 라운드)
  for (int round = 1; round <= k; round++) {
    // 1번 부터 순차적으로

    for (auto& player : players) {
      // 1. 이동
      movePlayer(player);

      // 2. 이동한 방향에 따라 처리하기
      play(player);
    }
  }
}

// TODO: k 라운드 동안 게임을 진행하면서 각 플레이어들이 획득한 포인트를 출력
void printScore() {
  for (int i = 0; i < m; i++) {
    cout << players[i].score << ' ';
  }
}

int main() {
  input();
  solve();
  printScore();

  return 0;
}
