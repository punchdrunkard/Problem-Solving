#include <bits/stdc++.h>
#define MAX 100001

using namespace std;

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n, m;
  cin >> n >> m;

  unordered_map<string, int> name_to_index;

  // unordered_map을 활용할 수 있겠지만, 배열에서 인덱스로 가져오는 것이 빠르다.
  // 배열의 인덱스로 둬도 아무 문제가 없는 상황이면 배열을 사용한다.

  string index_to_name[MAX];

  for (int i = 1; i <= n; i++) {
    string name;
    cin >> name;

    index_to_name[i] = name;
    name_to_index[name] = i;
  }

  while (m--) {
    string query;

    cin >> query;

    if (isdigit(query[0])) {
      cout << index_to_name[stoi(query)] << '\n';
    } else {
      cout << name_to_index[query] << '\n';
    }
  }

  return 0;
}
