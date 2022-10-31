const fs = require("fs");

// 입력
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

// BFS 를 위한 큐 구현
class Node {
  constructor(value) {
    this.next = null;
    this.value = value;
  }
}

class Queue {
  constructor() {
    this.front = null;
    this.back = null;
    this.length = 0;
  }
  pop() {
    const front = this.front;
    this.front = this.front.next;
    this.length -= 1;

    if (this.length === 0) {
      this.front = null;
      this.back = null;
    }

    return front.value;
  }

  push(value) {
    const newNode = new Node(value);
    if (this.length === 0) {
      this.front = newNode;
    } else {
      this.back.next = newNode;
    }
    this.back = newNode;
    this.length += 1;
  }
}

const [M, N] = stdin[0].split(" ").map((number) => parseInt(number));

const graph = [];
const distance = Array.from(Array(N), () => new Array(M).fill(0));

stdin.slice(1).forEach((line) => {
  const temp = [...line.split(" ")];
  graph.push(temp);
});

const ripped = "1";
const unRipped = "0";

const dx = [-1, 0, 0, 1];
const dy = [0, 1, -1, 0];

const queue = new Queue();

for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    if (graph[i][j] === ripped) {
      queue.push([i, j]);
    }
    if (graph[i][j] === unRipped) {
      distance[i][j] = -1;
    }
  }
}

while (queue.length !== 0) {
  const current = queue.pop();
  for (let i = 0; i < 4; i++) {
    const nextX = current[0] + dx[i];
    const nextY = current[1] + dy[i];

    if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
      if (distance[nextX][nextY] < 0) {
        // BFS를 이용한 거리 측정
        distance[nextX][nextY] = distance[current[0]][current[1]] + 1;
        queue.push([nextX, nextY]);
      }
    }
  }
}

let answer = 0;

outerLoop: for (let i = 0; i < N; i++) {
  innerLoop: for (let j = 0; j < M; j++) {
    // 익지 않은 토마토가 있는 지 확인
    if (distance[i][j] == -1) {
      answer = -1;
      break outerLoop;
    }
    answer = Math.max(answer, distance[i][j]);
  }
}

console.log(answer);
