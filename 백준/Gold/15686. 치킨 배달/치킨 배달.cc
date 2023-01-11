#include <bits/stdc++.h>
#define MAX 51
#define X first
#define Y second
using namespace std;

int city[MAX][MAX];
int n, m;
vector<pair<int, int>> chickens;
vector<pair<int, int>> homes;
int dist[MAX][MAX];
bool isValid[MAX][MAX];

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> m;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> city[i][j];

      if (city[i][j] == 2) {
        chickens.push_back({i, j});
      }

      if (city[i][j] == 1) {
        homes.push_back({i, j});
      }
    }
  }
}

int calHomeDistance(int x, int y, vector<int> sequence) {
  int dist = INT_MAX;

  for (auto index : sequence) {
    pair<int, int> loc = chickens[index];
    dist = min(dist, abs(x - loc.X) + abs(y - loc.Y));
  }

  return dist;
}

int calCityDistance() {
  int sum = 0;

  for (auto home : homes) {
    sum += dist[home.X][home.Y];
  }

  return sum;
}

int compute(int m) {
  vector<int> indexes;
  vector<vector<int>> sequences;
  vector<int> choose;

  int minCityDist = INT_MAX;

  // 모든 치킨 집 갯수에서 m개를 뽑기 -> 조합
  for (int i = 0; i < chickens.size(); i++) {
    indexes.push_back(i);
  }

  for (int i = 0; i < chickens.size(); ++i) choose.push_back(i < m ? 0 : 1);

  do {
    vector<int> sequence;
    for (auto index : indexes) {
      if (choose[index] == 0) sequence.push_back(index);
    }

    sequences.push_back(sequence);

  } while (next_permutation(choose.begin(), choose.end()));

  // 해당되는 위치 마킹해서 나타내기
  for (auto sequence : sequences) {
    for (auto index : sequence) {
      pair<int, int> loc = chickens[index];
      isValid[loc.X][loc.Y] = true;
    }

    // 각 좌표에 대해서 치킨 거리를 계산
    for (auto home : homes) {
      dist[home.X][home.Y] = calHomeDistance(home.X, home.Y, sequence);
    }

    // 도시의 치킨 거리 계산
    for (auto home : homes) {
      minCityDist = min(minCityDist, calCityDistance());
    }

    // 초기화
    memset(isValid, false, sizeof(isValid));
    memset(dist, 0, sizeof(dist));
  }

  return minCityDist;
}

int main() {
  input();
  cout << compute(m);

  return 0;
}
