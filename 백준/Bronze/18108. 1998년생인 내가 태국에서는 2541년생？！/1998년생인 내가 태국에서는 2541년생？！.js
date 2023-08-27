const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
console.log(parseInt(stdin[0]) - 543);
