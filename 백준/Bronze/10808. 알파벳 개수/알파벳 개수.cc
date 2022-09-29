#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  string word;
  cin >> word;

  int alpha['z' + 1];
  fill(alpha, alpha + 'z' + 1, 0);

  for (auto c : word) {
    alpha[int(c)] += 1;
  }

  for (char i = 'a'; i <= 'z'; i++) {
    cout << alpha[i] << ' ';
  }
  return 0;
}
