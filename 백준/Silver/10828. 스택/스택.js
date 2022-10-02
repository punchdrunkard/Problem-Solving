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

const N = stdin[0];
const operations = stdin.slice(1);
const answer = [];

function Stack() {
  this.dat = [];

  this.push = (element) => {
    this.dat.push(element);
  };

  this.size = () => {
    return this.dat.length;
  };

  this.pop = () => {
    if (this.size() > 0) {
      return this.dat.pop();
    } else {
      return -1;
    }
  };

  this.empty = () => {
    return this.size() === 0 ? 1 : 0;
  };

  this.top = () => {
    if (this.size() === 0) {
      return -1;
    } else {
      return this.dat[this.size() - 1];
    }
  };
}

const stack = new Stack();

operations.map((operation) => {
  op = operation.split(" ");
  if (op.length > 1) {
    // push 명령일 때
    stack.push(op[1]);
  } else if (op[0] === "pop") {
    answer.push(stack.pop());
  } else if (op[0] === "size") {
    answer.push(stack.size());
  } else if (op[0] === "empty") {
    answer.push(stack.empty());
  } else if (op[0] === "top") {
    answer.push(stack.top());
  }
});

console.log(answer.join("\n"));
