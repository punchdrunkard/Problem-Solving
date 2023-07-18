
#include <bits/stdc++.h>

using namespace std;

// m의 이진수 표현 중 끝 n 자리를 추출
int getBinaryN(int n, int m) {
  int res = 0;

  for (int i = n; i > 0; i--) {
    res = (res << 1) | ((m >> (i - 1)) & 1);
  }

  return res;
}

bool solve(int n, int m) {
  int mask = (1 << n) - 1;

  if ((mask & getBinaryN(n, m)) == mask) {
    return true;
  }

  return false;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int i = 1; i <= test_case; i++) {
    int n, m;
    cin >> n >> m;

    string answer = solve(n, m) ? "ON" : "OFF";

    cout << "#" << i << " " << answer << "\n";
  }

  return 0;
}
