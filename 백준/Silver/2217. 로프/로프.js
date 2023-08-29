const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(stdin[0]);
const ropes = stdin
  .slice(1)
  .map(Number)
  .sort((a, b) => b - a);

let answer = 0;

for (let i = 1; i <= n; i++) {
  const currentWeight = ropes[i - 1] * i;
  answer = Math.max(answer, currentWeight);
}

console.log(answer);
