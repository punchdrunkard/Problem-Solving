#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;  // 연습 문제에서 사용될 숫자의 갯수
vector<int> numbers;

int vectorToNumber(const vector<int>& vec) {
  int num = 0;

  for (int i = 0; i < vec.size(); i++) {
    num += (vec[i] * pow(10, vec.size() - i - 1));
  }

  return num;
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;

  while (n != 0) {
    numbers.resize(n);

    for (int i = 0; i < n; i++) {
      cin >> numbers[i];
    }

    vector<int> a, b;

    // numbers 배열을 정렬 (오름 차순)
    sort(numbers.begin(), numbers.end());

    // 자릿수에 맞게 작은 수부터 집어넣는다.
    for (int i = 0; i < n; i++) {
      // 길이가 긴쪽에 값이 작은 원소를 넣자.
      vector<int>& target = (a.size() <= b.size()) ? a : b;

      // 근데 첫 번째 원소가 0이면 안되므로
      if (target.empty() && numbers[i] == 0) {
        // numbers에서 0이 아닌 최소의 원소를 찾는다.
        auto it = find_if(numbers.begin() + i, numbers.end(),
                          [](int x) { return x != 0; });
        // it에 있는 원소와 해당 원소를 swap 한다.
        swap(*it, numbers[i]);
      }

      target.push_back(numbers[i]);
    }

    cout << vectorToNumber(a) + vectorToNumber(b) << '\n';
    cin >> n;
  }

  return 0;
}
