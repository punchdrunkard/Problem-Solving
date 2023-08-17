const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const numbers = stdin[1].split(' ').map((num) => parseInt(num));

// dp[i]  i번째 수를 끝으로 하는 연속합 중 가장 큰 값
const dp = Array.from({ length: n }, () => -1);
dp[0] = numbers[0];

for (let i = 1; i < n; i++) {
  dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
}

const answer = Math.max(...dp);
console.log(answer);
