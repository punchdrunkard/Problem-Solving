const fs = require("fs");
const stdin = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();


const n = parseInt(stdin[0]);

// fibo[n] = n번째 피보나치 수
const fibo = Array.from({length : n + 1}, () => {return 0});

// base case
fibo[1] = 1;
fibo[2] = 1;

// bottom-up
for (let i = 3; i <= n; i++){
    fibo[i] = fibo[i - 2] + fibo[i -1];
}

console.log(fibo[n]);
