#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int arr[21];
  for (int i = 1; i <= 20; i++) {
    arr[i] = i;
  }

  // 주어진 구간 뒤집기
  for (int i = 0; i < 10; i++) {
    int a, b;
    cin >> a >> b;

    reverse(arr + a, arr + b + 1);
  }

  for (int i = 1; i <= 20; i++) {
    cout << arr[i] << ' ';
  }
  return 0;
}
