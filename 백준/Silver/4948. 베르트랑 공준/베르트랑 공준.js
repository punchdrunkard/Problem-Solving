const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const numbers = stdin.slice(0, stdin.length - 1).map((num) => parseInt(num));
const answer = [];

const solution = (n) => {
  const isPrime = Array.from({ length: 2 * n + 1 }, () => true);

  isPrime[1] = false;

  for (let i = 2; i * i <= 2 * n; i++) {
    if (!isPrime[i]) continue;

    for (let j = i * i; j <= 2 * n; j += i) {
      isPrime[j] = false;
    }
  }

  let count = 0;

  for (let i = n + 1; i <= 2 * n; i++) {
    if (isPrime[i]) count += 1;
  }

  return count;
};

numbers.forEach((number) => answer.push(solution(number)));
console.log(answer.join('\n'));
