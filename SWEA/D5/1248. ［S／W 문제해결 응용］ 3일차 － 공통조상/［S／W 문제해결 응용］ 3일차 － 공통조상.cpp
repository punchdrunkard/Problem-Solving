#include <bits/stdc++.h>

using namespace std;

void makeTree(vector<int> &parent, vector<vector<int>> &tree, int e) {
  // 루트 노드 초기화, 루트 노드의 parent_node는 없음
  parent[1] = -1;

  for (int j = 1; j <= e; j++) {
    int parent_node, child_node;
    cin >> parent_node >> child_node;
    parent[child_node] = parent_node;

    // 자식과의 관계를 넣어줘야 함.
    tree[parent_node].push_back(child_node);
  }
}

int findDepth(vector<int> &parent, int x, int depth) {
  // x에서 부터, 부모로 계속 타고 올라간다.
  if (parent[x] == -1) {  // root인 경우
    return depth;
  }

  return findDepth(parent, parent[x], depth + 1);
}

int findLCA(vector<int> &parent, int x, int y) {
  // x와 y의 depth 를 구한다.
  int depth_x = findDepth(parent, x, 0);
  int depth_y = findDepth(parent, y, 0);

  // x와 y의 depth 중 깊은 depth를 찾고, 더 깊은 depth를 가지는 노드를 기준으로
  int base_node, other_node, base_depth, other_depth;

  if (depth_x > depth_y) {
    base_node = x;
    other_node = y;
    base_depth = depth_x;
    other_depth = depth_y;
  } else {
    base_node = y;
    other_node = x;
    base_depth = depth_y;
    other_depth = depth_x;
  }

  // baseNode가 다른 노드와 깊이가 같아질 때 까지 올라간다.
  while (parent[base_node] != -1 && base_depth > other_depth) {
    base_node = parent[base_node];
    base_depth--;
  }

  // 해당 노드와 다른 노드를에서 번갈아가면서 부모로 올라가면서,
  // 같은 노드를 만난다면 lca를 리턴한다.
  while (parent[base_node] != parent[other_node]) {
    base_node = parent[base_node];
    other_node = parent[other_node];
  }

  return parent[base_node];
}

int findTreeSize(int root, vector<vector<int>> &tree) {
  int size = 1;  // 현재 노드

  // 모든 자식 노드에 대해
  for (int i = 0; i < tree[root].size(); i++) {
    size += findTreeSize(tree[root][i], tree);
  }

  return size;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);
    
  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);
    
  int test_case;
  cin >> test_case;

  for (int i = 1; i <= test_case; i++) {
    int v, e, x, y;
    cin >> v >> e >> x >> y;

    vector<vector<int>> tree(v + 1);
    vector<int> parent(v + 1);

    makeTree(parent, tree, e);

    // 1. 두 노드 x와 y에 대해서 LCA를 찾는다.
    int lca = findLCA(parent, x, y);

    //  2. 해당 lca에서 부터 트리를 탐색하며, 트리의 크기를 구한다.
    int answer = findTreeSize(lca, tree);

    cout << "#" << i << " " << lca << " " << answer << "\n";
  }

  return 0;
}