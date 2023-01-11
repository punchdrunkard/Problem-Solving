#include <bits/stdc++.h>

#define MAX 500000

using namespace std;

int n, m;
int cards[MAX], targets[MAX];

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n;

  for (int i = 0; i < n; i++) {
    cin >> cards[i];
  }

  sort(cards, cards + n);

  cin >> m;

  for (int i = 0; i < m; i++) {
    cin >> targets[i];
  }
}

void compute() {
  for (int i = 0; i < m; i++) {
    cout << upper_bound(cards, cards + n, targets[i]) -
                lower_bound(cards, cards + n, targets[i])
         << ' ';
  }
}

int main() {
  input();
  compute();
  return 0;
}
