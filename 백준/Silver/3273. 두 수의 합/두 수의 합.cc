#include <bits/stdc++.h>
#define MAX_N 100000
#define MAX_X 2000000

using namespace std;

int n, x;
int number[MAX_X + 1];
vector<int> inputVector;

void input() {
  cin >> n;
  for (int i = 0; i < n; i++) {
    int num;
    cin >> num;
    inputVector.push_back(num);
    number[num] = 1;
  }
  cin >> x;
}

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);
  input();

  int answer = 0;

  for (auto num : inputVector) {
    if (x - num < 0) continue;
    if (number[x - num] == 1) answer++;
  }

  cout << answer / 2;

  return 0;
}
