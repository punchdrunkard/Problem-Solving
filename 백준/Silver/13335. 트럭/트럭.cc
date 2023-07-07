#include <bits/stdc++.h>
#define MAX 1001

using namespace std;

struct Truck {
  int weight;
  int timer;
};

int n, w, l;  // 다리를 건너는 트럭의 수, 다리의 길이, 다리의 최대 하중
Truck trucks[MAX];

void input() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  cin >> n >> w >> l;

  for (int i = 0; i < n; i++) {
    cin >> trucks[i].weight;
    trucks[i].timer = 0;
  }
}

void checkDq(deque<Truck> dq) {
  cout << "덱 상태 확인\n";

  while (!dq.empty()) {
    cout << dq.front().weight << "(타이머 : " << dq.front().timer << ") ";
    dq.pop_front();
  }

  cout << '\n';
}

void checkQ(queue<Truck> q) {
  cout << "큐 상태 확인\n";

  while (!q.empty()) {
    cout << q.front().weight << "(타이머 : " << q.front().timer << ") ";
    q.pop();
  }

  cout << '\n';
}

int solve() {
  int t = 0;

  queue<Truck> ready;
  deque<Truck> bridge;

  for (int i = 0; i < n; i++) {
    ready.push(trucks[i]);
  }

  while (!bridge.empty() || !ready.empty()) {
    // checkDq(bridge);
    // checkQ(ready);

    for (auto& truck : bridge) {
      truck.timer -= 1;
    }

    // 다리 위의 트럭을 확인하여, 건널 수 있다면 다리를 건넌다.
    if (!bridge.empty() && bridge.front().timer == 0) {
      bridge.pop_front();
    }

    // 초가 지나고, 다리의 용량이 충분하다면 원소 하나를 다리에 넣는다.
    int totalWeight = 0;  // 현재 트럭의 weight

    for (auto truck : bridge) {
      totalWeight += truck.weight;
    }

    // 트럭이 다리 위에 올라갈 수 있는 경우
    if (bridge.size() <= w && totalWeight + ready.front().weight <= l &&
        !ready.empty()) {
      Truck nextTruck = {ready.front().weight, w};
      ready.pop();
      bridge.push_back(nextTruck);
    }

    t++;
  }

  return t;
}

int main() {
  input();
  cout << solve();

  return 0;
}
