const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const testCases = stdin
  .slice(1)
  .map((row) => row.split(" ").map((num) => parseInt(num)));

const gcd = (a, b) => {
  if (b === 0) return a;
  else return gcd(b, a % b);
};

const answers = [];

testCases.forEach((testCase) => {
  const numbers = testCase.slice(1);
  let sum = 0;

  for (let i = 0; i < numbers.length; i++) {
    for (let j = i + 1; j < numbers.length; j++) {
      sum += gcd(numbers[i], numbers[j]);
    }
  }

  answers.push(sum);
});

console.log(answers.join("\n"));
