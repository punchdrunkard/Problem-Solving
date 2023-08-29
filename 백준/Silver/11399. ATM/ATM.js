const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(stdin[0]);
const p = stdin[1]
  .split(' ')
  .map(Number)
  .sort((a, b) => a - b);

let sum = 0,
  answer = 0;

for (let i = 0; i < n; i++) {
  sum += p[i];
  answer += sum;
}

console.log(answer);
