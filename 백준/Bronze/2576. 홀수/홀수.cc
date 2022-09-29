#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);
    
  vector<int> v;

  for (int i = 0; i < 7; i++) {
    int num;
    cin >> num;
    if (num % 2 == 1) v.push_back(num);
  }

  if (v.size() == 0) {
    cout << -1;
    return 0;
  }

  cout << accumulate(v.begin(), v.end(), 0) << "\n";
  cout << *min_element(v.begin(), v.end());
}
