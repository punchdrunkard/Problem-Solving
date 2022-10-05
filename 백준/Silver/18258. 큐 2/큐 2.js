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

function Node(value) {
  this.data = value;
  this.next = null;
}

function Queue() {
  this.front = null;
  this.rear = null;

  this.length = 0;

  this.push = (data) => {
    const newNode = new Node(data);

    if (this.length === 0) {
      this.length += 1;
      this.front = newNode;
      this.rear = newNode;
      return;
    }

    this.length += 1;
    this.rear.next = newNode;
    this.rear = newNode;
  };

  this.pop = () => {
    if (this.length === 0) {
      return -1;
    }
    const frontElement = this.front;
    const frontNextElement = this.front.next;
    this.front = frontNextElement;
    this.length -= 1;
    return frontElement.data;
  };

  this.size = () => {
    return this.length;
  };

  this.empty = () => {
    return this.length === 0 ? 1 : 0;
  };

  this.frontOperation = () => {
    if (this.empty()) return -1;
    return this.front.data;
  };

  this.back = () => {
    if (this.empty()) return -1;
    return this.rear.data;
  };
}

const queue = new Queue();
const answer = [];

operations.map((operation) => {
  const currentOperation = operation.split(" ");

  switch (currentOperation[0]) {
    case "push":
      queue.push(parseInt(currentOperation[1]));
      break;
    case "pop":
      answer.push(queue.pop());
      break;
    case "size":
      answer.push(queue.size());
      break;
    case "empty":
      answer.push(queue.empty());
      break;
    case "front":
      answer.push(queue.frontOperation());
      break;
    case "back":
      answer.push(queue.back());
      break;
  }
});

console.log(answer.join("\n"));
