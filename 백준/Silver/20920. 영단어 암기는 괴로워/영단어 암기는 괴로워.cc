#include <bits/stdc++.h>
#define fastio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;  // 단어의 개수, 길이 기준

bool compare(const pair<string, int> &a, const pair<string, int> &b) {
  // value 기준 내림차 순
  if (a.second != b.second) {
    return a.second > b.second;
  }
  // key 의 길이 기준 내림차순
  if (a.first.length() != b.first.length()) {
    return a.first.length() > b.first.length();
  }

  // key 사전 순
  return a.first < b.first;
}

int main() {
  fastio;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("input2.txt", "r", stdin);

  map<string, int> words;

  cin >> n >> m;

  // map에 원소를 넣는데 걸리는 시간 복잡도 n * log n
  for (int i = 0; i < n; i++) {
    string word;
    cin >> word;

    // 단어의 길이 체크
    if (word.length() < m) continue;

    words[word] += 1;
  }

  vector<pair<string, int>> sorted_words(words.begin(), words.end());

  // 정렬에 대한 시간 복잡도 n * log n
  sort(sorted_words.begin(), sorted_words.end(), compare);

  for (auto word : sorted_words) {
    cout << word.first << '\n';
  }

  return 0;
}
