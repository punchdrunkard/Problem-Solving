#include <bits/stdc++.h>
using namespace std;

#define MAX 201

int w[3];
bool visited[MAX][MAX][MAX];

vector<int> answers;

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  for (int i = 0; i < 3; i++) {
    cin >> w[i];
  }
}

void bfs() {
  queue<tuple<int, int, int>> q;

  q.push({0, 0, w[2]});

  while (!q.empty()) {
    int a = get<0>(q.front());
    int b = get<1>(q.front());
    int c = get<2>(q.front());

    q.pop();

    if (visited[a][b][c]) continue;
    visited[a][b][c] = true;

    if (a == 0) {
      answers.push_back(c);
    }

    // a -> b
    if (a + b > w[1]) {
      q.push({a + b - w[1], w[1], c});
    } else {
      q.push({0, a + b, c});
    }

    // a -> c
    if (a + c > w[2]) {
      q.push({a + c - w[2], b, w[2]});
    } else {
      q.push({0, b, a + c});
    }

    // b -> a
    if (b + a > w[0]) {
      q.push({w[0], b + a - w[0], c});
    } else {
      q.push({b + a, 0, c});
    }

    // b -> c
    if (b + c > w[2]) {
      q.push({a, b + c - w[2], w[2]});
    } else {
      q.push({a, 0, b + c});
    }

    // c -> a
    if (c + a > w[0]) {
      q.push({w[0], b, c + a - w[0]});
    } else {
      q.push({c + a, b, 0});
    }

    // c -> b
    if (c + b > w[1]) {
      q.push({a, w[1], c + b - w[1]});
    } else {
      q.push({a, c + b, 0});
    }
  }
}

void print() {
  sort(answers.begin(), answers.end());

  for (int i = 0; i < answers.size(); i++) {
    cout << answers[i] << ' ';
  }
}

int main() {
  input();
  bfs();
  print();

  return 0;
}
