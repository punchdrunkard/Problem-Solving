#include <bits/stdc++.h>

using namespace std;

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int tc = 1; tc <= test_case; tc++) {
    int n;
    cin >> n;                // 연산의 수
    priority_queue<int> pq;  // 최대 힙 선언

    vector<int> answer;

    for (int i = 0; i < n; i++) {
      int oper;
      cin >> oper;

      switch (oper) {
        case 1:  // 자연수 x를 삽입
          int x;
          cin >> x;
          pq.push(x);
          break;
        case 2:  // 최대 힙의 루트 노드의 키 값을 출력하고, 해당 노드를 삭제
          if (pq.empty()) {
            answer.push_back(-1);
          } else {
            answer.push_back(pq.top());
            pq.pop();
          }

          break;
      }
    }

    cout << "#" << tc << " ";

    for (auto a : answer) {
      cout << a << ' ';
    }

    cout << '\n';
  }

  return 0;
}
