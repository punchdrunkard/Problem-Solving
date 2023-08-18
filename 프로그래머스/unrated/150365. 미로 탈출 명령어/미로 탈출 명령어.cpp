#include <bits/stdc++.h>

using namespace std;

string answer = "";
string result = "impossible";

bool flag = false;

int n, m, x, y, r, c, k;
const int DX[4] = {1, 0, 0, -1};
const int DY[4] = {0, -1, 1, 0};
char DIRS[4] = {'d', 'l', 'r', 'u'};

void init(int _n, int _m, int _x, int _y, int _r, int _c, int _k) {
  n = _n;
  m = _m;
  x = _x;
  y = _y;
  r = _r;
  c = _c;
  k = _k;
}

int getDist(int x, int y, int a, int b) { return abs(x - a) + abs(y - b); }

bool isValid(int cx, int cy) { return cx > 0 && cx <= n && cy > 0 && cy <= m; }

void dfs(int cnt, int cur_x, int cur_y) {
  if (!flag) {
    int dist = getDist(cur_x, cur_y, r, c);
    if (k - cnt - dist < 0) return;
    if ((k - cnt - dist) % 2 == 1) return;

    if (cnt == k) {
      if (cur_x == r && cur_y == c) {
        flag = true;
        result = answer;
      }
      return;
    }
    for (int dir = 0; dir < 4; dir++) {
      int nx = cur_x + DX[dir];
      int ny = cur_y + DY[dir];

      if (isValid(nx, ny)) {
        answer += DIRS[dir];
        dfs(cnt + 1, nx, ny);
        answer.pop_back();
      }
    }
  }
}

string solution(int n, int m, int x, int y, int r, int c, int k) {
  init(n, m, x, y, r, c, k);
  dfs(0, x, y);
  return result;
}