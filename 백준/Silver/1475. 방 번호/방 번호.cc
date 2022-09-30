#include <bits/stdc++.h>
using namespace std;

int number[10];

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  string room;
  cin >> room;

  for (char c : room) {
    number[c - '0'] += 1;
  }

  int sum_69 = number[6] + number[9];

  if (sum_69 % 2 == 1) {
    number[6] = int(sum_69 / 2) + 1;
    number[9] = int(sum_69 / 2) - 1;
  } else {
    number[6] = sum_69 / 2;
    number[9] = sum_69 / 2;
  }

  cout << *max_element(number, number + 9);

  return 0;
}
