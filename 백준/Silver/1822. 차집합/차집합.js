const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const compare = (a, b) => a - b;
const a = stdin[1]
  .split(" ")
  .map((num) => parseInt(num))
  .sort(compare);

const b = stdin[2]
  .split(" ")
  .map((num) => parseInt(num))
  .sort(compare);

const binarySearch = (target, array) => {
  let [st, en] = [0, array.length - 1];

  while (st <= en) {
    let mid = parseInt((st + en) / 2);

    if (array[mid] < target) {
      st = mid + 1;
    } else if (array[mid] > target) {
      en = mid - 1;
    } else {
      return true;
    }
  }

  return false;
};

const answers = [];

a.forEach((element) => {
  if (!binarySearch(element, b)) answers.push(element);
});

console.log(answers.length);
console.log(answers.join(" "));
