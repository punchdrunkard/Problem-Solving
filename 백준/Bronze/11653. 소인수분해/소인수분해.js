const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);

const solution = (n) => {
  let [current, i] = [n, 2];
  const factors = [];

  while (i * i <= n) {
    if (current % i === 0) {
      factors.push(i);
      current /= i;
    } else {
      i += 1;
    }
  }

  // 마지막으로 남은 수가 1이 아니면, 그 수 자체가 소수이다.
  if (current !== 1) factors.push(current);

  return factors;
};

if (n !== 1) console.log(solution(n).join("\n"));
