#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

typedef long long ll;

struct Edge {
  int src;
  int dest;
  ll cost;
};

bool compare(const Edge &a, const Edge &b) { return a.cost < b.cost; }

int n;
vector<Edge> edgeList;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      ll cost;
      cin >> cost;

      if (i == j) continue;

      edgeList.push_back({i, j, cost});
    }
  }
}

class UnionFind {
 private:
  vector<int> parent;

 public:
  UnionFind(int n) {
    parent.resize(n);
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }

  int find(int a) {
    if (parent[a] == a) return a;
    int result = find(parent[a]);
    parent[a] = result;
    return result;
  }

  void merge(int a, int b) {
    a = find(a);
    b = find(b);

    if (a == b) return;

    parent[a] = b;
  }
};

// 크루스칼 알고리즘
ll MST() {
  ll mstCost = 0;
  int edgeCount = 0;

  // edgeList 정렬
  sort(edgeList.begin(), edgeList.end(), compare);
  UnionFind uf(n);

  for (auto edge : edgeList) {
    if (edgeCount == edgeList.size()) break;

    if (uf.find(edge.src) == uf.find(edge.dest)) {
      continue;
    }

    uf.merge(edge.src, edge.dest);
    mstCost += edge.cost;
    edgeCount++;
  }

  return mstCost;
}

int main() {
  input();
  cout << MST();

  return 0;
}
