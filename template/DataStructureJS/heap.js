class MinHeap {
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

class MaxHeap {
  constructor() {
    this.heap = [];
    this.size = 0;
  }

  push(x) {
    this.heap[++this.size] = x;
    let index = this.size;

    while (index != 1) {
      let parent = parseInt(index / 2);

      if (this.heap[parent] >= this.heap[index]) break;

      [this.heap[parent], this.heap[index]] = [
        this.heap[index],
        this.heap[parent],
      ];

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

      let maxChild = leftChild;

      if (
        rightChild <= this.size &&
        this.heap[rightChild] > this.heap[leftChild]
      ) {
        maxChild = rightChild;
      }

      if (this.heap[index] > this.heap[maxChild]) break;

      [this.heap[index], this.heap[maxChild]] = [
        this.heap[maxChild],
        this.heap[index],
      ];

      index = maxChild;
    }
  }
}
