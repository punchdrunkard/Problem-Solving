const fs = require("fs");

const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const points = stdin
  .slice(1)
  .map((row) => row.split(" ").map((num) => parseInt(num)));

const compare = (a, b) => {
  if (a[1] === b[1]) return a[0] - b[0];
  return a[1] - b[1];
};

console.log(
  points
    .sort(compare)
    .map((row) => row.join(" "))
    .join("\n")
);
