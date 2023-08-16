const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);

const dp = Array.from({ length: n + 1 }, () => -1);
const pre = Array.from({ length: n + 1 });

dp[1] = 0;
pre[1] = 0;

for (let i = 2; i <= n; i++) {
  dp[i] = dp[i - 1] + 1;
  pre[i] = i - 1;

  [2, 3].forEach((factor) => {
    if (i % factor === 0) {
      let temp = dp[i / factor] + 1;

      if (temp < dp[i]) {
        dp[i] = temp;
        pre[i] = i / factor;
      }
    }
  });
}

const route = [];
for (let cur = n; cur !== 1; cur = pre[cur]) {
  route.push(cur);
}
route.push(1);

const ans = [dp[n], route.join(' ')].join('\n');
console.log(ans);
