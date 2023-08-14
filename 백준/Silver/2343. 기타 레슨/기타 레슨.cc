#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

typedef long long ll;
const ll MAX = ll(100000000);

ll n, m;
vector<ll> videos;

// 길이가 k인 불루레이로 모든 영상을 녹화할 수 있는가?
bool check(ll k) {
  ll video_count = 0;  // 블루레이의 갯수
  ll sum = 0;          // 한 블루레이에 들어가는 누적 시간

  for (auto &video : videos) {  // 각 비디오에 대해서
    if ((sum + video) > k) {    // 용량을 넘어선다면
      video_count += 1;
      sum = 0;  // 누적 시간을 초기화
    }

    // 예외 처리, 비디오가 블루레이에 들어가지 못하는 경우
    if (sum + video > k) return false;

    sum += video;
  }

  return video_count < m;  // m개이 블루레이에 녹화할 수 이쓴지
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  videos.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> videos[i];
  }

  // 이분 탐색을 하면서 최소의 k를 구해보자.
  ll lo = 0, hi = MAX;

  while (lo + 1 < hi) {
    ll mid = (lo + hi) / 2;

    if (!check(mid)) {
      lo = mid;
    } else {
      hi = mid;
    }
  }

  cout << hi;

  return 0;
}
