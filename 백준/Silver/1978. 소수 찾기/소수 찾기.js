const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const nums = stdin[1].split(" ").map((num) => parseInt(num));

const isPrime = (num) => {
  if (num === 1) return false;

  for (let i = 2; i * i <= num; i++) {
    if (num % i === 0) return false;
  }

  return true;
};

let count = 0;

nums.forEach((num) => {
  if (isPrime(num)) count += 1;
});

console.log(count);
