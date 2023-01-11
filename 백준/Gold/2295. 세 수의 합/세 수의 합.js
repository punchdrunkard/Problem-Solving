// input
const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const compare = (a, b) => a - b;

const n = parseInt(stdin[0]);
const set = stdin
  .slice(1)
  .map((num) => parseInt(num))
  .sort(compare);

const two = [];

const binarySearch = (target, array) => {
  let [st, en] = [0, array.length - 1];

  while (st <= en) {
    let mid = parseInt((st + en) / 2);

    if (array[mid] > target) {
      en = mid - 1;
    } else if (array[mid] < target) {
      st = mid + 1;
    } else {
      return true;
    }
  }

  return false;
};

for (let i = 0; i < n; i++) {
  for (let j = i; j < n; j++) {
    two.push(set[i] + set[j]);
  }
}

two.sort(compare);

for (i = n - 1; i > 0; i--) {
  for (j = 0; j < i; j++) {
    if (binarySearch(set[i] - set[j], two)) {
      console.log(set[i]);
      return;
    }
  }
}
