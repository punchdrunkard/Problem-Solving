#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// 테이프를 앞에서부터 붙여나간다.

int n, l;               // 물이 새는 곳, 테이프의 길이
vector<int> locations;  // 물이 새는 곳의 위치

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> l;
  locations.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> locations[i];
  }
}

int main() {
  input();

  sort(locations.begin(), locations.end());

  int count = 0;
  int start = locations[0];
  int end = start + l;

  for (int i = 0; i < n; i++) {
    // 현재 붙인 테이프로 덮을 수 없는 경우
    if (start > locations[i] - 0.5 || locations[i] + 0.5 > end) {
      // count 증가 후
      count++;
      // 현재 위치에 테이프를 새로 붙인다.
      start = locations[i];
      end = start + l;
    }
  }

  cout << count;

  return 0;
}
