const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(input());
const cost = Array.from({ length: n }, () => input().split(' ').map(Number));

const [R, G, B] = [0, 1, 2];

const dp = Array.from({ length: n }, () => new Array(3));
[dp[0][R], dp[0][G], dp[0][B]] = cost[0];

for (let i = 1; i < n; i++) {
  dp[i][R] = Math.min(dp[i - 1][G], dp[i - 1][B]) + cost[i][R];
  dp[i][G] = Math.min(dp[i - 1][R], dp[i - 1][B]) + cost[i][G];
  dp[i][B] = Math.min(dp[i - 1][G], dp[i - 1][R]) + cost[i][B];
}

const answer = Math.min(dp[n - 1][R], dp[n - 1][G], dp[n - 1][B]);

console.log(answer);
