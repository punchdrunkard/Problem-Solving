#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

struct Edge {
  int src;
  int dest;
  int cost;
};

int n;  // 논의 수
vector<Edge> edgeList;

// 가장 작은 것부터 연결하기 위하여, edge를 cost 기준 오름차순 정렬해야 한다.
bool compare(const Edge &a, const Edge &b) { return a.cost < b.cost; }

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);
  cin >> n;

  // 가상의 노드 0을 만들고, 0과 연결된 간선을 우물을 파는 비용으로 하자.
  for (int i = 1; i <= n; i++) {
    int ownCost;
    cin >> ownCost;
    edgeList.push_back({0, i, ownCost});
  }

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      int cost;
      cin >> cost;

      if (i == j) {
        continue;
      }

      edgeList.push_back({i, j, cost});
    }
  }
}

// 크루스칼 알고리즘을 위한 유니온 파운드
class UnionFind {
 private:
  vector<int> parent;

 public:
  UnionFind(int n) {
    parent.resize(n + 1);
    for (int i = 0; i <= n; i++) {
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
int MST() {
  int mstCost = 0, edgeCount = 0;

  // 1. 간선 리스트 정렬
  sort(edgeList.begin(), edgeList.end(), compare);

  // Unionfind 초기화
  UnionFind uf(n);

  int edgeSize = edgeList.size();

  // 2. 작은 간선부터 선택하며 union
  for (auto edge : edgeList) {
    if (edgeCount == edgeSize) {
      break;
    }

    // 이미 같은 tree에 속해있는 경우
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
