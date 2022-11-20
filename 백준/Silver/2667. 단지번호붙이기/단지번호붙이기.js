const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const graph = stdin.slice(1);

const visited = Array.from(new Array(n), () => new Array(n).fill(false));
const houses = Array.from(new Array(n), () => new Array(n).fill(0));

const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];

const X = 0;
const Y = 1;

let number = 1;
const counts = [];

const bfs = (x, y, number) => {
  let count = 1;
  const q = [];
  q.push([x, y]);
  visited[x][y] = true;
  houses[x][y] = number;

  while (q.length !== 0) {
    const current = q.shift();
    for (let dir = 0; dir < 4; dir++) {
      const next = [current[X] + dx[dir], current[Y] + dy[dir]];
      if (next[X] < 0 || next[X] >= n || next[Y] < 0 || next[Y] >= n) continue;
      if (visited[next[X]][next[Y]] || graph[next[X]][next[Y]] === "0")
        continue;

      visited[next[X]][next[Y]] = true;
      houses[next[X]][next[Y]] = number;
      count += 1;
      q.push(next);
    }
  }
  counts.push(count);
};

for (let i = 0; i < n; i++) {
  for (let j = 0; j < n; j++) {
    if (!visited[i][j] && graph[i][j] === "1") {
      bfs(i, j, number);
      number += 1;
    }
  }
}

const answer = [
  number - 1,
  ...counts.sort((a, b) => {
    return a - b;
  }),
];

console.log(answer.join("\n"));
