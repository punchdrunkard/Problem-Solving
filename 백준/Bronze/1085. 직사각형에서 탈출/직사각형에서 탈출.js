const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [x, y, w, h] = stdin[0].split(" ").map((num) => parseInt(num));
const dist = [x, y, h - y, w - x];

const compare = (num1, num2) => {
  return num1 - num2;
};

dist.sort(compare);
console.log(dist[0]);
