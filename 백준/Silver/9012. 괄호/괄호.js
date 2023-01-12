const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const answer = [];

const solution = (brackets) => {
  const stack = [];

  for (let i = 0; i < brackets.length; i++) {
    if (brackets[i] === "(") stack.push(brackets[i]);
    else {
      if (stack.length === 0) {
        answer.push("NO");
        return;
      } else {
        stack.pop();
      }
    }
  }

  if (stack.length !== 0) answer.push("NO");
  else answer.push("YES");
};

stdin.slice(1).forEach((string) => solution(string));
console.log(answer.join("\n"));
