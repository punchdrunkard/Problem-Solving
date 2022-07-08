const inputData = () => {
  const input = require("fs");
  const N = parseInt(input.readFileSync("/dev/stdin"));

  return N;
};

const N = inputData();
let count = 0;
const cols = new Array(N + 1);

const isSameDiagonal = (x1, x2) => {
  return Math.abs(x1 - x2) === Math.abs(cols[x1] - cols[x2]);
};

const isNonPromise = (level) => {
  for (i = 1; i < level; i++) {
    if (cols[i] === cols[level] || isSameDiagonal(level, i)) {
      return true;
    }
  }

  return false;
};

const queens = (level) => {
  if (level === N) {
    count += 1;

    return true;
  } else {
    for (let i = 1; i <= N; i++) {
      cols[level + 1] = i;
      if (!isNonPromise(level + 1)) queens(level + 1);
    }
  }

  return false;
};

const main = () => {
  queens(0);
  console.log(count);
};

main();
