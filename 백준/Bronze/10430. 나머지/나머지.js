const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [A, B, C] = stdin[0].split(' ').map(Number);

const ans = [
  (A + B) % C,
  ((A % C) + (B % C)) % C,
  (A * B) % C,
  ((A % C) * (B % C)) % C,
];

console.log(ans.join('\n'));
