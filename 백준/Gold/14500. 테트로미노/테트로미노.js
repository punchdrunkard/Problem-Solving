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
let answer = 0;

// 모든 가능한 블럭
const block = [
  // 파란색
  [
    [0, 0],
    [0, 1],
    [0, 2],
    [0, 3],
  ],
  [
    [0, 0],
    [1, 0],
    [2, 0],
    [3, 0],
  ],

  // 노란색
  [
    [0, 0],
    [1, 0],
    [0, 1],
    [1, 1],
  ],

  // 주황색
  [
    [0, 0],
    [0, 1],
    [0, 2],
    [1, 2],
  ],
  [
    [0, 0],
    [0, 1],
    [0, 2],
    [1, 0],
  ],
  [
    [1, 0],
    [1, 1],
    [1, 2],
    [0, 2],
  ],
  [
    [1, 0],
    [1, 1],
    [1, 2],
    [0, 0],
  ],

  // 주황색 대칭 후 회전
  [
    [0, 1],
    [1, 1],
    [2, 1],
    [0, 0],
  ],
  [
    [0, 1],
    [1, 1],
    [2, 1],
    [2, 0],
  ],
  [
    [0, 0],
    [1, 0],
    [2, 0],
    [0, 1],
  ],
  [
    [0, 0],
    [1, 0],
    [2, 0],
    [2, 1],
  ],

  // 초록색
  [
    [0, 0],
    [0, 1],
    [1, 1],
    [1, 2],
  ],
  [
    [1, 0],
    [1, 1],
    [0, 1],
    [0, 2],
  ],
  [
    [0, 0],
    [1, 0],
    [1, 1],
    [2, 1],
  ],
  [
    [0, 1],
    [1, 1],
    [1, 0],
    [2, 0],
  ],

  // 분홍색
  [
    [0, 0],
    [0, 1],
    [0, 2],
    [1, 1],
  ],
  [
    [1, 0],
    [1, 1],
    [1, 2],
    [0, 1],
  ],
  [
    [0, 0],
    [1, 0],
    [2, 0],
    [1, 1],
  ],
  [
    [0, 1],
    [1, 1],
    [2, 1],
    [1, 0],
  ],
];

const compute = (x, y) => {
  for (let i = 0; i < 19; i++) {
    let sum = 0;
    for (let j = 0; j < 4; j++) {
      const nx = x + block[i][j][0];
      const ny = y + block[i][j][1];

      if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
        sum += board[nx][ny];
      }
    }
    answer = Math.max(answer, sum);
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
