#include <bits/stdc++.h>

#define MAX 101

using namespace std;

struct Node {
  char item;
  int left_child;   // left child의 정점 번호
  int right_child;  // right child의 정점 번호
};

vector<Node> tree;

// inorder 탐색 순서 : L, 자기자신, R
void inOrder(int node_index, int tree_size) {
  if (node_index != -1) {
    inOrder(tree[node_index].left_child, tree_size);
    cout << tree[node_index].item;
    inOrder(tree[node_index].right_child, tree_size);
  }
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("input.txt", "r", stdin);

  for (int i = 1; i <= 10; i++) {
    int n;
    cin >> n;

    tree.clear();
    tree.resize(n + 1);

    for (int i = 1; i <= n; i++) {
      int index, l_index, r_index;
      char item;

      cin >> index;
      cin >> item;
      tree[index].item = item;

      // 완전 이진 트리의 특성을 이용함
      if (i * 2 <= n) {
        cin >> l_index;
        tree[i].left_child = l_index;
      } else {
        tree[i].left_child = -1;  // 없음
      }

      if (i * 2 + 1 <= n) {
        cin >> r_index;
        tree[i].right_child = r_index;
      } else {
        tree[i].right_child = -1;
      }
    }

    cout << "#" << i << " ";
    inOrder(1, n);
    cout << "\n";
  }

  return 0;
}
