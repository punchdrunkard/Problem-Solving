const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

stdin.pop();

const answer = [];

stdin.forEach((number) => {
  if (number === number.split("").reverse().join("")) {
    answer.push("yes");
  } else {
    answer.push("no");
  }
});

console.log(answer.join("\n"));
