#include <bits/stdc++.h>
#define MAX 100001

using namespace std;

int n, s;
int arr[MAX];

int compute() {
  int len = MAX;
  int current_sum = arr[0];

  int l = 0, r = 0;

  while (l <= r && r < n) {
    if (current_sum < s) {
      r += 1;
      current_sum += arr[r];
    }

    else if (current_sum == s) {
      len = min(len, r - l + 1);
      r += 1;
      current_sum += arr[r];
    }

    else if (current_sum > s) {
      len = min(len, r - l + 1);
      current_sum -= arr[l];
      l += 1;
    }
  }

  if (len == MAX) return 0;
  return len;
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> s;

  for (int i = 0; i < n; i++) {
    cin >> arr[i];
  }

  cout << compute();

  return 0;
}
