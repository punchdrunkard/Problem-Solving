const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const k = parseInt(stdin[0]);
const [w, h] = stdin[1].split(" ").map((num) => parseInt(num));
const graph = stdin.slice(2).map((line) => {
  return line.split(" ").map((num) => parseInt(num));
});

const isValidRange = (x, y) => {
  return x >= 0 && x < h && y >= 0 && y < w;
};

const dxH = [-2, -2, -1, -1, 1, 1, 2, 2];
const dyH = [-1, 1, -2, 2, -2, 2, -1, 1];

const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];

const dist = Array.from({ length: h }, () =>
  Array.from({ length: w }, () => new Array(k + 1).fill(-1))
);

const q = [];
q.push([0, 0, 0]);

dist[0][0][0] = 0;

while (q.length) {
  const [x, y, count] = q.shift();
  if (x === h - 1 && y === w - 1) break;

  for (let i = 0; i < 4; i++) {
    const [nx, ny] = [x + dx[i], y + dy[i]];

    if (!isValidRange(nx, ny)) continue;
    if (dist[nx][ny][count] >= 0 || graph[nx][ny] === 1) continue;

    dist[nx][ny][count] = dist[x][y][count] + 1;

    q.push([nx, ny, count]);
  }

  if (count < k) {
    for (let i = 0; i < 8; i++) {
      const [nx, ny] = [x + dxH[i], y + dyH[i]];
      if (!isValidRange(nx, ny)) continue;
      if (dist[nx][ny][count + 1] >= 0 || graph[nx][ny] === 1) continue;

      dist[nx][ny][count + 1] = dist[x][y][count] + 1;
      q.push([nx, ny, count + 1]);
    }
  }
}

let min = Infinity;
let canGo = false;

for (let i = 0; i <= k; i++) {
  if (dist[h - 1][w - 1][i] !== -1) {
    canGo = true;
    min = Math.min(min, dist[h - 1][w - 1][i]);
    break;
  }
}

if (!canGo) console.log(-1);
else console.log(min);

