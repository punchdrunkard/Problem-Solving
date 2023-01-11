const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [k, n] = stdin[0].split(" ").map((num) => parseInt(num));
const lengths = stdin.slice(1).map((num) => BigInt(num));

// 현재 길이를 가지고 n 개 이상의 전선을 만들 수 있는가?
const solve = (x) => {
  let cur = BigInt(0);
  for (let i = 0; i < k; i++) {
    cur += lengths[i] / x;
  }
  return cur >= n;
};

let [st, en] = [BigInt(1), BigInt(Number.MAX_VALUE)];

while (st < en) {
  mid = BigInt((st + en + BigInt(1)) / BigInt(2));
  if (solve(mid)) st = mid;
  else en = mid - BigInt(1);
}

console.log(st.toString());
