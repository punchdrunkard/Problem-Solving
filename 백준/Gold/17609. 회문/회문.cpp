#include <bits/stdc++.h>

using namespace std;

enum PalindromeType { IS_PALIN, QUASI_PALIN, NOT_PALIN };

bool isValidRange(int left, int right) { return left <= right; }

bool isPalindrome(int left, int right, const string &word) {
  while (isValidRange(left, right)) {
    if (word[left] != word[right]) {
      return false;
    }
    left += 1;
    right -= 1;
  }
  return true;
}

PalindromeType checkPalindrome(const string &word) {
  int left = 0;
  int right = word.size() - 1;
  bool isOneCharDeleted = false;

  while (isValidRange(left, right)) {
    if (word[left] == word[right]) {
      left += 1;
      right -= 1;
    } else {
      if (isOneCharDeleted) {
        return NOT_PALIN;
      } else {
        if (isPalindrome(left + 1, right, word)) {
          left += 1;
          isOneCharDeleted = true;
          continue;
        } else if (isPalindrome(left, right - 1, word)) {
          right -= 1;
          isOneCharDeleted = true;
          continue;
        } else {
          return NOT_PALIN;
        }
      }
    }
  }

  return isOneCharDeleted ? QUASI_PALIN : IS_PALIN;
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  for (int i = 0; i < n; i++) {
    string word;
    cin >> word;

    cout << checkPalindrome(word) << '\n';
  }
  return 0;
}