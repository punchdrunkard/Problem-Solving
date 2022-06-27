const inputData = () => {
  const input = require("fs");
  const N = parseInt(input.readFileSync("/dev/stdin"));
  return N;
};

const fibonacci = (N) => {
  if (N <= 1) return N;
  else return fibonacci(N - 1) + fibonacci(N - 2);
};

const main = () => {
  const N = inputData();
  const answer = fibonacci(N);
  console.log(answer);
};

main();
