#include <bits/stdc++.h>

using namespace std;

// 각 지표로 변환해주는 함수
int getMapIndex(char c) {
  if (c == 'R' || c == 'T') {
    return 0;
  } else if (c == 'C' || c == 'F') {
    return 1;
  } else if (c == 'J' || c == 'M') {
    return 2;
  } else if (c == 'A' || c == 'N') {
    return 3;
  }

  return -1;
}

//  성격 유형에 따른 점수 지표
map<char, int> character[4];

// 각 지표에 맞게 성격 유형 점수를 높힌다.
void increaseScore(char c_type, int score) {
  int map_index = getMapIndex(c_type);
  character[map_index][c_type] += score;
}

// character 맵 초기화
void initMap() {
  character[0]['R'] = 0;
  character[0]['T'] = 0;
  character[1]['C'] = 0;
  character[1]['F'] = 0;
  character[2]['J'] = 0;
  character[2]['M'] = 0;
  character[3]['A'] = 0;
  character[3]['N'] = 0;
}

// 점수를 증가시킴
void calculateAnswer(vector<string> &survey, vector<int> &choices) {
  int count = survey.size();

  for (int i = 0; i < count; i++) {
    string answer = survey[i];

    if (choices[i] == 1) {  // 매우 비동의
      increaseScore(answer[0], 3);
    }

    else if (choices[i] == 2) {  // 비동의
      increaseScore(answer[0], 2);
    }

    else if (choices[i] == 3) {  // 약간 비동의
      increaseScore(answer[0], 1);
    }

    else if (choices[i] == 4) {  // 모르겠음
                                 // 아무것도 하지 않음
    }

    else if (choices[i] == 5) {  // 약간 동의
      increaseScore(answer[1], 1);
    }

    else if (choices[i] == 6) {  // 동의
      increaseScore(answer[1], 2);
    }

    else {  // 매우 동의
      increaseScore(answer[1], 3);
    }
  }
}

// 각 지표에서 점수가 높은 것을 선택한다.
string findAnswer() {
  string answer = "";

  answer += (character[0]['R'] >= character[0]['T']) ? 'R' : 'T';
  answer += (character[1]['C'] >= character[1]['F']) ? 'C' : 'F';
  answer += (character[2]['J'] >= character[2]['M']) ? 'J' : 'M';
  answer += (character[3]['A'] >= character[3]['N']) ? 'A' : 'N';

  return answer;
}

string solution(vector<string> survey, vector<int> choices) {
  initMap();
  calculateAnswer(survey, choices);

  string answer = findAnswer();
  return answer;
}
