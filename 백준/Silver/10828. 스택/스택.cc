#include <bits/stdc++.h>
#define MAX 10001
using namespace std;

vector<string> op = {"push", "pop", "size", "empty", "top"};

int get_type(string w) {
  for (int i = 0; i < op.size(); i++) {
    if (w == op[i]) return (i);
  }
  return (-1);  // 없는 경우
}

int n;

int stk[MAX];
int top_index = 0;

void push(int x) {
  top_index += 1;
  stk[top_index] = x;
}

int pop() {
  if (top_index == 0) {
    return -1;
  } else {
    return stk[top_index--];
  }
}

int size() { return top_index; }

int empty() {
  if (top_index == 0) {
    return 1;
  } else {
    return 0;
  }
}

int top() {
  if (top_index == 0) {
    return -1;
  } else {
    return stk[top_index];
  }
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
        cout << top() << '\n';
        break;
    }
  }

  return 0;
}
