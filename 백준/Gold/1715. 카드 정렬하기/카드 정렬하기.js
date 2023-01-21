const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const cards = stdin.slice(1).map((x) => parseInt(x));

class PriorityQueue {
  constructor() {
    this.heap = [];
    this.size = 0;
  }

  push(x) {
    this.heap[++this.size] = x;
    let index = this.size;

    while (index != 1) {
      let parent = parseInt(index / 2);

      if (this.heap[parent] < this.heap[index]) break;

      [this.heap[index], this.heap[parent]] = [
        this.heap[parent],
        this.heap[index],
      ];
      index = parent;
    }
  }

  top() {
    if (this.size === 0) return 0;
    return this.heap[1];
  }

  pop() {
    this.heap[1] = this.heap[this.size--];
    let index = 1;

    // 루트에서 내려가기
    while (2 * index <= this.size) {
      let [lc, rc] = [2 * index, 2 * index + 1];
      let minChild = lc;

      if (rc <= this.size && this.heap[rc] < this.heap[lc]) {
        minChild = rc;
      }

      if (this.heap[index] < this.heap[minChild]) break;

      [this.heap[index], this.heap[minChild]] = [
        this.heap[minChild],
        this.heap[index],
      ];

      index = minChild;
    }
  }
}

const pq = new PriorityQueue();
cards.forEach((x) => {
  pq.push(x);
});

let answer = 0;

while (pq.size > 1) {
  let a = pq.top();
  pq.pop();
  let b = pq.top();
  pq.pop();

  answer += a + b;
  pq.push(a + b);
}

console.log(answer);
