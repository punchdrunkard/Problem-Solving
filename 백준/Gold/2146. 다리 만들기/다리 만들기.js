const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const graph = stdin.slice(1).map((line) => {
  return line.split(" ");
});

const makeArray = (elem) => {
  return Array.from(new Array(n), () => new Array(n).fill(elem));
};

let visited = makeArray(false);
const islands = makeArray(0);
const dist = makeArray(0);
const edgesQ = [];
const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];

let number = 1;

const isValidRange = (x, y) => {
  return 0 <= x && x < n && 0 <= y && y < n;
};

const divisionIslands = () => {
  const bfs = (x, y, number) => {
    const q = [];
    q.push([x, y]);
    islands[x][y] = number;
    visited[x][y] = true;

    while (q.length) {
      const [currentX, currentY] = q.shift();

      let isEdge = false;

      for (let dir = 0; dir < 4; dir++) {
        const [nextX, nextY] = [currentX + dx[dir], currentY + dy[dir]];

        if (!isValidRange(nextX, nextY)) continue;
        if (visited[nextX][nextY]) continue;
        if (graph[nextX][nextY] === "0") {
          isEdge = true;
          continue;
        }

        islands[nextX][nextY] = number;
        visited[nextX][nextY] = true;
        q.push([nextX, nextY]);
      }

      isEdge && edgesQ.push([currentX, currentY, number]);
    }
  };

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (graph[i][j] !== "0" && !visited[i][j]) {
        bfs(i, j, number);
        number += 1;
      }
    }
  }
};

const calculateDist = () => {
  let answer = Infinity;

  while (edgesQ.length) {
    const [currentX, currentY, number] = edgesQ.shift();

    for (let dir = 0; dir < 4; dir++) {
      const [nextX, nextY] = [currentX + dx[dir], currentY + dy[dir]];
      if (!isValidRange(nextX, nextY)) continue;

      if (islands[nextX][nextY] === 0) {
        islands[nextX][nextY] = number;
        dist[nextX][nextY] = dist[currentX][currentY] + 1;
        edgesQ.push([nextX, nextY, number]);
      } else if (islands[nextX][nextY] !== number) {
        answer = Math.min(
          answer,
          dist[currentX][currentY] + dist[nextX][nextY]
        );
      }
    }
  }

  console.log(answer);
};

divisionIslands();
calculateDist();
