#include <bits/stdc++.h>
#define MAX 10001
using namespace std;

vector<string> op = {"push", "pop", "size", "empty", "front", "back"};

int get_type(string w) {
  for (int i = 0; i < op.size(); i++) {
    if (w == op[i]) return (i);
  }
  return (-1);  // 없는 경우
}

int n;

int q[MAX];
int front_index;
int back_index;

void push(int x) {
  q[back_index++] = x;
}

int pop() {
  if (front_index == back_index) return -1;
  return q[front_index++];
}

int size() { return back_index - front_index; }

int empty() { return front_index == back_index; }

int front() {
  if (empty()) return -1;
  return q[front_index];
}

int back() {
  if (empty()) return -1;
  return q[back_index - 1];
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n;

  while (n--) {
    string op;
    int type;

    cin >> op;
    type = get_type(op);

    switch (type) {
      case 0:
        int x;
        cin >> x;
        push(x);
        break;
      case 1:
        cout << pop() << '\n';
        break;
      case 2:
        cout << size() << '\n';
        break;
      case 3:
        cout << empty() << '\n';
        break;
      case 4:
        cout << front() << '\n';
        break;
      case 5:
        cout << back() << '\n';
        break;
    }
  }

  return 0;
}
