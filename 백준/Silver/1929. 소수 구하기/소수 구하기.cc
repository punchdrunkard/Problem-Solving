#include <bits/stdc++.h>

using namespace std;

int m, n;

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> m >> n;
}

void compute() {
  vector<bool> isPrime(n + 1, true);
  vector<int> primes;

  isPrime[1] = false;

  for (int i = 2; i <= i * i; i++) {
    if (!isPrime[i]) continue;

    for (int j = i * i; j <= n; j += i) {
      isPrime[j] = false;
    }
  }

  for (int i = m; i <= n; i++) {
    if (isPrime[i]) {
      cout << i << '\n';
    }
  }
}

int main() {
  input();
  compute();
  return 0;
}
