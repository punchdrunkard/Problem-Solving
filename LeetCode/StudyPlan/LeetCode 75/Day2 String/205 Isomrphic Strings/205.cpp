// Runtime 17ms, Beats 9.45 %
// Memory 7.5 MB, Beats 5.42%

#include <bits/stdc++.h>
using namespace std;

class Solution {
 public:
  bool isIsomorphic(string s, string t) {
    int len = s.size();  // s.length == t.length

    map<char, char> sChar, tChar;

    int s_charNum = 0;
    int t_charNum = 0;
    string sChar_to_num, tChar_to_num;

    for (int i = 0; i < len; i++) {
      if (sChar.find(s[i]) == sChar.end()) {
        s_charNum++;
        sChar.insert({s[i], s_charNum + '0'});
      }

      sChar_to_num += sChar[s[i]];

      if (tChar.find(t[i]) == tChar.end()) {
        t_charNum++;
        tChar.insert({t[i], t_charNum + '0'});
      }

      tChar_to_num += tChar[t[i]];
    }

    return sChar_to_num == tChar_to_num;
  }
};