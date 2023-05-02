#include <bits/stdc++.h>
using namespace std;

class Solution {
 public:
  bool isIsomorphic(string s, string t) {
    // hash map을 사용해서, 첫 번째 s에 있는 모든 알파벳의 replacement를 저장

    // 두 개의 unordered map을 사용하여 문자열 s의 각 문자를 문자열 t의 문자로
    // 대응시키고, 이미 사용된 문자열 t의 문자를 추적한다.

    unordered_map<char, char> rep;   // replacement
    unordered_map<char, bool> used;  // 사용 여부

    // traverse all elements through the loop
    // 문자열 s의 길이만큼 반복문을 사용하여 문자열 s의 각 문자에 대해 반복한다.
    for (int idx = 0; idx < s.length(); idx++) {
      if (rep.count(s[idx])) {
        // replacement가 문자열 s의 idx 위치의 문자를 키로 포함하는 경우,
        // 해당 문자의 대응 문자가 이미 설정되어 있다는 뜻이다.
        // 이 경우, 대응 문자가 문자열 t의 idx 위치의 문자와 동일한지 확인한다.

        if (t[idx] != rep[idx]) {
          return false;
        }
      } else {
        if (used[t[idx]]) {  // 문자열 t의 idx 위치의 문자가 이미 다른 문자와
                             // 대응되었는지 확인
          return false;
        }
        // 아직 문자열 s의 idx 위치의 문자가 대응되지 않은 경우,
        // 문자열 s의 idx 위치의 문자를 문자열 t의 idx 문자와 대응시키고, 문자열
        // t의 idx 위치의 문자가 사용되었음을 표시한다.
        rep[s[idx]] = t[idx];
        used[t[idx]] = true;
      }
    }

    return true;
  }
};