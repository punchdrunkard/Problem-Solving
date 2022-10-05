const fs = require("fs");

const stdin = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.replace("\r", ""))
  .map((number) => parseInt(number));

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = stdin[0];
const numbers = stdin.splice(1);

let index = 0;
const stack = [];
const answer = [];

let i = 1;
let isImpossible = false;

while (index < numbers.length) {
  let currentNumber = numbers[index];
  let stackTop = stack[stack.length - 1];

  if (stack.length === 0) {
    answer.push("+");
    stack.push(i);
    i += 1;
    continue;
  }

  if (stackTop === currentNumber) {
    answer.push("-");
    stack.pop();
    index += 1;
  } else if (stackTop < currentNumber) {
    answer.push("+");
    stack.push(i);
    i += 1;
  } else if (stackTop > currentNumber) {
    isImpossible = true;
    break;
  }
}

isImpossible ? console.log("NO") : console.log(answer.join("\n"));
