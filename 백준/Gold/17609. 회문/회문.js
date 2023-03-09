const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const answer = [];

const [IS_PALIN, QUASI_PALIN, NOT_PALIN] = [0, 1, 2];

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const isValidRange = (left, right) => {
  return left <= right;
};

const isPalindrome = (left, right, word) => {
  while (isValidRange(left, right)) {
    if (word[left] !== word[right]) {
      return false;
    }
    left += 1;
    right -= 1;
  }
  return true;
};

const checkPalindrome = (word) => {
  let [left, right] = [0, word.length - 1];
  let isOneCharDeleted = false;

  while (isValidRange(left, right)) {
    if (word[left] === word[right]) {
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
};

stdin.slice(1).forEach((word) => {
  answer.push(checkPalindrome(word));
});

console.log(answer.join("\n"));
