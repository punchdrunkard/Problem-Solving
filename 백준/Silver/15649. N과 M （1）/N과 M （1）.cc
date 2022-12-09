#include <bits/stdc++.h>
using namespace std;

int n, m;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> m;

  vector<int> numbers;
  for (int i = 1; i <= n; i++) {
    numbers.push_back(i);
  }

  do {
    for (int i = 0; i < m; i++) {
      cout << numbers[i] << ' ';
    }
    cout << '\n';
    reverse(numbers.begin() + m, numbers.end());
  } while (next_permutation(numbers.begin(), numbers.end()));

  return 0;
}
