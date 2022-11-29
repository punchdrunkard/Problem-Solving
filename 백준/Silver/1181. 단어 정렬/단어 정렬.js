const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const words = [...new Set(stdin.slice(1))];

const compare = (word1, word2) => {
  if (word1.length === word2.length) {
    if (word1 > word2) {
      return 1;
    } else if (word1 < word2) {
      return -1;
    } else {
      return 0;
    }
  }

  return word1.length - word2.length;
};

words.sort(compare);
console.log(words.join("\n"));
