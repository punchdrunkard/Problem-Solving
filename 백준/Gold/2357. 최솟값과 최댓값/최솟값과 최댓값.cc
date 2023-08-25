#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define INF 1e10

using namespace std;

int n, m;
vector<int> minSegmentTree, maxSegmentTree;

// minSegmentTree와 maxSegmentTree를 init
void initTree() {
  // 원래 배열의 원소는 n ~ (2n -1) 까지 저장되어 있으므로
  // n - 1 부터 거꾸로 저장하면 된다.
  for (int i = n - 1; i > 0; i--) {
    minSegmentTree[i] = min(minSegmentTree[i << 1], minSegmentTree[i << 1 | 1]);
    maxSegmentTree[i] = max(maxSegmentTree[i << 1], maxSegmentTree[i << 1 | 1]);
  }
}

// [a, b)의 최댓값
int queryMAX(int l, int r) {
  int result = -1;

  for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
    if (l & 1) result = max(result, maxSegmentTree[l++]);
    if (r & 1) result = max(result, maxSegmentTree[--r]);
  }

  return result;
}

// [a, b)의 최솟값
int queryMin(int l, int r) {
  int result = INF;

  for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
    if (l & 1) result = min(result, minSegmentTree[l++]);
    if (r & 1) result = min(result, minSegmentTree[--r]);
  }

  return result;
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;

  minSegmentTree.resize(n << 1);
  maxSegmentTree.resize(n << 1);

  for (int i = 0; i < n; i++) {
    int num;
    cin >> num;
    minSegmentTree[i + n] = num;
    maxSegmentTree[i + n] = num;
  }

  initTree();

  for (int i = 0; i < m; i++) {
    int a, b;  // [a , b] 에서의 최소, 최댓값
    cin >> a >> b;
    cout << queryMin(a - 1, b) << " " << queryMAX(a - 1, b) << "\n";
  }

  return 0;
}
