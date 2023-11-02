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

int getAvg() {
  // 산술 평균
  int sum = 0;

  for (auto number : numbers) {
    sum += number;
  }

  int avg = round(sum / (double)n);

  return avg;
}

int getMid() {
  // 중앙값
  int mid = numbers[int(n / 2)];

  return mid;
}

int getMode() {
  // 최빈값
  // 여러 개 있을 때는 최빈값 중 두번째로 작은 값을 출력한다.

  // 각 값마다 몇 번 등장했는지 count
  map<int, int> counter;

  for (auto number : numbers) {
    counter[number]++;
  }

  // 최대로 많이 등장한 값을 찾기
  vector<int> temp;
  int max_count = counter[numbers[0]];

  for (auto m : counter) {
    if (m.second > max_count) {
      max_count = m.second;
      temp.clear();
      temp.push_back(m.first);
    } else if (m.second == max_count) {
      temp.push_back(m.first);
    }
  }

  sort(temp.begin(), temp.end());

  if (temp.size() > 1) {
    return temp[1];
  } else {
    return temp[0];
  }
}

int getRange() {
  int range = numbers[n - 1] - numbers[0];

  return range;
}

void solve() {
  sort(numbers.begin(), numbers.end());

  int avg = getAvg();
  int mid = getMid();
  int mode = getMode();
  int range = getRange();

  cout << avg << "\n" << mid << "\n" << mode << "\n" << range;
}

int main() {
  input();
  solve();

  return 0;
}
