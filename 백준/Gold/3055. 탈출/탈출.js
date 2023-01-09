// 고슴도치는 물이 이동한 시간을 알아야 움직일 수 있다.
// 가중치가 없는 배열에서의 거리는 bfs 를 통하여 구할 수 있으므로
// 물에 대한 bfs 를 먼저 돌려서, 물의 첫 좌표에서 부터 각 좌표까지 도달할 수 있는 최단 거리 (시간)을 구하고
// 이 값을 통하여 고슴도치에 대한 bfs 를 돌린다.

// 반례 : https://www.acmicpc.net/board/view/29096 (물이 여러 개 있을 수 있음)

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

const [r, c] = stdin[0].split(" ").map((num) => parseInt(num));

const board = stdin.slice(1);

let wCoord = [];
let sCoord, dCoord;

for (let i = 0; i < r; i++) {
  for (let j = 0; j < c; j++) {
    if (board[i][j] == "S") {
      sCoord = [i, j];
    }

    if (board[i][j] == "*") {
      wCoord.push([i, j]);
    }

    if (board[i][j] == "D") {
      dCoord = [i, j];
    }
  }
}
const distS = makeArray2D(r, c, -1);
const distW = makeArray2D(r, c, -1);

const dx = [-1, 0, 1, 0];
const dy = [0, -1, 0, 1];

const isInValidRange = (x, y, r, c) => {
  return x < 0 || x >= r || y < 0 || y >= c;
};

// 물에 대한 bfs
const bfsW = () => {
  const q = [];

  for (let coord of wCoord) {
    distW[coord[0]][coord[1]] = 0;
    q.push([coord[0], coord[1]]);
  }

  while (q.length !== 0) {
    const [cx, cy] = q.shift();

    for (let dir = 0; dir < 4; dir++) {
      const [nx, ny] = [cx + dx[dir], cy + dy[dir]];

      // 가지 못하는 경우
      if (
        isInValidRange(nx, ny, r, c) ||
        board[nx][ny] === "X" ||
        board[nx][ny] === "D"
      ) {
        continue;
      }

      // 이미 방문한 경우
      if (distW[nx][ny] >= 0) continue;

      distW[nx][ny] = distW[cx][cy] + 1;
      q.push([nx, ny]);
    }
  }
};

if (wCoord.length > 0) bfsW();

// 고슴도치에 대한 bfs
const bfsS = (x, y) => {
  const q = [];
  distS[x][y] = 0;
  q.push([x, y]);

  while (q.length !== 0) {
    const [cx, cy] = q.shift();

    for (let dir = 0; dir < 4; dir++) {
      const [nx, ny] = [cx + dx[dir], cy + dy[dir]];

      // 가지 못하는 경우
      if (
        isInValidRange(nx, ny, r, c) ||
        board[nx][ny] === "X" ||
        board[nx][ny] === "*" ||
        (distW[nx][ny] !== -1 && distS[cx][cy] + 1 >= distW[nx][ny])
      ) {
        continue;
      }

      // 이미 방문한 경우
      if (distS[nx][ny] >= 0) continue;

      distS[nx][ny] = distS[cx][cy] + 1;
      q.push([nx, ny]);
    }
  }
};

bfsS(sCoord[0], sCoord[1]);

console.log(
  distS[dCoord[0]][dCoord[1]] === -1 ? "KAKTUS" : distS[dCoord[0]][dCoord[1]]
);
