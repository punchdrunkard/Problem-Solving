const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n, k] = stdin[0].split(" ").map((num) => parseInt(num));

const dp = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(0));
const DIV = 10007;

const solution = () => {
  for (let i = 1; i <= n; i++) {
    dp[i][0] = 1;
    dp[i][i] = 1;

    for (let j = 1; j < i; j++) {
      dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % DIV;
    }
  }
};

solution();
console.log(dp[n][k]);
