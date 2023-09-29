const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const answer = [];

stdin.slice(1).forEach((input, index) => {
  const [a, b] = input.split(' ').map(Number);
  answer.push(`Case #${index + 1}: ${a} + ${b} = ${a + b}`);
});

console.log(answer.join('\n'));
