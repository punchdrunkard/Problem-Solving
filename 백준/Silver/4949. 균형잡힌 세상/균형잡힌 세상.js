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

const lineCount = stdin.length;
const answer = [];

stdin.slice(0, lineCount - 1).map((line) => {
  const stack = [];

  for (let i = 0; i < line.length; i++) {
    // 여는 괄호인 경우 : 스택에 넣는다.
    if (line[i] === "[" || line[i] === "(") {
      stack.push(line[i]);
    } else if (line[i] === "]" || line[i] === ")") {
      // 닫는 괄호인 경우, 스택의 top 을 확인하고 top 에 있는 괄호가
      // 짝이 맞는 괄호이면 pop 한다.
      const top = stack[stack.length - 1];
      if (line[i] === "]" && top === "[") {
        stack.pop();
      } else if (line[i] === ")" && top === "(") {
        stack.pop();
      } else {
        answer.push("no");
        return;
      }
    }
  }

  if (stack.length) {
    answer.push("no");
  } else {
    answer.push("yes");
  }
});

console.log(answer.join("\n"));
