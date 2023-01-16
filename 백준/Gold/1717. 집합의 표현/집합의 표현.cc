#include <bits/stdc++.h>
#define MAX 1000001

using namespace std;

int n, m;
int parent[MAX];

int find(int a) {
  if (parent[a] == a) {
    return a;
  }

  int result = find(parent[a]);
  parent[a] = result;
  return result;
}

void merge(int a, int b) {
  a = find(a);
  b = find(b);

  if (a == b) {
    return;
  }

  parent[a] = b;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> m;

  for (int i = 0; i <= n; i++) {
    parent[i] = i;
  }

  while (m--) {
    int op, a, b;
    cin >> op >> a >> b;

    if (op == 0) {  
      merge(a, b);
    } else {
      find(a) == find(b) ? cout << "YES\n" : cout << "NO\n";
    }
  }

  return 0;
}
