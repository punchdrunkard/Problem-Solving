#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// 사진의 밝기가 평균 이상이 되지 않으면 일절 보려 하지 않는다.
// 사진에서 일부분이라도 볼 수 있는 부분을 찾아주자.

// 두 점 (r1, c1) ~ (r2, c2) 를 꼭짓점으로 하는 직사각형의 밝기 평균
// 사진의 일부분에 해당하는 밝기 평균을 구하기

// 평균 = 합 / 갯수 이므로, 누적합을 구하고 갯수로 나누면 됨

int r, c, q;
vector<vector<int>> photo, pSum;

void input() {
  FASTIO;
  cin >> r >> c >> q;
  photo.resize(r + 1, vector<int>(c + 1));

  for (int i = 1; i <= r; i++) {
    for (int j = 1; j <= c; j++) {
      cin >> photo[i][j];
    }
  }
}

void makePSum() {
  pSum.resize(r + 1, vector<int>(c + 1, 0));

  // pSum[i][j] = (1, 1) ~ (i, j)의 누적합
  for (int i = 1; i <= r; i++) {
    for (int j = 1; j <= c; j++) {
      pSum[i][j] =
          pSum[i - 1][j] + pSum[i][j - 1] - pSum[i - 1][j - 1] + photo[i][j];
    }
  }
}

int solve(int r1, int c1, int r2, int c2) {
  int sum =
      pSum[r2][c2] - pSum[r1 - 1][c2] - pSum[r2][c1 - 1] + pSum[r1 - 1][c1 - 1];

  return floor(sum / ((r2 - r1 + 1) * (c2 - c1 + 1)));
}

int main() {
  input();
  makePSum();

  for (int i = 0; i < q; i++) {
    int r1, c1, r2, c2;
    cin >> r1 >> c1 >> r2 >> c2;
    cout << solve(r1, c1, r2, c2) << '\n';
  }
  return 0;
}
