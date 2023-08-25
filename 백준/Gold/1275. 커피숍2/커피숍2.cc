// 커피숍
#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

typedef long long ll;

using namespace std;

int n, q;         // 수의 갯수, 턴의 갯수
vector<ll> tree;  // 세그먼트 트리

void initTree() {
  // 리프노드 바로 위에 있는 부분을 업데이트
  for (int i = n - 1; i > 0; i--) {
    tree[i] = tree[i << 1] + tree[i << 1 | 1];
  }
}

// [l, r)의 구간 합 쿼리
ll query(ll l, ll r) {
  ll result = 0;

  // 밑에서부터 위로 올라가면서 답을 찾는다.
  for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
    if (l & 1) result += tree[l++];
    if (r & 1) result += tree[--r];
  }

  return result;
}

void update(int pos, int val) {
  tree[pos + n] = val;

  for (pos += n; pos > 1; pos >>= 1) {
    tree[pos >> 1] = tree[pos] + tree[pos ^ 1];
  }
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> q;
  tree.resize(n << 1);

  for (int i = 0; i < n; i++) {
    cin >> tree[n + i];
  }

  initTree();

  for (int i = 0; i < q; i++) {
    ll x, y, a, b;
    cin >> x >> y >> a >> b;

    //  이 문제에서는 x > y인 경우 y번째 부터 x번째이다.
    if (x > y) {
      swap(x, y);
    }

    // [x, y]까지의 합을 구하여라
    cout << query(x - 1, y) << "\n";

    // a번째 수를 b로 바꾸어라
    update(a - 1, b);  // 0-indexed
  }

  return 0;
}
