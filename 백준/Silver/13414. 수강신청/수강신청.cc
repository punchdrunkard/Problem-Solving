#include <bits/stdc++.h>
using namespace std;

bool compare(pair<string, int> &a, pair<string, int> &b) {
  return a.second < b.second;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int k, l;
  cin >> k >> l;

  unordered_map<string, int> students_list;

  for (int i = 1; i <= l; i++) {
    string student_number;
    cin >> student_number;

    if (students_list.find(student_number) != students_list.end()) {
      students_list.erase(student_number);
    }

    students_list[student_number] = i;
  }

  vector<pair<string, int>> student_v(students_list.begin(),
                                      students_list.end());

  sort(student_v.begin(), student_v.end(), compare);

  // 수강 신청 가능 인원이 수강 신청 한 인원보다 클 수도 있음.
  int total = min(k, int(student_v.size()));

  for (int i = 0; i < total; i++) {
    cout << student_v[i].first << '\n';
  }

  return 0;
}
