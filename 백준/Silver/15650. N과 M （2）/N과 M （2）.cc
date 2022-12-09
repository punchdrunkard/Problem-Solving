#include <bits/stdc++.h>
using namespace std;

int N, M;
vector<int> choose;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> N >> M;

  for (int i = 0; i < N; ++i) choose.push_back(i < M ? 0 : 1);

  do {
    for (int i = 0; i < N; ++i)
      if (choose[i] == 0) cout << i + 1 << ' ';
    cout << '\n';
  } while (next_permutation(choose.begin(), choose.end()));
}
