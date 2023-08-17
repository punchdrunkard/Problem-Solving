const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const MAX = 101;

const dp = Array.from({ length: MAX }, () => -1); // 1-based

const calculate = () => {
  [dp[1], dp[2], dp[3], dp[4], dp[5]] = [1, 1, 1, 2, 2];

  for (let i = 6; i <= 100; i++) {
    dp[i] = dp[i - 5] + dp[i - 1];
  }
};

calculate();

const answer = [];
stdin.slice(1).forEach((n) => answer.push(dp[n]));
console.log(answer.join('\n'));
