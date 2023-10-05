#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

vector<int> move_count(10);  // 주사위에서 나올 수

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  for (int i = 0; i < 10; i++) {
    cin >> move_count[i];
  }
}

struct Location {
  int board_num;  // 보드 번호
  int index;      // 보드 내에서의 인덱스
};

struct BoardInfo {
  int num;        // 칸에 적혀있는 수
  Location next;  // 다음 칸에 대한 정보
};

vector<vector<BoardInfo>> boards(5);

Location current = {1, 0};
int score = 0;

void initBoard() {  // 보드 초기화
  // 1번 보드 초기화 (시작 ~ 40까지)
  boards[1].resize(20);

  for (int i = 0; i < 20; i++) {
    BoardInfo b = {i * 2, {1, i + 1}};

    if (i == 19) {
      b.next = {4, 6};  // 40
    }

    boards[1][i] = b;
  }

  // 2번 보드 초기화 (10 ~ 25)
  boards[2].resize(5);
  boards[2][0] = {10, {2, 1}};
  boards[2][1] = {13, {2, 2}};
  boards[2][2] = {16, {2, 3}};
  boards[2][3] = {19, {2, 4}};
  boards[2][4] = {25, {4, 4}};

  // 3번 보드 초기화 (30 ~ 25)
  boards[3].resize(5);
  boards[3][0] = {30, {3, 1}};
  boards[3][1] = {28, {3, 2}};
  boards[3][2] = {27, {3, 3}};
  boards[3][3] = {26, {3, 4}};
  boards[3][4] = {25, {4, 4}};

  // 4번 보드 초기화 (20 ~ 도착)
  boards[4].resize(8);
  boards[4][0] = {20, {4, 1}};
  boards[4][1] = {22, {4, 2}};
  boards[4][2] = {24, {4, 3}};
  boards[4][3] = {25, {4, 4}};
  boards[4][4] = {30, {4, 5}};
  boards[4][5] = {35, {4, 6}};
  boards[4][6] = {40, {4, 7}};
  boards[4][7] = {0, {4, 7}};  // 도착
}

bool isEnd(Location l) { return l.board_num == 4 && l.index == 7; }

// i번쨰 말을 이동시킨 위치를 반환한다.
Location movePlayer(int move, int player_number, vector<Location> &players) {
  Location c = players[player_number];
  if (isEnd(c)) return c;

  for (int i = 0; i < move; i++) {
    Location next;

    // 파란색 화살표 처리
    if (i == 0 && c.board_num == 1) {
      if (c.index == 5) {
        next = {2, 1};
      } else if (c.index == 10) {
        next = {4, 1};
      } else if (c.index == 15) {
        next = {3, 1};
      } else {
        next = boards[c.board_num][c.index].next;
      }
    } else {
      next = boards[c.board_num][c.index].next;
    }

    if (isEnd(next)) {
      return next;
    }

    c = next;
  }

  return c;
}

bool canChoose(Location next, int current_index, vector<Location> &players) {
  for (int i = 0; i < 4; i++) {
    if (i == current_index) continue;

    if (players[i].board_num == next.board_num &&
        players[i].index == next.index) {
      // 도착지점은 여러 말이 동시에 있을 수 있다.
      if (!isEnd(next)) return false;
    }
  }

  return true;
}

void dfs(int count, vector<Location> players, int current_score) {
  // 종료 조건
  if (count == 10) {
    score = max(current_score, score);
    return;
  }

  // 말을 선택한다.
  for (int i = 0; i < 4; i++) {
    Location original_player = players[i];

    // 도착칸에 있는 말은 고르면 안된다.
    if (isEnd(players[i])) continue;

    Location next = movePlayer(move_count[count], i, players);

    // 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다.
    if (!canChoose(next, i, players)) {
      continue;
    }

    int current_number = boards[next.board_num][next.index].num;

    players[i] = next;

    dfs(count + 1, players, current_score + current_number);
    players[i] = original_player;
  }
}

void solve() {
  initBoard();

  // 말들의 초기 상태
  vector<Location> players = {{1, 0}, {1, 0}, {1, 0}, {1, 0}};
  dfs(0, players, 0);

  cout << score;
}

int main() {
  input();
  solve();

  return 0;
}
