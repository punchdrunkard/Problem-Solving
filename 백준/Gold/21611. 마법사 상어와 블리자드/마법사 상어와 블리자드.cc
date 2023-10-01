#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

struct BoardData {
  int room_number;
  int marble_number;
};

int n, m;  // 격자, 블리자드 마법
vector<vector<BoardData>> board;
vector<int> marbles;               // 보드 칸에 따른 구슬들 저장
vector<pair<int, int>> locations;  // 보드 칸에 대한 위치 저장

// 방향 : 1-indexed
int DX[5] = {0, -1, 1, 0, 0};
int DY[5] = {0, 0, 0, -1, 1};

int explode_count[4];  // 폭발한 구슬의 갯수

bool isValidRange(int x, int y) { return 1 <= x && x <= n && 1 <= y && y <= n; }

void numberingBoard() {
  // 소용돌이로 돌면서 보드 넘버링
  int S_DX[4] = {0, 1, 0, -1};
  int S_DY[4] = {-1, 0, 1, 0};

  int length = 1, count = 0, dir = 0;
  int mid = (n + 1) / 2;
  int x = mid, y = mid;

  board[x][y].room_number = count++;
  locations[count] = {x, y};
  marbles[0] = 0;

  while (length <= n) {
    for (int i = 0; i < 2; i++) {          // 소용돌이 한 단위
      for (int j = 1; j <= length; j++) {  // 지정된 길이만큼 순회
        x += S_DX[dir];
        y += S_DY[dir];
        if (isValidRange(x, y)) {
          locations[count] = {x, y};
          marbles[count] = board[x][y].marble_number;
          board[x][y].room_number = count++;
        }
      }
      dir = (dir + 1) % 4;  // 방향 전환
    }
    length++;
  }
}

void printMarbleState() {
  for (int i = 0; i < n * n; i++) {
    cout << marbles[i] << ' ';
  }
  cout << '\n';
}

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  board.resize(n + 1, vector<BoardData>(n + 1));
  locations.resize(n * n);
  marbles.resize(n * n);

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cin >> board[i][j].marble_number;
    }
  }

  numberingBoard();
}

void destroyMarble(int d, int s) {
  int mid = (n + 1) / 2;

  for (int i = 1; i <= s; i++) {
    int x = mid + DX[d] * i;
    int y = mid + DY[d] * i;

    if (!isValidRange(x, y)) continue;

    // 해당 칸의 구슬을 파괴한다.
    int current_room = board[x][y].room_number;
    board[x][y].marble_number = 0;
    marbles[current_room] = 0;
  }
}

// 구슬 상태와 보드 상태를 동기화
void syncMarble() {
  for (int i = 1; i < n * n; i++) {
    int x = locations[i].X;
    int y = locations[i].Y;

    board[x][y].marble_number = marbles[i];
  }
}

void moveMarble() {
  // marble 배열 이용
  vector<int> temp(n * n, 0);

  int temp_index = 1;

  for (int idx = 1; idx < n * n; idx++) {
    if (marbles[idx] != 0) {
      temp[temp_index++] = marbles[idx];
    }
  }

  marbles = temp;
}

// 구슬을 폭발시키고, 폭발되었는지 여부를 반환
bool explodeMarble() {
  int count = 1;  // 연속하는 구슬의 수
  int current_marble = marbles[1];
  bool flag = false;

  for (int i = 2; i < n * n; i++) {
    if (marbles[i] == current_marble) {
      count++;
    } else {
      if (count >= 4) {  // 이전까지 다 터뜨려준다.
        flag = true;
        int prev_idx = i - count;
        explode_count[current_marble] += count;

        for (int j = prev_idx; j < i; j++) {
          marbles[j] = 0;
        }
      }

      count = 1;
      current_marble = marbles[i];
    }
  }

  return flag;
}

void changeMarble() {
  vector<int> temp(n * n, 0);  // 구슬 상태 저장할 임시 배열

  int count = 1;  // 연속하는 구슬의 수
  int current_marble = marbles[1];
  int temp_index = 1;

  for (int i = 2; i < n * n; i++) {
    // 구슬 그룹 찾기 = 연속하는 구슬
    if (current_marble == marbles[i]) {
      count++;
    } else {
      // 값 갱신 후
      int a = count;           // 그룹에 들어있는 구슬의 개수
      int b = current_marble;  // 그룹을 이루고 있는 구슬의 번호

      // 구슬은 다시 그룹의 순서대로 1번 칸부터 들어간다.
      temp[temp_index++] = a;
      temp[temp_index++] = b;

      // 만약 구슬이 칸의 수보다 많아 칸에 들어가지 못하는 경우 사라진다.
      if (temp_index >= n * n) break;

      count = 1;
      current_marble = marbles[i];
    }
  }

  marbles = temp;
}

void blizzard() {
  for (int cmd = 0; cmd < m; cmd++) {
    int d, s;  // 방향, 거리
    cin >> d >> s;

    destroyMarble(d, s);
    moveMarble();

    while (true) {
      bool isExplode = explodeMarble();
      if (!isExplode) {
        break;
      }
      moveMarble();
    }

    changeMarble();
    syncMarble();
  }
}

void printAnswer() {
  int answer = 0;
  for (int i = 1; i <= 3; i++) {
    answer += (explode_count[i] * i);
  }

  cout << answer;
}

int main() {
  input();
  blizzard();
  printAnswer();

  return 0;
}
