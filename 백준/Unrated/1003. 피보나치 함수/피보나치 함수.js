const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const problems = stdin.slice(1).map((num) => parseInt(num));
const answer = [];

problems.forEach((problem) => {
  // dp[i] : i 일 때, fibo(0)과 fibo(1)이 호출된 횟수
  const dp = Array.from({ length: problem + 1 }, () => [0, 0]);

  // base case
  dp[0] = [1, 0];
  dp[1] = [0, 1];

  for (let i = 2; i <= problem; i++) {
    dp[i] = [dp[i - 1][0] + dp[i - 2][0], dp[i - 1][1] + dp[i - 2][1]];
  }

  answer.push(dp[problem].join(' '));
});

console.log(answer.join('\n'));
