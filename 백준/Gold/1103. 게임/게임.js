// input
const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const makeArray2D = (n, m, initializer) => {
  return Array.from({ length: n }, () => new Array(m).fill(initializer));
};

const [n, m] = stdin[0].split(" ").map((num) => parseInt(num));

const board = stdin.slice(1).map((row) => {
  return [...row].map((col) => {
    return col === "H" ? -1 : parseInt(col);
  });
});

const visited = makeArray2D(n, m, false);
const cache = makeArray2D(n, m, 0);

const dx = [-1, 0, 1, 0];
const dy = [0, -1, 0, 1];

let answer = 0;
let isInfinite = false;

const isInValidRange = (x, y, n, m) => {
  return x < 0 || x >= n || y < 0 || y >= m;
};

// dfs : 현재 점 x, y 에서 이동할 수 있는 횟수의 최대값을 기록한다.
const dfs = (x, y, count) => {
  if (count > answer) {
    answer = count;
  }

  cache[x][y] = count;

  for (let i = 0; i < 4; i++) {
    const num = board[x][y];

    const [nx, ny] = [x + dx[i] * num, y + dy[i] * num];

    if (isInValidRange(nx, ny, n, m) || board[nx][ny] === -1) {
      continue;
    }

    if (visited[nx][ny]) {
      isInfinite = true;
      return;
    }

    if (cache[nx][ny] > count) continue;

    visited[nx][ny] = true;
    dfs(nx, ny, count + 1);
    visited[nx][ny] = false;
  }
};

dfs(0, 0, 1);
console.log(isInfinite ? -1 : answer);
