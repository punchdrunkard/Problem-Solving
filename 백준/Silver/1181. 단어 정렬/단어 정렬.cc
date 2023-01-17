#include <bits/stdc++.h>

using namespace std;

bool compare(string a, string b) {
  if (a.size() == b.size()) return a < b;
  return a.size() < b.size();
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  vector<string> words(n);

  for (int i = 0; i < n; i++) {
    cin >> words[i];
  }

  sort(words.begin(), words.end(), compare);
  words.erase(unique(words.begin(), words.end()), words.end());

  for (auto word : words) {
    cout << word << '\n';
  }

  return 0;
}
