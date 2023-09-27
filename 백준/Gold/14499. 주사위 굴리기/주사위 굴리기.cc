#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

// 주사위의 맨 위의 면은 항상 1이라고 생각하고, 숫자만 갱신한다!

enum Direction { EAST = 1, WEST, NORTH, SOUTH };

struct Dice {
  int face[7];  // 1번 면부터 6번 면까지 적혀있는 숫자
  int x;
  int y;
};

// 동, 서, 북, 남, 1-indexed
const array<int, 5> DX = {0, 0, 0, -1, 1};
const array<int, 5> DY = {0, 1, -1, 0, 0};

int n, m, x, y, k;

vector<vector<int>> game_map;
Dice dice;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> x >> y >> k;

  game_map.resize(n, vector<int>(m));
  dice.x = x;
  dice.y = y;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> game_map[i][j];
    }
  }
}

void rollDice(int dir) {
  // 원래 눈에 있던 값들
  int d1 = dice.face[1];
  int d2 = dice.face[2];
  int d3 = dice.face[3];
  int d4 = dice.face[4];
  int d5 = dice.face[5];
  int d6 = dice.face[6];

  switch (dir) {
    case EAST: {
      dice.face[1] = d4;
      dice.face[6] = d3;
      dice.face[4] = d6;
      dice.face[3] = d1;
      break;
    }
    case WEST: {
      dice.face[1] = d3;
      dice.face[6] = d4;
      dice.face[4] = d1;
      dice.face[3] = d6;
      break;
    }
    case NORTH: {
      dice.face[1] = d5;
      dice.face[2] = d1;
      dice.face[5] = d6;
      dice.face[6] = d2;
      break;
    }
    case SOUTH: {
      dice.face[1] = d2;
      dice.face[2] = d6;
      dice.face[5] = d1;
      dice.face[6] = d5;
      break;
    }
  }
}

bool isValidRange(pair<int, int> p) {
  return 0 <= p.X && p.X < n && 0 <= p.Y && p.Y < m;
}

void solve() {
  for (int op = 0; op < k; op++) {
    int cmd;
    cin >> cmd;

    pair<int, int> next = {dice.x + DX[cmd], dice.y + DY[cmd]};

    if (isValidRange(next)) {
      rollDice(cmd);

      dice.x = next.X;
      dice.y = next.Y;

      if (game_map[next.X][next.Y] == 0) {
        // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
        game_map[next.X][next.Y] = dice.face[6];
      } else {
        // 칸에 쓰여 있는 수가 주사위의 바닥 면에 복사되고,
        // 칸에 있는 수는 0이 된다.
        dice.face[6] = game_map[next.X][next.Y];
        game_map[next.X][next.Y] = 0;
      }

      cout << dice.face[1] << '\n';
    }
  }
}

int main() {
  input();
  solve();

  return 0;
}
