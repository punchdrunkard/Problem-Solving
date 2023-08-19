const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// N이 150만이라서, O(N) 이 필요한 듯! => dp
// N + 1 이 되는 날에 퇴사한다.
const n = parseInt(stdin[0]);

const [t, p] = stdin
  .slice(1)
  .map((row) => row.split(' ').map(Number))
  .reduce(
    (acc, [t_i, p_i]) => {
      acc[0].push(t_i);
      acc[1].push(p_i);

      return acc;
    },
    [[], []],
  );

// dp[i] : i일차에 상담을 하면 얻을 수 있는 최대 이익
const dp = Array.from({ length: n + 1 }, () => 0);

// init
dp[n - 1] = n - 1 + t[n - 1] > n ? 0 : p[n - 1];

for (let i = n - 2; i >= 0; i--) {
  const next = i + t[i];

  // 해당 날짜에 상담을 할 수 있는 경우
  if (next <= n) {
    dp[i] = Math.max(dp[i + 1], p[i] + dp[next]);
  } else {
    dp[i] = dp[i + 1];
  }
}

console.log(dp[0]);
