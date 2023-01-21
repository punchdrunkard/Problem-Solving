const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const op = stdin.slice(1).map((x) => parseInt(x));
const answer = [];

class Heap {
  constructor() {
    this.heap = [];
    this.size = 0;
  }

  push(x) {
    this.heap[++this.size] = x;
    let index = this.size;

    while (index != 1) {
      let parent = parseInt(index / 2);
      if (this.heap[index] >= this.heap[parent]) break;
      [this.heap[index], this.heap[parent]] = [
        this.heap[parent],
        this.heap[index],
      ];

      index = parent;
    }
  }

  pop() {
    if (this.size === 0) return;
    this.heap[1] = this.heap[this.size--];
    let index = 1;

    while (2 * index <= this.size) {
      let [leftChild, rightChild] = [2 * index, 2 * index + 1];
      let minChild = leftChild;
      if (
        rightChild <= this.size &&
        this.heap[rightChild] < this.heap[leftChild]
      ) {
        minChild = rightChild;
      }

      if (this.heap[index] < this.heap[minChild]) break;

      [this.heap[index], this.heap[minChild]] = [
        this.heap[minChild],
        this.heap[index],
      ];

      index = minChild;
    }
  }

  top() {
    if (this.size === 0) return 0;
    return this.heap[1];
  }
}

const minHeap = new Heap();

op.forEach((x) => {
  if (x === 0) {
    answer.push(minHeap.top());
    minHeap.pop();
  } else {
    minHeap.push(x);
  }
});

console.log(answer.join("\n"));
