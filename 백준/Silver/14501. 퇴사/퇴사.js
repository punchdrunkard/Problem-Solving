const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

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

// dp[i] : i일 부터 얻을 수 있는 최대 이익
const dp = Array.from({ length: n + 1 }, () => 0);

for (let i = n; i >= 0; i--) {
  let next = i + t[i];

  // 해당 날짜에 일을 할 수 있는 경우
  if (next < n + 1) {
    // dp[i + 1] : i + 1 부터 얻을 수 있는 최대 이익
    // dp[next] + p[i] : i 날짜에 상담을 한 경우, 상담이 종료된 날짜부터 얻을 수 있는 이익
    dp[i] = Math.max(dp[i + 1], dp[next] + p[i]);
  } else {
    // 해당 날짜에 일을 할 수 없는 경우
    dp[i] = dp[i + 1] || 0;
  }
}

console.log(dp[0]);
