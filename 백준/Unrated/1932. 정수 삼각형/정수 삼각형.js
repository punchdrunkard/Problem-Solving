const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const table = stdin
  .slice(1)
  .map((line) => line.split(' ').map((el) => parseInt(el)));

// dp[i][j] = i행 j열까지 내려올 때 최대값
const dp = Array.from({ length: n }, () => Array(n).fill(0));
// base condition
dp[0][0] = table[0][0];
let answer = table[0][0];

for (let i = 0; i < n; i++) {
  for (let j = 0; j < i + 1; j++) {
    if (i === 0 && j === 0) continue;

    dp[i][j] = dp[i - 1][j] + table[i][j];

    if (j > 0) {
      dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + table[i][j]);
    }

    answer = Math.max(answer, dp[i][j]);
  }
}

console.log(answer);
