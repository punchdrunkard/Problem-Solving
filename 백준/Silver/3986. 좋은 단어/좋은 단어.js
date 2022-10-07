const fs = require("fs");

const stdin = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.replace("\r", ""));

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(stdin[0]);
const words = stdin.slice(1);

let count = 0;

words.map((word) => {
  const stack = [];
  for (let i = 0; i < word.length; i++) {
    if (!stack.length) {
      stack.push(word[i]);
    } else {
      const top = stack[stack.length - 1];

      if (word[i] === top) {
        stack.pop();
      } else {
        stack.push(word[i]);
      }
    }
  }

  if (!stack.length) {
    count += 1;
  }
});

console.log(count);
