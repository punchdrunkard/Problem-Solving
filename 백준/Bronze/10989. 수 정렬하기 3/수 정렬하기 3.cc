#include <bits/stdc++.h>
#define MAX 10001
using namespace std;

int n, numbers[MAX];

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> n;

  for (int i = 0; i < n; i++) {
    int number;
    cin >> number;
    numbers[number] += 1;
  }

  for (int i = 0; i < MAX; i++) {
    if (numbers[i] == 0)
      continue;
    else {
      for (int j = 0; j < numbers[i]; j++) {
        cout << i << '\n';
      }
    }
  }

  return 0;
}
