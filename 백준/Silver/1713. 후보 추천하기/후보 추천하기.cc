#include <bits/stdc++.h>
#define fastio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, total;  // 사진 틀의 갯수, 전체 학생의 총 추천 횟수

void input() {
  fastio;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("input.txt", "r", stdin);
  cin >> n >> total;
}

bool compare(const tuple<int, int, int>& a, const tuple<int, int, int>& b) {
  if (get<1>(a) != get<1>(b)) {
    return get<1>(a) < get<1>(b);
  } else {
    return get<2>(a) < get<2>(b);
  }
}

bool compareByStudent(const tuple<int, int, int>& a,
                      const tuple<int, int, int>& b) {
  return get<0>(a) < get<0>(b);
}

int main() {
  input();

  vector<tuple<int, int, int>> slots;

  // 최악의 경우 : 모든 경우에 모든 슬롯을 뒤져야 할 때
  // 1000 * 20 (해당 학생 찾기) * 20(나갈 학생 찾기) = 400000 (그냥 해도 될듯!)
  for (int i = 0; i < total; i++) {
    int student;
    cin >> student;

    // 현재 학생을 벡터에서 찾기
    int idx = -1;

    for (int i = 0; i < slots.size(); i++) {
      if (get<0>(slots[i]) == student) {
        idx = i;
        break;
      }
    }

    // 1. 벡터에 없는 경우
    if (idx == -1) {
      // 백터 사이즈를 확인
      if (slots.size() == n) {  // 슬롯이 이미 꽉 차 있는 경우
        // 나갈 수 있는 후보군을 찾음 (추천 수 작을 수록, 들어온 시점 작을 수록)
        auto victim_it = min_element(slots.begin(), slots.end(), compare);
        auto victim = *victim_it;

        int slot_idx = distance(slots.begin(), victim_it);
        slots[slot_idx] = {student, 1, i};
      } else {
        // 슬롯에 자리가 있는 경우
        slots.push_back({student, 1, i});
      }
    } else {  // 2. 슬롯에 해당 학생이 존재하는 경우
      get<1>(slots[idx]) += 1;
    }
  }

  sort(slots.begin(), slots.end(), compareByStudent);

  for (auto slot : slots) {
    cout << get<0>(slot) << ' ';
  }

  return 0;
}