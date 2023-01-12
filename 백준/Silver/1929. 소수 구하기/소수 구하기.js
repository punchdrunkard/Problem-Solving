const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [m, n] = stdin[0].split(" ").map((num) => parseInt(num));
const answer = [];

const solution = (m, n) => {
  const isPrime = Array.from({ length: n + 1 }, () => true);
  isPrime[1] = false;

  for (let i = 2; i * i <= n; i++) {
    if (!isPrime[[i]]) continue;

    for (let j = i * i; j <= n; j += i) {
      isPrime[j] = false;
    }
  }

  for (let i = m; i <= n; i++) {
    if (isPrime[i]) answer.push(i);
  }
};

solution(m, n);
console.log(answer.join("\n"));
