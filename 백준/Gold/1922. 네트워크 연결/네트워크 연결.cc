#include <bits/stdc++.h>

#define MAX 100001

using namespace std;

struct edge {
  int start;
  int end;
  int weight;
};

int parent[MAX];

// union-find
void initParent(int n) {
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

vector<edge> edgeList;

bool compare(edge a, edge b) { return a.weight < b.weight; }

int MST(int v) {
  int mstWeight = 0;
  int edgeCount = 0;
    
  sort(edgeList.begin(), edgeList.end(), compare);
  initParent(v);

  for (int i = 0; i < edgeList.size(); i++) {
    edge current = edgeList[i];

    if (find(current.start) != find(current.end)) {
      merge(current.start, current.end);
      edgeCount += 1;
      mstWeight += current.weight;
    }

    if (edgeCount == v - 1) {
      return mstWeight;
    }
  }

  return -1;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n, m;
  cin >> n >> m;

  edgeList.resize(m);

  for (int i = 0; i < m; i++) {
    int a, b, c;
    cin >> a >> b >> c;
    edgeList.push_back({a, b, c});
  }

  cout << MST(n);

  return 0;
}
