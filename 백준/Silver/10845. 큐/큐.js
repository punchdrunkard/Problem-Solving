// input
const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const ops = stdin.slice(1);

class Queue {
  constructor() {
    this.queue = [];
  }

  push(x) {
    this.queue.push(x);
  }

  pop() {
    if (this.queue.length === 0) return -1;
    return this.queue.shift();
  }

  size() {
    return this.queue.length;
  }

  empty() {
    return this.queue.length === 0 ? 1 : 0;
  }

  front() {
    if (this.empty()) return -1;
    else return this.queue[0];
  }

  back() {
    if (this.empty()) return -1;
    else return this.queue[this.queue.length - 1];
  }
}

const q = new Queue();
const answers = [];

ops.forEach((op) => {
  const operation = op.split(" ");

  if (operation.length >= 2) {
    q.push(operation[1]);
  } else {
    switch (op) {
      case "pop":
        answers.push(q.pop());
        break;
      case "size":
        answers.push(q.size());
        break;
      case "empty":
        answers.push(q.empty());
        break;
      case "front":
        answers.push(q.front());
        break;
      case "back":
        answers.push(q.back());
        break;
    }
  }
});

console.log(answers.join("\n"));
