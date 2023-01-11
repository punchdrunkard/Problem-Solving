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
