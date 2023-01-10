#include <bits/stdc++.h>
using namespace std;

void update(int n, long long v, vector<long long> &tree, int PIV) {
  n += PIV;
  tree[n] = v;
  n /= 2;
  while (n > 0) {
    // 조상 = 왼쪽 자식 + 오른쪽 자식
    tree[n] = tree[n * 2] + tree[n * 2 + 1];
    n /= 2;  // 윗 조상으로 옮김
  }
}

long long query(int l, int r, vector<long long> &tree, int PIV) {
  long long ret = 0;
  l += PIV, r += PIV;
  while (l <= r) {
    if (l % 2 == 1) ret += tree[l++];  // l이 홀수 일 때
    if (r % 2 == 0) ret += tree[r--];  // r이 짝수 일 떄
    l /= 2, r /= 2;
  }
  return ret;
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n, m, k;
  cin >> n >> m >> k;

  // PIV 는 n 을 넘는 2의 배수이다.
  int PIV = 1;
  while (PIV <= n) PIV *= 2;

  vector<long long> tree(PIV * 2);
  fill(tree.begin(), tree.end(), 0);

  // init
  for (int i = 1; i <= n; i++) {
    long long num;
    cin >> num;
    update(i, num, tree, PIV);
  }

  int a, b;
  long long c;

  for (int i = 0; i < m + k; i++) {
    cin >> a >> b >> c;

    if (a == 1) {
      update(b, c, tree, PIV);
    } else {
      cout << query(b, (int)c, tree, PIV) << '\n';
    }
  }

  return 0;
}
