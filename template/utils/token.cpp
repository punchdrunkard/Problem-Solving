// C++ 토큰 처리 기법

#include <bits/stdc++.h>
using namespace std;

vector<string> mytoken = {"front", "cut", "double", "report", "back"};

int get_type(string w) {
  for (int i = 0; i < mytoken.size(); i++) {
    if (w == mytoken[i]) return (i);
  }
  return (-1);  // 없는 경우
}

int main() {
  string myword;
  int mytype;

  cout << "token을 함 쳐보시오: ";
  cin >> myword;
  mytype = get_type(myword);

  switch (mytype) {
    case 0:
      cout << "front가 입력";
      break;
    case 1:
      cout << "cut이   입력";
      break;
    case 2:
      cout << "double이 입력";
      break;
    case 3:
      cout << "report가 입력";
      break;
    case 4:
      cout << "back이  입력";
      break;
    case -1:
      cout << "이 뭐꼬 ?    ";
      break;
    default:
      cout << "이건 아닌데...";
  }
  return 0;
}  // end of main()  by Zoh Q.
