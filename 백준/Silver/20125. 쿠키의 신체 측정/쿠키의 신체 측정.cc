#include <bits/stdc++.h>
#define MAX 1001
#define X first
#define Y second

using namespace std;

int n;
char board[MAX][MAX];

void input() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input2.txt", "r", stdin);

  cin >> n;

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cin >> board[i][j];
    }
  }
}

void checkBoard() {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cout << board[i][j] << ' ';
    }
    cout << '\n';
  }
}

pair<int, int> findHead() {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (board[i][j] == '*') return {i, j};
    }
  }

  return {0, 0};
}

int main() {
  input();
  // checkBoard();

  pair<int, int> head = findHead();

  // 심장의 위치 찾기
  pair<int, int> heart = {head.X + 1, head.Y};

  // 심장의 위치를 기준으로 왼쪽 팔
  int left_hand = 0;
  int right_hand = 0;
  int back = 0;
  int left_leg = 0;
  int right_leg = 0;

  // 심장부터 왼쪽으로 간다 -> 언제까지? -이 나올떄까지
  for (int i = heart.Y - 1; i >= 1; i--) {
    if (board[heart.X][i] == '_') break;
    left_hand += 1;
  }

  // 오른쪽 팔
  for (int i = heart.Y + 1; i <= n; i++) {
    if (board[heart.X][i] == '_') break;
    right_hand += 1;
  }

  pair<int, int> back_end_point;

  // 허리
  for (int i = heart.X + 1; i <= n; i++) {
    if (board[i][heart.Y] == '_') {
      // 좌표도 함께 저장
      back_end_point = {i - 1, heart.Y};
      break;
    }
    back += 1;
  }

  // 왼쪽 다리
  for (int i = back_end_point.X + 1; i <= n; i++) {
    if (board[i][back_end_point.Y - 1] == '_') {
      break;
    }

    left_leg += 1;
  }

  // 오른쪽 다리
  for (int i = back_end_point.X + 1; i <= n; i++) {
    if (board[i][back_end_point.Y + 1] == '_') {
      break;
    }

    right_leg += 1;
  }

  cout << heart.X << ' ' << heart.Y << '\n';
  cout << left_hand << ' ' << right_hand << ' ' << back << ' ' << left_leg
       << ' ' << right_leg;

  return 0;
}
