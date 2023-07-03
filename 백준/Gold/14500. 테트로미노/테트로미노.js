const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n, m] = stdin[0].split(' ').map((num) => parseInt(num));
const board = stdin
  .slice(1)
  .map((row) => row.split(' ').map((num) => parseInt(num)));

let dx = [
  [0, 0, 0, 0],
  [0, 0, 1, 1],
  [0, 1, 2, 2],
  [0, 1, 1, 2],
  [0, 0, 0, 1],
];

let dy = [
  [0, 1, 2, 3],
  [0, 1, 0, 1],
  [0, 0, 0, 1],
  [0, 0, 1, 1],
  [0, 1, 2, 1],
];

// 4분면 기준으로 회전 좌표 : + +, - +, -, -, + -
let rx = [1, -1, -1, 1];
let ry = [1, 1, -1, -1];

let answer = 0;

const isValidRange = (x, y) => {
  return x >= 0 && x < n && y >= 0 && y < m;
};

const compute = (x, y) => {
  for (let k = 0; k < 4; k++) {
    for (let i = 0; i < 5; i++) {
      let count = 0;

      for (let j = 0; j < 4; j++) {
        let [nx, ny] = [x + dx[i][j] * rx[k], y + dy[i][j] * ry[k]];

        if (!isValidRange(nx, ny)) {
          count = 0;
          break;
        }

        count += board[nx][ny];
      }

      answer = Math.max(answer, count);
      count = 0;

      // 대칭시키는 경우
      for (let j = 0; j < 4; j++) {
        let [nx, ny] = [x + dy[i][j] * rx[k], y + dx[i][j] * ry[k]];

        if (!isValidRange(nx, ny)) {
          count = 0;
          break;
        }

        count += board[nx][ny];
      }

      answer = Math.max(answer, count);
    }
  }
};

const main = () => {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      compute(i, j);
    }
  }

  console.log(answer);
};

main();
