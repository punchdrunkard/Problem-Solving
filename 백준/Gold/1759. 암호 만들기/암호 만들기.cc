#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// 사전식으로 가능성 있는 암호를 모두 출력
int l, c;
vector<char> alpha;

void input() {
  FASTIO;
  cin >> l >> c;
  alpha.resize(c);

  for (int i = 0; i < c; i++) {
    cin >> alpha[i];
  }

  // 사전 순 정렬
  sort(alpha.begin(), alpha.end());
}

// 자음 모음 카운트
pair<int, int> countVandC(vector<char> &s) {
  int v_count = 0, c_count = 0;

  for (int i = 0; i < s.size(); i++) {
    if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' ||
        s[i] == 'u') {
      v_count++;
    } else {
      c_count++;
    }
  }

  return {v_count, c_count};
}

vector<char> current;

void dfs(int idx) {
  if (current.size() == l) {
    pair<int, int> count = countVandC(current);

    if (count.first >= 1 && count.second >= 2) {
      sort(current.begin(), current.end());
      cout << string(current.begin(), current.end()) << '\n';
    }

    return;
  }

  for (int i = idx; i < c; i++) {
    current.push_back(alpha[i]);
    dfs(i + 1);
    current.pop_back();
  }
}

int main() {
  input();
  dfs(0);
  return 0;
}
