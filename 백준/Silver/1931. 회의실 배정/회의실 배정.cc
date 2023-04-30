#include <bits/stdc++.h>
using namespace std;

int n;
vector<pair<int, int>> meetings;

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> n;
  for (int i = 0; i < n; i++) {
    pair<int, int> temp;
    cin >> temp.second >> temp.first;
    meetings.push_back(temp);
  }
}

int main() {
  input();

  sort(meetings.begin(), meetings.end());

  int answer = 0;
  int time = 0;

  for (auto meeting : meetings) {
    if (time > meeting.second) continue;
    answer += 1;
    time = meeting.first;
  }

  cout << answer;

  return 0;
}