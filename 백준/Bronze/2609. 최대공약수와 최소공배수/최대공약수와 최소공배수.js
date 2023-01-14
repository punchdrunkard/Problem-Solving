const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [a, b] = stdin[0].split(" ").map((num) => parseInt(num));

const gcd = (a, b) => {
  return b === 0 ? a : gcd(b, a % b);
};

const lcm = (a, b, gcd) => {
  return (a / gcd) * b;
};

const gcdAB = gcd(a, b);
console.log([gcdAB, lcm(a, b, gcdAB)].join("\n"));
