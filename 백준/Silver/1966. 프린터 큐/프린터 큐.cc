#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int main() {
  FASTIO;

  int test_case;
  cin >> test_case;

  for (int tc = 1; tc <= test_case; tc++) {
    // n개의 문서 중, 현재 큐에 m 번째에 있는 문서가 몇 번째로 인쇄되는가?
    int n, m;
    cin >> n >> m;

    int count = 0;
    queue<pair<int, int>> q;

    // seq[i] := 현재 큐에서 i번째 있는 문서가 몇 번째로 인쇄되는가?
    vector<int> seq(n);

    for (int i = 0; i < n; i++) {
      int d;
      cin >> d;
      q.push({d, i});
    }

    while (!q.empty()) {
      pair<int, int> front = q.front();
      q.pop();

      bool is_current_back = false;

      queue<pair<int, int>> temp = q;  // 임시 큐

      while (!temp.empty()) {
        pair<int, int> current = temp.front();

        if (current.first > front.first) {  // 재배치 해야 한다.

          q.push(front);
          is_current_back = true;
          break;
        }

        temp.pop();
      }

      // 바로 인쇄를 한다.
      if (!is_current_back) {
        count++;
        seq[front.second] = count;
      }
    }

    cout << seq[m] << '\n';
  }

  return 0;
}
