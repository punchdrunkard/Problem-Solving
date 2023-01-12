#include <bits/stdc++.h>
#define MAX 4000

using namespace std;

int n;
int A[MAX], B[MAX], C[MAX], D[MAX];

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n;

  for (int i = 0; i < n; i++) {
    cin >> A[i] >> B[i] >> C[i] >> D[i];
  }
}

int main() {
  input();

  vector<long long> two;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      two.push_back(A[i] + B[j]);
    }
  }

  sort(two.begin(), two.end());

  long long count = 0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      long long target = -(C[i] + D[j]);

      count += (upper_bound(two.begin(), two.end(), target) -
                lower_bound(two.begin(), two.end(), target));
      ;
    }
  }

  cout << count;

  return 0;
}
