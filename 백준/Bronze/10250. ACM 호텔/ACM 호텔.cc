#include <bits/stdc++.h>

using namespace std;

int main() {
  int t;
  cin >> t;
  int h, w, n, y, x;

  while (t--) {
    cin >> h >> w >> n;

    x = n / h + 1;
    y = n % h;

    if (y == 0) {
      y = h;
      x -= 1;
    }

    printf("%d%02d\n", y, x);
  }

  return 0;
}
