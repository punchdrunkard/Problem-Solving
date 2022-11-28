const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const [N, r, c] = stdin[0].split(" ").map((num) => parseInt(num));

const move = (r, c, n) => {
  if (n === 0) {
    return 0;
  }

  const half = 2 ** (n - 1);

  if (r < half && c < half) return move(r, c, n - 1);
  if (r < half && c >= half) return half * half + move(r, c - half, n - 1);
  if (r >= half && c < half) return 2 * half * half + move(r - half, c, n - 1);
  if (r >= half && c >= half)
    return 3 * half * half + move(r - half, c - half, n - 1);
};

console.log(move(r, c, N));
