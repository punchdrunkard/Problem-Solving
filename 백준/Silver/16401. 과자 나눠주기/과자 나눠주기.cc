#include <bits/stdc++.h>

#define MAX 1000001
using namespace std;

int m, n;
int cookies[MAX];

bool solve(int length) {
  if (length == 0) return false;

  int count = 0;

  for (int i = 0; i < n; i++) {
    count += (cookies[i] / length);
  }

  return count >= m ? true : false;
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> m >> n;

  for (int i = 0; i < n; i++) {
    cin >> cookies[i];
  }

  int answer = 0;

  int st = 0;
  int en = *max_element(cookies, cookies + n);

  while (st <= en) {
    int mid = (st + en) / 2;

    if (solve(mid)) {
      answer = mid;
      st = mid + 1;
    } else {
      en = mid - 1;
    }
  }

  cout << answer << '\n';

  return 0;
}
