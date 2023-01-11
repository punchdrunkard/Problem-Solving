#include <bits/stdc++.h>
#define MAX 1000000

using namespace std;

int n;
int x[MAX];
vector<int> sorted;

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n;
  for (int i = 0; i < n; i++) {
    cin >> x[i];
    sorted.push_back(x[i]);
  }
}

void compute() {
  sort(sorted.begin(), sorted.end());
  sorted.erase(unique(sorted.begin(), sorted.end()), sorted.end());

  for (int i = 0; i < n; i++) {
    cout << lower_bound(sorted.begin(), sorted.end(), x[i]) - sorted.begin()
         << ' ';
  }
}

int main() {
  input();
  compute();
  return 0;
}
