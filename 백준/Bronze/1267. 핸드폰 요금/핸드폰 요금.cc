#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int N;
  cin >> N;

  int arr[N];

  for (int i = 0; i < N; i++) {
    cin >> arr[i];
  }

  int ySum = 0;
  int mSum = 0;

  for (int i = 0; i < N; i++) {
    int yQuotient = arr[i] / 29;
    int mQuotient = arr[i] / 60;

    ySum += yQuotient * 10 + 10;
    mSum += mQuotient * 15 + 15;
  }

  if (ySum < mSum) {
    cout << "Y"
         << " " << ySum;
  } else if (ySum > mSum) {
    cout << "M"
         << " " << mSum;
  } else {
    cout << "Y M"
         << " " << ySum;
  }
  return 0;
}
