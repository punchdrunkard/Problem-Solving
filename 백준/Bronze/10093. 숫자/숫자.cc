#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  long long A, B;
  cin >> A >> B;

  if (A == B) {
    cout << 0;
    return 0;
  }

  long long smallNum, bigNum;

  if (A < B) {
    smallNum = A;
    bigNum = B;
  }

  if (A > B) {
    smallNum = B;
    bigNum = A;
  }

  cout << abs(A - B) - 1 << '\n';

  for (long long i = smallNum + 1; i < bigNum; i++) {
    cout << i << ' ';
  }

  return 0;
}
