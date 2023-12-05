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

  vector<long long> sumAB, sumCD;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      sumAB.push_back(A[i] + B[j]);
      sumCD.push_back(C[i] + D[j]);
    }
  }

  sort(sumAB.begin(), sumAB.end());
  sort(sumCD.begin(), sumCD.end());

  long long count = 0;

  for (int i = 0; i < int(sumCD.size()); i++) {
    count += (upper_bound(sumAB.begin(), sumAB.end(), -sumCD[i]) -
              lower_bound(sumAB.begin(), sumAB.end(), -sumCD[i]));
  }

  cout << count;

  return 0;
}
