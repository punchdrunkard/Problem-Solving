// 문제를 풀기위한 목표 코딩력과 알고력이 필요
// 현제 { 알고력, 코딩력 } => { 목표 알고력, 목표 코딩력 }
// 또한 어떠한 {알고력, 코딩력} 에서 다른 {알고력, 코딩력} 으로 갈 수 있는
// cost가 있다고 생각하면 이 문제를 그래프로 추상화 시킬 수 있음.

#include <bits/stdc++.h>

#define X first
#define Y second

using namespace std;

const int MAX = 151;
const int INF = INT_MAX;

int alp, cop;
vector<vector<int>> problems;

struct State {
  int alg, cod, cost;

  // 최소 힙
  bool operator<(const State& other) const { return cost > other.cost; }
};

void init(int _alp, int _cop, vector<vector<int>> _problems) {
  alp = _alp;
  cop = _cop;
  problems = _problems;
}

pair<int, int> findTargetStatus() {
  int maxAlg = -1, maxCod = -1;

  for (auto& p : problems) {
    maxAlg = max(maxAlg, p[0]);
    maxCod = max(maxCod, p[1]);
  }

  return {maxAlg, maxCod};
}

int dijkstra(pair<int, int> target) {
  vector<vector<int>> dist(MAX, vector<int>(MAX, INF));
  priority_queue<State> pq;

  State start = {alp, cop, 0};
  pq.push(start);
  dist[alp][cop] = 0;

  while (!pq.empty()) {
    State current = pq.top();
    pq.pop();

    // 답을 찾은 경우
    if (current.alg >= target.X && current.cod >= target.Y) return current.cost;

    if (dist[current.alg][current.cod] < current.cost) continue;

    // 문제를 푸는 경우
    for (auto& p : problems) {
      // 문제를 풀 수 있는 경우
      if (current.alg < p[0] || current.cod < p[1]) continue;

      int newAlg = min(current.alg + p[2], target.X);
      int newCod = min(current.cod + p[3], target.Y);
      int newCost = current.cost + p[4];

      if (dist[newAlg][newCod] > newCost) {
        dist[newAlg][newCod] = newCost;
        pq.push({newAlg, newCod, newCost});
      }
    }

    // 공부를 하는 경우
    if (current.alg < target.X) {
      State nextAlg = {current.alg + 1, current.cod, current.cost + 1};

      if (dist[nextAlg.alg][nextAlg.cod] > nextAlg.cost) {
        dist[nextAlg.alg][nextAlg.cod] = nextAlg.cost;
        pq.push(nextAlg);
      }
    }

    if (current.cod < target.Y) {
      State nextCod = {current.alg, current.cod + 1, current.cost + 1};
      if (dist[nextCod.alg][nextCod.cod] > nextCod.cost) {
        dist[nextCod.alg][nextCod.cod] = nextCod.cost;
        pq.push(nextCod);
      }
    }
  }

  return 1;
}

int solution(int _alp, int _cop, vector<vector<int>> _problems) {
  init(_alp, _cop, _problems);
  pair<int, int> target = findTargetStatus();
  return dijkstra(target);
}
