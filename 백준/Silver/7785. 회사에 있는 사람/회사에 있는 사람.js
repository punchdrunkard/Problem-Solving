const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const set = new Set();

stdin.slice(1).forEach((testCase) => {
  const [name, state] = testCase.split(" ");
  state === "enter" ? set.add(name) : set.delete(name);
});

console.log([...set].sort().reverse().join("\n"));
