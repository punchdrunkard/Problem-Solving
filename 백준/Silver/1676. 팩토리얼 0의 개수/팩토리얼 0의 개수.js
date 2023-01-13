const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const factorization = (n) => {
  let current = n;
  let i = 2;
  const primes = [];
  let count5 = 0,
    count2 = 0;

  while (true) {
    if (i * i > n) break;

    if (current % i === 0) {
      current /= i;
      primes.push(i);
    } else {
      i++;
    }
  }

  if (current !== 1) primes.push(current);

  for (let prime of primes) {
    if (prime === 2) {
      count2 += 1;
    }

    if (prime === 5) {
      count5 += 1;
    }
  }

  return [count2, count5];
};

let n = parseInt(stdin[0]);

let count2 = 0;
let count5 = 0;

while (n > 0) {
  let temp = factorization(n);
  count2 += temp[0];
  count5 += temp[1];
  n--;
}

console.log(Math.min(count2, count5));
