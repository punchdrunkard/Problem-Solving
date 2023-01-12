const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const datas = stdin
  .slice(1)
  .map((row) => row.split(" ").map((num) => parseInt(num)));

// <x: y>는 M으로 나눈 나머지가 x, N으로 나눈 나머지가 y 임을 나타낸다.
// 따라서 <x: y>가 유효한 표현이려면 마지막 해 미만의 숫자 중,
// M으로 나눈 나머지가 x이면서 N으로 나눈 나머지가 y인 수가 존재하여야 하고,
// 이 수는 이 해가 몇 번 째 해를 나타내는가를 의미한다.

const gcd = (a, b) => {
  if (b === 0) return a;
  return gcd(b, a % b);
};

const lcm = (a, b) => {
  return (a / gcd(a, b)) * b;
};

answers = [];

datas.forEach((data) => {
  const [M, N, x, y] = data;
  answer = -1;

  const maxYear = lcm(M, N);

  // M으로 나눈 나머지가 x인 것들에 한정해서, 이에 대하여 N으로 나눈 나머지가 y인지를 확인한다.
  for (let i = x; i <= maxYear; i += M) {
    if (i % N === y % N) {
      answer = i;
      break;
    }
  }

  answers.push(answer);
});

console.log(answers.join("\n"));
