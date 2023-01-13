const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n, m] = stdin[0].split(" ").map((num) => parseInt(num));
const map = new Map();
const answer = [];

stdin.slice(1, n + 1).forEach((data) => {
  const [url, password] = data.split(" ");
  map.set(url, password);
});

stdin.slice(n + 1).forEach((query) => {
  answer.push(map.get(query));
});

console.log(answer.join("\n"));
