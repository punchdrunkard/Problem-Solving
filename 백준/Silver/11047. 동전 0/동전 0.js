const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

let [n, k] = stdin[0].split(' ').map((num) => parseInt(num));
const coins = stdin.slice(1).map((coin) => parseInt(coin));

let ans = 0; // 동전의 개수

for (let i = n - 1; i >= 0; i--) {
  ans += Math.floor(k / coins[i]);
  k %= coins[i];
}

console.log(ans);
