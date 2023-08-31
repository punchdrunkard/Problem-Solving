#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;
vector<int> numbers;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  numbers.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> numbers[i];
  }
}

int main() {
  input();

  // 부호가 같은 것 끼리 곱하면 양수다.
  // 부호가 같은 것 끼리 곱해야 하고, 곱할 때는 절댓값이 커야 한다.

  // 배열을 정렬하면, 음수는 앞에 양수는 뒤에 오는데
  // 음수는 *앞으로 갈 수록 절댓값이 큰 수* -> 앞에서 부터 묶는다.
  // 양수는 *뒤로 갈 수록 절댓값이 큰 수* -> 뒤에서 부터 묶는다.
  long long answer = 0;

  sort(numbers.begin(), numbers.end());

  // 양수
  for (int i = n - 1; i >= 0;) {
    // 더 이상 양수가 아니면.. break;
    if (numbers[i] <= 0) break;
    if (numbers[i - 1] > 1 && i - 1 >= 0) {
      answer += (numbers[i] * numbers[i - 1]);
      i -= 2;
    } else {  // 1인 경우에는 그냥 더한다. (0이 포함되더라도 어짜피 아무 영향을
              // 미치지 않아서 신경안써도 됨.)
      answer += numbers[i];
      i -= 1;
    }
  }

  // 음수
  for (int i = 0; i < n;) {
    if (numbers[i] > 0) break;

    if (numbers[i + 1] <= 0 && i + 1 < n) {
      answer += (numbers[i] * numbers[i + 1]);
      i += 2;
    } else {
      answer += numbers[i];
      i += 1;
    }
  }

  cout << answer;

  return 0;
}
