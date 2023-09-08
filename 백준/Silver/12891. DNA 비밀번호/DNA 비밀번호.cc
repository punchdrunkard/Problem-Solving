#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// DNA 문자열 : 모든 문자열에 등장하는 문자가 A, C, G, T 인 문자열

// 부분 문자열에서 등장하는 문자의 갯수가 특정 개수 이상이여야 사용할 수 있다.
// 문자열의 순서는 상관 없고, 문자열의 구성만 신경쓰면 된다!

// 임의의 DNA 문자열 = 크기가 N인 배열
// 비밀번호로 사용할 부분 문자열의 길이 = Sliding Window size, W
// A, C, G, T가 각각 몇 번 이상 등장해야 비밀번호로 사용할 수 있는지

int s, p;  // DNA 문자열 길이, 비밀번호로 사용할 부분 문자열의 길이
string dna_str;  // dna 문자열

unordered_map<char, int> cond;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> s >> p >> dna_str;
  cin >> cond['A'] >> cond['C'] >> cond['G'] >> cond['T'];
}

bool check(unordered_map<char, int> &current) {
  return current['A'] >= cond['A'] && current['C'] >= cond['C'] &&
         current['G'] >= cond['G'] && current['T'] >= cond['T'];
}

int main() {
  input();

  int count = 0;
  unordered_map<char, int> current;  // 현재 문자열 구성

  // init
  for (int i = 0; i < p; i++) {
    current[dna_str[i]]++;
  }

  if (check(current)) count++;

  // 창문을 오른쪽으로 이동 : 이전 알파벳은 빠지고, 맨 뒤의 알파벳이 추가됨.
  for (int i = 1; i <= s - p; i++) {
    current[dna_str[i - 1]]--;
    current[dna_str[i + p - 1]]++;

    if (check(current)) count++;
  }

  cout << count;

  return 0;
}
