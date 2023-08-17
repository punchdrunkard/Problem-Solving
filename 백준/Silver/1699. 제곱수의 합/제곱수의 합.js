const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

// 주어진 자연수를 제곱수의 합으로 나타낼 때, 그 제곱수 항의 최소 개수
const n = parseInt(stdin[0]);
const dp = Array.from({ length: n + 1 }, () => Infinity);

// init dp array
for (let i = 1; i * i <= n; i++) {
  dp[i * i] = 1;
}

for (let i = 2; i <= n; i++) {
  for (let j = 1; j * j <= i; j++) {
    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
  }
}

console.log(dp[n]);
