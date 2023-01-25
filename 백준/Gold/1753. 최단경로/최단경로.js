const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [v] = stdin[0].split(" ").map((num) => parseInt(num));
const start = parseInt(stdin[1]);

// [weight, vertex]
const adj = Array.from({ length: v + 1 }, () => new Array());

stdin.slice(2).forEach((row) => {
  const [u, v, w] = row.split(" ").map((num) => parseInt(num));
  adj[u].push([w, v]);
});

const weight = 0;
const vertex = 1;
const INF = 1e9;

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

      if (this.heap[index][weight] >= this.heap[parent][weight]) break;

      [this.heap[index], this.heap[parent]] = [
        this.heap[parent],
        this.heap[index],
      ];

      index = parent;
    }
  }

  top() {
    return this.heap[1];
  }

  pop() {
    if (this.empty()) return;
    this.heap[1] = this.heap[this.size--];
    let index = 1;

    while (2 * index <= this.size) {
      let [leftChildren, rightChildren] = [index * 2, index * 2 + 1];
      let minChildren = leftChildren;

      if (
        rightChildren <= this.size &&
        this.heap[rightChildren][weight] < this.heap[leftChildren][weight]
      ) {
        minChildren = rightChildren;
      }

      if (this.heap[index][weight] <= this.heap[minChildren][weight]) break;

      [this.heap[index], this.heap[minChildren]] = [
        this.heap[minChildren],
        this.heap[index],
      ];

      index = minChildren;
    }
  }

  empty() {
    return this.size === 0;
  }
}

const djik = (v, start) => {
  const dist = Array.from({ length: v + 1 }, () => INF);
  const pq = new PriorityQueue();

  dist[start] = 0;

  pq.push([dist[start], start]);

  while (!pq.empty()) {
    let [currentWeight, currentVertex] = pq.top();

    pq.pop();

    if (dist[currentVertex] !== currentWeight) continue;

    for (let next of adj[currentVertex]) {
      const [nextWeight, nextVertex] = next;

      if (dist[nextVertex] > dist[currentVertex] + nextWeight) {
        dist[nextVertex] = dist[currentVertex] + nextWeight;
        pq.push([dist[nextVertex], nextVertex]);
      }
    }
  }

  console.log(
    dist
      .slice(1)
      .map((value) => (value === INF ? "INF" : value))
      .join("\n")
  );
};

djik(v, start);
