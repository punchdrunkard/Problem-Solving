#include <bits/stdc++.h>
#define MAX 1000000

using namespace std;

int n, m;
int tree[MAX];

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> m;

  for (int i = 0; i < n; i++) {
    cin >> tree[i];
  }
}

bool solve(long long h) {
  long long cur = 0;

  for (int i = 0; i < n; i++) {
    long long length = tree[i] - h;

    if (tree[i] - h > 0) {
      cur += tree[i] - h;
    }
  }

  return cur >= m;
}

int main() {
  input();

  long long st = 0, en = *max_element(tree, tree + n);

  while (st < en) {
    long long mid = (st + en + 1) / 2;

    if (solve(mid)) {
      st = mid;
    } else
      en = mid - 1;
  }

  cout << st;
  return 0;
}
