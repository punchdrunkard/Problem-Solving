#include <bits/stdc++.h>

using namespace std;

int maxDiff;  // 최대 점수 차

vector<int> answer = {-1};
vector<int> lion(11, 0);
vector<int> peach;

// 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우,
// 가장 낮은 점수를 더 많이 맞힌 경우를 return 해주세요.
bool isLionWin() {
  for (int i = 10; i >= 0; i--) {
    if (lion[i] > answer[i])
      return true;
    else if (lion[i] < answer[i])
      return false;
  }
}

// 점수 계산
void calculateScore() {
  int lionScore = 0, peachScore = 0;

  for (int i = 0; i < 11; i++) {
    if (lion[i] > peach[i])
      lionScore += 10 - i;
    else if (peach[i] > 0)
      peachScore += 10 - i;
  }

  int diff = lionScore - peachScore;

  if (diff >= maxDiff) {
    if (maxDiff == diff && !isLionWin()) return;

    maxDiff = diff;
    answer = lion;
  }
}

// dfs
void dfs(int idx, int arrows) {
  if (idx == 11 || arrows == 0) {
    lion[10] += arrows;
    calculateScore();
    lion[10] -= arrows;
    return;
  }

  // 점수를 얻는 경우
  if (peach[idx] < arrows) {
    lion[idx] += peach[idx] + 1;
    dfs(idx + 1, arrows - peach[idx] - 1);
    lion[idx] -= peach[idx] + 1;
  }

  // 점수를 얻지 않는 경우
  dfs(idx + 1, arrows);
}

vector<int> solution(int n, vector<int> info) {
  peach = info;
  dfs(0, n);

  return answer;
}
