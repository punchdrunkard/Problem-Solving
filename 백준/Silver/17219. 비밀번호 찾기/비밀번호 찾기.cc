#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n, m;
  cin >> n >> m;

  unordered_map<string, string> sites;

  while (n--) {
    string url, password;
    cin >> url >> password;
    sites[url] = password;
  }

  while (m--) {
    string query;
    cin >> query;
    cout << sites[query] << '\n';
  }

  return 0;
}
