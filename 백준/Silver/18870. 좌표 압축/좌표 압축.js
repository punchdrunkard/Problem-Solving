// input
const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const x = stdin[1].split(" ").map((num) => parseInt(num));
const originalXs = [...x];

x.sort((a, b) => a - b);
const sorted = [...new Set(x)];

const lowerBound = (array, target) => {
  let [st, en] = [0, array.length];

  while (st < en) {
    let mid = parseInt((st + en) / 2);

    if (array[mid] >= target) {
      en = mid;
    } else {
      st = mid + 1;
    }
  }

  return st;
};

console.log(
  originalXs.map((originalX) => lowerBound(sorted, originalX)).join(" ")
);
