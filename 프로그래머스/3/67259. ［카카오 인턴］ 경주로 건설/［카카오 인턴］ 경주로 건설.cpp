#include <bits/stdc++.h>
using namespace std;

int n;
vector<vector<int>> board;
vector<vector<vector<int>>> costs;
int DX[4] = {0, 1, 0, -1};  // 상, 하, 좌, 우
int DY[4] = {-1, 0, 1, 0};

bool isValidRange(int x, int y) { return 0 <= x && x < n && 0 <= y && y < n; }

int solution(vector<vector<int>> _board) {
    n = _board.size();
    board = _board;
    costs = vector<vector<vector<int>>>(n, vector<vector<int>>(n, vector<int>(4, INT_MAX)));
    queue<tuple<int, int, int, int>> q;  // x, y, cost, direction

    // 시작점에서 모든 방향으로 탐색 시작
    for (int i = 0; i < 4; i++) {
        q.push({0, 0, 0, i});
        costs[0][0][i] = 0;
    }

    while (!q.empty()) {
        auto [x, y, cost, dir] = q.front();
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];
            int new_cost = cost + 100;  // 직선 도로 비용

            if (!isValidRange(nx, ny) || board[nx][ny] == 1) {
                continue;
            }

            if (dir != i) {
                new_cost += 500;  // 코너 비용 추가
            }

            if (costs[nx][ny][i] > new_cost) {
                costs[nx][ny][i] = new_cost;
                q.push({nx, ny, new_cost, i});
            }
        }
    }


    return *min_element(costs[n - 1][n - 1].begin(), costs[n - 1][n - 1].end());
}
