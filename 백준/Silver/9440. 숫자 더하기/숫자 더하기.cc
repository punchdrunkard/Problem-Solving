#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;  // 연습 문제에서 사용될 숫자의 갯수
vector<int> numbers;

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  //  freopen("sample_input.txt", "r", stdin);

  cin >> n;

  while (n != 0) {
    numbers.resize(n);

    for (int i = 0; i < n; i++) {
      cin >> numbers[i];
    }

    int answer = 0;

    vector<int> a, b;

    // numbers 배열을 정렬 (오름 차순)
    sort(numbers.begin(), numbers.end());

    // 자릿수를 결정한다.
    int a_length = n / 2, b_length = n / 2;

    if (n % 2 == 1) {
      a_length++;
    }

    // 자릿수에 맞게 작은 수부터 집어넣는다.
    for (int i = 0; i < n; i++) {
      if (a_length > b_length) {
        // 길이가 긴쪽에 작은 원소를 넣자.

        // 근데 첫 번째 원소가 0이면 안되므로
        if (a.size() == 0 && numbers[i] == 0) {
          // numbers에서 0이 아닌 최소의 원소를 찾는다.
          auto it = find_if(numbers.begin() + i, numbers.end(),
                            [](int x) { return x != 0; });
          // it에 있는 원소와 해당 원소를 swap 한다.
          swap(*it, numbers[i]);
        }

        a.push_back(numbers[i]);
        a_length--;

      } else {
        if (b.size() == 0 && numbers[i] == 0) {
          // numbers에서 0이 아닌 최소의 원소를 찾는다.
          auto it = find_if(numbers.begin() + i, numbers.end(),
                            [](int x) { return x != 0; });
          // it에 있는 원소와 해당 원소를 swap 한다.
          swap(*it, numbers[i]);
        }

        b.push_back(numbers[i]);
        b_length--;
      }
    }

    // 벡터 내부에 있는 수를 합치자.
    int num1 = 0, num2 = 0;

    for (int i = 0; i < a.size(); i++) {
      num1 += (a[i] * pow(10, a.size() - i - 1));
    }

    for (int i = 0; i < b.size(); i++) {
      num2 += (b[i] * pow(10, (b.size() - i - 1)));
    }

    cout << num1 + num2 << '\n';

    cin >> n;
  }

  return 0;
}
