const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const t = parseInt(stdin[0]);
const numbers = stdin.slice(1).map(Number);

const answer = [];

// dp[n] : n을 1, 2, 3의 합으로 나타내는 방법의 수
const dp = Array.from({ length: 12 }, () => 0);

dp[1] = 1; // 1
dp[2] = 2; // 1 + 1, 2
dp[3] = 4; // 1 + 1 + 1, 1 + 2, 2 + 2, 3

for (let i = 4; i <= 11; i++) {
  dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
}

numbers.map((n) => answer.push(dp[n]));

console.log(answer.join('\n'));
