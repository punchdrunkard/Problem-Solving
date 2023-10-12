const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = parseInt(stdin[0]);

// 666 나올때 마다 카운트 하나씩 증가
let count = 0;
let number = 665;

while (count < n) {
  number++;

  if (number.toString().includes('666')) {
    count++;
  }
}

console.log(number);
