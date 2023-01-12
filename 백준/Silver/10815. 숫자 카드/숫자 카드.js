const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const cards = stdin[1]
  .split(" ")
  .map((num) => parseInt(num))
  .sort((a, b) => a - b);

const targets = stdin[3].split(" ").map((num) => parseInt(num));
const answers = [];

const binarySearch = (target, array) => {
  let [st, en] = [0, array.length - 1];

  while (st <= en) {
    let mid = parseInt((st + en) / 2);

    if (target < array[mid]) {
      en = mid - 1;
    } else if (target > array[mid]) {
      st = mid + 1;
    } else {
      return 1;
    }
  }

  return 0;
};

targets.forEach((target) => {
  answers.push(binarySearch(target, cards));
});

console.log(answers.join(" "));
