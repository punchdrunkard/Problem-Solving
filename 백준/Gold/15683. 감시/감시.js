const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n, m] = stdin[0].split(" ").map((num) => parseInt(num));
const rooms = stdin
  .slice(1)
  .map((row) => row.split(" ").map((num) => parseInt(num)));

const MAX = 8 * 8 + 1;

// cctv의 좌표를 저장
const cctvs = [];

for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    if (rooms[i][j] === 0 || rooms[i][j] === 6) continue;
    cctvs.push([i, j]);
  }
}

// cctv 돌린 상태
const sequences = [];

const makeSequence = (cctvCount, sequence) => {
  if (sequence.length === cctvCount) {
    sequences.push([...sequence]);
    return;
  }

  for (let i = 0; i <= 3; i++) {
    sequence.push(i);
    makeSequence(cctvCount, sequence);
    sequence.pop();
  }
};

makeSequence(cctvs.length, []);

const copyRooms = () => {
  const newRooms = Array.from({ length: n }, () => new Array(m));

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      newRooms[i][j] = rooms[i][j];
    }
  }

  return newRooms;
};

const watchLine = (type, current, x, y) => {
  switch (type) {
    case "right":
      for (let i = y; i < m; i++) {
        if (current[x][i] === 6) break; // 벽
        if (current[x][i] !== 0) continue; // cctv
        current[x][i] = -1;
      }
      break;
    case "left":
      for (let i = y; i >= 0; i--) {
        if (current[x][i] === 6) break;
        if (current[x][i] !== 0) continue;
        current[x][i] = -1;
      }
      break;
    case "up":
      for (let i = x; i >= 0; i--) {
        if (current[i][y] === 6) break;
        if (current[i][y] !== 0) continue;
        current[i][y] = -1;
      }
      break;
    case "down":
      for (let i = x; i < n; i++) {
        if (current[i][y] === 6) break;
        if (current[i][y] !== 0) continue;
        current[i][y] = -1;
      }
      break;
  }
};

const watch = (sequence) => {
  const current = copyRooms();

  cctvs.forEach((cctv, i) => {
    const [x, y] = cctv;
    const kind = rooms[x][y];

    switch (kind) {
      case 1:
        if (sequence[i] === 0) {
          watchLine("right", current, x, y);
        }
        if (sequence[i] === 1) {
          watchLine("up", current, x, y);
        }
        if (sequence[i] === 2) {
          watchLine("left", current, x, y);
        }
        if (sequence[i] === 3) {
          watchLine("down", current, x, y);
        }
        break;
      case 2:
        if (sequence[i] === 0 || sequence === 2) {
          watchLine("left", current, x, y);
          watchLine("right", current, x, y);
        }
        if (sequence[i] === 1 || sequence === 3) {
          watchLine("up", current, x, y);
          watchLine("down", current, x, y);
        }
        break;
      case 3:
        if (sequence[i] === 0) {
          watchLine("up", current, x, y);
          watchLine("right", current, x, y);
        }
        if (sequence[i] === 1) {
          watchLine("up", current, x, y);
          watchLine("left", current, x, y);
        }
        if (sequence[i] === 2) {
          watchLine("left", current, x, y);
          watchLine("down", current, x, y);
        }
        if (sequence[i] === 3) {
          watchLine("down", current, x, y);
          watchLine("right", current, x, y);
        }
        break;
      case 4:
        if (sequence[i] === 0) {
          watchLine("left", current, x, y);
          watchLine("up", current, x, y);
          watchLine("right", current, x, y);
        }
        if (sequence[i] === 1) {
          watchLine("up", current, x, y);
          watchLine("left", current, x, y);
          watchLine("down", current, x, y);
        }
        if (sequence[i] === 2) {
          watchLine("left", current, x, y);
          watchLine("down", current, x, y);
          watchLine("right", current, x, y);
        }
        if (sequence[i] === 3) {
          watchLine("up", current, x, y);
          watchLine("right", current, x, y);
          watchLine("down", current, x, y);
        }
        break;
      case 5:
        watchLine("up", current, x, y);
        watchLine("down", current, x, y);
        watchLine("left", current, x, y);
        watchLine("right", current, x, y);
        break;
    }
  });

  let count = 0;

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      if (current[i][j] === 0) {
        count += 1;
      }
    }
  }

  return count;
};

const solve = () => {
  let answer = MAX;

  sequences.forEach((sequence) => {
    const current = watch(sequence);
    answer = Math.min(current, answer);
  });

  console.log(answer);
};

solve();
