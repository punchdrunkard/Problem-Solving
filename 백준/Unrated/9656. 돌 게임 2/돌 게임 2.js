const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const dp = Array.from({ length: n });

const FIRST = true;
const SECOND = false;

dp[1] = FIRST;
dp[2] = SECOND;
dp[3] = FIRST;

// dp[i] : 마지막으로 돌을 가져가는 사람
for (let i = 4; i <= n; i++) {
  dp[i] = !(dp[i - 1] && dp[i - 3]);
}

if (dp[n] === FIRST) {
  console.log('CY');
} else {
  console.log('SK');
}
