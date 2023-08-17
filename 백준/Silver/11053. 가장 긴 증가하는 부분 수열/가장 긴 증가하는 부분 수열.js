const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);
const numbers = stdin[1].split(' ').map(Number);

// dp[i] : index i까지 가장 긴 증가하는 부분 수열의 길이, 단 i를 포함
const dp = Array.from({ length: n }, () => -1);

for (let i = 0; i < n; i++) {
  dp[i] = 1; // 초깃값
  for (let j = 0; j < i; j++) {
    // 이전의 값과 비교, 단 문제의 조건을 만족해야 함.
    if (numbers[j] < numbers[i]) {
      dp[i] = Math.max(dp[i], dp[j] + 1);
    }
  }
}

const answer = Math.max(...dp);
console.log(answer);
