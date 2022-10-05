const fs = require("fs");

const stdin = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.replace("\r", ""));

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const N = parseInt(stdin[0]);
const towers = stdin[1].split(" ").map((tower) => parseInt(tower));
towers.unshift(0);

const dp = new Array(N + 1);
dp[1] = 0;

const stack = [];

for (let i = 2; i <= N; i++) {
  let currentHeight = towers[i];
  if (currentHeight <= towers[i - 1]) {
    dp[i] = i - 1;
    stack.push([i - 1, towers[i - 1]]);
  } else {
    while (stack.length) {
      let [maxIndex, maxHeight] = stack[stack.length - 1];
      if (currentHeight <= maxHeight) {
        dp[i] = maxIndex;
        break;
      }
      stack.pop();
    }
    if (stack.length === 0) {
      stack.push([towers[i], i]);
      dp[i] = 0;
    }
  }
}

console.log(dp.slice(1).join(" "));
