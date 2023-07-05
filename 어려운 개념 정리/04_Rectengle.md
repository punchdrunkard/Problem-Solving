## 격자 위에서 직사각형 표현하기

- 격자 위에서 직사각형은 **점 두개**로 표현할 수 있다.
  - 따라서 격자 위에서 임의의 직사각형을 선택할 때, 4중 for문을 사용하여 직사각형을 선택할 수 있다.

## 직사각형 끼리의 겹침 여부 확인하기

> 주의! 시간 복잡도가 높은 방법이지만, 간단하게 구현할 수 있으므로 수의 범위를 _반드시_ 확인해야 한다.

- bfs처럼, `visited` 배열을 응용한다. 단, 이 때의 `visited` 배열은 `bool` 배열이 아니라 **몇 번 visited를 방문했는지**를 의미한다.
- 직사각형이 두 점으로 표현되었다면, 해당 점 내부의 영역을 표시하면서 `visited`의 count 를 증가시킨다.
- 모든 직사각형 내부에서 작업을 수행하고, 마지막으로 `visited` 배열을 확인하여, 그 값이 2 이상이라면 직사각형이 겹친다는 것을 의미한다.

## 예시 코드

```c++
// 1. 격자에서 두 개의 직사각형을 선택한다. (각 직사각형은 격자의 두 점으로
// 표시할 수 있다.)
// 2. 선택한 직사각형이 서로 겹치는지 확인한다.
// 직사각형의 영역을 돌면서 visited 배열에 체크하고, 이 visited 배열이 2
// 이상이라면 겹친다는 의미이다. (순수한 브루트포스...)
// 3. 직사각형의 영역 내부에서 안쪽의 합을 계산한다.
#include <bits/stdc++.h>
#define MAX 6
#define X first
#define Y second
using namespace std;
int n, m;
int board[MAX][MAX];
int visited[MAX][MAX];
void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> n >> m;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> board[i][j];
    }
  }
}
void markVisited(pair<int, int> a, pair<int, int> b) {
  for (int i = a.X; i <= b.X; i++) {
    for (int j = a.Y; j <= b.Y; j++) {
      visited[i][j] += 1;
    }
  }
}
bool isOverlapped(pair<int, int> a, pair<int, int> b, pair<int, int> c,
                  pair<int, int> d) {
  memset(visited, 0, sizeof(visited));
  markVisited(a, b);
  markVisited(c, d);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (visited[i][j] >= 2) {
        return true;
      }
    }
  }
  return false;
}
int getRectSum(pair<int, int> a, pair<int, int> b) {
  int sum = 0;
  for (int i = a.X; i <= b.X; i++) {
    for (int j = a.Y; j <= b.Y; j++) {
      sum += board[i][j];
    }
  }
  return sum;
}
int findMaxSum(pair<int, int> a, pair<int, int> b) {
  int maxSum = INT_MIN;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {  // 직사각형의 한 점 선택
      pair<int, int> aa = {i, j};
      for (int k = i; k < n; k++) {
        for (int l = j; l < m; l++) {  // 직사각형에서 또 다른 한 점 선택
          pair<int, int> bb = {k, l};
          // 선택한 점이 겹치는지 확인한다.
          if (isOverlapped(a, b, aa, bb)) continue;
          int sum = getRectSum(a, b) + getRectSum(aa, bb);
          maxSum = max(sum, maxSum);
        }
      }
    }
  }
  return maxSum;
}
int solve() {
  int maxSum = INT_MIN;
  // 격자에서 기준이 되는 한 직사각형을 선택한다.
  // 직사각형은 격자에서 두 점으로 표현할 수 있다.
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {  // 직사각형의 한 점 선택
      pair<int, int> a = {i, j};
      for (int k = i; k < n; k++) {
        for (int l = j; l < m; l++) {  // 직사각형에서 또 다른 한 점 선택
          pair<int, int> b = {k, l};
          maxSum = max(maxSum, findMaxSum(a, b));
        }
      }
    }
  }
  return maxSum;
}
int main(void) {
  input();
  cout << solve();
  return 0;
}
```
