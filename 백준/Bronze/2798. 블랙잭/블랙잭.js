const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [n, m] = stdin[0].split(' ').map(Number);
const cards = stdin[1].split(' ').map(Number);

let diff = Infinity;
let answer = Infinity;

const dfs = (count, sum, index) => {
  if (count === 3) {
    if (sum <= m && diff > Math.abs(m - sum)) {
      answer = sum;
      diff = Math.abs(m - sum);
    }
    return;
  }

  for (let i = index; i < n; i++) {
    sum += cards[i];
    dfs(count + 1, sum, i + 1);
    sum -= cards[i];
  }
};

const solve = () => {
  dfs(0, 0, 0);
  console.log(answer);
};

solve();
