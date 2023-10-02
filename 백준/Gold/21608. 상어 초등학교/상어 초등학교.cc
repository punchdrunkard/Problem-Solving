#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;

vector<vector<int>> students, classroom;  // 학생의 정보, 학생의 자리
vector<int> order;  // 자리 배치하는 학생들 순서

int DX[4] = {-1, 1, 0, 0};
int DY[4] = {0, 0, -1, 1};

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  students.resize(n * n + 1);
  classroom.resize(n + 1, vector<int>(n + 1));

  order.resize(n * n);

  for (int i = 0; i < n * n; i++) {
    int id, s1, s2, s3, s4;
    cin >> id >> s1 >> s2 >> s3 >> s4;

    students[id] = {s1, s2, s3, s4};
    order[i] = id;
  }
}

bool isValidRange(int x, int y) { return 1 <= x && x <= n && 1 <= y && y <= n; }

bool canSeat(int like_count, int empty_count, int current_like,
             int current_empty, int x, int y, int px, int py) {
  if (current_like > like_count) {
    return true;
  }

  if (current_like == like_count) {
    return current_empty > empty_count;
  }

  if (current_empty == empty_count) {
    return x < px;
  }

  if (x == px) {
    return y < py;
  }

  return false;
}

int getSatisfaction() {
  int satisfaction = 0;

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      int current = classroom[i][j];
      int like_count = 0;

      for (int dir = 0; dir < 4; dir++) {
        int nx = i + DX[dir];
        int ny = j + DY[dir];

        if (!isValidRange(nx, ny)) continue;

        // 좋아하는 학생 수 세기
        for (auto like_student : students[current]) {
          if (classroom[nx][ny] == like_student) {
            like_count++;
            break;
          }
        }
      }

      if (like_count > 0) {
        satisfaction += pow(10, like_count - 1);
      }
    }
  }

  return satisfaction;
}

int solve() {
  for (auto current_student : order) {
    int like_count = -1, empty_count = -1;
    int x, y;

    // 각 칸에 대해서
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (classroom[i][j] == 0) {  // 비어있으면
          int current_like = 0, current_empty = 0;

          // 해당 위치에서 4방향 탐색을 하며, 좋아하는 학생의 수와 빈 칸을 센다.
          for (int dir = 0; dir < 4; dir++) {
            int nx = i + DX[dir];
            int ny = j + DY[dir];

            if (!isValidRange(nx, ny)) continue;

            // 좋아하는 학생 수 세기
            for (auto like_student : students[current_student]) {
              if (classroom[nx][ny] == like_student) {
                current_like++;
                break;
              }
            }

            // 빈칸 세기
            if (classroom[nx][ny] == 0) {
              current_empty++;
            }
          }

          if (canSeat(like_count, empty_count, current_like, current_empty, i,
                      j, x, y)) {
            x = i;
            y = j;
            like_count = current_like;
            empty_count = current_empty;
          };
        }
      }  // end of if
    }

    classroom[x][y] = current_student;
  }

  return getSatisfaction();
}

int main() {
  input();
  cout << solve();

  return 0;
}
