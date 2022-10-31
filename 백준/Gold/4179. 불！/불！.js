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

const [N, M] = stdin[0].split(" ").map((number) => parseInt(number));
const maze = [];
stdin.slice(1).forEach((line) => maze.push(Array.from(line)));
const distanceJ = Array.from(Array(N), () => new Array(M).fill(-1));
const distanceF = Array.from(Array(N), () => new Array(M).fill(-1));

// BFS를 위한 큐를 구현
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

    if (this.length == 0) {
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

  isEmpty() {
    return this.length === 0;
  }
}

const queueF = new Queue();
const queueJ = new Queue();

// 시작점 넣기
for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    if (maze[i][j] === "J") {
      queueJ.push([i, j]);
      distanceJ[i][j] = 0;
    }
    if (maze[i][j] === "F") {
      queueF.push([i, j]);
      distanceF[i][j] = 0;
    }
  }
}

// 각각의 대한 BFS 를 위한 상수, 함수 정의
const dx = [-1, 0, 0, 1];
const dy = [0, -1, 1, 0];

const X = 0;
const Y = 1;

const isVisited = (distance) => {
  return distance >= 0;
};

const isValidRange = (x, y) => {
  return 0 <= x && x < N && 0 <= y && y < M;
};

// 불에 대해 BFS
while (!queueF.isEmpty()) {
  const current = queueF.pop();

  for (let i = 0; i < 4; i++) {
    const nextX = current[X] + dx[i];
    const nextY = current[Y] + dy[i];

    if (isValidRange(nextX, nextY)) {
      if (!isVisited(distanceF[nextX][nextY]) && maze[nextX][nextY] !== "#") {
        queueF.push([nextX, nextY]);
        distanceF[nextX][nextY] = distanceF[current[X]][current[Y]] + 1;
      }
    }
  }
}

// 지훈이에 대한 BFS
while (!queueJ.isEmpty()) {
  const current = queueJ.pop();

  for (let i = 0; i < 4; i++) {
    const nextX = current[X] + dx[i];
    const nextY = current[Y] + dy[i];

    // 가장자리에서 탈출이 가능하다 => 범위를 벗어나면 탈출을 성공하였다.
    // 큐에 거리 순으로 들어가므로, 최초에 탈출한 시간을 출력
    if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
      console.log(distanceJ[current[X]][current[Y]] + 1);
      return;
    }

    if (isVisited(distanceJ[nextX][nextY]) || maze[nextX][nextY] === "#")
      continue;

    if (
      distanceF[nextX][nextY] !== -1 &&
      distanceF[nextX][nextY] <= distanceJ[current[X]][current[Y]] + 1
    ) {
      continue;
    }

    queueJ.push([nextX, nextY]);
    distanceJ[nextX][nextY] = distanceJ[current[X]][current[Y]] + 1;
  }
}

console.log("IMPOSSIBLE");
