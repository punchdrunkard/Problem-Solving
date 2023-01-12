const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [, m] = stdin[0].split(" ").map((num) => parseInt(num));
const trees = stdin[1].split(" ").map((num) => parseInt(num));

const solve = (h) => {
  let cur = 0;

  trees.forEach((tree) => {
    if (tree - h > 0) cur += tree - h;
  });

  return cur >= m;
};

let [st, en] = [0, Math.max(...trees)];

while (st < en) {
  let mid = parseInt((st + en + 1) / 2);

  if (solve(mid)) {
    st = mid;
  } else {
    en = mid - 1;
  }
}

console.log(st);
