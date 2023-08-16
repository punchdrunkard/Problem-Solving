const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);

const MOD = 10_007;

function calculateWaysToFillRectangle(length) {
  // dp[i] = 2 * i 일 때, 사각형을 채우는 방법 수
  const dp = Array.from({ length: length + 1 }, () => 0);
  dp[1] = 1;
  dp[2] = 3;

  for (let i = 3; i <= length; i++) {
    dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % MOD;
  }

  return dp[length];
}

console.log(calculateWaysToFillRectangle(n));
