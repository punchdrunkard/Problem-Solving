const fs = require('fs');
const stdin = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

// 재배열 부등식
// 배열 a의 수와 b의 수를 재배열해서 짝지어 곱한 후 합을 구할 때,
// 큰 원소를 큰 원소와 매칭시켜주면 결과가 최대가 되고,
// 큰 원소를 작은 원소와 매칭시켜주면 결과가 최소가 된다.

const a = stdin[1]
  .split(' ')
  .map(Number)
  .sort((_a, _b) => _a - _b);
const b = stdin[2]
  .split(' ')
  .map(Number)
  .sort((_a, _b) => _b - _a);

const answer = a.reduce((acc, val, index) => acc + val * b[index], 0);
console.log(answer);
