
#include <bits/stdc++.h>
using namespace std;

int a, b, c;

long long solution(int a, int b, int c) {
  if (b == 1) {
    return a % c;
  }

  long long value = solution(a, b / 2, c);
  value = value * value % c;
  if (b % 2 == 0) return value;
  return value * a % c;
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> a >> b >> c;
  cout << solution(a, b, c);
}
