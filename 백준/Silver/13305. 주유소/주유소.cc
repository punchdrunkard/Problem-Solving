#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define INF 1'000'000'001

using namespace std;
typedef long long ll;

int n;  // 도시의 갯수
// 두 도시를 연결하는 도로의 길이, 주유소의 리터 당 가격
vector<ll> roads, oils;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  roads.resize(n - 1);
  oils.resize(n);

  for (int i = 0; i < n - 1; i++) {
    cin >> roads[i];
  }

  for (int i = 0; i < n; i++) {
    cin >> oils[i];
  }
}

int solve() {
  // 어짜피 지금 등장한 작은값은 앞의 거리에 영향을 못미침
  // 앞에서 부터 거리를 누적하면서, 이전 값보다 작은 값이 나오면 값을 바꾸고,
  ll answer = 0;
  int min_cost = oils[0];
  ll distance = 0;

  for (int i = 1; i < n; i++) {
    if (oils[i] < min_cost) {
      distance += roads[i - 1];
      cout << "distance : " << distance << '\n';
      cout << "min_cost : " << min_cost << '\n';

      answer += (min_cost * distance);
      min_cost = oils[i];
      distance = 0;
    } else {
      distance += roads[i - 1];
    }
  }

  answer += (distance * min_cost);

  return answer;
}

int main() {
  input();
  cout << solve();

  return 0;
}
