// input
const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const cards = stdin[1]
  .split(" ")
  .map((num) => parseInt(num))
  .sort((a, b) => a - b);
const targets = stdin[3].split(" ").map((num) => parseInt(num));

// cards 에서 target 보다 크거나 같은 값이 첫 번째로 나타나는 위치
const lowerBound = (target, cards, size) => {
  let [st, en] = [0, size];

  while (st < en) {
    let mid = parseInt((st + en) / 2);

    if (cards[mid] >= target) {
      en = mid;
    } else {
      st = mid + 1;
    }
  }

  return st;
};

// cards 에서 target 보다 큰 값이 첫 번째로 나타나는 위치
const upperBound = (target, cards, size) => {
  let [st, en] = [0, size];

  while (st < en) {
    let mid = parseInt((st + en) / 2);

    if (cards[mid] > target) {
      en = mid;
    } else {
      st = mid + 1;
    }
  }

  return st;
};

console.log(
  targets
    .map(
      (target) => upperBound(target, cards, n) - lowerBound(target, cards, n)
    )
    .join(" ")
);
