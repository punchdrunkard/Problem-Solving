#include <bits/stdc++.h>

#define fastio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

using ll = long long;
using pll = pair<long, long>;

int n;
double e;

struct Edge {
  int start;
  int end;
  ll weight;
};

vector<Edge> edge_list;
vector<pll> vertex;

bool compare(Edge &a, Edge &b) { return a.weight < b.weight; };

vector<int> parent;

void init() {
  edge_list.clear();
  parent.resize(n);
  vertex.clear();
  vertex.resize(n);

  // disjoint set 초기화
  for (int i = 0; i < n; i++) {
    parent[i] = i;
  }
}

void inputGraph() {
  for (int i = 0; i < n; i++) {
    cin >> vertex[i].first;
  }

  for (int i = 0; i < n; i++) {
    cin >> vertex[i].second;
  }
}

void initEdgeList() {
  for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
      ll dx = vertex[i].X - vertex[j].X;
      ll dy = vertex[i].Y - vertex[j].Y;
      Edge e = {i, j, dx * dx + dy * dy};
      edge_list.push_back(e);
    };
  }
}

// find 연산, 해당 원소가 속한 트리의 루트 반환
int find(int a) {
  if (parent[a] == a) return a;
  int result = find(parent[a]);
  parent[a] = result;
  return result;
}

// union 연산
void merge(int a, int b) {
  int parent_a = find(a);
  int parent_b = find(b);
  if (parent_a == parent_b) return;  // 이미 같은 루트에 속하는 경우
  parent[parent_a] = parent_b;
}

ll MST() {
  ll mst_weight = 0, edge_count = 0;

  // 1. 간선을 오름차순으로 정렬한다.
  sort(edge_list.begin(), edge_list.end(), compare);

  for (auto edge : edge_list) {
    // greedy, 작은 가중치로 정렬되어있으므로 해당 엣지 부터 연결한다.
    if (find(edge.start) != find(edge.end)) {
      merge(edge.start, edge.end);

      edge_count += 1;
      mst_weight += edge.weight;
    }

    if (edge_count == n - 1) {  // 최소 스패닝 트리의 edge 갯수는 vertex - 1
      return mst_weight;
    }
  }

  return -1;
}

int main() {
  fastio;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int i = 1; i <= test_case; i++) {
    cin >> n;
    init();
    inputGraph();
    cin >> e;

    initEdgeList();

    double result = MST() * e;
    ll answer = round(result);

    cout << "#" << i << " " << answer << "\n";
  }

  return 0;
}