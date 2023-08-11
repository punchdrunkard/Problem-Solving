#include <bits/stdc++.h>
#define fastio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

void print(int mask) {
  const int NUM_SEGMENTS = 4;
  const int BITS_PER_SEGMENT = 8;
  const int INITIAL_SHIFT = 24;

  int shift = INITIAL_SHIFT;
  for (int i = 0; i < NUM_SEGMENTS; i++, shift -= BITS_PER_SEGMENT) {
    int segment = (mask >> shift) & ((1 << BITS_PER_SEGMENT) - 1);
    cout << segment << (i != NUM_SEGMENTS - 1 ? '.' : '\n');
  }
}

int main() {
  fastio;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("input.txt", "r", stdin);

  int n;
  cin >> n;

  vector<int> ip(n);

  for (int i = 0; i < n; i++) {
    string ip_string;
    cin >> ip_string;
    stringstream ss(ip_string);
    string segment;

    for (int j = 0; j < 4; j++) {
      getline(ss, segment, '.');
      int a = stoi(segment);
      ip[i] <<= 8;
      ip[i] |= a;
    }
  }

  int subnet_mask = 0;

  // 처음으로 달라지는 위치를 찾기
  for (int i = 31; i >= 0; i--) {
    int bitMask = 1 << i;
    bool isDifferent = false;

    for (int j = 1; j < n; j++) {
      if ((ip[0] & bitMask) != (ip[j] & bitMask)) {
        isDifferent = true;
        break;
      }
    }

    if (isDifferent) {
      break;
    } else {
      subnet_mask |= bitMask;
    }
  }

  print(ip[0] & subnet_mask);  // 네트워크 주소
  print(subnet_mask);          // 서브넷 주소

  return 0;
}
