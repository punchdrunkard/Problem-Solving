const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const op = stdin.slice(1).map((x) => parseInt(x));
const answer = [];

const isBigger = (a, b) => {
  const [absA, absB] = [Math.abs(a), Math.abs(b)];
  return absA === absB ? a > b : absA > absB;
};

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

      if (isBigger(this.heap[parent], this.heap[index])) {
        [this.heap[parent], this.heap[index]] = [
          this.heap[index],
          this.heap[parent],
        ];
      }

      index = parent;
    }
  }

  top() {
    if (this.size === 0) return 0;
    return this.heap[1];
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
        isBigger(this.heap[leftChild], this.heap[rightChild])
      ) {
        minChild = rightChild;
      }

      if (isBigger(this.heap[minChild], this.heap[index])) break;

      [this.heap[index], this.heap[minChild]] = [
        this.heap[minChild],
        this.heap[index],
      ];
      index = minChild;
    }
  }
}

const absHeap = new Heap();

op.forEach((x) => {
  if (x === 0) {
    answer.push(absHeap.top());
    absHeap.pop();
  } else {
    absHeap.push(x);
  }
});

console.log(answer.join("\n"));
