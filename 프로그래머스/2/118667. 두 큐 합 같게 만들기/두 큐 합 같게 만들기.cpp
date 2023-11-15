#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int solution(vector<int> queue1, vector<int> queue2) {
  // 연산을 위해 배열을 큐로 옮김
  queue<int> q1{{begin(queue1), end(queue1)}};
  queue<int> q2{{begin(queue2), end(queue2)}};

  ll sum1 = accumulate(begin(queue1), end(queue1), 0ll);
  ll sum2 = accumulate(begin(queue2), end(queue2), 0ll);

  // 답을 구할 수 없는 경우
  if ((sum1 + sum2) % 2 == 1) return -1;
  ll target = (sum1 + sum2) / 2;

  int totalLength = queue1.size() + queue2.size();
  int moveCount = 0;

  while (sum1 != sum2) {
    // 모든 원소를 다 옮겨봤는데도 답이 안나오면
    if (moveCount > 2 * totalLength) return -1;

    // 합이 큰 곳에 있는 원소를 빼서 작은 곳으로 넣는다.
    if (sum1 > sum2) {
      int current = q1.front();
      q1.pop();
      sum1 -= current;
      q2.push(current);
      sum2 += current;
    } else {
      int current = q2.front();
      q2.pop();
      sum2 -= current;
      q1.push(current);
      sum1 += current;
    }

    moveCount++;
  }

  return moveCount;
}