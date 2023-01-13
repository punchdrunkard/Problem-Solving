#include <bits/stdc++.h>

using namespace std;
int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int x;
  cin >> x;

  int i = 1;

  while (x > i) {
    x -= i;
    i++;
  }
  if (i % 2 == 1)
    cout << i + 1 - x << "/" << x;
  else
    cout << x << "/" << i + 1 - x;
}
