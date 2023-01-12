const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n, k] = stdin[0].split(" ").map((num) => parseInt(num));

const solution = (n, k) => {
  const isPrime = Array.from({ length: n + 1 }, () => true);
  const erased = [];

  isPrime[1] = false;

  for (let i = 2; i <= n; i++) {
    if (!isPrime[[i]]) continue;

    erased.push(i); // 소수

    for (let j = i * i; j <= n; j += i) {
      if (!isPrime[j]) continue; // 이미 지워진 경우

      isPrime[j] = false;
      erased.push(j);
    }
  }

  return erased[k - 1];
};

console.log(solution(n, k));
