const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const a = stdin[1]
  .split(" ")
  .map((num) => parseInt(num))
  .sort((a, b) => a - b);
const targets = stdin[3].split(" ").map((num) => parseInt(num));

const binarySearch = (target) => {
  let [st, en] = [0, n - 1];

  while (st <= en) {
    let mid = parseInt((en + st) / 2);

    if (a[mid] < target) {
      st = mid + 1;
    } else if (a[mid] > target) {
      en = mid - 1;
    } else {
      return 1;
    }
  }

  return 0;
};

console.log(targets.map((target) => binarySearch(target)).join("\n"));
