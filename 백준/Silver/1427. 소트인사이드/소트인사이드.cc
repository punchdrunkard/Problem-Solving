#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int main() {
  string num;
  cin >> num;

  vector<int> numbers;

  for (auto n : num) {
    numbers.push_back(n - '0');
  }

  sort(numbers.begin(), numbers.end(), greater<int>());

  for (int n : numbers) {
    cout << n;
  }

  return 0;
}
