const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const n = Number(stdin[0]);
const a = stdin[1].split(' ').map(Number);

const solve = () => {
  const row = Array.from({ length: n }, () => 0);

  for (let i = 0; i < n; i++) {
    let count = 0; 

    for (let j = 0; j < n; j++) {
      if (count === a[i] && row[j] === 0) {
        row[j] = i + 1;
        break;
      } else if (row[j] === 0) {
        count += 1;
      }
    }
  }

  return row;
};

console.log(solve().join(' '));

