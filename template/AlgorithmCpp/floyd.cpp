#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

#define MAX_SIZE 101
#define INF 1e6

int dist[MAX_SIZE][MAX_SIZE];

void floyd(int n) {
  // 플로이드 워셜
  // i부터 j까지, 1 ~ k 개의 정점을 이용하여 최단 거리를 구한다.
  //  DP를 사용하는 문제!
  // N번 만큼의 단계를 반복하며 '점화식에 맞게' 2차원 리스트를 갱신 하는 것

  // 1. 현재 단계를 K 번째라고 할 때,
  // 현재 dist에는 1 ~ (k - 1) 번째 정점을 사용하여 나올 수 있는 최단 거리가
  // 남아있음 1 ~ k의 정점을 이용하여 도달 가능한 최단 거리를 구함 dp이므로
  // 과거의 값을 이용하여 다음 값을 구하는 방식!

  // 2. 점화식
  // 두 정점 i, j에 대해 k번 정점을 지나가면서 가면 최단거리가 더 짧아지는가?
  // 즉 기준 i -> j 보다 i - > k -> j가 더 짧은 최단거리를 가지는지 확인

  // 3. 위의 점화식을 모든 (i , j)에 적용함!
  // 조건 => if (dist[i][j] > dist[i][k] + dist[k][j])
  // 위의 if 문으로 판단하면서 기존보다 빨라지면 최단거리 갱신을 한다.

  for (int k = 1; k <= n; k++) {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        // 최단 거리가 더 짧은 걸 dist로 갱신
        // (i → j 가 있고 i → k → j 가 있다면 이 둘 중 짧은걸 찾아서 최단거리를
        // 구하면 된다.)
        dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
      }
    }
  }
}

int main() {
  int n = 5;

  // 초기화
  // 자기 자신은 0으로, 나머지는 INF로 설정함
  // -> 자기 자신을 0으로 두는 것은 자기 자신으로 이동이 가능하다는 것을 의미
  // 간혹 자기 자신을 못가는 경우도 있을 수 있음. 그 때는 INF로 하기

  for (int i = 0; i <= n; i++) {
    for (int j = 0; j <= n; j++) {
      dist[i][j] = i == j ? 0 : INF;
    }
  }

  dist[1][2] = 2;
  dist[1][3] = 3;
  dist[1][4] = 1;
  dist[1][5] = 10;
  dist[2][4] = 2;
  dist[3][1] = 8;
  dist[3][4] = 1;
  dist[3][5] = 7;
  dist[5][1] = 9;

  floyd(n);

  return 0;
}

// 보통 플로이드 워셜로 뭔갈 하면 그 다음에 또 뭔갈 하는 문제로 출제
// 일반적으로 플로이드 워셜 안에 있는게 바뀌는 경우는 거의 없다.