#include <bits/stdc++.h>

#define ALL_BIT_ONE 1023

using namespace std;

int solve(long long n) {
  int res = 0;
  int count = 1;

  while (true) {
    long long current = count * n;
    string str = to_string(current);

    for (size_t i = 0; i < str.size(); i++) {
      res |= (1 << (str[i] - '0'));
    }

    if (res == ALL_BIT_ONE) {
      return count;
    }

    count++;
  }
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input1.txt", "r", stdin);

  int t;
  cin >> t;

  for (int i = 1; i <= t; i++) {
    long long n;
    cin >> n;

    cout << "#" << i << " " << (solve(n)) * n << "\n";
  }

  return 0;
}
