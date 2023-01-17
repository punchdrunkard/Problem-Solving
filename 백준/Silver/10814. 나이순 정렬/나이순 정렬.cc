#include <bits/stdc++.h>

using namespace std;

struct member {
  int order;
  int age;
  string name;
};

bool compare(member a, member b) {
  if (a.age == b.age) return a.order < b.order;
  return a.age < b.age;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  member members[n];

  for (int i = 0; i < n; i++) {
    member m;
    cin >> m.age >> m.name;
    m.order = i;
    members[i] = m;
  }

  sort(members, members + n, compare);

  for (int i = 0; i < n; i++) {
    cout << members[i].age << ' ' << members[i].name << '\n';
  }

  return 0;
}
