const inputData = () => {
  const input = require("fs");
  const N = parseInt(input.readFileSync("/dev/stdin"));
  return N;
};

const factorial = (N) => {
  if (N === 0) return 1;
  else return N * factorial(N - 1);
};

const main = () => {
  const N = inputData();
  const answer = factorial(N);
  console.log(answer);
};

main();
