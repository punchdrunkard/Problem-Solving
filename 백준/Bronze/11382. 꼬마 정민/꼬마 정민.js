const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [A, B, C] = stdin[0].split(' ').map(Number);
console.log(A + B + C);
