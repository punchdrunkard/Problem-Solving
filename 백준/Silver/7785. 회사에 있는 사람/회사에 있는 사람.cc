#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  unordered_set<string> s;

  for (int i = 0; i < n; i++) {
    string name, state;
    cin >> name >> state;

    if (state == "enter") {
      s.insert(name);
    } else {  // leave
      s.erase(name);
    }
  }

  vector<string> names;

  for (auto name : s) {
    names.push_back(name);
  }

  sort(names.begin(), names.end(), greater<string>());
  for (auto name : names) cout << name << '\n';
   
  return 0;
}
