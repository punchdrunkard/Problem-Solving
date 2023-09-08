#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// n의 길이를 가지는 수열 중, i 번째 수 부터 j 번째 수의 합이 m이 되는 경우의 수
int n;
long long m;

vector<int> numbers;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  numbers.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> numbers[i];
  }
}

int main() {
  input();

  // [lo, hi) 까지의 구간 합!
  int lo = 0, hi = 0, count = 0;  // index-0에서  시작
  long long sum = 0;              // 현재 합

  while (lo <= hi && hi <= n) {
    if (sum < m) {
      sum += numbers[hi++];

    } else if (sum > m) {
      sum -= numbers[lo++];

    } else {  // sum == m
      // 정답을 찾은 경우 : 더 이상 lo로 시작하는 합이 m인 부분 수열이 없다.
      count++;
      sum -= numbers[lo++];
    }
  }

  cout << count;

  return 0;
}
